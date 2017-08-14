package vn.fpt.dbp.vccb.service.admin.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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

import vn.fpt.dbp.vccb.core.domain.role.QRole;
import vn.fpt.dbp.vccb.core.domain.role.Role;
import vn.fpt.dbp.vccb.core.domain.role.repository.RoleRepository;
import vn.fpt.dbp.vccb.core.domain.user.QUser;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.Permissions;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.dbp.vccb.core.util.TransactionCodeGenerator;
import vn.fpt.dbp.vccb.service.admin.service.RoleService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class RoleController {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";
	
	@Value("${spring.application.name}")
	private String serviceName;
	
	private boolean isPrimary() {
		return "digital-banking-vccb-service-admin".equals(serviceName);
	}
	
	@Secured("ROLE_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/role/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public Role roleInfor(Principal principle, @PathVariable("id") Long id){
		Role role = roleRepository.findOne(id);
		return role;
	}
	
//	@CrossOrigin(origins = "*")
//	@RequestMapping(value = "/api/role/code/{code}", method = RequestMethod.GET, produces = "application/json")
//	public Role roleInforByCode(Principal principle, @PathVariable("code") String code){
//		Role role = roleRepository.findByCode(code);
//		return role;
//	}
	@Secured({"ROLE_VIEW","ROLE_ADD","ROLE_APPROVE"})
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/role/getapprovallist", method = RequestMethod.GET, produces = "application/json")
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
		   if(u.hasPermission("ROLE",Permissions.APPROVE)){
		      approvalList.add(u);
		   }
		}
		
		return approvalList;
	}
	
	@Secured("ROLE_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/role/code/{code}", method = RequestMethod.GET, produces = "application/json")
	public List<Role> roleInforByCode(Principal principle, @PathVariable("code") String code){
		QRole qRole = QRole.role;
		List<Role> role = (List<Role>) roleRepository.findAll(qRole.code.eq(code));
		return role;
	}
	
	@Secured("ROLE_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/role/checkexisted/code/{code}", method = RequestMethod.GET, produces = "application/json")
	public List<Role> checkRoleByCode(Principal principle, @PathVariable("code") String code){
		String restUrl;
		List<Role> roles = new ArrayList<Role>();
		try{
			if(isPrimary()){
				restUrl = apiGatewayUrl + "/tellerapp/admin_his/api/role/checkexisted/code/" + code;
				roles = vn.fpt.dbp.vccb.core.rest.role.RoleService.findList(restUrl);
			}else{
				//1. In History, find Role by code except workFlowStatus = SAVE_DRAFT, REJECTED, APPROVED
				QRole qRole = QRole.role;
				BooleanExpression booleanExpression = qRole.code.equalsIgnoreCase(code);
				booleanExpression = booleanExpression.and(qRole.workflowStatus.notEqualsIgnoreCase(Status.SAVE_DRAFT));
				booleanExpression = booleanExpression.and(qRole.workflowStatus.notEqualsIgnoreCase(Status.REJECTED));
				booleanExpression = booleanExpression.and(qRole.workflowStatus.notEqualsIgnoreCase(Status.APPROVED));
				List<Role> roleHis = (List<Role>) roleRepository.findAll(booleanExpression);
				//2. In Primary, find Role by code
				restUrl = apiGatewayUrl + "/tellerapp/admin/api/role/code/" + code;
				List<Role>rolePri = vn.fpt.dbp.vccb.core.rest.role.RoleService.findList(restUrl);
				//3. Add result of 1 & 2 into a list
				if(roleHis != null){
					roles.addAll(roleHis);
				}
				if(rolePri != null){
					roles.addAll(rolePri);
				}
			}
			return roles;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	@Secured("ROLE_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/role/name/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Role> roleInforByName(Principal principle, @PathVariable("name") String name){
		QRole qRole = QRole.role;
		List<Role> role = (List<Role>)roleRepository.findAll(qRole.name.eq(name));
		return role;
	}
	
	@Secured("ROLE_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/role/checkexisted/name/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Role> checkRoleByName(Principal principle, @PathVariable("name") String name){
		String restUrl;
		List<Role> roles = new ArrayList<Role>();
		try{
			if(isPrimary()){
				restUrl = apiGatewayUrl + "/tellerapp/admin_his/api/role/checkexisted/name/" + name;
				roles = vn.fpt.dbp.vccb.core.rest.role.RoleService.findList(restUrl);
			}else{
				//1. In History, find Role by name except workFlowStatus = SAVE_DRAFT, REJECTED, APPROVED
				QRole qRole = QRole.role;
				BooleanExpression booleanExpression = qRole.code.equalsIgnoreCase(name);
				booleanExpression = booleanExpression.and(qRole.workflowStatus.notEqualsIgnoreCase(Status.SAVE_DRAFT));
				booleanExpression = booleanExpression.and(qRole.workflowStatus.notEqualsIgnoreCase(Status.REJECTED));
				booleanExpression = booleanExpression.and(qRole.workflowStatus.notEqualsIgnoreCase(Status.APPROVED));
				List<Role> roleHis = (List<Role>) roleRepository.findAll(booleanExpression);
				//2. In Primary, find Role by name
				restUrl = apiGatewayUrl + "/tellerapp/admin/api/role/name/" + name;
				List<Role>rolePri = vn.fpt.dbp.vccb.core.rest.role.RoleService.findList(restUrl);
				//3. Add result of 1 & 2 into a list
				if(roleHis != null){
					roles.addAll(roleHis);
				}
				if(rolePri != null){
					roles.addAll(rolePri);
				}
			}
			return roles;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	@Secured("ROLE_VIEW")
	@CrossOrigin(origins = "*")	
	@RequestMapping(value = "/api/role/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Role> searchRole(Principal principle, @RequestBody Role role, Pageable pageable){
		QRole qrole = QRole.role;
		BooleanExpression booleanExpression = qrole.name.like("%");
		
		//TO DO: need to build base on document
		if (StringUtils.isNotEmpty(role.getCode())){
			booleanExpression = booleanExpression.and(qrole.code.upper().like(role.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(role.getName())){
			booleanExpression = booleanExpression.and(qrole.name.upper().like(role.getName().toUpperCase()));
		}	
		if (StringUtils.isNotEmpty(role.getStatus())) {
			booleanExpression = booleanExpression.and(qrole.status.upper().like(role.getStatus().toUpperCase()));
		}
		if (role.getIsAdmin()) {
			booleanExpression = booleanExpression.and(qrole.isAdmin.eq(role.getIsAdmin()));
		}
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (role.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qrole.processInstanceId.eq(role.getProcessInstanceId()));
		}
		if (role.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qrole.taskId.eq(role.getTaskId()));
		}
		
		Page<Role> result = roleRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Role>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(), result.getTotalElements());

	}
	
	@Secured("ROLE_VIEW")
	@CrossOrigin(origins = "*")	
	@RequestMapping(value = "/api/role/find", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Role> findRole(Principal principle, @RequestBody Role role, Pageable pageable){
		User user = userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(principle.getName(), Status.APPROVED);
		
		if(user!=null){
			role.setCreatedBy(user);
		}
		
		return roleRepository.searchLastedVersion(role, pageable);
	}
	/*
	@Secured({"ROLE_VIEW","ROLE_ADD","ROLE_APPROVE"})
	@CrossOrigin(origins = "*")	
	@RequestMapping(value = "/api/role/finduser", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<User> findUserOfRole(Principal principle, @RequestBody Role role, Pageable pageable){
		QUser qUser = QUser.user;
		Page<User> result = null;
		
		Role roleQuery = null;
		if(role.getId()!=null){
			roleQuery = roleRepository.findOne(role.getId());
			
		}else if(StringUtils.isNotEmpty(role.getCode())){
			
			roleQuery = roleRepository.findTop1ByCodeAndWorkflowStatusOrderByApprovedDateAsc(role.getCode(), Status.APPROVED);
		}
		
		if(roleQuery != null){
			
			result = userRepository.findAll(qUser.userRoles.any().role.eq(roleQuery),pageable);
		}
		
		if (result == null){
			return new PagedResource<User>();	
		}
		return new PagedResource<User>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages()); 
		
	}
	*/
	@Secured("ROLE_VIEW")
	@CrossOrigin(origins = "*")	
	@RequestMapping(value = "/api/role/savedraft/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Role> searchSavedDraftRole(Principal principle, @RequestBody Role role, Pageable pageable){
		QRole qrole = QRole.role;
		BooleanExpression booleanExpression = qrole.workflowStatus.eq(Status.SAVE_DRAFT);
		
		if (StringUtils.isNotEmpty(role.getCode())){
			booleanExpression = booleanExpression.and(qrole.code.upper().like(role.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(role.getName())){
			booleanExpression = booleanExpression.and(qrole.name.upper().like(role.getName().toUpperCase()));
		}	
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (role.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qrole.processInstanceId.eq(role.getProcessInstanceId()));
		}
		if (role.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qrole.taskId.eq(role.getTaskId()));
		}
		
		Page<Role> result = roleRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Role>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());

	}
	
	@Secured("ROLE_VIEW")
	@CrossOrigin(origins = "*")	
	@RequestMapping(value = "/api/role/sendforapprove/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Role> searchSendForApproveRole(Principal principle, @RequestBody Role role, Pageable pageable){
		QRole qrole = QRole.role;
		BooleanExpression booleanExpression = qrole.workflowStatus.eq(Status.SEND_FOR_APPROVE);
		
		//TO DO: need to build base on document
		if (StringUtils.isNotEmpty(role.getCode())){
			booleanExpression = booleanExpression.and(qrole.code.upper().like(role.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(role.getName())){
			booleanExpression = booleanExpression.and(qrole.name.upper().like(role.getName().toUpperCase()));
		}	
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (role.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qrole.processInstanceId.eq(role.getProcessInstanceId()));
		}
		if (role.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qrole.taskId.eq(role.getTaskId()));
		}
		
		Page<Role> result = roleRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Role>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());

	}
	
	@Secured("ROLE_VIEW")
	@CrossOrigin(origins = "*")	
	@RequestMapping(value = "/api/role/assigned/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Role> searchAssignedRole(Principal principle, @RequestBody Role role, Pageable pageable){
		QRole qrole = QRole.role;
		BooleanExpression booleanExpression = qrole.workflowStatus.eq(Status.ASSIGNED);
		
		//TO DO: need to build base on document
		if (StringUtils.isNotEmpty(role.getCode())){
			booleanExpression = booleanExpression.and(qrole.code.upper().like(role.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(role.getName())){
			booleanExpression = booleanExpression.and(qrole.name.upper().like(role.getName().toUpperCase()));
		}	
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (role.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qrole.processInstanceId.eq(role.getProcessInstanceId()));
		}
		if (role.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qrole.taskId.eq(role.getTaskId()));
		}
		
		Page<Role> result = roleRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Role>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());

	}

	@Secured("ROLE_VIEW")
	@CrossOrigin(origins = "*")	
	@RequestMapping(value = "/api/role/approved/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Role> searchApprovedRole(Principal principle, @RequestBody Role role, Pageable pageable){
		QRole qrole = QRole.role;
		BooleanExpression booleanExpression = qrole.workflowStatus.eq(Status.APPROVED);
		
		//TO DO: need to build base on document
		if (StringUtils.isNotEmpty(role.getCode())){
			booleanExpression = booleanExpression.and(qrole.code.upper().like(role.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(role.getName())){
			booleanExpression = booleanExpression.and(qrole.name.upper().like(role.getName().toUpperCase()));
		}	
		
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (role.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qrole.processInstanceId.eq(role.getProcessInstanceId()));
		}
		if (role.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qrole.taskId.eq(role.getTaskId()));
		}
		
		Page<Role> result = roleRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Role>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());

	}
	
	@Secured("ROLE_VIEW")
	@CrossOrigin(origins = "*")	
	@RequestMapping(value = "/api/role/rejected/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Role> searchRejectedRole(Principal principle, @RequestBody Role role, Pageable pageable){
		QRole qrole = QRole.role;
		BooleanExpression booleanExpression = qrole.workflowStatus.eq(Status.REJECTED);
		
		//TO DO: need to build base on document
		if (StringUtils.isNotEmpty(role.getCode())){
			booleanExpression = booleanExpression.and(qrole.code.upper().like(role.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(role.getName())){
			booleanExpression = booleanExpression.and(qrole.name.upper().like(role.getName().toUpperCase()));
		}	
		
		//--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (role.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(qrole.processInstanceId.eq(role.getProcessInstanceId()));
		}
		if (role.getTaskId() != null) {
			booleanExpression = booleanExpression.and(qrole.taskId.eq(role.getTaskId()));
		}
		
		Page<Role> result = roleRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Role>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());

	}
	
	@Secured("ROLE_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/role/savedraft/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Role> createSaveDraftRole(Principal principle, @RequestBody Role request) {
		RestResponse<Role> resp = new RestResponse<Role>();
		
		try {
			//1. set created date
			request.setCreatedDate(new Date());
			request.setCreatedBy(getCurrentUser(principle));
			//2 process
			Role role = roleService.saveAsDraft(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(role);
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("ROLE_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/role/sendforapprove/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Role> createSendForApproveRole(Principal principle, @RequestBody Role request) {
		RestResponse<Role> resp = new RestResponse<Role>();
		try {
			//1. set created date
			request.setCreatedDate(new Date());
			request.setCreatedBy(getCurrentUser(principle));
			request.setReferenceCode(TransactionCodeGenerator.generate());
			//request.setAssignee(null);
			
			//2.process
			
			Role role = roleService.sendForApprove(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(role);
			
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("ROLE_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/role/assigned/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Role> createAssignedRole(Principal principle, @RequestBody Role request) {
		RestResponse<Role> resp = new RestResponse<Role>();
		try {
			//1. set assigned date
			request.setAssignedDate(new Date());
			request.setAssignee(getCurrentUser(principle));
			//2. process
			Role role = roleService.assign(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(role);
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("ROLE_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/role/assigned/return", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Role> createReturnAssignedRole(Principal principle, @RequestBody Role request) {
		RestResponse<Role> resp = new RestResponse<Role>();
		try {
			
			//2. process
			Role role = roleService.returnRole(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(role);
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("ROLE_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/role/approved/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Role> createApprovedRole(Principal principle, @RequestBody Role request) {
		RestResponse<Role> resp = new RestResponse<Role>();
		try {
			//1. set approval date, created date
			request.setApprovedDate(new Date());
			request.setApprovedBy(getCurrentUser(principle));
			//2.process

			Role role = roleService.approve(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(role);
		} 
		catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("ROLE_APPROVE")
	@RequestMapping(value = "/api/role/approved/createinprimary", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Role> createApprovedRoleInPrimary(Principal principle, @RequestBody Role request) {
		RestResponse<Role> resp = new RestResponse<Role>();
		if (!isPrimary()) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage("This method cannot be called directly from history service");
			resp.setContent(null);
			return resp;
		}
		
		try {
			System.out.println("Run service /api/role/approved/createinprimary");
			Role role = roleService.approveInPrimary(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage(null);
			resp.setContent(role);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("ROLE_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/role/rejected/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Role> createRejectedRole(Principal principle, @RequestBody Role request) {
		RestResponse<Role> resp = new RestResponse<Role>();
		try {
			//1. set approval date
			request.setApprovedDate(new Date());
			request.setApprovedBy(getCurrentUser(principle));
			
			//2.process
			Role role = roleService.reject(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(role);
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@Secured("ROLE_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/role/savedraft/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Role> deleteSaveDraftLimit(Principal principle, @RequestBody Role request) {
		RestResponse<Role> resp = new RestResponse<Role>();
		
		try {
			
			Role role = roleService.deleteSaveDraft(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(role);

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