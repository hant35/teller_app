package vn.fpt.dbp.vccb.service.admin.service;

import static org.axonframework.domain.GenericEventMessage.asEventMessage;

import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.fpt.dbp.vccb.core.domain.base.Comment;
import vn.fpt.dbp.vccb.core.domain.user.RestrictUserBranch;
import vn.fpt.dbp.vccb.core.domain.user.RestrictUserCurrency;
import vn.fpt.dbp.vccb.core.domain.user.RestrictUserCustomerGroup;
import vn.fpt.dbp.vccb.core.domain.user.RestrictUserFunction;
import vn.fpt.dbp.vccb.core.domain.user.RestrictUserProduct;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.UserCurrency;
import vn.fpt.dbp.vccb.core.domain.user.UserCustomerGroup;
import vn.fpt.dbp.vccb.core.domain.user.UserLimitGroup;
import vn.fpt.dbp.vccb.core.domain.user.UserPermission;
import vn.fpt.dbp.vccb.core.domain.user.UserRole;
import vn.fpt.dbp.vccb.core.domain.user.UserTill;
import vn.fpt.dbp.vccb.core.domain.user.UserVault;
import vn.fpt.dbp.vccb.core.domain.user.event.PasswordChangedEvent;
import vn.fpt.dbp.vccb.core.domain.user.event.UserApprovedEvent;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.DBPException;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.RestResponse;

@Service
public class UserService {

	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;
	
	@Value("${spring.application.name}")
	private String serviceName;
	
	@Autowired
	private UserRepository userRepository;
	
	//@Autowired RolePermissionRepository permissionRepository;

	@Autowired
	private EventBus eventBus;

	@Transactional
	public User saveAsDraft(User request) throws Exception {
		
		User user = request.getId() == null ? null : userRepository.findOne(request.getId());
		
		if (user != null) {
		
			if(!Status.SAVE_DRAFT.equalsIgnoreCase(user.getWorkflowStatus()))
			{
				throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			}
		}

		//request.setWorkflowStatus(Status.SAVE_DRAFT);
		
		return updateUserInfo(request, Status.SAVE_DRAFT);
	}
	
	@Transactional
	public User sendForApprove(User request) throws Exception {
		
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
		
		return updateUserInfo(request, status);
		
	}
	
	@Transactional
	public User assign(User request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getAssignee() == null || request.getAssignee().getId() == null){
			throw new Exception(DBPException.NO_ASSIGNEE);
		}
		if(!Status.SEND_FOR_APPROVE.equalsIgnoreCase(request.getWorkflowStatus()) &&
				!Status.SAVE_DRAFT.equalsIgnoreCase(request.getWorkflowStatus()))
		{
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		//User temp = new User();
		//temp = userRepository.findOne(request.getId());
		return updateUserInfo(request, Status.ASSIGNED);
		
	}
	
	@Transactional
	public User returnUser(User request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		
		if(!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus()))
		{
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}

		User user = userRepository.findOne(request.getId());
		user.setAssignedDate(null);
		user.setAssignee(null);
		user.setWorkflowStatus(Status.SEND_FOR_APPROVE);
		
		return userRepository.save(user);
		//return updateUserInfo(request, Status.ASSIGNED);
		
	}
	
	@Transactional
	public User approve(User request) throws Exception {
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
			throw new Exception(DBPException.NO_APPROVER);
		}
		if(!Status.ASSIGNED.equalsIgnoreCase(request.getWorkflowStatus()))
		{
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
		}
		
		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		
		//Luu y: Can findTop1ByCodeAndWorkflowStatusOrderByApprovedDateAsc ra truoc khi approve request.
		
		User originalUser = userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(request.getUsername(),Status.APPROVED);
		
		/* Do can thiet dung origianlID de kiem qua quan he la version
		 * nen tat ca cac record ma co quan he version thi deu co cung originalID
		 * Vi the, first version record cung can gan originalId = ID cua chinh no
		 * Truoc khi approve
		 */
		if(request.getOriginalId() == null){ //la version dau tien
			request.setOriginalId(request.getId());
		}
		User approvedUser = updateUserInfo(request, Status.APPROVED);
		
		if (originalUser != null && approvedUser != null ){
			Long newId = approvedUser.getId();
			Long originalId = originalUser.getId();
			
			swapUser(newId,originalId);
			
			//Sau khi swap thi can Get lai approvedUser voi Id của originalId
			approvedUser = userRepository.findOne(originalId);
		}

		//now create user in primary database
		String restUrl = apiGatewayUrl + "/tellerapp/admin/api/user/approved/createinprimary";
		User primaryUser = new User();
			
		beanUtils.copyProperties(primaryUser, approvedUser);
			
		if(approvedUser.getOriginalId() != null){
			primaryUser.setId(approvedUser.getOriginalId()); //this is important to update the right record in primary db
			primaryUser.setOriginalId(approvedUser.getId()); //to keep reference to primary record
		}
			
		RestResponse<User> restResponse = vn.fpt.dbp.vccb.core.rest.user.UserService.cud(restUrl, primaryUser);
		if (restResponse.getStatus() != 0) {
				throw new Exception(restResponse.getErrorMessage());
		}
			
		UserApprovedEvent event = new UserApprovedEvent(primaryUser);
		eventBus.publish(asEventMessage(event));
			
		return approvedUser;
	}
	
	@Transactional
	public User approveInPrimary(User request) throws Exception {
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
			throw new Exception(DBPException.NO_APPROVER);
		}
		
		User approvedUser = updateUserInfo(request, Status.APPROVED);
		
		
		return approvedUser;
	}
	
	@Transactional
	public User reject(User request) throws Exception {
		
		if (request.getId() == null) {
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
		}
		
		if (request.getApprovedBy() == null || request.getApprovedBy().getId() == null){
			throw new Exception(DBPException.NO_APPROVER);
		}
		
		return updateUserInfo(request, Status.REJECTED);
		
	}
	
	@Transactional
	public User deleteSaveDraft(User request) throws Exception {
		
		if(request.getId() == null)
		{
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
			
		}
		
		User deletedUser = userRepository.findOne(request.getId());
			
		if( ! Status.SAVE_DRAFT.equalsIgnoreCase(deletedUser.getWorkflowStatus())){
			throw new Exception(DBPException.WRONG_WORKFLOW_STATUS);
			
		}
		
		userRepository.delete(deletedUser);
		return new User(deletedUser.getId());
	}
	
	@Transactional
	public User resetPassword(User request) throws Exception {
		
		if(request.getId() == null)
		{
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
			
		}
		User updateUser = updateUserInfo(request,request.getWorkflowStatus());

		PasswordChangedEvent event = new PasswordChangedEvent(updateUser);
		eventBus.publish(asEventMessage(event));
		
		return updateUser;
	}
	
	@Transactional
	public User changePassword(User request) throws Exception {
		
		if(request.getId() == null)
		{
			throw new Exception(DBPException.RECORD_NOT_EXISTED);
			
		}
		User updateUser = updateUserInfo(request,request.getWorkflowStatus());

		PasswordChangedEvent event = new PasswordChangedEvent(updateUser);
		eventBus.publish(asEventMessage(event));
		
		return updateUser;
	}

	/*
	 * Update data and status of User
	 */
	private User updateUserInfo(User request, String status) throws Exception {
		
		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		User user =null;
		
		//System.out.println("*******Start to updateUserInfo ********");
		
		if(request.getId() != null)
		{
			//System.out.println("*******request.getId() =  "+ request.getId());
			user = userRepository.findOne(request.getId());	
		}
		
		if (user != null) {
			//System.out.println("*******Start to remove old data of user ");
			//remove old data
			if(user.getComments() != null && user.getComments().size() > 0){
				for(Comment comment : user.getComments())
				{
					comment.setWorkFlowModel(null);
				}
				user.getComments().clear();
			}
			
			if(user.getUserTills() != null && user.getUserTills().size() > 0){
				for(UserTill userTill : user.getUserTills())
				{
					userTill.setUser(null);
				}
				user.getUserTills().clear();
			}
			
			if(user.getUserVaults() != null && user.getUserVaults().size() > 0){
				for(UserVault userVault : user.getUserVaults())
				{
					userVault.setUser(null);
				}
				user.getUserVaults().clear();
			}
			
			if(user.getUserRoles() != null && user.getUserRoles().size() > 0)
			{
				for(UserRole userRole : user.getUserRoles())
				{
					userRole.setUser(null);
				}
				user.getUserRoles().clear();
			}
			
			if(user.getUserPermission() != null && user.getUserPermission().size() > 0)
			{
				for(UserPermission p : user.getUserPermission())
				{
					p.setUser(null);
				}
				user.getUserPermission().clear();
			}
			
			if(user.getUserCurrency() != null && user.getUserCurrency().size() > 0)
			{
				for(UserCurrency p : user.getUserCurrency())
				{
					p.setUser(null);
				}
				user.getUserCurrency().clear();
			}
			
			if(user.getUserCustomerGroup() != null && user.getUserCustomerGroup().size() > 0)
			{
				for(UserCustomerGroup p : user.getUserCustomerGroup())
				{
					p.setUser(null);
				}
				user.getUserCustomerGroup().clear();
			}
			
			if(user.getUserLimitGroup() != null && user.getUserLimitGroup().size() > 0)
			{
				for(UserLimitGroup p : user.getUserLimitGroup())
				{
					p.setUser(null);
				}
				user.getUserLimitGroup().clear();
			}
			
			if(user.getRestrictUserBranch() != null && user.getRestrictUserBranch().size() > 0)
			{
				for(RestrictUserBranch p : user.getRestrictUserBranch())
				{
					p.setUser(null);
				}
				user.getRestrictUserBranch().clear();
			}
			
			if(user.getRestrictUserCurrency() != null && user.getRestrictUserCurrency().size() > 0)
			{
				for(RestrictUserCurrency p : user.getRestrictUserCurrency())
				{
					p.setUser(null);
				}
				user.getRestrictUserCurrency().clear();
			}
			
			if(user.getRestrictUserCustomerGroup() != null && user.getRestrictUserCustomerGroup().size() > 0)
			{
				for(RestrictUserCustomerGroup p : user.getRestrictUserCustomerGroup())
				{
					p.setUser(null);
				}
				user.getRestrictUserCustomerGroup().clear();
			}
			
			if(user.getRestrictUserFunction() != null && user.getRestrictUserFunction().size() > 0)
			{
				for(RestrictUserFunction p : user.getRestrictUserFunction())
				{
					p.setUser(null);
				}
				user.getRestrictUserFunction().clear();
			}
			
			if(user.getRestrictUserProduct() != null && user.getRestrictUserProduct().size() > 0)
			{
				for(RestrictUserProduct p : user.getRestrictUserProduct())
				{
					p.setUser(null);
				}
				user.getRestrictUserProduct().clear();
			}
			//System.out.println("*******End to remove old data of user *****");
			//System.out.println("*******Start to copy data of user *****");
			//System.out.println("*********** request = " + request);
			//System.out.println("*********** user = " + user);
			try{
				beanUtils.copyProperties(user, request);
			}catch (Exception e){
				System.out.println("++++++ Error = " + e.getMessage());
				e.printStackTrace();
				throw e;
			}
			
			//System.out.println("*******End to copy data of user *****");
			
		} else {
			user = request;
		}
		
		//System.out.println("*******Start to add new data of user *******");
		
		//set data comments
		if(user.getComments() != null && user.getComments().size() > 0)
		{
			for(Comment comment : user.getComments())
			{
				comment.setWorkFlowModel(user);
			}
		}
		
		if(user.getUserTills() != null && user.getUserTills().size() > 0){
			for(UserTill userTill : user.getUserTills())
			{
				userTill.setUser(user);
			}
		}
		
		if(user.getUserVaults() != null && user.getUserVaults().size() > 0){
			for(UserVault userVault : user.getUserVaults())
			{
				userVault.setUser(user);
			}
		}
		
		if(user.getUserRoles() != null && user.getUserRoles().size() > 0)
		{
			//System.out.println("*******user.getUserRoles() =  "+ user.getUserRoles().size());
			for(UserRole userRole : user.getUserRoles())
			{
				userRole.setUser(user);
			}
		}
		
		if(user.getUserPermission() != null && user.getUserPermission().size() > 0)
		{
			for(UserPermission p : user.getUserPermission())
			{
				p.setUser(user);
			}
		}
		
		if(user.getUserCurrency() != null && user.getUserCurrency().size() > 0)
		{
			for(UserCurrency p : user.getUserCurrency())
			{
				p.setUser(user);
			}
		}
		
		if(user.getUserCustomerGroup() != null && user.getUserCustomerGroup().size() > 0)
		{
			for(UserCustomerGroup p : user.getUserCustomerGroup())
			{
				p.setUser(user);
			}
		}
		
		if(user.getUserLimitGroup() != null && user.getUserLimitGroup().size() > 0)
		{
			for(UserLimitGroup p : user.getUserLimitGroup())
			{
				p.setUser(user);
			}
		}
		
		if(user.getRestrictUserBranch() != null && user.getRestrictUserBranch().size() > 0)
		{
			for(RestrictUserBranch p : user.getRestrictUserBranch())
			{
				p.setUser(user);
			}
		}
		
		if(user.getRestrictUserCurrency() != null && user.getRestrictUserCurrency().size() > 0)
		{
			for(RestrictUserCurrency p : user.getRestrictUserCurrency())
			{
				p.setUser(user);
			}
		}
		
		if(user.getRestrictUserCustomerGroup() != null && user.getRestrictUserCustomerGroup().size() > 0)
		{
			for(RestrictUserCustomerGroup p : user.getRestrictUserCustomerGroup())
			{
				p.setUser(user);
			}
		}
		
		if(user.getRestrictUserFunction() != null && user.getRestrictUserFunction().size() > 0)
		{
			for(RestrictUserFunction p : user.getRestrictUserFunction())
			{
				p.setUser(user);
			}
		}
		
		if(user.getRestrictUserProduct() != null && user.getRestrictUserProduct().size() > 0)
		{
			for(RestrictUserProduct p : user.getRestrictUserProduct())
			{
				p.setUser(user);
			}
		}
		//System.out.println("*******End to add new data of user *******");
		user.setWorkflowStatus(status);
		
		//System.out.println("*******End to updateUserInfo ********");

		return userRepository.save(user);
	}
	
	private void swapUser(Long firstId, Long secondId) throws Exception {
		User firstUser, secondUser;
		User tempFirstUser, tempSecondUser, emptyUser;
		NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
		
		firstUser = userRepository.findOne(firstId);
		secondUser = userRepository.findOne(secondId);
		
		tempFirstUser = new User();
		tempSecondUser = new User();
		emptyUser = new User();
		
		if (firstUser != null && secondUser != null){
			
			beanUtils.copyProperties(tempFirstUser, firstUser);
			beanUtils.copyProperties(tempSecondUser, secondUser);
			emptyUser.setId(firstId);
			
			//Xu ly cho firstUser
			tempFirstUser.setId(secondId);
			if(tempFirstUser.getComments() != null && tempFirstUser.getComments().size() > 0)
			{
				for(Comment comment : tempFirstUser.getComments())
				{
					comment.setWorkFlowModel(tempFirstUser);
				}
			}
			
			if(tempFirstUser.getUserTills() != null && tempFirstUser.getUserTills().size() > 0){
				for(UserTill userTill : tempFirstUser.getUserTills())
				{
					userTill.setUser(tempFirstUser);
				}
			}
			
			if(tempFirstUser.getUserVaults() != null && tempFirstUser.getUserVaults().size() > 0){
				for(UserVault userVault : tempFirstUser.getUserVaults())
				{
					userVault.setUser(tempFirstUser);
				}
			}
			
			if(tempFirstUser.getUserRoles() != null && tempFirstUser.getUserRoles().size() > 0)
			{
				for(UserRole userRole : tempFirstUser.getUserRoles())
				{
					userRole.setUser(tempFirstUser);
				}
			}
			
			if(tempFirstUser.getUserPermission() != null && tempFirstUser.getUserPermission().size() > 0)
			{
				for(UserPermission p : tempFirstUser.getUserPermission())
				{
					p.setUser(tempFirstUser);
				}
			}
			
			if(tempFirstUser.getUserCurrency() != null && tempFirstUser.getUserCurrency().size() > 0)
			{
				for(UserCurrency p : tempFirstUser.getUserCurrency())
				{
					p.setUser(tempFirstUser);
				}
			}
			
			if(tempFirstUser.getUserCustomerGroup() != null && tempFirstUser.getUserCustomerGroup().size() > 0)
			{
				for(UserCustomerGroup p : tempFirstUser.getUserCustomerGroup())
				{
					p.setUser(tempFirstUser);
				}
			}
			
			if(tempFirstUser.getUserLimitGroup() != null && tempFirstUser.getUserLimitGroup().size() > 0)
			{
				for(UserLimitGroup p : tempFirstUser.getUserLimitGroup())
				{
					p.setUser(tempFirstUser);
				}
			}
			
			if(tempFirstUser.getRestrictUserBranch() != null && tempFirstUser.getRestrictUserBranch().size() > 0)
			{
				for(RestrictUserBranch p : tempFirstUser.getRestrictUserBranch())
				{
					p.setUser(tempFirstUser);
				}
			}
			
			if(tempFirstUser.getRestrictUserCurrency() != null && tempFirstUser.getRestrictUserCurrency().size() > 0)
			{
				for(RestrictUserCurrency p : tempFirstUser.getRestrictUserCurrency())
				{
					p.setUser(tempFirstUser);
				}
			}
			
			if(tempFirstUser.getRestrictUserCustomerGroup() != null && tempFirstUser.getRestrictUserCustomerGroup().size() > 0)
			{
				for(RestrictUserCustomerGroup p : tempFirstUser.getRestrictUserCustomerGroup())
				{
					p.setUser(tempFirstUser);
				}
			}
			
			if(tempFirstUser.getRestrictUserFunction() != null && tempFirstUser.getRestrictUserFunction().size() > 0)
			{
				for(RestrictUserFunction p : tempFirstUser.getRestrictUserFunction())
				{
					p.setUser(tempFirstUser);
				}
			}
			
			if(tempFirstUser.getRestrictUserProduct() != null && tempFirstUser.getRestrictUserProduct().size() > 0)
			{
				for(RestrictUserProduct p : tempFirstUser.getRestrictUserProduct())
				{
					p.setUser(tempFirstUser);
				}
			}
			
			//Xu ly cho secondUser
			tempSecondUser.setId(firstId);
			if(tempSecondUser.getComments() != null && tempSecondUser.getComments().size() > 0)
			{
				for(Comment comment : tempSecondUser.getComments())
				{
					comment.setWorkFlowModel(tempSecondUser);
				}
			}
			
			if(tempSecondUser.getUserTills() != null && tempSecondUser.getUserTills().size() > 0){
				for(UserTill userTill : tempSecondUser.getUserTills())
				{
					userTill.setUser(tempSecondUser);
				}
			}
			
			if(tempSecondUser.getUserVaults() != null && tempSecondUser.getUserVaults().size() > 0){
				for(UserVault userVault : tempSecondUser.getUserVaults())
				{
					userVault.setUser(tempSecondUser);
				}
			}
			
			if(tempSecondUser.getUserRoles() != null && tempSecondUser.getUserRoles().size() > 0)
			{
				for(UserRole userRole : tempSecondUser.getUserRoles())
				{
					userRole.setUser(tempSecondUser);
				}
			}
			
			if(tempSecondUser.getUserPermission() != null && tempSecondUser.getUserPermission().size() > 0)
			{
				for(UserPermission p : tempSecondUser.getUserPermission())
				{
					p.setUser(tempSecondUser);
				}
			}
			
			if(tempSecondUser.getUserCurrency() != null && tempSecondUser.getUserCurrency().size() > 0)
			{
				for(UserCurrency p : tempSecondUser.getUserCurrency())
				{
					p.setUser(tempSecondUser);
				}
			}
			
			if(tempSecondUser.getUserCustomerGroup() != null && tempSecondUser.getUserCustomerGroup().size() > 0)
			{
				for(UserCustomerGroup p : tempSecondUser.getUserCustomerGroup())
				{
					p.setUser(tempSecondUser);
				}
			}
			
			if(tempSecondUser.getUserLimitGroup() != null && tempSecondUser.getUserLimitGroup().size() > 0)
			{
				for(UserLimitGroup p : tempSecondUser.getUserLimitGroup())
				{
					p.setUser(tempSecondUser);
				}
			}
			
			if(tempSecondUser.getRestrictUserBranch() != null && tempSecondUser.getRestrictUserBranch().size() > 0)
			{
				for(RestrictUserBranch p : tempSecondUser.getRestrictUserBranch())
				{
					p.setUser(tempSecondUser);
				}
			}
			
			if(tempSecondUser.getRestrictUserCurrency() != null && tempSecondUser.getRestrictUserCurrency().size() > 0)
			{
				for(RestrictUserCurrency p : tempSecondUser.getRestrictUserCurrency())
				{
					p.setUser(tempSecondUser);
				}
			}
			
			if(tempSecondUser.getRestrictUserCustomerGroup() != null && tempSecondUser.getRestrictUserCustomerGroup().size() > 0)
			{
				for(RestrictUserCustomerGroup p : tempSecondUser.getRestrictUserCustomerGroup())
				{
					p.setUser(tempSecondUser);
				}
			}
			
			if(tempSecondUser.getRestrictUserFunction() != null && tempSecondUser.getRestrictUserFunction().size() > 0)
			{
				for(RestrictUserFunction p : tempSecondUser.getRestrictUserFunction())
				{
					p.setUser(tempSecondUser);
				}
			}
			
			if(tempSecondUser.getRestrictUserProduct() != null && tempSecondUser.getRestrictUserProduct().size() > 0)
			{
				for(RestrictUserProduct p : tempSecondUser.getRestrictUserProduct())
				{
					p.setUser(tempSecondUser);
				}
			}
			
			/*
			 * http://stackoverflow.com/questions/20760403/how-to-swap-records-having-unique-constraint-in-hibernate
			 * 1. Update row A with null/empty data
			 * 2. Update row B with new data (from A data)
			 * 3. Update row A with new data (from B data)
			 */
			userRepository.save(emptyUser);
			userRepository.save(tempFirstUser);
			userRepository.save(tempSecondUser);
		}
	}

}
