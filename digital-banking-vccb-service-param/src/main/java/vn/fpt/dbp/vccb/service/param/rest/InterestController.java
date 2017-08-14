package vn.fpt.dbp.vccb.service.param.rest;

import java.net.URL;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

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

import com.alsb.CoreACInterestRateSearchResponse.InterestRateList;
import com.alsb.CoreACInterestRateSearchResponse.InterestRateList.InterestRate;
import com.alsb.WebService;
import com.alsb.WebService_Service;
import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.interest.Interest;
import vn.fpt.dbp.vccb.core.domain.interest.InterestParameter;
import vn.fpt.dbp.vccb.core.domain.interest.QInterest;
import vn.fpt.dbp.vccb.core.domain.interest.repository.InterestRepository;
import vn.fpt.dbp.vccb.core.domain.product.ProductAccountClass;
import vn.fpt.dbp.vccb.core.domain.user.QUser;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.Permissions;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.dbp.vccb.service.param.service.InterestService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class InterestController {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";

	@Value("${spring.application.name}")
	private String serviceName;

	private boolean isPrimary() {
		return "digital-banking-vccb-service-param".equals(serviceName);
	}

	@Autowired
	protected InterestRepository interestRepository;

	@Autowired
	protected InterestService interestService;

	@Autowired
	private UserRepository userRepository;

	@Value("${dbp.osb-gateway-url}")
	private String OSBGateway;

	@Secured("INTEREST_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/list", method = RequestMethod.GET, produces = "application/json")
	public List<Interest> listInterests(Principal principle) {
		return interestRepository.findAll();
	}

	@Secured("INTEREST_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public Interest interestInfo(Principal principle, @PathVariable("id") String id) {
		Interest interest = new Interest();
		try {
			interest = interestService.detailInterest(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return interest;
	}

	@Secured("INTEREST_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Interest> searchInterest(Principal principle, @RequestBody Interest interest,
			Pageable pageable) {
		QInterest qInterest = QInterest.interest;
		BooleanExpression booleanExpression = qInterest.id.isNotNull();

		booleanExpression = interestService.buildCommonQuery(booleanExpression, interest);

		Page<Interest> result = interestRepository.findAll(booleanExpression, pageable);

		return new PagedResource<Interest>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());

	}

	@Secured("INTEREST_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/find", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Interest> findInterest(Principal principle, @RequestBody Interest interest,
			Pageable pageable) {
		String username = "";
		if (principle != null) {
			username = principle.getName();
		}
		User user = userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(username,
				Status.APPROVED);
		interest.setCreatedBy(user);

		PagedResource<Interest> result = interestRepository.searchLastedVersion(interest, pageable);

		return result;

	}

	@Secured("INTEREST_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/savedraft/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Interest> searchSaveDraftInterest(Principal principle, @RequestBody Interest interest,
			Pageable pageable) {
		QInterest qInterest = QInterest.interest;
		BooleanExpression booleanExpression = qInterest.workflowStatus.like(Status.SAVE_DRAFT);

		booleanExpression = interestService.buildCommonQuery(booleanExpression, interest);

		Page<Interest> result = interestRepository.findAll(booleanExpression, pageable);

		return new PagedResource<Interest>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}

	@Secured("INTEREST_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/sendforapprove/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Interest> searchWaitingInterest(Principal principle, @RequestBody Interest interest,
			Pageable pageable) {
		QInterest qInterest = QInterest.interest;
		BooleanExpression booleanExpression = qInterest.workflowStatus.like(Status.SEND_FOR_APPROVE);

		booleanExpression = interestService.buildCommonQuery(booleanExpression, interest);

		Page<Interest> result = interestRepository.findAll(booleanExpression, pageable);

		return new PagedResource<Interest>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}

	@Secured("INTEREST_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/assigned/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Interest> searchAssignedInterest(Principal principle, @RequestBody Interest interest,
			Pageable pageable) {
		QInterest qInterest = QInterest.interest;
		BooleanExpression booleanExpression = qInterest.workflowStatus.like(Status.ASSIGNED);

		booleanExpression = interestService.buildCommonQuery(booleanExpression, interest);

		Page<Interest> result = interestRepository.findAll(booleanExpression, pageable);

		return new PagedResource<Interest>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}

	@Secured("INTEREST_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/approved/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Interest> searchApprovedInterest(Principal principle, @RequestBody Interest interest,
			Pageable pageable) {
		QInterest qInterest = QInterest.interest;
		BooleanExpression booleanExpression = qInterest.workflowStatus.like(Status.APPROVED);

		booleanExpression = interestService.buildCommonQuery(booleanExpression, interest);

		Page<Interest> result = interestRepository.findAll(booleanExpression, pageable);

		return new PagedResource<Interest>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}

	@Secured("INTEREST_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/rejected/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Interest> searchRejectedInterest(Principal principle, @RequestBody Interest interest,
			Pageable pageable) {
		QInterest qInterest = QInterest.interest;
		BooleanExpression booleanExpression = qInterest.workflowStatus.like(Status.REJECTED);

		booleanExpression = interestService.buildCommonQuery(booleanExpression, interest);

		Page<Interest> result = interestRepository.findAll(booleanExpression, pageable);

		return new PagedResource<Interest>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}

	@Secured("INTEREST_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/savedraft/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Interest> createSaveDraftInterest(Principal principle, @RequestBody Interest request) {
		RestResponse<Interest> resp = new RestResponse<Interest>();
		try {
			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/interest/savedraft/create";
				resp = vn.fpt.dbp.vccb.core.rest.interest.InterestService.cud(restUrl, request);
			} else {
				User creater = getUser(principle);
				request.setCreatedBy(creater);
				Interest interest = interestService.saveAsDraft(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage(null);
				resp.setContent(interest);
			}
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/sendforapprove/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Interest> createSendForApproveInterest(Principal principle, @RequestBody Interest request) {
		RestResponse<Interest> resp = new RestResponse<Interest>();
		try {
			User creater = getUser(principle);
			request.setCreatedBy(creater);
			Interest interest = interestService.sendForApproved(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(interest);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/assigned/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Interest> createAssignedInterest(Principal principle, @RequestBody Interest request) {
		RestResponse<Interest> resp = new RestResponse<Interest>();
		try {
			User assignee = getUser(principle);
			request.setAssignee(assignee);
			Interest interest = interestService.assign(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(interest);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/assigned/return", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Interest> createReturnAssignedInterest(Principal principle, @RequestBody Interest request) {
		RestResponse<Interest> resp = new RestResponse<Interest>();
		try {
			Interest interest = interestService.returnInterest(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(interest);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/approved/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Interest> createApprovedInterest(Principal principle, @RequestBody Interest request) {
		RestResponse<Interest> resp = new RestResponse<Interest>();
		try {
			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/interest/approved/create";
				resp = vn.fpt.dbp.vccb.core.rest.interest.InterestService.cud(restUrl, request);
			} else {
				User approver = getUser(principle);
				request.setApprovedBy(approver);
				Interest interest = interestService.approvedInterest(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage(null);
				resp.setContent(interest);
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
	@RequestMapping(value = "/api/interest/approved/createinprimary", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Interest> createApprovedInterestInPrimary(Principal principle, @RequestBody Interest request) {
		RestResponse<Interest> resp = new RestResponse<Interest>();
		if (!isPrimary()) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage("This method cannot be called directly from history service");
			resp.setContent(null);
			return resp;
		}
		try {
			Interest interest = interestService.approveInPrimary(request);
			// TODO call OSB
			// updateCore(interest);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage(null);
			resp.setContent(interest);
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
	@RequestMapping(value = "/api/interest/rejected/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Interest> createRejectedInterest(Principal principle, @RequestBody Interest request) {
		RestResponse<Interest> resp = new RestResponse<Interest>();
		try {
			User approver = getUser(principle);
			request.setApprovedBy(approver);
			Interest interest = interestService.rejectedInterest(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(interest);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_DELETE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/savedraft/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Interest> deleteSaveDraftInterest(Principal principle, @RequestBody Interest request) {
		RestResponse<Interest> resp = new RestResponse<Interest>();
		try {

			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/param_his/api/interest/savedraft/delete";
				resp = vn.fpt.dbp.vccb.core.rest.interest.InterestService.cud(restUrl, request);
			} else {
				Interest interest = interestService.deleteSaveDraft(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage("");
				resp.setContent(new Interest(interest.getId()));
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
	@RequestMapping(value = "/api/interest/osb/param/get", method = RequestMethod.POST, produces = "application/json")
	public List<InterestParameter> getCoreParam(Principal principal, @RequestBody Interest request) {
		return getCoreParamList(request);
	}

	@Secured("INTEREST_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/workflowinfo/update", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Interest> updateWorkFlow(Principal principle, @RequestBody Interest request) {
		RestResponse<Interest> resp = new RestResponse<Interest>();
		try {
			Interest interest = interestService.updateWorkFlowInfo(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(interest);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("INTEREST_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/interest/getapprovallist", method = RequestMethod.GET, produces = "application/json")
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

	private void updateCore(Interest request) throws Exception {
		QName seviceName = new QName("http://www.alsb.com/", "WebService");
		URL wsdlURL = new URL(OSBGateway);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		WebService_Service wss = new WebService_Service(wsdlURL, seviceName);
		WebService ws = wss.getWebService();
		String resultCode = "";
		String pMSG = "";
		String pACLASS = "";
		String pCCY = "";
		String pEFFDATE = "";
		String pBRN = "";
		String pUDE = "";
		String pPRODUCTCODE = "";
		String pMAKERID = "";
		String pCHECKERID = "";
		try {
			pMSG = request.getReferenceCode();

			if (request.getProduct() != null && !(request.getProduct().getProductAccountClasses() == null)) {
				Set<ProductAccountClass> productAccountClass = request.getProduct().getProductAccountClasses();
				if (!productAccountClass.isEmpty() && productAccountClass.iterator().hasNext()) {
					pACLASS = productAccountClass.iterator().next().getCode();
				}
			}

			if (request.getCurrency() != null) {
				pCCY = request.getCurrency().getCode();
			}

			if (request.getEffectDate() != null) {
				pEFFDATE = simpleDateFormat.format(request.getEffectDate());
			}

			if (request.getBranch() != null) {
				pBRN = request.getBranch().getCode();
			}

			if (request.getCreatedBy() != null) {
				pMAKERID = request.getCreatedBy().getUsername();
			}

			if (request.getApprovedBy() != null) {
				pCHECKERID = request.getApprovedBy().getUsername();
			}

			// TODO
			// resultCode = ws.coreACInterestRateUpdate(sourceRef, accountClass,
			// ccycd, effectiveDate, brid, interestRateList, maker, approver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!"0".equals(resultCode) && !"00".equals(resultCode)) {
			throw new Exception("Error when calling OSB update.");
		}
	}

	private List<InterestParameter> getCoreParamList(Interest request) {
		List<InterestParameter> interestParameters = new ArrayList<InterestParameter>();
		try {
			QName seviceName = new QName("http://www.alsb.com/", "WebService");
			URL wsdlURL = new URL(OSBGateway);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

			String pACCLASS = "";
			String pCCY = "";
			String pEFFECTIVEDATE = "";
			String pBRANCH = "";
			String pMAKER = "";
			String pCHECKER = "";

			if (request.getBranch() != null) {
				pBRANCH = request.getBranch().getCode();
			}

			if (request.getProduct() != null && !(request.getProduct().getProductAccountClasses() == null)) {
				Set<ProductAccountClass> productAccountClass = request.getProduct().getProductAccountClasses();
				if (!productAccountClass.isEmpty() && productAccountClass.iterator().hasNext()) {
					pACCLASS = productAccountClass.iterator().next().getCode();
				}
			}

			if (request.getCurrency() != null) {
				pCCY = request.getCurrency().getCode();
			}

			if (request.getEffectDate() != null) {
				pEFFECTIVEDATE = simpleDateFormat.format(request.getEffectDate());
			}

			if (request.getCreatedBy() != null) {
				pMAKER = request.getCreatedBy().getUsername();
			}

			if (request.getApprovedBy() != null) {
				pCHECKER = request.getApprovedBy().getUsername();
			}

			WebService_Service wss = new WebService_Service(wsdlURL, seviceName);
			WebService ws = wss.getWebService();

			InterestRateList interestRateList = ws.coreACInterestRateSearch(pACCLASS, pCCY, pEFFECTIVEDATE, pBRANCH,
					pMAKER, pCHECKER);

			if (interestRateList != null) {
				for (InterestRate interestRate : interestRateList.getInterestRate()) {
					InterestParameter interestParameter = new InterestParameter();

					interestParameter.setCode(interestRate.getInterestRateCode());
					interestParameter.setParameter(interestRate.getParamCode());
					interestParameter.setValue(Double.parseDouble(interestRate.getInterestRate()));

					interestParameters.add(interestParameter);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return interestParameters;
	}
}
