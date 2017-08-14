package vn.fpt.dbp.vccb.service.admin.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import vn.fpt.dbp.vccb.core.domain.limit.Limit;
import vn.fpt.dbp.vccb.core.domain.limit.LimitGroup;
import vn.fpt.dbp.vccb.core.domain.limit.QLimit;
import vn.fpt.dbp.vccb.core.domain.limit.QLimitGroup;
import vn.fpt.dbp.vccb.core.domain.limit.repository.LimitGroupRepository;
import vn.fpt.dbp.vccb.core.domain.user.QUser;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.Permissions;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.dbp.vccb.core.util.TransactionCodeGenerator;
import vn.fpt.dbp.vccb.service.admin.service.LimitGroupService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class LimitGroupController {
	
	private static final Logger logger = LoggerFactory.getLogger(LimitGroupController.class);
	
	@Autowired
	private LimitGroupRepository limitGroupRepository;
	
	@Autowired
	private LimitGroupService limitGroupService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;
	
	@Value("${spring.application.name}")
	private String serviceName;
	
	private boolean isPrimary() {
		return "digital-banking-vccb-service-admin".equals(serviceName);
	}
	
	@Secured("GROUP_LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/list", method = RequestMethod.GET, produces = "application/json")
	public List<LimitGroup> listLimitGroups(Principal principle) {
		logger.debug("run service /api/limitgroup/list");
		return limitGroupRepository.findAll();
	}
	
	@Secured("GROUP_LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public LimitGroup limitGroupInfo(Principal principle, @PathVariable("id") Long id) {
		logger.debug("run service /api/limitgroup/detail/" + id);
		LimitGroup limitGroup = limitGroupRepository.findOne(id);
		return limitGroup;
	}
	
	@Secured("GROUP_LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/code/{code}", method = RequestMethod.GET, produces = "application/json")
	public List<LimitGroup> limitGroupInfoByCode(Principal principle, @PathVariable("code") String code) {
		QLimitGroup qLimit = QLimitGroup.limitGroup;
		logger.debug("run service /api/limitgroup/code/" + code);
		
		List<LimitGroup> limitGroup = (List<LimitGroup>) limitGroupRepository.findAll(qLimit.code.eq(code));
		
		return limitGroup;
	}
	
	@Secured("GROUP_LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/checkexisted/code/{code}", method = RequestMethod.GET, produces = "application/json")
	public List<LimitGroup> checkExistedLimitGroupByCode(Principal principle, @PathVariable("code") String code) {
		String restUrl;
		List<LimitGroup> limitGroups = new ArrayList<LimitGroup>();
		try{
			if(isPrimary()){
				restUrl = apiGatewayUrl + "/tellerapp/admin_his/api/limitgroup/checkexisted/code/" + code;
				limitGroups = vn.fpt.dbp.vccb.core.rest.limit.LimitGroupService.findList(restUrl);
			}else{
				//1. In History, find LimitGroup by Code except workFlowStatus = SAVE_DRAFT, REJECTED, APPROVED
				QLimitGroup qLimitGroup = QLimitGroup.limitGroup;
				BooleanExpression booleanExpression = qLimitGroup.code.equalsIgnoreCase(code);
				booleanExpression = booleanExpression.and(qLimitGroup.workflowStatus.notEqualsIgnoreCase(Status.SAVE_DRAFT));
				booleanExpression = booleanExpression.and(qLimitGroup.workflowStatus.notEqualsIgnoreCase(Status.REJECTED));
				booleanExpression = booleanExpression.and(qLimitGroup.workflowStatus.notEqualsIgnoreCase(Status.APPROVED));
		
				List<LimitGroup> limitGroupHis = (List<LimitGroup>) limitGroupRepository.findAll(booleanExpression);
				//2. In Primary, find LimitGroup by code
				restUrl = apiGatewayUrl + "/tellerapp/admin/api/limitgroup/code/" + code;
				List<LimitGroup> limitGroupPri = vn.fpt.dbp.vccb.core.rest.limit.LimitGroupService.findList(restUrl);
				//3. Add result of 1 & 2 into a list
				if(limitGroupHis != null){
					limitGroups.addAll(limitGroupHis);
				}
				if(limitGroupPri != null){
					limitGroups.addAll(limitGroupPri);
				}
				
			}
			return limitGroups;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	@Secured("GROUP_LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/name/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<LimitGroup> limitGroupInfoByName(Principal principle, @PathVariable("name") String name) {
		List<LimitGroup> limitGroup = limitGroupRepository.findByName(name);
		return limitGroup;
	}
	
	@Secured("GROUP_LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/checkexisted/name/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<LimitGroup> checkLimitGroupByName(Principal principle, @PathVariable("name") String name) {
		String restUrl;
		List<LimitGroup> limitGroups = new ArrayList<LimitGroup>();
		try{
			if(isPrimary()){
				restUrl = apiGatewayUrl + "/tellerapp/admin_his/api/limitgroup/checkexisted/name/" + name;
				limitGroups = vn.fpt.dbp.vccb.core.rest.limit.LimitGroupService.findList(restUrl);
			}else{
				//1. In History, find LimitGroup by name except workFlowStatus = SAVE_DRAFT, REJECTED, APPROVED
				QLimitGroup qLimitGroup = QLimitGroup.limitGroup;
				BooleanExpression booleanExpression = qLimitGroup.code.equalsIgnoreCase(name);
				booleanExpression = booleanExpression.and(qLimitGroup.workflowStatus.notEqualsIgnoreCase(Status.SAVE_DRAFT));
				booleanExpression = booleanExpression.and(qLimitGroup.workflowStatus.notEqualsIgnoreCase(Status.REJECTED));
				booleanExpression = booleanExpression.and(qLimitGroup.workflowStatus.notEqualsIgnoreCase(Status.APPROVED));
				
				List<LimitGroup> limitGroupHis = (List<LimitGroup>) limitGroupRepository.findAll(booleanExpression);
				//2. In Primary, find LimitGroup by name
				restUrl = apiGatewayUrl + "/tellerapp/admin/api/limitgroup/name/" + name;
				List<LimitGroup> limitGroupPri = vn.fpt.dbp.vccb.core.rest.limit.LimitGroupService.findList(restUrl);
				
				//3. Add result of 1 & 2 into a list
				if(limitGroupHis != null){
					limitGroups.addAll(limitGroupHis);
				}
				if(limitGroupPri != null){
					limitGroups.addAll(limitGroupPri);
				}
			}
			return limitGroups;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	@Secured({"GROUP_LIMIT_VIEW","GROUP_LIMIT_ADD"})
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/getapprovallist", method = RequestMethod.GET, produces = "application/json")
	public List<User> getUserApprovals(Principal principle) {
		QUser quser = QUser.user;
		List<User> users = new ArrayList<User>();
		List<User> approvalList = new ArrayList<User>();
		String username = "";
		Long branchId;
		
		if (principle != null){
			username = principle.getName();
		}
		User user = userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(username, Status.APPROVED);

		if (user != null){
			
			branchId = user.getCurrentBranch()== null?user.getBranch().getId():user.getCurrentBranch().getId();
		    users = (List<User>) userRepository.findAll(quser.branch.id.eq(branchId));
		}
		
		for(User u : users){
		   if(u.hasPermission("GROUP_LIMIT",Permissions.APPROVE)){
		      approvalList.add(u);
		   }
		}
		
		return approvalList;
	}
	
	@Secured("GROUP_LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<LimitGroup> searchLimitGroup(Principal principle, @RequestBody LimitGroup limitGroup, Pageable pageable) {
		QLimitGroup qLimitGroup = QLimitGroup.limitGroup;
		BooleanExpression booleanExpression = qLimitGroup.name.like("%");
		if (StringUtils.isNotEmpty(limitGroup.getCode())) {
			booleanExpression = booleanExpression.and(qLimitGroup.code.upper().like(limitGroup.getCode().toUpperCase()));// support to search value %a
		}
		if (StringUtils.isNotEmpty(limitGroup.getName())) {
			booleanExpression = booleanExpression.and(qLimitGroup.name.upper().like(limitGroup.getName().toUpperCase()));
		}
		
		if (StringUtils.isNotEmpty(limitGroup.getStatus())) {
			booleanExpression = booleanExpression.and(qLimitGroup.status.upper().like(limitGroup.getStatus().toUpperCase()));
		}
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (limitGroup.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qLimitGroup.processInstanceId.eq(limitGroup.getProcessInstanceId()));
		}
		if (limitGroup.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qLimitGroup.taskId.eq(limitGroup.getTaskId()));
		}
		
		Page<LimitGroup> result = limitGroupRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<LimitGroup>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		
	}
	
	@Secured("GROUP_LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/find", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<LimitGroup> findLimitGroup(Principal principle, @RequestBody LimitGroup limitGroup, Pageable pageable) {
		
		User user = userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(principle.getName(), Status.APPROVED);
		
		if(user!=null){
			limitGroup.setCreatedBy(user);
		} 
		return limitGroupRepository.searchLastedVersion(limitGroup, pageable);
	}
	
	@Secured("GROUP_LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/history", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<LimitGroup> findLimitHistory(Principal principle, @RequestBody LimitGroup limitGroup, Pageable pageable){
		QLimitGroup qLimitGroup = QLimitGroup.limitGroup;
		BooleanExpression booleanExpression = qLimitGroup.workflowStatus.like("%");
		
		if(limitGroup.getOriginalId() != null){
			booleanExpression = booleanExpression.and(qLimitGroup.originalId.eq(limitGroup.getOriginalId()));
		}else{
			if(limitGroup.getId() != null){
				LimitGroup queryLimitGroup = limitGroupRepository.findOne(limitGroup.getId());
				booleanExpression = booleanExpression.and(qLimitGroup.originalId.eq(queryLimitGroup.getOriginalId()));
			}else{ 
				//truong hop nay set condition de khong tim thay record nao
				//-->muc dich: neu limit.getId = null và limit.getOriginalId = null khong tim thay history
				booleanExpression = booleanExpression.and(qLimitGroup.workflowStatus.isNull());
			}
		}
		Page<LimitGroup> result = limitGroupRepository.findAll(booleanExpression, pageable);
		return new PagedResource<LimitGroup>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		//return limitRepository.searchLastedVersion(limit, pageable);
		
	}	
	
	@Secured("GROUP_LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/savedraft/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<LimitGroup> searchSaveDraftLimitGroup(Principal principle, @RequestBody LimitGroup limitGroup, Pageable pageable) {
		QLimitGroup qLimitGroup = QLimitGroup.limitGroup;
		BooleanExpression booleanExpression = qLimitGroup.workflowStatus.like(Status.SAVE_DRAFT);
		
		if (StringUtils.isNotEmpty(limitGroup.getCode())) {
			booleanExpression = booleanExpression.and(qLimitGroup.code.upper().like(limitGroup.getCode().toUpperCase())); // support to search value %a
		}
		if (StringUtils.isNotEmpty(limitGroup.getName())) {
			booleanExpression = booleanExpression.and(qLimitGroup.name.upper().like(limitGroup.getName().toUpperCase()));
		}
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (limitGroup.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qLimitGroup.processInstanceId.eq(limitGroup.getProcessInstanceId()));
		}
		if (limitGroup.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qLimitGroup.taskId.eq(limitGroup.getTaskId()));
		}
		
		Page<LimitGroup> result = limitGroupRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<LimitGroup>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		
	}
	
	@Secured("GROUP_LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/sendforapprove/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<LimitGroup> searchWaitingLimitGroup(Principal principle, @RequestBody LimitGroup limitGroup, Pageable pageable) {
		QLimitGroup qLimitGroup = QLimitGroup.limitGroup;
		BooleanExpression booleanExpression = qLimitGroup.workflowStatus.like(Status.SEND_FOR_APPROVE);
		
		if (StringUtils.isNotEmpty(limitGroup.getCode())) {
			booleanExpression = booleanExpression.and(qLimitGroup.code.upper().like(limitGroup.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(limitGroup.getName())) {
			booleanExpression = booleanExpression.and(qLimitGroup.name.upper().like(limitGroup.getName().toUpperCase()));
		}
		
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (limitGroup.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qLimitGroup.processInstanceId.eq(limitGroup.getProcessInstanceId()));
		}
		if (limitGroup.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qLimitGroup.taskId.eq(limitGroup.getTaskId()));
		}
		
		Page<LimitGroup> result = limitGroupRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<LimitGroup>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		
	}
	
	@Secured("GROUP_LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/approved/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<LimitGroup> searchApprovedLimitGroup(Principal principle, @RequestBody LimitGroup limitGroup, Pageable pageable) {
		QLimitGroup qLimitGroup = QLimitGroup.limitGroup;
		BooleanExpression booleanExpression = qLimitGroup.workflowStatus.like(Status.APPROVED);
		
		if (StringUtils.isNotEmpty(limitGroup.getCode())) {
			booleanExpression = booleanExpression.and(qLimitGroup.code.upper().like(limitGroup.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(limitGroup.getName())) {
			booleanExpression = booleanExpression.and(qLimitGroup.name.upper().like(limitGroup.getName().toUpperCase()));
		}
		
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (limitGroup.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qLimitGroup.processInstanceId.eq(limitGroup.getProcessInstanceId()));
		}
		if (limitGroup.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qLimitGroup.taskId.eq(limitGroup.getTaskId()));
		}
		
		Page<LimitGroup> result = limitGroupRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<LimitGroup>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		
	}
	
	@Secured("GROUP_LIMIT_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/rejected/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<LimitGroup> searchRejectedLimitGroup(Principal principle, @RequestBody LimitGroup limitGroup, Pageable pageable) {
		QLimitGroup qLimitGroup = QLimitGroup.limitGroup;
		BooleanExpression booleanExpression = qLimitGroup.workflowStatus.like(Status.REJECTED);
		
		if (StringUtils.isNotEmpty(limitGroup.getCode())) {
			booleanExpression = booleanExpression.and(qLimitGroup.code.upper().like(limitGroup.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(limitGroup.getName())) {
			booleanExpression = booleanExpression.and(qLimitGroup.name.upper().like(limitGroup.getName().toUpperCase()));
		}
		
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (limitGroup.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qLimitGroup.processInstanceId.eq(limitGroup.getProcessInstanceId()));
		}
		if (limitGroup.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qLimitGroup.taskId.eq(limitGroup.getTaskId()));
		}
				
		Page<LimitGroup> result = limitGroupRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<LimitGroup>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		
	}
	
	@Secured("GROUP_LIMIT_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/savedraft/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<LimitGroup> createSaveDraftLimitGroup(Principal principle, @RequestBody LimitGroup request) {
		RestResponse<LimitGroup> resp = new RestResponse<LimitGroup>();
		LimitGroup result = null;
		try {
			request.setCreatedDate(new Date());
			request.setCreatedBy(getCurrentUser(principle));
			LimitGroup limitGroup = limitGroupService.saveAsDraft(request);
			/*
			 * Can find lai de tra ve, neu khong chi co Id, data cua cac limit bi null
			 */
			if (limitGroup.getId() != null){
				System.out.println("***** Starting test createSaveDraftLimitGroup***** ");
				System.out.println("***** limitGroup.getId() = " +limitGroup.getId());
				
				result = limitGroupRepository.getOne(limitGroup.getId());
				for(Limit limit: result.getLimits()){
					System.out.println("----- limit.getId() = " +limit.getId());
					System.out.println("----- limit.getCode() = " +limit.getCode());
					System.out.println("----- limit.getName() = " +limit.getName());
				}
			}
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage(null);
			resp.setContent(result);
			
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("GROUP_LIMIT_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/sendforapprove/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<LimitGroup> createSendForApproveLimitGroup(Principal principle, @RequestBody LimitGroup request) {
		RestResponse<LimitGroup> resp = new RestResponse<LimitGroup>();
		
		try {
			// 1. set data: created date, referenceCode 
			
			request.setCreatedDate(new Date());
			request.setCreatedBy(getCurrentUser(principle));
			request.setReferenceCode(TransactionCodeGenerator.generate());
						
			LimitGroup limitGroup = limitGroupService.sendForApprove(request);
			
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(limitGroup);
						
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("GROUP_LIMIT_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/assigned/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<LimitGroup> createAssignedLimitGroup(Principal principle, @RequestBody LimitGroup request) {
		RestResponse<LimitGroup> resp = new RestResponse<LimitGroup>();
		
		try {
			//1. set assigned date
			request.setAssignedDate(new Date());
			request.setAssignee(getCurrentUser(principle));
			if (StringUtils.isEmpty(request.getReferenceCode())){
				request.setReferenceCode(TransactionCodeGenerator.generate());
			}
			
			LimitGroup limitGroup = limitGroupService.assign(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(limitGroup);	
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("GROUP_LIMIT_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/assigned/return", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<LimitGroup> createReturnAssignedLimitGroup(Principal principle, @RequestBody LimitGroup request) {
		RestResponse<LimitGroup> resp = new RestResponse<LimitGroup>();
		
		try {
			
			LimitGroup limitGroup = limitGroupService.returnLimitGroup(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(limitGroup);	
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("GROUP_LIMIT_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/approved/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<LimitGroup> createApprovedLimitGroup(Principal principle, @RequestBody LimitGroup request) {
		RestResponse<LimitGroup> resp = new RestResponse<LimitGroup>();
		try {
			//1. set approval date
			request.setApprovedDate(new Date());
			request.setApprovedBy(getCurrentUser(principle));
			
			LimitGroup limitGroup = limitGroupService.approve(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage(null);
			resp.setContent(limitGroup);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("GROUP_LIMIT_APPROVE")
	@RequestMapping(value = "/api/limitgroup/approved/createinprimary", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<LimitGroup> createApprovedLimitInPrimary(Principal principle, @RequestBody LimitGroup request) {
		RestResponse<LimitGroup> resp = new RestResponse<LimitGroup>();
		if (!isPrimary()) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage("This method cannot be called directly from history service");
			resp.setContent(null);
			return resp;
		}
		
		try {
			System.out.println("Run service /api/limitgroup/approved/createinprimary");
			LimitGroup limitGroup = limitGroupService.approveInPrimary(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage(null);
			resp.setContent(limitGroup);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("GROUP_LIMIT_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/rejected/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<LimitGroup> createRejectedLimitGroup(Principal principle, @RequestBody LimitGroup request) {
		RestResponse<LimitGroup> resp = new RestResponse<LimitGroup>();
		
		try {
			request.setApprovedDate(new Date());
			request.setApprovedBy(getCurrentUser(principle));
			
			LimitGroup limitGroup = limitGroupService.reject(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(limitGroup);			
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("GROUP_LIMIT_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/limitgroup/savedraft/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<LimitGroup> deleteSaveDraftLimit(Principal principle, @RequestBody LimitGroup request) {
		RestResponse<LimitGroup> resp = new RestResponse<LimitGroup>();
		
		try {
			
			LimitGroup limitGroup = limitGroupService.deleteSaveDraft(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(limitGroup);			

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
