package vn.fpt.dbp.vccb.service.admin.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.limit.Limit;
import vn.fpt.dbp.vccb.core.domain.limit.QLimit;
import vn.fpt.dbp.vccb.core.domain.limit.repository.LimitRepository;
import vn.fpt.dbp.vccb.core.domain.user.QUser;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.Permissions;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.dbp.vccb.core.util.TransactionCodeGenerator;
import vn.fpt.dbp.vccb.service.admin.service.LimitService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;


@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class LimitController {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";
	
	@Value("${spring.application.name}")
	private String serviceName;

	private boolean isPrimary() {
		return "digital-banking-vccb-service-admin".equals(serviceName);
	}
	
	//@Autowired
	//private EventBus eventBus;

	@Autowired
	private LimitRepository limitRepository;
	
	@Autowired
	private LimitService limitService;
	
	@Autowired
	private UserRepository userRepository;

	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/list", method = RequestMethod.GET, produces = "application/json")
	public List<Limit> listLimits(Principal principle) {
		return limitRepository.findAll();
	}
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public Limit limitInfo(Principal principle, @PathVariable("id") Long id) {
		Limit limit = limitRepository.findOne(id);
		return limit;
	}
	
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/getapprovallist", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUserApprovals(Principal principle) {
		QUser quser = QUser.user;
		List<User> users = new ArrayList<User>();
		List<User> approvalList = new ArrayList<User>();
		String username = "";
		Long branchId;
		if (principle != null){
			username = principle.getName();
		}
		//System.out.println("***** Testing Principal***** ");
		//System.out.println("***** principle.getName() = " + principle.getName());
				
		User user = userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(username, Status.APPROVED);

		if (user != null){
			branchId = user.getCurrentBranch()== null?user.getBranch().getId():user.getCurrentBranch().getId();
		    users = (List<User>) userRepository.findAll(quser.branch.id.eq(branchId));
		}
		
		for(User u : users){
			System.out.println("--------checking user = " + u.getUsername());
			if(u.hasPermission("LIMIT",Permissions.APPROVE)){
				approvalList.add(u);
			}
		}
		
		return approvalList;
	}
	
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/code/{code}", method = RequestMethod.GET, produces = "application/json")
	public List<Limit> limitInfoByCode(Principal principle, @PathVariable("code") String code) {
		QLimit qLimit = QLimit.limit;
		
		List<Limit> limit = (List<Limit>) limitRepository.findAll(qLimit.code.upper().eq(code.toUpperCase()));
		
		return limit;
	}
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/checkexisted/code/{code}", method = RequestMethod.GET, produces = "application/json")
	public List<Limit> checkExistedLimitByCode(Principal principle, @PathVariable("code") String code) {
		List<Limit> limits = new ArrayList<Limit>();
		String restUrl;
		try{
			if(isPrimary()){
				restUrl = apiGatewayUrl + "/tellerapp/admin_his/api/limit/checkexisted/code/" + code;
				limits = vn.fpt.dbp.vccb.core.rest.limit.LimitService.findList(restUrl);
			}else{
				QLimit qLimit = QLimit.limit;
				//1. In History, find Limit by Code except workFlowStatus = SAVE_DRAFT, REJECTED, APPROVED
				BooleanExpression booleanExpression = qLimit.code.equalsIgnoreCase(code);
				booleanExpression = booleanExpression.and(qLimit.workflowStatus.notEqualsIgnoreCase(Status.SAVE_DRAFT));
				booleanExpression = booleanExpression.and(qLimit.workflowStatus.notEqualsIgnoreCase(Status.REJECTED));
				booleanExpression = booleanExpression.and(qLimit.workflowStatus.notEqualsIgnoreCase(Status.APPROVED));
				List<Limit> limitHis = (List<Limit>) limitRepository.findAll(booleanExpression);
				//System.out.println("**** In History ****");
				//System.out.println("----- limitHis.size() =" + limitHis.size());
				//2. In Primary, find Limit by code
				restUrl = apiGatewayUrl + "/tellerapp/admin/api/limit/code/" + code;
				List<Limit> limitPri = vn.fpt.dbp.vccb.core.rest.limit.LimitService.findList(restUrl);
				//System.out.println("**** In Primary ****");
				//System.out.println("----- limitPri.size() =" + limitPri.size());
				//3. Add result of 1 & 2 into a list
				if(limitHis != null){
					limits.addAll(limitHis);

				}
				if(limitPri != null){
					limits.addAll(limitPri);
				}
			}
			return limits;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/name/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Limit> limitInfoByName(Principal principle, @PathVariable("name") String name) {
		List<Limit> limit = limitRepository.findByName(name);
		return limit;
	}
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/checkexisted/name/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Limit> checkExistedLimitByName(Principal principle, @PathVariable("name") String name) {
		List<Limit> limits = new ArrayList<Limit>();
		String restUrl;
		try{
			if(isPrimary()){
				restUrl = apiGatewayUrl + "/tellerapp/admin_his/api/limit/checkexisted/name/" + name;
				limits = vn.fpt.dbp.vccb.core.rest.limit.LimitService.findList(restUrl);
			}else{
				//1. In History, find Limit by Name except workFlowStatus = SAVE_DRAFT, REJECTED, APPROVED
				QLimit qLimit = QLimit.limit;
				BooleanExpression booleanExpression = qLimit.name.equalsIgnoreCase(name);
				booleanExpression = booleanExpression.and(qLimit.workflowStatus.notEqualsIgnoreCase(Status.SAVE_DRAFT));
				booleanExpression = booleanExpression.and(qLimit.workflowStatus.notEqualsIgnoreCase(Status.REJECTED));
				booleanExpression = booleanExpression.and(qLimit.workflowStatus.notEqualsIgnoreCase(Status.APPROVED));
				
				List<Limit> limitHis = (List<Limit>) limitRepository.findAll(booleanExpression);
				
				//2. In Primary, find Limit by Name
				restUrl = apiGatewayUrl + "/tellerapp/admin/api/limit/name/" + name;
				List<Limit> limitPri = vn.fpt.dbp.vccb.core.rest.limit.LimitService.findList(restUrl);
				//3. Add result of 1 & 2 into a list
				if(limitHis != null){
					limits.addAll(limitHis);

				}
				if(limitPri != null){
					limits.addAll(limitPri);

				}
				
			}	
			return limits;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Limit> searchLimit(Principal principle, @RequestBody Limit limit, Pageable pageable) {
		QLimit qLimit = QLimit.limit;
		BooleanExpression booleanExpression = qLimit.name.like("%");
		if (StringUtils.isNotEmpty(limit.getCode())) {
			booleanExpression = booleanExpression.and(qLimit.code.upper().like(limit.getCode().toUpperCase()));// support to search value %a
		}
		if (StringUtils.isNotEmpty(limit.getName())) {
			booleanExpression = booleanExpression.and(qLimit.name.upper().like(limit.getName().toUpperCase()));
		}
		if(StringUtils.isNotEmpty(limit.getStatus())){
			booleanExpression = booleanExpression.and(qLimit.status.upper().like(limit.getStatus().toUpperCase()));
		}
		
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (limit.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qLimit.processInstanceId.eq(limit.getProcessInstanceId()));
		}
		if (limit.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qLimit.taskId.eq(limit.getTaskId()));
		}
		
		Page<Limit> result = limitRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Limit>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		
	}
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/find", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Limit> findLimit(Principal principle, @RequestBody Limit limit, Pageable pageable){
		
		User user = userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(principle.getName(), Status.APPROVED);
		
		if(user!=null){
			limit.setCreatedBy(user);
		}
		
		return limitRepository.searchLastedVersion(limit, pageable);
	}
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/history", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Limit> findLimitHistory(Principal principle, @RequestBody Limit limit, Pageable pageable){
		QLimit qLimit = QLimit.limit;
		BooleanExpression booleanExpression = qLimit.workflowStatus.like("%");
		
		if(limit.getOriginalId() != null){
			booleanExpression = booleanExpression.and(qLimit.originalId.eq(limit.getOriginalId()));
		}else{
			if(limit.getId() != null){
				Limit queryLimit = limitRepository.findOne(limit.getId());
				booleanExpression = booleanExpression.and(qLimit.originalId.eq(queryLimit.getOriginalId()));
			}else{ 
				//truong hop nay set condition de khong tim thay record nao
				//-->muc dich: neu limit.getId = null và limit.getOriginalId = null khong tim thay history
				booleanExpression = booleanExpression.and(qLimit.workflowStatus.isNull());
			}
		}
		Page<Limit> result = limitRepository.findAll(booleanExpression, pageable);
		return new PagedResource<Limit>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		//return limitRepository.searchLastedVersion(limit, pageable);
		
	}
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/savedraft/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Limit> searchSaveDraftLimit(Principal principle, @RequestBody Limit limit, Pageable pageable) {
		QLimit qLimit = QLimit.limit;
		BooleanExpression booleanExpression = qLimit.workflowStatus.like(Status.SAVE_DRAFT);
		
		if (StringUtils.isNotEmpty(limit.getCode())) {
			booleanExpression = booleanExpression.and(qLimit.code.upper().like(limit.getCode().toUpperCase())); // support to search value %a
		}
		if (StringUtils.isNotEmpty(limit.getName())) {
			booleanExpression = booleanExpression.and(qLimit.name.upper().like(limit.getName().toUpperCase()));
		}
        
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (limit.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qLimit.processInstanceId.eq(limit.getProcessInstanceId()));
		}
		if (limit.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qLimit.taskId.eq(limit.getTaskId()));
		}

		Page<Limit> result = limitRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Limit>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		
	}
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/sendforapprove/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Limit> searchWaitingLimit(Principal principle, @RequestBody Limit limit, Pageable pageable) {
		QLimit qLimit = QLimit.limit;
		BooleanExpression booleanExpression = qLimit.workflowStatus.like(Status.SEND_FOR_APPROVE);
		
		if (StringUtils.isNotEmpty(limit.getCode())) {
			booleanExpression = booleanExpression.and(qLimit.code.upper().like(limit.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(limit.getName())) {
			booleanExpression = booleanExpression.and(qLimit.name.upper().like(limit.getName().toUpperCase()));
		}
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (limit.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qLimit.processInstanceId.eq(limit.getProcessInstanceId()));
		}
		if (limit.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qLimit.taskId.eq(limit.getTaskId()));
		}
		
		Page<Limit> result = limitRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Limit>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		
	}
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/assigned/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Limit> searchAssignedLimit(Principal principle, @RequestBody Limit limit, Pageable pageable) {
		QLimit qLimit = QLimit.limit;
		BooleanExpression booleanExpression = qLimit.workflowStatus.like(Status.ASSIGNED);
		
		if (StringUtils.isNotEmpty(limit.getCode())) {
			booleanExpression = booleanExpression.and(qLimit.code.upper().like(limit.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(limit.getName())) {
			booleanExpression = booleanExpression.and(qLimit.name.upper().like(limit.getName().toUpperCase()));
		}
		
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (limit.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qLimit.processInstanceId.eq(limit.getProcessInstanceId()));
		}
		if (limit.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qLimit.taskId.eq(limit.getTaskId()));
		}
		
		Page<Limit> result = limitRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Limit>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		
	}
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/approved/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Limit> searchApprovedLimit(Principal principle, @RequestBody Limit limit, Pageable pageable) {
		QLimit qLimit = QLimit.limit;
		BooleanExpression booleanExpression = qLimit.workflowStatus.like(Status.APPROVED);
		
		if (StringUtils.isNotEmpty(limit.getCode())) {
			booleanExpression = booleanExpression.and(qLimit.code.upper().like(limit.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(limit.getName())) {
			booleanExpression = booleanExpression.and(qLimit.name.upper().like(limit.getName().toUpperCase()));
		}
		
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (limit.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qLimit.processInstanceId.eq(limit.getProcessInstanceId()));
		}
		if (limit.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qLimit.taskId.eq(limit.getTaskId()));
		}
				
		Page<Limit> result = limitRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Limit>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		
	}
	
	@Secured("LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/rejected/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Limit> searchRejectedLimit(Principal principle, @RequestBody Limit limit, Pageable pageable) {
		QLimit qLimit = QLimit.limit;
		BooleanExpression booleanExpression = qLimit.workflowStatus.like(Status.REJECTED);
		
		if (StringUtils.isNotEmpty(limit.getCode())) {
			booleanExpression = booleanExpression.and(qLimit.code.upper().like(limit.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(limit.getName())) {
			booleanExpression = booleanExpression.and(qLimit.name.upper().like(limit.getName().toUpperCase()));
		}
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (limit.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qLimit.processInstanceId.eq(limit.getProcessInstanceId()));
		}
		if (limit.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qLimit.taskId.eq(limit.getTaskId()));
		}
		
		Page<Limit> result = limitRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Limit>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		
	}
	
	@Secured("LIMIT_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/savedraft/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Limit> createSaveDraftLimit(Principal principle, @RequestBody Limit request) {
		RestResponse<Limit> resp = new RestResponse<Limit>();
		try {
			//1. set created date, created by
			request.setCreatedDate(new Date());
			request.setCreatedBy(getCurrentUser(principle));
			
			//2. process
			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/admin_his/api/limit/savedraft/create";
				//String restUrl = apiGatewayUrl + ":2001/api/limit/savedraft/create";
				resp = vn.fpt.dbp.vccb.core.rest.limit.LimitService.cud(restUrl, request);
			} else {
				/*
				System.out.print("----Limit savedraft start");
				if (request.getLimitDetails()!=null)
				{
					System.out.print("---- limit.getLimitDetails().size() = " + request.getLimitDetails().size());
					for (LimitDetail lm : request.getLimitDetails()) {
						System.out.print("---- limitDetail.Id = " +lm.getId() );
						System.out.print("---- limitDetail.Branch = " +lm.getBranch() );
						if (lm.getBranch() != null)
						{
							System.out.print("---- primaryLimitDetail.getBranch.name = " + lm.getBranch().getName());
						}
						System.out.print("---- limitDetail.getLimitValue = " +lm.getLimitValue() );
						System.out.print("---- limitDetail.Limit = " +lm.getLimit() );
					}
				}
				System.out.print("----Limit savedraft end");
				
				
				NoDeduplicateIdDeserializationContext deserializationContext = new NoDeduplicateIdDeserializationContext(BeanDeserializerFactory.instance);
		        ObjectMapper mapper = new ObjectMapper(null, null, deserializationContext);
		        String json = mapper.writeValueAsString(request);
		        mapper.configure(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID, true);

		        System.out.println("++++++++++++++++start dumping json request++++++++");
		        System.out.println(json);
		        System.out.println("++++++++++++++++end dumping json request++++++++");
		        */
				
				Limit limit = limitService.saveAsDraft(request);
				
				//resp = new RestResponse<Limit>();
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage(null);
				resp.setContent(limit);
			}
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		
		return resp;
	}
	
	@Secured("LIMIT_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/sendforapprove/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Limit> createSendForApproveLimit(Principal principle, @RequestBody Limit request) {
		RestResponse<Limit> resp = new RestResponse<Limit>();
		
		try {
			
			// 1. set data: created date, created by, referenceCode 
			
			request.setCreatedDate(new Date());
			request.setCreatedBy(getCurrentUser(principle));
			request.setReferenceCode(TransactionCodeGenerator.generate());
			
			//2. process
			Limit limit = limitService.sendForApprove(request);
			
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(limit);
						
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("LIMIT_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/assigned/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Limit> createAssignedLimit(Principal principle, @RequestBody Limit request) {
		RestResponse<Limit> resp = new RestResponse<Limit>();
		
		try {
			//1. set assigned date
			request.setAssignedDate(new Date());
			request.setAssignee(getCurrentUser(principle));
			//2. process
			Limit limit = limitService.assign(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(limit);	
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("LIMIT_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/assigned/return", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Limit> createReturnAssignedLimit(Principal principle, @RequestBody Limit request) {
		RestResponse<Limit> resp = new RestResponse<Limit>();
		
		try {
			//1. set assigned date
			request.setAssignedDate(new Date());
			request.setAssignee(getCurrentUser(principle));
			//2. process
			Limit limit = limitService.returnLimit(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(limit);	
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("LIMIT_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/approved/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Limit> createApprovedLimit(Principal principle, @RequestBody Limit request) {
		RestResponse<Limit> resp = new RestResponse<Limit>();
		try {
			System.out.print("---------Start to run service /api/limit/approved/create");
			//1. set approval date
			request.setApprovedDate(new Date());
			request.setApprovedBy(getCurrentUser(principle));
			
			//2. process
			if (isPrimary()) {
				System.out.print("----------Goto isPrimary condition");
				String restUrl = apiGatewayUrl + "/tellerapp/admin_his/api/limit/approved/create";
				//String restUrl = apiGatewayUrl + ":2001/api/limit/approved/create";
				System.out.print("----------With url = " + restUrl);
				resp = vn.fpt.dbp.vccb.core.rest.limit.LimitService.cud(restUrl, request);
			} else {
				
				System.out.print("----------Goto Non-isPrimary condition");
				System.out.println("----------request.getApprovedBy()=" + request.getApprovedBy());
				
				Limit limit = limitService.approve(request);
				//resp = new RestResponse<Limit>();
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage(null);
				resp.setContent(limit);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp = new RestResponse<Limit>();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("LIMIT_APPROVE")
	@RequestMapping(value = "/api/limit/approved/createinprimary", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Limit> createApprovedLimitInPrimary(Principal principle, @RequestBody Limit request) {
		RestResponse<Limit> resp = new RestResponse<Limit>();
		if (!isPrimary()) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage("This method cannot be called directly from history service");
			resp.setContent(null);
			return resp;
		}
		
		try {
			System.out.println("Run service /api/limit/approved/createinprimary");
			Limit limit = limitService.approveInPrimary(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage(null);
			resp.setContent(limit);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("LIMIT_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/rejected/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Limit> createRejectedLimit(Principal principle, @RequestBody Limit request) {
		RestResponse<Limit> resp = new RestResponse<Limit>();
		
		try {
			//1. set approval date
			request.setApprovedDate(new Date());
			
			//2. process
			Limit limit = limitService.reject(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(limit);			
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("LIMIT_DELETE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limit/savedraft/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Limit> deleteSaveDraftLimit(Principal principle, @RequestBody Limit request) {
		RestResponse<Limit> resp = new RestResponse<Limit>();
		
		try {
			
			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/admin_his/api/limit/savedraft/delete";
				resp = vn.fpt.dbp.vccb.core.rest.limit.LimitService.cud(restUrl, request);
			}else{
				Limit limit = limitService.deleteSaveDraft(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage("");
				resp.setContent(limit);
			}

		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	
	private User getCurrentUser(Principal principle){
		String username = "";
		if (principle != null){
			username = principle.getName();
		}
		
		User user = userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(username, Status.APPROVED);
		return user;

	}

}
