package vn.fpt.dbp.vccb.service.param.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.exchange.ExchangeUploadFile;
import vn.fpt.dbp.vccb.core.domain.exchange.QExchangeUploadFile;
import vn.fpt.dbp.vccb.core.domain.exchange.repository.ExchangeUploadFileRepository;
import vn.fpt.dbp.vccb.core.domain.user.QUser;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.Permissions;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.dbp.vccb.core.util.TransactionCodeGenerator;
import vn.fpt.dbp.vccb.service.param.service.ExchangeUploadFileService;
import vn.fpt.dbp.vccb.service.param.util.UserUtil;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class ExchangeUploadFileController {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";

	@Value("${spring.application.name}")
	private String serviceName;

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected ExchangeUploadFileRepository exchangeUploadFileRepository;

	@Autowired
	protected ExchangeUploadFileService exchangeUploadFileService;

	@Autowired
	protected UserUtil userUtil;

	private boolean isPrimary() {
		return "digital-banking-vccb-service-param".equals(serviceName);
	}

	private static final Logger logger = LoggerFactory.getLogger(ExchangeUploadFileController.class);

	@Secured("EXCHANGE_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchangeuploadfile/list", method = RequestMethod.GET, produces = "application/json")
	public List<ExchangeUploadFile> list(Principal principle) {
		return exchangeUploadFileRepository.findAll();
	}

	@Secured("EXCHANGE_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchangeuploadfile/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public ExchangeUploadFile detail(Principal principle, @PathVariable("id") Long id) {
		ExchangeUploadFile exchangeUploadFile = exchangeUploadFileRepository.findOne(id);

		return exchangeUploadFile;
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchangeuploadfile/getapprovallist", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUserApprovals(Principal principle) {
		QUser quser = QUser.user;
		List<User> users = new ArrayList<User>();
		List<User> approvalList = new ArrayList<User>();

		String username = "";
		Long branchId;
		if (principle != null){
			username = principle.getName();
		}
//		System.out.println("***** Testing Principal***** ");
//		System.out.println("***** principle.getName() = " + principle.getName());

		User user = userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(username, Status.APPROVED);

		if (user != null){
			users = (List<User>) userRepository.findAll(quser.branch.id.eq(user.getBranch().getId()));
		}

		for(User u : users){
//			System.out.println("--------checking user = " + u.getUsername());
			if(u.hasPermission("EXCHANGE", Permissions.APPROVE)){ //Trong table Function chưa có EXCHANGE
				approvalList.add(u);
			}
		}

		return approvalList;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchangeuploadfile/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<ExchangeUploadFile> search(Principal principle, @RequestBody ExchangeUploadFile exchangeUploadFile, Pageable pageable){
		QExchangeUploadFile qExchangeUploadFile = QExchangeUploadFile.exchangeUploadFile;
		BooleanExpression booleanExpression = qExchangeUploadFile.id.isNotNull();
		// Ten file
		if (StringUtils.isNotEmpty(exchangeUploadFile.getFileName())) {
			booleanExpression = booleanExpression
					.and(qExchangeUploadFile.fileName.toUpperCase().like(exchangeUploadFile.getFileName().toUpperCase()));
		}
		// Ngay nhap
		if (exchangeUploadFile.getUploadDate() != null) {
			booleanExpression = booleanExpression
					.and(qExchangeUploadFile.uploadDate.between(DateUtils.truncate(exchangeUploadFile.getUploadDate(), Calendar.DATE),
							DateUtils.addMilliseconds(DateUtils.ceiling(exchangeUploadFile.getUploadDate(), Calendar.DATE), -1)));
		}
		// Nguoi nhap
		if (exchangeUploadFile.getCreatedBy() != null) {
			booleanExpression = booleanExpression
					.and(qExchangeUploadFile.createdBy.eq(exchangeUploadFile.getCreatedBy()));
		}
		// Trang thai
		if (StringUtils.isNotEmpty(exchangeUploadFile.getWorkflowStatus())) {
			booleanExpression = booleanExpression
					.and(qExchangeUploadFile.workflowStatus.like(exchangeUploadFile.getWorkflowStatus()));
		}
		// --- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (exchangeUploadFile.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qExchangeUploadFile.processInstanceId.eq(exchangeUploadFile.getProcessInstanceId()));
		}
		if (exchangeUploadFile.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qExchangeUploadFile.taskId.eq(exchangeUploadFile.getTaskId()));
		}

		Page<ExchangeUploadFile> result = exchangeUploadFileRepository.findAll(booleanExpression, pageable);

		return new PagedResource<ExchangeUploadFile>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchangeuploadfile/savedraft/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<ExchangeUploadFile> saveAsDraft(Principal principle, @RequestBody ExchangeUploadFile request) {
		RestResponse<ExchangeUploadFile> resp = new RestResponse<ExchangeUploadFile>();
		try {
			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/exchangeuploadfile/savedraft/create";
				resp = vn.fpt.dbp.vccb.core.rest.exchange.ExchangeUploadFileService.cud(restUrl, request);
			}
			else {
				request.setCreatedDate(new Date());

				ExchangeUploadFile exchangeuploadfile = exchangeUploadFileService.saveAsDraft(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage(null);
				resp.setContent(exchangeuploadfile);
			}
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchangeuploadfile/sendforapprove/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<ExchangeUploadFile> sendForApproved(Principal principle, @RequestBody ExchangeUploadFile request) {
		RestResponse<ExchangeUploadFile> resp = new RestResponse<ExchangeUploadFile>();
		try {
			request.setCreatedDate(new Date());
			request.setCreatedBy(userUtil.getUser(principle.getName()));
			request.setReferenceCode(TransactionCodeGenerator.generate());
			request.setUploadDate(new Date());

			if(request.getAssignee() == null) {
				ExchangeUploadFile exchangeuploadfile = exchangeUploadFileService.sendForApproved(request);

				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage("");
				resp.setContent(exchangeuploadfile);
			}
			//Có chỉ định người duyệt
			else {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/exchangeuploadfile/assigned/create";
				resp = vn.fpt.dbp.vccb.core.rest.exchange.ExchangeUploadFileService.cud(restUrl, request);
			}

		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchangeuploadfile/assigned/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<ExchangeUploadFile> assign(Principal principle, @RequestBody ExchangeUploadFile request) {
		RestResponse<ExchangeUploadFile> resp = new RestResponse<ExchangeUploadFile>();
		try {
			request.setAssignedDate(new Date());
			//Không chỉ định người duyệt
			if (request.getAssignee() == null) {
				request.setAssignee(userUtil.getUser(principle.getName()));
			}

			ExchangeUploadFile exchangeuploadfile = exchangeUploadFileService.assign(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(exchangeuploadfile);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchangeuploadfile/assigned/return", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<ExchangeUploadFile> returnAssign(Principal principle, @RequestBody ExchangeUploadFile request) {
		RestResponse<ExchangeUploadFile> resp = new RestResponse<ExchangeUploadFile>();
		try {
			request.setAssignedDate(null);

			ExchangeUploadFile exchangeuploadfile = exchangeUploadFileService.returnAssigned(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(exchangeuploadfile);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchangeuploadfile/approved/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<ExchangeUploadFile> approve(Principal principle, @RequestBody ExchangeUploadFile request) {
		RestResponse<ExchangeUploadFile> resp = new RestResponse<ExchangeUploadFile>();
		try {
//			System.out.print("---------Start to run service /api/exchangeuploadfile/approved/create");
			//1. set approval date
			request.setApprovedDate(new Date());
			request.setApprovedBy(userUtil.getUser(principle.getName()));

			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/exchangeuploadfile/approved/create";
//				System.out.print("----------With url = " + restUrl);

				resp = vn.fpt.dbp.vccb.core.rest.exchange.ExchangeUploadFileService.cud(restUrl, request);
			} else {
//				System.out.print("----------Goto Non-isPrimary condition");
//				System.out.println("----------request.getApprovedBy()=" + request.getApprovedBy());

				ExchangeUploadFile exchangeuploadfile = exchangeUploadFileService.approve(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage(null);
				resp.setContent(exchangeuploadfile);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@RequestMapping(value = "/api/exchangeuploadfile/approved/createinprimary", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<ExchangeUploadFile> approveInPrimary(Principal principle, @RequestBody ExchangeUploadFile request) {
		RestResponse<ExchangeUploadFile> resp = new RestResponse<ExchangeUploadFile>();
		if (!isPrimary()) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage("This method cannot be called directly from history service");
			resp.setContent(null);
			return resp;
		}
		try {
			logger.info("Run service /api/exchangeuploadfile/approved/createinprimary");
			ExchangeUploadFile exchangeuploadfile = exchangeUploadFileService.approveInPrimary(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage(null);
			resp.setContent(exchangeuploadfile);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchangeuploadfile/rejected/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<ExchangeUploadFile> reject(Principal principle, @RequestBody ExchangeUploadFile request) {
		RestResponse<ExchangeUploadFile> resp = new RestResponse<ExchangeUploadFile>();
		try {
			request.setAssignedDate(new Date());
			request.setApprovedBy(userUtil.getUser(principle.getName()));

			ExchangeUploadFile exchangeuploadfile = exchangeUploadFileService.reject(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(exchangeuploadfile);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchangeuploadfile/savedraft/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<ExchangeUploadFile> delete(Principal principle, @RequestBody ExchangeUploadFile request) {
		RestResponse<ExchangeUploadFile> resp = new RestResponse<ExchangeUploadFile>();
		try {
			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/exchangeuploadfile/savedraft/delete";
				resp = vn.fpt.dbp.vccb.core.rest.exchange.ExchangeUploadFileService.cud(restUrl, request);
			} else {
				ExchangeUploadFile exchangeuploadfile = exchangeUploadFileService.deleteSaveDraft(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage("");
				resp.setContent(exchangeuploadfile);
			}

		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}


}
