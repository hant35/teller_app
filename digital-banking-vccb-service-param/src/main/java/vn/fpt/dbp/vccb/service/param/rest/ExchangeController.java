package vn.fpt.dbp.vccb.service.param.rest;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.currency.repository.CurrencyRepository;
import vn.fpt.dbp.vccb.core.domain.exchange.Exchange;
import vn.fpt.dbp.vccb.core.domain.exchange.ExchangeDetail;
import vn.fpt.dbp.vccb.core.domain.exchange.ExchangeType;
import vn.fpt.dbp.vccb.core.domain.exchange.QExchange;
import vn.fpt.dbp.vccb.core.domain.exchange.repository.ExchangeDetailRepository;
import vn.fpt.dbp.vccb.core.domain.exchange.repository.ExchangeRepository;
import vn.fpt.dbp.vccb.core.domain.exchange.repository.ExchangeTypeRepository;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;
import vn.fpt.dbp.vccb.core.domain.organization.repository.BranchRepository;
import vn.fpt.dbp.vccb.core.domain.user.QUser;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.Permissions;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.dbp.vccb.core.util.TransactionCodeGenerator;
import vn.fpt.dbp.vccb.service.param.service.ExchangeService;
import vn.fpt.dbp.vccb.service.param.util.UserUtil;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

import javax.xml.namespace.QName;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class ExchangeController {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";

	@Value("${spring.application.name}")
	private String serviceName;

	private boolean isPrimary() {
		return "digital-banking-vccb-service-param".equals(serviceName);
	}

	@Value("${dbp.osb-gateway-url}")
	private String OSBGateway;

	@Autowired
	protected ExchangeRepository exchangeRepository;
	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected ExchangeService exchangeService;

	@Autowired
	protected UserUtil userUtil;

	@Autowired
	protected ExchangeTypeRepository exchangeTypeRepository;

	@Autowired
	protected BranchRepository branchRepository;

	@Autowired
	protected CurrencyRepository currencyRepository;

	@Autowired
	protected ExchangeDetailRepository exchangeDetailRepository;

	public static final Logger logger = LoggerFactory.getLogger(ExchangeController.class);

	@Secured("EXCHANGE_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/list", method = RequestMethod.GET, produces = "application/json")
	public List<Exchange> list(Principal principle) {
		return exchangeRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public Exchange detail(Principal principle, @PathVariable("id") Long id) {
		Exchange exchange = exchangeRepository.findOne(id);

		return exchange;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/getapprovallist", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUserApprovals(Principal principle) {
		QUser quser = QUser.user;
		List<User> users = new ArrayList<User>();
		List<User> approvalList = new ArrayList<User>();

		String username = "";
		Long branchId;

		User user = userUtil.getUser(principle.getName());

		if (user != null) {
			users = (List<User>) userRepository.findAll(quser.branch.id.eq(user.getBranch().getId()));
		}

		for (User u : users) {
			System.out.println("--------checking user = " + u.getUsername());
			if (u.hasPermission("EXCHANGE", Permissions.APPROVE)) { // Trong
																	// table
																	// Function
																	// chưa có
																	// EXCHANGE
				approvalList.add(u);
			}
		}

		return approvalList;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Exchange> search(Principal principle, @RequestBody Exchange exchange, Pageable pageable) {
		QExchange qExchange = QExchange.exchange;
		BooleanExpression expr = qExchange.id.isNotNull();

		if (exchange.getArea() != null) {
			expr = expr.and(qExchange.area.id.eq(exchange.getArea().getId()));
		}

		if (exchange.getBranch() != null) {
			expr = expr.and(qExchange.branch.id.eq(exchange.getBranch().getId()));
		}

		if (exchange.getCurrencyFrom() != null) {
			expr = expr.and(qExchange.currencyFrom.id.eq(exchange.getCurrencyFrom().getId()));
		}

		if (exchange.getCurrencyTo() != null) {
			expr = expr.and(qExchange.currencyTo.id.eq(exchange.getCurrencyTo().getId()));
		}

		if (exchange.getWorkflowStatus() != null) {
			expr = expr.and(qExchange.workflowStatus.like(exchange.getWorkflowStatus()));
		}

		if (exchange.getFromDate() != null && exchange.getToDate() == null) {
			expr = expr.and(qExchange.exchangeDetails.any().effectedDate.goe(exchange.getFromDate()));
		}
		if (exchange.getFromDate() == null && exchange.getToDate() != null ) {
			expr = expr.and(qExchange.exchangeDetails.any().effectedDate.loe(exchange.getToDate()));
		}
		if (exchange.getFromDate() != null && exchange.getToDate() != null ) {
			expr = expr.and(qExchange.exchangeDetails.any().effectedDate.between(exchange.getFromDate(), exchange.getToDate()));
		}

		// --- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (exchange.getProcessInstanceId() != null) {
			expr = expr.and(qExchange.processInstanceId.eq(exchange.getProcessInstanceId()));
		}
		if (exchange.getTaskId() != null) {
			expr = expr.and(qExchange.taskId.eq(exchange.getTaskId()));
		}

		if (exchange.getExchangeDetails() != null && exchange.getExchangeDetails().size() > 0) {
			for (ExchangeDetail exchangeDetail : exchange.getExchangeDetails()) {
				if (exchangeDetail.getExchangeType() != null) {
					expr = expr.and(qExchange.exchangeDetails.any().exchangeType.eq(exchangeDetail.getExchangeType()));
				}
				break;
			}
		}

		Page<Exchange> result = exchangeRepository.findAll(expr, pageable);

		return new PagedResource<Exchange>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/search/exchangedetail", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Exchange> searchExchangeDetail(Principal principle, @RequestBody Exchange exchange, final Pageable pageable) throws Exception {
        NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
	    QExchange qExchange = QExchange.exchange;

		BooleanExpression expr = qExchange.id.isNotNull();

		if (exchange.getArea() != null) {
			expr = expr.and(qExchange.area.id.eq(exchange.getArea().getId()));
		}

		if (exchange.getBranch() != null) {
			expr = expr.and(qExchange.branch.id.eq(exchange.getBranch().getId()));
		}

		if (exchange.getCurrencyFrom() != null) {
			expr = expr.and(qExchange.currencyFrom.id.eq(exchange.getCurrencyFrom().getId()));
		}

		if (exchange.getCurrencyTo() != null) {
			expr = expr.and(qExchange.currencyTo.id.eq(exchange.getCurrencyTo().getId()));
		}

		if (exchange.getWorkflowStatus() != null) {
			expr = expr.and(qExchange.workflowStatus.like(exchange.getWorkflowStatus()));
		}

		if (exchange.getFromDate() != null && exchange.getToDate() == null) {
			expr = expr.and(qExchange.exchangeDetails.any().effectedDate.goe(exchange.getFromDate()));
		}
		if (exchange.getFromDate() == null && exchange.getToDate() != null ) {
			expr = expr.and(qExchange.exchangeDetails.any().effectedDate.loe(exchange.getToDate()));
		}
		if (exchange.getFromDate() != null && exchange.getToDate() != null ) {
			expr = expr.and(qExchange.exchangeDetails.any().effectedDate.between(exchange.getFromDate(), exchange.getToDate()));
		}

		// --- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (exchange.getProcessInstanceId() != null) {
			expr = expr.and(qExchange.processInstanceId.eq(exchange.getProcessInstanceId()));
		}
		if (exchange.getTaskId() != null) {
			expr = expr.and(qExchange.taskId.eq(exchange.getTaskId()));
		}

		if (exchange.getExchangeDetails() != null && exchange.getExchangeDetails().size() > 0) {
			for (ExchangeDetail exchangeDetail : exchange.getExchangeDetails()) {
				if (exchangeDetail.getExchangeType() != null) {
					expr = expr.and(qExchange.exchangeDetails.any().exchangeType.eq(exchangeDetail.getExchangeType()));
				}
				break;
			}
		}

		//Chỉ lấy exchange not null
		BooleanExpression exprSingle = expr;
		exprSingle = exprSingle.and(qExchange.exchangeUploadFile.isNull());
		List<Exchange> exchangeListSingle = new ArrayList<Exchange>();
		exchangeListSingle = (List<Exchange>) exchangeRepository.findAll(exprSingle);

		//Chỉ lấy exchange có exchangeUploadFile not null && workFlowStatus = 'APPROVED'
		BooleanExpression exprMulti = expr;
		exprMulti = exprMulti.and(qExchange.exchangeUploadFile.isNotNull());
		exprMulti = exprMulti.and(qExchange.workflowStatus.like(Status.APPROVED));
		List<Exchange> exchangeListMulti = new ArrayList<Exchange>();
		exchangeListMulti = (List<Exchange>) exchangeRepository.findAll(exprMulti);

		//Join 2 list
		List<Exchange> exchangeList = new ArrayList<Exchange>();
		exchangeList.addAll(exchangeListSingle);
		exchangeList.addAll(exchangeListMulti);


		List<Exchange> exchangeListResult = new ArrayList<Exchange>();


		for (Exchange itemExchange : exchangeList)
		{
			for( ExchangeDetail itemExchangeDetail : itemExchange.getExchangeDetails() )
			{
				Exchange itemExchangeResult = new Exchange();

				beanUtils.copyProperties(itemExchangeResult, itemExchange);
				List<ExchangeDetail> tempExchangeDetailList= new ArrayList<ExchangeDetail>();
				tempExchangeDetailList.add(itemExchangeDetail);
				itemExchangeResult.setExchangeDetails(new HashSet<ExchangeDetail>(tempExchangeDetailList));

				exchangeListResult.add(itemExchangeResult);
			}
		}

		int start = pageable.getOffset();
		int end = (start + pageable.getPageSize()) > exchangeListResult.size() ? exchangeListResult.size() : (start + pageable.getPageSize());
		int totalPages = (int) Math.ceil((double) exchangeListResult.size() / pageable.getPageSize());
		int totalItems = exchangeListResult.size();

		//Sort: order createdDate, direction descending
		Collections.sort(exchangeListResult, new Comparator<Exchange>() {
			public int compare(Exchange o1, Exchange o2) {
				return o2.getCreatedDate().compareTo( o1.getCreatedDate());
			}
		});

		try {
			PagedResource<Exchange> exchangePagedResource = new PagedResource<Exchange>(exchangeListResult.subList(start, end), pageable.getPageNumber(), pageable.getPageSize(), totalPages, totalItems);
			return exchangePagedResource;
		}
		catch( Exception e){
			e.printStackTrace();
			throw e;
//			return new PagedResource<ExchangeDetail>(new ArrayList<ExchangeDetail>() , pageable.getPageNumber(), pageable.getPageSize(),0,0);
		}

	}


	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/find", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Exchange> find(Principal principle, @RequestBody Exchange exchange, Pageable pageable) {
		exchange.setCreatedBy(userUtil.getUser(principle.getName()));
		try {
			return exchangeRepository.searchLastedVersion(exchange, pageable);
		} catch (Exception e) {
			return new PagedResource<Exchange>(new ArrayList<Exchange>(), pageable.getPageNumber(),pageable.getPageSize(),0);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchangedetail/find", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<ExchangeDetail> findExchangeDetail(Principal principle, @RequestBody Exchange exchange, Pageable pageable) {
		exchange.setCreatedBy(userUtil.getUser(principle.getName()));
		try {
			return exchangeDetailRepository.searchLastedVersionCust(exchange, pageable);
		} catch (Exception e) {
			logger.error("/api/exchangedetail/find" + e.getMessage());
			return new PagedResource<ExchangeDetail>(new ArrayList<ExchangeDetail>(), pageable.getPageNumber(),pageable.getPageSize(),0);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/osb/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Exchange> searchExchangeOSB(Principal principle, @RequestBody Exchange exchange, Pageable pageable) {

		String branchCode = null;
		String currencyFromCode = null;
		String currencyToCode = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Page<Exchange> resultPage = null;
		List<Exchange> exchangeList = new ArrayList<Exchange>();

        try {
            QName SERVICE_NAME = new QName("http://www.alsb.com/", "WebService");
            URL wsdlURL = new URL(OSBGateway);
            com.alsb.WebService_Service ss = new com.alsb.WebService_Service(wsdlURL, SERVICE_NAME);
            com.alsb.WebService port = ss.getWebService();

			if (exchange.getBranch() != null) {
				Branch branch = branchRepository.findOne(exchange.getBranch().getId());
				if(branch != null){
					branchCode = branch.getCode();
				}
			}
			if (exchange.getCurrencyFrom() != null) {
				vn.fpt.dbp.vccb.core.domain.currency.Currency currencyFrom = currencyRepository.findOne(exchange.getCurrencyFrom().getId());
				if(currencyFrom != null){
					currencyFromCode = currencyFrom.getCode();
				}
			}
			if (exchange.getCurrencyTo() != null) {
				vn.fpt.dbp.vccb.core.domain.currency.Currency currencyTo = currencyRepository.findOne(exchange.getCurrencyTo().getId());
				if(currencyTo != null){
					currencyToCode= currencyTo.getCode();
				}
			}

            com.alsb.CoreExchangeRateSearchResponse.ExchangeRateList exchangeRateListResponse =   port.coreExchangeRateSearch(branchCode, currencyFromCode, currencyToCode, "abc", "abc");

            for (com.alsb.CoreExchangeRateSearchResponse.ExchangeRateList.ExchangeRate item : exchangeRateListResponse.getExchangeRate() ) {
            	ExchangeDetail exchangeDetailTmp = new ExchangeDetail();
            	Set<ExchangeDetail> exchangeDetailSetTmp = new HashSet<ExchangeDetail>();
            	Exchange exchangeTmp = exchange;

				ExchangeType exchangeTypeTmp = null;
            	if(StringUtils.isNotEmpty(item.getExchangeRateTypeCode())) {
					exchangeTypeTmp = exchangeTypeRepository.findByCode(item.getExchangeRateTypeCode());
				}
				exchangeDetailTmp.setExchangeType(exchangeTypeTmp);
				exchangeDetailTmp.setMidRate(Double.parseDouble(item.getAverageRate()));
				exchangeDetailTmp.setBuyBand(Double.parseDouble(item.getBuyAmplitude()));
				exchangeDetailTmp.setSellBand(Double.parseDouble(item.getSellAmplitude()));
				exchangeDetailTmp.setBuyPrice(Double.parseDouble(item.getBuyRate()));
				exchangeDetailTmp.setSellPrice(Double.parseDouble(item.getSellRate()));
				exchangeDetailTmp.setEffectedDate(simpleDateFormat.parse(item.getEffectiveDate()));
				exchangeDetailTmp.setChangedNumber(Integer.parseInt(item.getNumOfChange()));
				exchangeDetailSetTmp.add(exchangeDetailTmp);
				exchange.setExchangeDetails(exchangeDetailSetTmp);

				exchangeList.add(exchange);
            }

            if (exchangeList == null || exchangeList.size() <= 0) {
                return new PagedResource<Exchange>(new ArrayList<Exchange>(), 0,0,0,0);
            } else {
                resultPage = new PageImpl<Exchange>(exchangeList, pageable, exchangeList.size());
            }

            return new PagedResource<Exchange>(resultPage.getContent(), resultPage.getNumber(), resultPage.getSize(),
                    resultPage.getTotalPages(), resultPage.getTotalElements());
        } catch (Exception e) {
			System.out.println("Error at service /api/exchange/osb/search : " + e.getMessage());
			return new PagedResource<Exchange>(new ArrayList<Exchange>(), 0,0,0,0);
        }
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/savedraft/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Exchange> saveAsDraft(Principal principle, @RequestBody Exchange request) {
		RestResponse<Exchange> resp = new RestResponse<Exchange>();
		try {
			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/exchange/savedraft/create";
				resp = vn.fpt.dbp.vccb.core.rest.exchange.ExchangeService.cud(restUrl, request);
			} else {
				request.setCreatedDate(new Date());
				request.setCreatedBy(userUtil.getUser(principle.getName()));

				Exchange exchange = exchangeService.saveAsDraft(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage(null);
				resp.setContent(exchange);
			}
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/sendforapprove/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Exchange> sendForApproved(Principal principle, @RequestBody Exchange request) {
		RestResponse<Exchange> resp = new RestResponse<Exchange>();
		try {
			request.setCreatedDate(new Date());
			request.setCreatedBy(userUtil.getUser(principle.getName()));
			request.setReferenceCode(TransactionCodeGenerator.generate());

			if(request.getAssignee() == null) {
				Exchange exchange = exchangeService.sendForApproved(request);

				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage("");
				resp.setContent(exchange);
			}
			//Có chỉ định người duyệt
			else {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/exchange/assigned/create";
				resp = vn.fpt.dbp.vccb.core.rest.exchange.ExchangeService.cud(restUrl, request);
			}
		}
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/assigned/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Exchange> assign(Principal principle, @RequestBody Exchange request) {
		RestResponse<Exchange> resp = new RestResponse<Exchange>();
		try {
			request.setAssignedDate(new Date());
			//Không chỉ định người duyệt
			if (request.getAssignee() == null){
				request.setAssignee(userUtil.getUser(principle.getName()));
			}
			Exchange exchange = exchangeService.assign(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(exchange);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/assigned/return", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Exchange> returnAssign(Principal principle, @RequestBody Exchange request) {
		RestResponse<Exchange> resp = new RestResponse<Exchange>();
		try {
			request.setAssignedDate(null);

			Exchange exchange = exchangeService.returnAssigned(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(exchange);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/approved/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Exchange> approve(Principal principle, @RequestBody Exchange request) {
		RestResponse<Exchange> resp = new RestResponse<Exchange>();
		try {
			System.out.print("---------Start to run service /api/exchange/approved/create");
			// 1. set approval date
			request.setApprovedDate(new Date());
			request.setApprovedBy(userUtil.getUser(principle.getName()));

			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/exchange/approved/create";
				System.out.print("----------With url = " + restUrl);

				resp = vn.fpt.dbp.vccb.core.rest.exchange.ExchangeService.cud(restUrl, request);
			} else {
				System.out.print("----------Goto Non-isPrimary condition");
				System.out.println("----------request.getApprovedBy()=" + request.getApprovedBy());

				Exchange exchange = exchangeService.approve(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage(null);
				resp.setContent(exchange);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@RequestMapping(value = "/api/exchange/approved/createinprimary", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Exchange> approveInPrimary(Principal principle, @RequestBody Exchange request) {
		RestResponse<Exchange> resp = new RestResponse<Exchange>();
		if (!isPrimary()) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage("This method cannot be called directly from history service");
			resp.setContent(null);
			return resp;
		}
		try {
//			System.out.println("Run service /api/exchange/approved/createinprimary");
			Exchange exchange = exchangeService.approveInPrimary(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage(null);
			resp.setContent(exchange);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/rejected/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Exchange> reject(Principal principle, @RequestBody Exchange request) {
		RestResponse<Exchange> resp = new RestResponse<Exchange>();
		try {
			request.setApprovedDate(new Date());
			request.setApprovedBy(userUtil.getUser(principle.getName()));

			Exchange exchange = exchangeService.reject(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(exchange);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/savedraft/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Exchange> delete(Principal principle, @RequestBody Exchange request) {
		RestResponse<Exchange> resp = new RestResponse<Exchange>();
		try {
			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/limit/savedraft/delete";
				resp = vn.fpt.dbp.vccb.core.rest.exchange.ExchangeService.cud(restUrl, request);
			} else {
				Exchange exchange = exchangeService.deleteSaveDraft(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage("");
				resp.setContent(exchange);
			}

		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/upload/create", method = RequestMethod.POST, produces = "application/json")
	public Integer upload(Principal principle, @RequestParam("file") MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get("UPLOADED_FOLDER" + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/exchange/sendbatchforapprove/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Set<Exchange>> commitBatch(Principal principle, @RequestBody Set<Exchange> requestList) {
		RestResponse<Set<Exchange>> resp = new RestResponse<Set<Exchange>>();
		Set<Exchange> restList = new HashSet<Exchange>();
		try {
			for (Exchange exchange : requestList) {
				exchange.setReferenceCode(TransactionCodeGenerator.generate());
				Exchange ints = exchangeService.sendForApproved(exchange);
				restList.add(ints);
			}
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(restList);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

}
