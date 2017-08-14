package vn.fpt.dbp.vccb.service.admin.service;

import static org.axonframework.domain.GenericEventMessage.asEventMessage;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.fpt.dbp.vccb.core.domain.base.Comment;
import vn.fpt.dbp.vccb.core.domain.role.Role;
import vn.fpt.dbp.vccb.core.domain.role.RolePermission;
import vn.fpt.dbp.vccb.core.domain.role.event.RoleApprovedEvent;
import vn.fpt.dbp.vccb.core.domain.role.repository.RoleRepository;
import vn.fpt.dbp.vccb.core.util.DBPException;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.RestResponse;

@Service
public class RoleService {
	
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;
	
	@Value("${spring.application.name}")
	private String serviceName;
	
	@Autowired
	private EventBus eventBus;
	
	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	public Role saveAsDraft(Role request) throws Exception {
		
		Role role = request.getId() == null ? null : roleRepository.findOne(request.getId());
		
		if (role != null) {
		
			if(!Status.SAVE_DRAFT.equalsIgnoreCase(role.getWorkflowStatus()))
			{
				throw new Exception(DBPException.RECORD_NOT_EXISTED);
			}
		}

		return updateRoleInfo(request, Status.SAVE_DRAFT);
	}
	
	@Transactional
	public Role sendForApprove(Role request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		if(!Status.SAVE_DRAFT.equalsIgnoreCase(request.getWorkflowStatus()))
		{
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		
		String status = Status.SEND_FOR_APPROVE;
		if(request.getAssignee() != null && request.getAssignee().getId() != null){
			status = Status.ASSIGNED;
		}
		
		return updateRoleInfo(request, status);
		
	}
	
	@Transactional
	public Role assign(Role request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getAssignee() == null || request.getAssignee().getId() == null){
			throw new Exception(DBPException.NO_ASSIGNEE);
		}
		if(!Status.SEND_FOR_APPROVE.equalsIgnoreCase(request.getWorkflowStatus()) &&
				!Status.SAVE_DRAFT.equalsIgnoreCase(request.getWorkflowStatus())){
			 Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			 throw e;
		}
		
		return updateRoleInfo(request, Status.ASSIGNED);
		
	}
	
	@Transactional
	public Role returnRole(Role request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		
		if(!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())){
			 Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			 throw e;
		}
		
		Role role = roleRepository.findOne(request.getId());
		
		role.setAssignedDate(null);
		role.setAssignee(null);
		role.setWorkflowStatus(Status.SEND_FOR_APPROVE);
		
		return roleRepository.save(role);
		//return updateRoleInfo(request, Status.ASSIGNED);
		
	}
	
	@Transactional
	public Role approve(Role request) throws Exception {
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
			throw new Exception(DBPException.NO_APPROVER);
		}
		if(!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())){
			 Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			 throw e;
		}
		
		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		
		//Luu y: Can findTop1ByCodeAndWorkflowStatusOrderByApprovedDateAsc ra truoc khi approve request.
		Role originalRole = roleRepository.findTop1ByCodeAndWorkflowStatusOrderByApprovedDateDesc(request.getCode(),Status.APPROVED);
		
		/* Do can thiet dung origianlID de kiem qua quan he la version
		 * nen tat ca cac record ma co quan he version thi deu co cung originalID
		 * Vi the, first version record cung can gan originalId = ID cua chinh no
		 * Truoc khi approve
		 */
		if(request.getOriginalId() == null){ //la version dau tien
			request.setOriginalId(request.getId());
		}
		Role approvedRole = updateRoleInfo(request, Status.APPROVED);
		
		if (originalRole != null && approvedRole != null ){
			Long newId = approvedRole.getId();
			Long originalId = originalRole.getId();
			
			swapRole(newId,originalId);
			/*
			 * Sau khi swap thi can Get lai approvedRole voi Id của originalId
			 */
			approvedRole = roleRepository.findOne(originalId);
		}
		
		//now create role in primary database
		String restUrl = apiGatewayUrl + "/tellerapp/admin/api/role/approved/createinprimary";
		
		Role primaryRole = new Role();
		beanUtils.copyProperties(primaryRole, approvedRole);
		
		if (approvedRole.getOriginalId() != null ){
			primaryRole.setId(approvedRole.getOriginalId()); //this is important to update the right record in primary db
			primaryRole.setOriginalId(approvedRole.getId()); //to keep reference to primary record
		}
		
		System.out.println("++++++++++++++++start call approve primaryLimit++++++++");
		RestResponse<Role> restResponse = vn.fpt.dbp.vccb.core.rest.role.RoleService.cud(restUrl, primaryRole);
		if (restResponse.getStatus() != 0) {
			throw new Exception(restResponse.getErrorMessage());
		}
		RoleApprovedEvent event = new RoleApprovedEvent(primaryRole);
		eventBus.publish(asEventMessage(event));

		return approvedRole;
	}
	
	@Transactional
	public Role approveInPrimary(Role request) throws Exception {
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
			throw new Exception(DBPException.NO_APPROVER);
		}
		System.out.print("+++Start to run function approveInPrimary");
		
		Role approvedRole = updateRoleInfo(request, Status.APPROVED);
		
		System.out.print("+++End to run function approveInPrimary");
		
		return approvedRole;
	}
	
	@Transactional
	public Role reject(Role request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
			throw new Exception(DBPException.NO_APPROVER);
		}
		if(!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus())){
			 Exception e = new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			 throw e;
		}
		
		return updateRoleInfo(request, Status.REJECTED);
		
	}
	
	@Transactional
	public Role deleteSaveDraft(Role request) throws Exception {
		
		if(request.getId() == null)
		{
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
			
		}
		Role deletedRole = roleRepository.findOne(request.getId());
			
		if( ! Status.SAVE_DRAFT.equalsIgnoreCase(deletedRole.getWorkflowStatus())){
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			
		}
		
		roleRepository.delete(deletedRole);
		return new Role(deletedRole.getId());
	}

	/*
	 * Updata data and status of Role
	 */
	private Role updateRoleInfo(Role request, String status) throws Exception {
		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		
		Role role = null;
		
		if(request.getId() != null)
		{
			role = roleRepository.findOne(request.getId());
		}
		
		
		if (role != null) {
			
			for(RolePermission pm : role.getPermissions()){
				pm.setRole(null);
			}
			role.getPermissions().clear();
			
			//xoa cac comment cu
			for(Comment comment : role.getComments())
			{
				comment.setWorkFlowModel(null);
			}
			role.getComments().clear();
			
			beanUtils.copyProperties(role, request);
		} else {
			role = request;
		}
		
		if (role.getPermissions() != null && role.getPermissions().size() > 0 ) {
			for (RolePermission per : request.getPermissions()) {
				per.setRole(request);
			}
		}
		
		//set data comments
		if(role.getComments() != null && role.getComments().size() > 0)
		{
			for(Comment comment : role.getComments())
			{
				comment.setWorkFlowModel(role);
			}
		}
		
		role.setWorkflowStatus(status);
		
		return roleRepository.save(role);
	}
	
	private void swapRole(Long firstId, Long secondId) throws Exception {
		Role firstRole, secondRole;
		Role tempFirstRole, tempSecondRole, emptyRole;
		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		
		firstRole = roleRepository.findOne(firstId);
		secondRole = roleRepository.findOne(secondId);
		
		tempFirstRole = new Role();
		tempSecondRole = new Role();
		emptyRole = new Role();
		
		if (firstRole != null && secondRole != null){
			
			beanUtils.copyProperties(tempFirstRole, firstRole);
			beanUtils.copyProperties(tempSecondRole, secondRole);
			emptyRole.setId(firstId);
			
			//Xu ly cho firstRole
			tempFirstRole.setId(secondId);
			for(RolePermission firstRP : tempFirstRole.getPermissions()){
				firstRP.setRole(tempFirstRole);
			}
			for(Comment firstCM : tempFirstRole.getComments())
			{
				firstCM.setWorkFlowModel(tempFirstRole);
			}
			
			//Xu ly cho secondRole
			tempSecondRole.setId(firstId);
			for(RolePermission secondRP : tempSecondRole.getPermissions()){
				secondRP.setRole(tempSecondRole);
			}
			for(Comment secondCM : tempSecondRole.getComments())
			{
				secondCM.setWorkFlowModel(tempSecondRole);
			}
			/*
			 * http://stackoverflow.com/questions/20760403/how-to-swap-records-having-unique-constraint-in-hibernate
			 * 1. Update row A with null/empty data
			 * 2. Update row B with new data (from A data)
			 * 3. Update row A with new data (from B data)
			 */
			roleRepository.save(emptyRole);
			roleRepository.save(tempFirstRole);
			roleRepository.save(tempSecondRole);
		}
		
	}

}
