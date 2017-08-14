package vn.fpt.dbp.vccb.service.param.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.interest.InterestUploadFile;
import vn.fpt.dbp.vccb.core.domain.interest.QInterestUploadFile;
import vn.fpt.dbp.vccb.core.domain.interest.repository.InterestUploadFileRepository;
import vn.fpt.dbp.vccb.core.domain.user.QUser;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.Permissions;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.dbp.vccb.service.param.service.InterestUploadFileService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class InterestUploadFileController {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";

	@Value("${spring.application.name}")
	private String serviceName;

	private boolean isPrimary() {
		return "digital-banking-vccb-service-param".equals(serviceName);
	}

	@Autowired
	protected InterestUploadFileService interestUploadFileService;

	@Autowired
	protected InterestUploadFileRepository interestUploadFileRepository;

	@Autowired
	private UserRepository userRepository;

	@Secured("INTEREST_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interestuploadfile/list", method = RequestMethod.GET, produces = "application/json")
	public List<InterestUploadFile> listInterestUploadFile(Principal principle) {
		return interestUploadFileRepository.findAll();
	}

	@Secured("INTEREST_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interestuploadfile/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<InterestUploadFile> searchInterestUploadFile(Principal principle,
			@RequestBody InterestUploadFile interestUploadFile, Pageable pageable) {
		QInterestUploadFile qInterestUploadFile = QInterestUploadFile.interestUploadFile;
		BooleanExpression booleanExpression = qInterestUploadFile.id.isNotNull();
		// Ten file
		if (StringUtils.isNotEmpty(interestUploadFile.getFileName())) {
			booleanExpression = booleanExpression
					.and(qInterestUploadFile.fileName.like(interestUploadFile.getFileName()));
		}
		// Ngay nhap
		if (interestUploadFile.getCreatedDate() != null) {
			// Set to YYYY-MM-DD 00:00:00
			Date dateFrom = DateUtils.ceiling(interestUploadFile.getCreatedDate(), Calendar.DATE);
			// Set to YYYY-MM-(DD+1) 00:00:00
			Date dateTo = DateUtils.addDays(dateFrom, 1);
			// YYYY-MM-DD 00:00:00 <= createdDate < YYYY-MM-(DD+1) 00:00:00
			booleanExpression = booleanExpression.and(qInterestUploadFile.createdDate.goe(dateFrom))
					.and(qInterestUploadFile.createdDate.lt(dateTo));
		}
		// Nguoi nhap
		if (interestUploadFile.getCreatedBy() != null) {
			booleanExpression = booleanExpression
					.and(qInterestUploadFile.createdBy.eq(interestUploadFile.getCreatedBy()));
		}
		// Trang thai
		if (StringUtils.isNotEmpty(interestUploadFile.getWorkflowStatus())) {
			booleanExpression = booleanExpression
					.and(qInterestUploadFile.workflowStatus.like(interestUploadFile.getWorkflowStatus()));
		}

		if (interestUploadFile.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression
					.and(qInterestUploadFile.processInstanceId.eq(interestUploadFile.getProcessInstanceId()));
		}

		if (interestUploadFile.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qInterestUploadFile.taskId.eq(interestUploadFile.getTaskId()));
		}

		Page<InterestUploadFile> result = interestUploadFileRepository.findAll(booleanExpression, pageable);

		return new PagedResource<InterestUploadFile>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());

	}

	@Secured("INTEREST_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interestuploadfile/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public InterestUploadFile interestUploadFileInfo(Principal principle, @PathVariable("id") String id) {
		InterestUploadFile interestUploadFile;
		try {
			interestUploadFile = new InterestUploadFile(id);
			interestUploadFile = interestUploadFileRepository.findOne(interestUploadFile.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return interestUploadFile;
	}

	@Secured("INTEREST_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interestuploadfile/savedraft/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<InterestUploadFile> createSaveDraftInterestUploadFile(Principal principle,
			@RequestBody InterestUploadFile request) {
		RestResponse<InterestUploadFile> resp = new RestResponse<InterestUploadFile>();
		try {
			User creater = getUser(principle);
			request.setCreatedBy(creater);
			InterestUploadFile interestUploadFile = interestUploadFileService.saveAsDraft(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(interestUploadFile);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_DELETE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interestuploadfile/savedraft/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<InterestUploadFile> deleteSaveDraftInterestUploadFile(Principal principle,
			@RequestBody InterestUploadFile request) {
		RestResponse<InterestUploadFile> resp = new RestResponse<InterestUploadFile>();
		try {

			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/interestuploadfile/savedraft/delete";
				resp = vn.fpt.dbp.vccb.core.rest.interest.InterestUploadFileService.cud(restUrl, request);
			} else {
				InterestUploadFile interestUploadFile = interestUploadFileService.deleteSaveDraft(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage("");
				resp.setContent(new InterestUploadFile(interestUploadFile.getId()));
			}

		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interestuploadfile/sendforapprove/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<InterestUploadFile> createSendForApproveInterestUploadFile(Principal principle,
			@RequestBody InterestUploadFile request) {
		RestResponse<InterestUploadFile> resp = new RestResponse<InterestUploadFile>();
		try {
			User creater = getUser(principle);
			request.setCreatedBy(creater);
			InterestUploadFile interestUploadFile = interestUploadFileService.sendForApproved(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(interestUploadFile);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interestuploadfile/assigned/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<InterestUploadFile> createAssignedInterestUploadFile(Principal principle,
			@RequestBody InterestUploadFile request) {
		RestResponse<InterestUploadFile> resp = new RestResponse<InterestUploadFile>();
		try {
			User assignee = getUser(principle);
			request.setAssignee(assignee);
			InterestUploadFile interestUploadFile = interestUploadFileService.assign(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(interestUploadFile);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interestuploadfile/assigned/return", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<InterestUploadFile> createReturnAssignedInterestUploadFile(Principal principle,
			@RequestBody InterestUploadFile request) {
		RestResponse<InterestUploadFile> resp = new RestResponse<InterestUploadFile>();
		try {
			InterestUploadFile interestUploadFile = interestUploadFileService.returnInterestUploadFile(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(interestUploadFile);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interestuploadfile/approved/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<InterestUploadFile> createApprovedInterestUploadFile(Principal principle,
			@RequestBody InterestUploadFile request) {
		RestResponse<InterestUploadFile> resp = new RestResponse<InterestUploadFile>();
		try {
			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/interestuploadfile/approved/create";
				resp = vn.fpt.dbp.vccb.core.rest.interest.InterestUploadFileService.cud(restUrl, request);
			} else {
				User approver = getUser(principle);
				request.setApprovedBy(approver);
				InterestUploadFile interestUploadFile = interestUploadFileService.approvedInterestUploadFile(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage(null);
				resp.setContent(interestUploadFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interestuploadfile/approved/createinprimary", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<InterestUploadFile> createApprovedInterestInPrimary(Principal principle,
			@RequestBody InterestUploadFile request) {
		RestResponse<InterestUploadFile> resp = new RestResponse<InterestUploadFile>();
		if (!isPrimary()) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage("This method cannot be called directly from history service");
			resp.setContent(null);
			return resp;
		}
		try {
			InterestUploadFile interestUploadFile = interestUploadFileService.approveInPrimary(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage(null);
			resp.setContent(interestUploadFile);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interestuploadfile/rejected/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<InterestUploadFile> createRejectedInterestUploadFile(Principal principle,
			@RequestBody InterestUploadFile request) {
		RestResponse<InterestUploadFile> resp = new RestResponse<InterestUploadFile>();
		try {
			User approver = getUser(principle);
			request.setApprovedBy(approver);
			InterestUploadFile interestUploadFile = interestUploadFileService.rejectedInterestUploadFile(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(interestUploadFile);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interestuploadfile/getapprovallist", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUserApprovals(Principal principle) {
		QUser quser = QUser.user;
		List<User> users = new ArrayList<User>();
		List<User> approvalList = new ArrayList<User>();
		String username = "";
		Long branchId;
		if (principle != null) {
			username = principle.getName();
		}

		User user = userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(username,
				Status.APPROVED);

		if (user != null) {
			branchId = user.getCurrentBranch() == null ? user.getBranch().getId() : user.getCurrentBranch().getId();
			users = (List<User>) userRepository.findAll(quser.branch.id.eq(branchId));
		}

		for (User u : users) {
			if (u.hasPermission("INTEREST", Permissions.APPROVE)) {
				approvalList.add(u);
			}
		}

		return approvalList;
	}

	@Secured("INTEREST_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interestuploadfile/getcreaterlist", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUserCreaters(Principal principle) {
		List<User> users = new ArrayList<User>();
		// List<User> createrList = new ArrayList<User>();

		users = (List<User>) userRepository.findAll();

		// for (User u : users) {
		// if (u.hasPermission("INTEREST", Permissions.ADD)) {
		// createrList.add(u);
		// }
		// }

		return users;
	}

	protected User getUser(Principal principal) throws Exception {
		String username = "";
		if (principal != null) {
			username = principal.getName();
		}

		User user = userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(username,
				Status.APPROVED);
		if (user == null) {
			throw new Exception("Error at get User from DB: Username not exits. username=" + username);
		}

		return user;
	}
}
