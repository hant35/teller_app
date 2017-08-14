package vn.fpt.dbp.vccb.service.admin.rest;

/*
 * tich hop osb 
 */
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
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
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.limit.Limit;
import vn.fpt.dbp.vccb.core.domain.limit.LimitGroup;
import vn.fpt.dbp.vccb.core.domain.limit.repository.LimitGroupRepository;
import vn.fpt.dbp.vccb.core.domain.limit.repository.LimitRepository;
import vn.fpt.dbp.vccb.core.domain.role.Role;
import vn.fpt.dbp.vccb.core.domain.role.repository.RoleRepository;
import vn.fpt.dbp.vccb.core.domain.user.QUser;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.UserLimitGroup;
import vn.fpt.dbp.vccb.core.domain.user.UserRole;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.Permissions;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.dbp.vccb.core.util.TransactionCodeGenerator;
import vn.fpt.dbp.vccb.service.admin.service.UserService;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private LimitRepository limitRepository;
	
	@Autowired
	private LimitGroupRepository limitGroupRepository;

	@Value("${dbp.osb-gateway-url}")
	private String OSBGateway;
	
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";

	@Value("${spring.application.name}")
	private String serviceName;

	private boolean isPrimary() {
		return "digital-banking-vccb-service-admin".equals(serviceName);
	}
	
	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public User userInformation(Principal principle, @PathVariable("id") Long id) {
		User user = userRepository.findOne(id);
		return user;
	}

	/*
	 * @CrossOrigin(origins = "*")
	 * 
	 * @RequestMapping(value = "/api/user/username/{username}", method =
	 * RequestMethod.GET, produces = "application/json") public List<User>
	 * findUserByUserName(Principal principle, @PathVariable("username") String
	 * username){ //User user =
	 * userRepository.findByUsernameAndWorkflowStatus(username,
	 * Status.APPROVED); List<User> users =
	 * userRepository.findByUsername(username); return users; }
	 */
	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/username/{username}", method = RequestMethod.GET, produces = "application/json")
	public User findUserByUserName(Principal principle, @PathVariable("username") String username) {
		User user = new User();
		user = userRepository.findByUsernameAndWorkflowStatus(username, Status.APPROVED);
		return user;
	}
	
	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/email/{email}", method = RequestMethod.GET, produces = "application/json")
	public List<User> findUserByEmail(Principal principle, @PathVariable("email") String email) {
		//User user = new User();
		QUser quser = QUser.user;
		//user = userRepository.findByUsernameAndWorkflowStatus(username, Status.APPROVED);
		return (List<User>) userRepository.findAll(quser.email.eq(email));
		//return user;
	}

	@Secured({ "USER_VIEW", "USER_ADD", "USER_APPROVE" })
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/getapprovallist", method = RequestMethod.GET, produces = "application/json")
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
			if (u.hasPermission("USER", Permissions.APPROVE)) {
				approvalList.add(u);
			}
		}

		return approvalList;
	}

	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<User> searchUser(Principal principle, @RequestBody User user, Pageable pageable) {
		QUser quser = QUser.user;
		BooleanExpression booleanExpression = quser.username.like("%");

		if (StringUtils.isNotEmpty(user.getCifCode())) {
			booleanExpression = booleanExpression.and(quser.cifCode.like(user.getCifCode()));
		}
		if (StringUtils.isNotEmpty(user.getEmployeeCode())) {
			booleanExpression = booleanExpression.and(quser.employeeCode.like(user.getEmployeeCode()));
		}
		if (StringUtils.isNotEmpty(user.getUsername())) {
			booleanExpression = booleanExpression.and(quser.username.like(user.getUsername()));
		}
		if (StringUtils.isNotEmpty(user.getCustomerName())) {
			booleanExpression = booleanExpression.and(quser.customerName.like(user.getCustomerName()));
		}
		if (user.getBranch() != null) {
			booleanExpression = booleanExpression.and(quser.branch.eq(user.getBranch()));
		}
		if (StringUtils.isNotEmpty(user.getEmail())) {
			booleanExpression = booleanExpression.and(quser.email.eq(user.getEmail()));
		}
		// --- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (user.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(quser.processInstanceId.eq(user.getProcessInstanceId()));
		}
		if (user.getTaskId() != null) {
			booleanExpression = booleanExpression.and(quser.taskId.eq(user.getTaskId()));
		}

		Page<User> result = userRepository.findAll(booleanExpression, pageable);

		return new PagedResource<User>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());

	}
	
	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")	
	@RequestMapping(value = "/api/user/checkexisted", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<User> checkExistedUser(Principal principle,@RequestBody User user,Pageable pageable){
		List<User> users = new ArrayList<User>();
		String restUrl;
		String username, email;
		Page<User> resultPage;
		username = user.getUsername();
		email = user.getEmail();
		
		try{
			if(isPrimary()){
				restUrl = apiGatewayUrl + "/tellerapp/admin_his/api/user/checkexisted";
				return vn.fpt.dbp.vccb.core.rest.user.UserService.search(restUrl,user);
				
			}else{
				QUser quser = QUser.user;
				//1. In History, find user  except workFlowStatus = SAVE_DRAFT, REJECTED, APPROVED
				BooleanExpression booleanExpression = quser.username.like("%");
				
				if(StringUtils.isNotEmpty(username)){
					booleanExpression = booleanExpression.and(quser.username.equalsIgnoreCase(username));
				}
				if(StringUtils.isNotEmpty(email)){
					booleanExpression = booleanExpression.and(quser.email.equalsIgnoreCase(email));
				}
				booleanExpression = booleanExpression.and(quser.workflowStatus.notEqualsIgnoreCase(Status.SAVE_DRAFT));
				booleanExpression = booleanExpression.and(quser.workflowStatus.notEqualsIgnoreCase(Status.REJECTED));
				booleanExpression = booleanExpression.and(quser.workflowStatus.notEqualsIgnoreCase(Status.APPROVED));
				List<User> userHis = (List<User>) userRepository.findAll(booleanExpression);
				//2. In Primary, find user by username & email
				List<User> userPri1 = null;
				List<User> userPri2 = null;
				
				if(StringUtils.isNotEmpty(username)){
					restUrl = apiGatewayUrl + "/tellerapp/admin/api/user/username/" + username;
					userPri1 = vn.fpt.dbp.vccb.core.rest.user.UserService.findList(restUrl);
				}
				
				if(StringUtils.isNotEmpty(email)){
					restUrl = apiGatewayUrl + "/tellerapp/admin/api/user/email/" + email;
					userPri2 = vn.fpt.dbp.vccb.core.rest.user.UserService.findList(restUrl);
				}
				if(userHis != null){
					users.addAll(userHis);

				}
				
				if(userPri1 != null){
					users.addAll(userPri1);
				}
				if(userPri2 != null){
					users.addAll(userPri2);
				}
				
				//3. Core, check username existed trong core
				if(StringUtils.isNotEmpty(username)){
					QName SERVICE_NAME = new QName("http://www.alsb.com/", "WebService");
					URL wsdlURL = new URL(OSBGateway);
					com.alsb.WebService_Service ss = new com.alsb.WebService_Service(wsdlURL, SERVICE_NAME);
					com.alsb.WebService port = ss.getWebService();
					
					String result = port.coreUsernameVerify(username,"","");
					if(!"0".equals(result) && !"00".equals(result)){ // da co ton tai user nay tren CORE
						User tmpUser = new User();
						tmpUser.setUsername(username);
						users.add(tmpUser);
					}
				
				}
				
			}
			if(users.size() < 1){
				return new PagedResource<User>();
			}
			
			if (users == null || users.size() < 1) {
				return new PagedResource<User>();
			} else {
				resultPage = new PageImpl<User>(users, pageable, users.size());
			}

			return new PagedResource<User>(resultPage.getContent(), resultPage.getNumber(), resultPage.getSize(),
					resultPage.getTotalPages(), resultPage.getTotalElements());
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")	
	@RequestMapping(value = "/api/user/find", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<User> findUser(Principal principle, @RequestBody User user, Pageable pageable){
		User createdUser = userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(principle.getName(), Status.APPROVED);
		
		if(user!=null){
			user.setCreatedBy(createdUser);
		}
		
		return userRepository.searchLastedVersion(user, pageable);
	}

	/*
	 * @Secured("USER_VIEW")
	 * 
	 * @CrossOrigin(origins = "*")
	 * 
	 * @RequestMapping(value = "/api/user/search/role", method =
	 * RequestMethod.POST, produces = "application/json") public
	 * PagedResource<User> findUserOfRole(Principal principle, @RequestBody User
	 * user, Pageable pageable) { QUser qUser = QUser.user; Page<User> result =
	 * null;
	 * 
	 * if (user.getUserRoles() != null){ for(UserRole
	 * userRole:user.getUserRoles()){ if(userRole.getRole()!=null){
	 * 
	 * Role role=userRole.getRole(); Role roleQuery = new Role();
	 * 
	 * if(role.getId()!=null){ roleQuery = roleRepository.findOne(role.getId());
	 * 
	 * }else if(StringUtils.isNotEmpty(role.getCode())){
	 * 
	 * roleQuery =
	 * roleRepository.findTop1ByCodeAndWorkflowStatusOrderByApprovedDateAsc(role
	 * .getCode(), Status.APPROVED); }
	 * 
	 * if(roleQuery != null){ //need to fix this. Should be add result into
	 * toatalResult. result =
	 * userRepository.findAll(qUser.userRoles.any().role.eq(roleQuery),pageable)
	 * ; } //result =
	 * userRepository.findAll(qUser.userRoles.any().role.eq(roleQuery),pageable)
	 * ; } } }
	 * 
	 * if (result == null){ return new PagedResource<User>(); } return new
	 * PagedResource<User>(result.getContent(), result.getNumber(),
	 * result.getSize(), result.getTotalPages(),result.getTotalElements()); }
	 */
	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/search/role", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<User> findUserOfRole(Principal principle, @RequestBody User user, Pageable pageable) {
		QUser qUser = QUser.user;
		Page<User> resultPage = null;
		List<User> totalResult = new ArrayList<User>();
		if (user.getUserRoles() != null) {
			for (UserRole userRole : user.getUserRoles()) {
				if (userRole.getRole() != null) {

					Role role = userRole.getRole();
					Role roleQuery = new Role();

					if (role.getId() != null) {
						roleQuery = roleRepository.findOne(role.getId());

					} else if (StringUtils.isNotEmpty(role.getCode())) {

						roleQuery = roleRepository.findTop1ByCodeAndWorkflowStatusOrderByApprovedDateDesc(role.getCode(),
								Status.APPROVED);
					}

					if (roleQuery != null) {

						List<User> result = (List<User>) userRepository
								.findAll(qUser.userRoles.any().role.eq(roleQuery));
						totalResult.addAll(result);
					}

				}
			}
		}

		if (totalResult == null || totalResult.size() < 1) {
			return new PagedResource<User>();
		} else {
			resultPage = new PageImpl<User>(totalResult, pageable, totalResult.size());
		}
		//testing paging from list data of osb
//		int fromIndex, toIndex;
//		fromIndex = Math.max(0,pageable.getPageNumber() * pageable.getPageSize());
//		toIndex = Math.min(totalResult.size(),(pageable.getPageNumber() + 1) * pageable.getPageSize());
//		
//		System.out.println("fromIndex = " + fromIndex);
//		System.out.println("toIndex = " + toIndex);

		//return new PagedResource<User>(totalResult.subList(fromIndex, toIndex), resultPage.getNumber(), resultPage.getSize(),
		//		resultPage.getTotalPages(), resultPage.getTotalElements());
		return new PagedResource<User>(resultPage.getContent(), resultPage.getNumber(), resultPage.getSize(),
						resultPage.getTotalPages(), resultPage.getTotalElements());
	}
	
	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/search/limit", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<User> findUserOfLimit(Principal principle, @RequestBody User user, Pageable pageable) {
		QUser qUser = QUser.user;
		Page<User> resultPage = null;
		List<User> totalResult = new ArrayList<User>();
		
		if (user.getUserLimitGroup() != null) {
			logger.warn("[UserController]: user.getUserLimitGroup() = " + user.getUserLimitGroup());
			System.out.println("[UserController]: user.getUserLimitGroup() = " + user.getUserLimitGroup());
			for (UserLimitGroup userLimitGroup : user.getUserLimitGroup()) {
				
				if (userLimitGroup.getLimitGroup() != null) {
					LimitGroup limitGroup = userLimitGroup.getLimitGroup();
					logger.info("[UserController]: userLimitGroup.getLimitGroup() = " + userLimitGroup.getLimitGroup());
					System.out.println("[UserController]: userLimitGroup.getLimitGroup() = " + userLimitGroup.getLimitGroup());
					
					if(limitGroup != null){
						for(Limit limit : limitGroup.getLimits()){
							Limit limitQuery = new Limit();
							logger.info("[UserController]: limit.getId() = " + limit.getId());
							logger.info("[UserController]: limit.getCode() = " + limit.getCode());
							System.out.println("[UserController]: limit.getId() = " + limit.getId());
							System.out.println("[UserController]: limit.getCode() = " + limit.getCode());
							
							if(limit.getId() != null){
								limitQuery = limitRepository.findOne(limit.getId());
								
							}else if(StringUtils.isNotEmpty(limit.getCode())){
								limitQuery = limitRepository.findTop1ByCodeAndWorkflowStatusOrderByApprovedDateDesc(limit.getCode(), Status.APPROVED);
							}
							
							if(limitQuery!=null){
								logger.info("[UserController]: limitQuery = " + limitQuery);
								System.out.println("[UserController]: limitQuery = " + limitQuery);
								//List<User> result = (List<User>) userRepository
								//		.findAll(qUser.userLimitGroup.any().limitGroup.limits.any().limit.eq(limitQuery));
								
								List<User> result = (List<User>) userRepository.findAll(qUser.userLimitGroup.any().limitGroup.limits.any().id.eq(limitQuery.getId()));
										
								logger.info("[UserController]: result = " + result);
								System.out.println("[UserController]: result = " + result);
								totalResult.addAll(result);
							}
						}
					}
				}
			}
		}

		if (totalResult == null || totalResult.size() < 1) {
			return new PagedResource<User>();
		} else {
			resultPage = new PageImpl<User>(totalResult, pageable, totalResult.size());
		}
		logger.warn("[UserController]: resultPage = " + resultPage);
		return new PagedResource<User>(resultPage.getContent(), resultPage.getNumber(), resultPage.getSize(),
				resultPage.getTotalPages(), resultPage.getTotalElements());
	}
	
	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/search/limitgroup", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<User> findUserOfLimitGroup(Principal principle, @RequestBody User user, Pageable pageable) {
		QUser qUser = QUser.user;
		Page<User> resultPage = null;
		List<User> totalResult = new ArrayList<User>();
		
		if (user.getUserLimitGroup() != null) {
			
			for (UserLimitGroup userLimitGroup : user.getUserLimitGroup()) {
				
				if (userLimitGroup.getLimitGroup() != null) {
					LimitGroup limitGroup = userLimitGroup.getLimitGroup();
					
					if(limitGroup != null){
						
						LimitGroup limitGroupQuery = new LimitGroup();
						
						if(limitGroup.getId() != null){
							limitGroupQuery = limitGroupRepository.findOne(limitGroup.getId());
						}
						if(StringUtils.isNotEmpty(limitGroup.getCode())){
							limitGroupQuery = limitGroupRepository.findTop1ByCodeAndWorkflowStatusOrderByApprovedDateDesc(limitGroup.getCode(), Status.APPROVED);
						}
						
						if(limitGroupQuery != null || limitGroupQuery.getId() !=null){
							List<User> result = (List<User>) userRepository.findAll(qUser.userLimitGroup.any().limitGroup.id.eq(limitGroupQuery.getId()));
							
							totalResult.addAll(result);
						}
					}
				}
			}
		}

		if (totalResult == null || totalResult.size() < 1) {
			return new PagedResource<User>();
		} else {
			resultPage = new PageImpl<User>(totalResult, pageable, totalResult.size());
		}

		return new PagedResource<User>(resultPage.getContent(), resultPage.getNumber(), resultPage.getSize(),
				resultPage.getTotalPages(), resultPage.getTotalElements());
	}

	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/search/employee", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<User> findEmployeeByUser(Principal principle, @RequestBody User user, Pageable pageable) {

		String employeeCode = null;
		String employeeName = null;
		String employeeEmail = null;
		String employeeUserName = null;

		Page<User> resultPage = null;
		List<User> users = new ArrayList<User>();

		try {
			QName SERVICE_NAME = new QName("http://www.alsb.com/", "WebService");
			URL wsdlURL = new URL(OSBGateway);
			com.alsb.WebService_Service ss = new com.alsb.WebService_Service(wsdlURL, SERVICE_NAME);
			com.alsb.WebService port = ss.getWebService();

			if (StringUtils.isNotEmpty(user.getEmployeeCode())) {
				employeeCode = user.getEmployeeCode();
			}

			if (StringUtils.isNotEmpty(user.getEmployeeName())) {
				employeeName = user.getEmployeeName();
			}

			if (StringUtils.isNotEmpty(user.getUsername())) {
				employeeUserName = user.getUsername();
			}

			if (StringUtils.isNotEmpty(user.getEmail())) {
				employeeEmail = user.getEmail();
			}

			com.alsb.LDAPUserSearchResponse.UserList ldapUserSearch_return = port.ldapUserSearch(employeeCode,
					employeeName, employeeUserName, employeeEmail);

			for (com.alsb.LDAPUserSearchResponse.UserList.User item : ldapUserSearch_return.getUser()) {
				User tempUser = new User();

				tempUser.setEmployeeCode(item.getUserID());
				tempUser.setEmployeeName(item.getName());
				tempUser.setUsername(item.getUsername());
				tempUser.setEmail(item.getEmail());
				tempUser.setPhone(item.getPhoneNumber());

				users.add(tempUser);
			}

			if (users == null || users.size() < 1) {
				return new PagedResource<User>();
			} else {
				resultPage = new PageImpl<User>(users, pageable, users.size());
			}
			
			int fromIndex, toIndex;
			fromIndex = Math.max(0,pageable.getPageNumber() * pageable.getPageSize());
			toIndex = Math.min(users.size(),(pageable.getPageNumber() + 1) * pageable.getPageSize());
			
			logger.warn("fromIndex = " + fromIndex);
			logger.warn("toIndex = " + toIndex);
			System.out.println("fromIndex = " + fromIndex);
			System.out.println("toIndex = " + toIndex);
			
			return new PagedResource<User>(users.subList(fromIndex, toIndex), resultPage.getNumber(), resultPage.getSize(),
					resultPage.getTotalPages(), resultPage.getTotalElements());

			//return new PagedResource<User>(resultPage.getContent(), resultPage.getNumber(), resultPage.getSize(),
			//		resultPage.getTotalPages(), resultPage.getTotalElements());
		} catch (Exception e) {
			logger.error("Error at service /api/user/search/employee : " + e.getMessage());
			return null;
		}
	}

	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/savedraft/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<User> searchSaveDraftUser(Principal principle, @RequestBody User user, Pageable pageable) {
		QUser quser = QUser.user;
		BooleanExpression booleanExpression = quser.workflowStatus.eq(Status.SAVE_DRAFT);

		if (StringUtils.isNotEmpty(user.getCifCode())) {
			booleanExpression = booleanExpression.and(quser.cifCode.like(user.getCifCode()));
		}
		if (StringUtils.isNotEmpty(user.getEmployeeCode())) {
			booleanExpression = booleanExpression
					.and(quser.employeeCode.upper().like(user.getEmployeeCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(user.getUsername())) {
			booleanExpression = booleanExpression.and(quser.username.upper().like(user.getUsername().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(user.getCustomerName())) {
			booleanExpression = booleanExpression
					.and(quser.customerName.upper().like(user.getCustomerName().toUpperCase()));
		}
		if (user.getBranch() != null) {
			booleanExpression = booleanExpression.and(quser.branch.eq(user.getBranch()));
		}
		// --- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (user.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(quser.processInstanceId.eq(user.getProcessInstanceId()));
		}
		if (user.getTaskId() != null) {
			booleanExpression = booleanExpression.and(quser.taskId.eq(user.getTaskId()));
		}

		Page<User> result = userRepository.findAll(booleanExpression, pageable);

		return new PagedResource<User>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());

	}

	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/sendforapprove/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<User> searchSendForApprovedUser(Principal principle, @RequestBody User user,
			Pageable pageable) {
		QUser quser = QUser.user;
		BooleanExpression booleanExpression = quser.workflowStatus.eq(Status.SEND_FOR_APPROVE);

		if (StringUtils.isNotEmpty(user.getCifCode())) {
			booleanExpression = booleanExpression.and(quser.cifCode.like(user.getCifCode()));
		}
		if (StringUtils.isNotEmpty(user.getEmployeeCode())) {
			booleanExpression = booleanExpression.and(quser.employeeCode.like(user.getEmployeeCode()));
		}
		if (StringUtils.isNotEmpty(user.getUsername())) {
			booleanExpression = booleanExpression.and(quser.username.upper().like(user.getUsername().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(user.getCustomerName())) {
			booleanExpression = booleanExpression
					.and(quser.customerName.upper().like(user.getCustomerName().toUpperCase()));
		}
		if (user.getBranch() != null) {
			booleanExpression = booleanExpression.and(quser.branch.eq(user.getBranch()));
		}
		// --- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (user.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(quser.processInstanceId.eq(user.getProcessInstanceId()));
		}
		if (user.getTaskId() != null) {
			booleanExpression = booleanExpression.and(quser.taskId.eq(user.getTaskId()));
		}
		Page<User> result = userRepository.findAll(booleanExpression, pageable);

		return new PagedResource<User>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());

	}

	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/assigned/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<User> searchAssignedUser(Principal principle, @RequestBody User user, Pageable pageable) {
		QUser quser = QUser.user;
		BooleanExpression booleanExpression = quser.workflowStatus.eq(Status.ASSIGNED);

		if (StringUtils.isNotEmpty(user.getCifCode())) {
			booleanExpression = booleanExpression.and(quser.cifCode.like(user.getCifCode()));
		}
		if (StringUtils.isNotEmpty(user.getEmployeeCode())) {
			booleanExpression = booleanExpression.and(quser.employeeCode.like(user.getEmployeeCode()));
		}
		if (StringUtils.isNotEmpty(user.getUsername())) {
			booleanExpression = booleanExpression.and(quser.username.upper().like(user.getUsername().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(user.getCustomerName())) {
			booleanExpression = booleanExpression
					.and(quser.customerName.upper().like(user.getCustomerName().toUpperCase()));
		}
		if (user.getBranch() != null) {
			booleanExpression = booleanExpression.and(quser.branch.eq(user.getBranch()));
		}
		// --- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (user.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(quser.processInstanceId.eq(user.getProcessInstanceId()));
		}
		if (user.getTaskId() != null) {
			booleanExpression = booleanExpression.and(quser.taskId.eq(user.getTaskId()));
		}
		Page<User> result = userRepository.findAll(booleanExpression, pageable);

		return new PagedResource<User>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());

	}

	@Secured("USER_VIEW")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/approved/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<User> searchApprovedUser(Principal principle, @RequestBody User user, Pageable pageable) {
		QUser quser = QUser.user;
		BooleanExpression booleanExpression = quser.workflowStatus.eq(Status.APPROVED);

		if (StringUtils.isNotEmpty(user.getCifCode())) {
			booleanExpression = booleanExpression.and(quser.cifCode.like(user.getCifCode()));
		}
		if (StringUtils.isNotEmpty(user.getEmployeeCode())) {
			booleanExpression = booleanExpression
					.and(quser.employeeCode.upper().like(user.getEmployeeCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(user.getUsername())) {
			booleanExpression = booleanExpression.and(quser.username.upper().like(user.getUsername().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(user.getCustomerName())) {
			booleanExpression = booleanExpression
					.and(quser.customerName.upper().like(user.getCustomerName().toUpperCase()));
		}
		if (user.getBranch() != null) {
			booleanExpression = booleanExpression.and(quser.branch.eq(user.getBranch()));
		}
		// --- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (user.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(quser.processInstanceId.eq(user.getProcessInstanceId()));
		}
		if (user.getTaskId() != null) {
			booleanExpression = booleanExpression.and(quser.taskId.eq(user.getTaskId()));
		}
		Page<User> result = userRepository.findAll(booleanExpression, pageable);

		return new PagedResource<User>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/rejected/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<User> searchRejectedUser(Principal principle, @RequestBody User user, Pageable pageable) {
		QUser quser = QUser.user;
		BooleanExpression booleanExpression = quser.workflowStatus.eq(Status.REJECTED);

		if (StringUtils.isNotEmpty(user.getCifCode())) {
			booleanExpression = booleanExpression.and(quser.cifCode.like(user.getCifCode()));
		}
		if (StringUtils.isNotEmpty(user.getEmployeeCode())) {
			booleanExpression = booleanExpression.and(quser.employeeCode.like(user.getEmployeeCode()));
		}
		if (StringUtils.isNotEmpty(user.getUsername())) {
			booleanExpression = booleanExpression.and(quser.username.upper().like(user.getUsername().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(user.getCustomerName())) {
			booleanExpression = booleanExpression
					.and(quser.customerName.upper().like(user.getCustomerName().toUpperCase()));
		}
		if (user.getBranch() != null) {
			booleanExpression = booleanExpression.and(quser.branch.eq(user.getBranch()));
		}
		// --- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
		if (user.getProcessInstanceId() != null) {
			booleanExpression = booleanExpression.and(quser.processInstanceId.eq(user.getProcessInstanceId()));
		}
		if (user.getTaskId() != null) {
			booleanExpression = booleanExpression.and(quser.taskId.eq(user.getTaskId()));
		}
		Page<User> result = userRepository.findAll(booleanExpression, pageable);

		return new PagedResource<User>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());

	}

	@Secured("USER_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/savedraft/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<User> createSaveDraftUser(Principal principle, @RequestBody User request) {
		RestResponse<User> resp = new RestResponse<User>();
		try {
			// 1. set created date
			request.setCreatedDate(new Date());
			request.setCreatedBy(getCurrentUser(principle));
			// 2.process

			User user = userService.saveAsDraft(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(user);

		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;

	}

	@Secured("USER_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/sendforapprove/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<User> createSendForApprovedUser(Principal principle, @RequestBody User request) {
		RestResponse<User> resp = new RestResponse<User>();
		try {
			// 1. set created date
			request.setCreatedDate(new Date());
			request.setCreatedBy(getCurrentUser(principle));
			request.setReferenceCode(TransactionCodeGenerator.generate());

			// 2. process
			User user = userService.sendForApprove(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(user);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;

	}

	@Secured("USER_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/assigned/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<User> createAssignedUser(Principal principle, @RequestBody User request) {
		RestResponse<User> resp = new RestResponse<User>();
		try {
			// 1. set data: created date
			request.setCreatedDate(new Date());
			request.setAssignee(getCurrentUser(principle));
			// 2.process

			User user = userService.assign(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(user);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;

	}

	@Secured("USER_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/assigned/return", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<User> createReturnAssignedUser(Principal principle, @RequestBody User request) {
		RestResponse<User> resp = new RestResponse<User>();
		try {

			User user = userService.returnUser(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(user);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;

	}

	@Secured("USER_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/approved/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<User> createApprovedUser(Principal principle, @RequestBody User request) {
		RestResponse<User> resp = new RestResponse<User>();
		try {

			// 1. set assigned date
			request.setAssignedDate(new Date());
			request.setApprovedBy(getCurrentUser(principle));
			request.setPassword(generatePassword());

			// 2. process
			if (isPrimary()) {
				String restUrl = apiGatewayUrl + "/tellerapp/admin_his/api/user/approved/create";
				resp = vn.fpt.dbp.vccb.core.rest.user.UserService.cud(restUrl, request);
			} else {
				//1. approve user
				User user = userService.approve(request);
				
				//2. call OSB to create user
				QName SERVICE_NAME = new QName("http://www.alsb.com/", "WebService");
				URL wsdlURL = new URL(OSBGateway);
				com.alsb.WebService_Service ss = new com.alsb.WebService_Service(wsdlURL, SERVICE_NAME);
				com.alsb.WebService port = ss.getWebService();
				
				String sourceRef, username, password, name, userID, brid, customerNo, maker, approver;
				sourceRef = user.getReferenceCode();
				username = user.getUsername();
				password = user.getPassword();
				name = user.getEmployeeName();
				userID = user.getEmployeeCode();
				brid = user.getBranch().getCode();
				customerNo = user.getCifCode();
				maker = user.getCreatedBy().getUsername();
				approver = user.getApprovedBy().getUsername();
				
				String result = port.coreUserCreate(sourceRef, username, password, name, userID, brid, customerNo, maker, approver);
				
				if(!"0".equals(result) && !"00".equals(result) ){
					resp.setStatus(RestResponse.STATUS_ERROR);
					resp.setErrorMessage("Osb occurred problem");
					resp.setContent(user);
				}else{

					resp.setStatus(RestResponse.STATUS_SUCCESS);
					resp.setErrorMessage("");
					resp.setContent(user);
				}
			}
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;

	}

	// @Secured("USER_APPROVE")
	@RequestMapping(value = "/api/user/approved/createinprimary", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<User> createApprovedUserInPrimary(Principal principle, @RequestBody User request) {
		RestResponse<User> resp = new RestResponse<User>();
		if (!isPrimary()) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage("This method cannot be called directly from history service");
			resp.setContent(null);
			return resp;
		}

		try {
			System.out.println("Run service /api/user/approved/createinprimary");
			User user = userService.approveInPrimary(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage(null);
			resp.setContent(user);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@Secured("USER_APPROVE")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/rejected/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<User> createRejectedUser(Principal principle, @RequestBody User request) {
		RestResponse<User> resp = new RestResponse<User>();
		try {

			User user = userService.reject(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(user);
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;

	}

	@Secured("USER_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/savedraft/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<User> deleteSaveDraftUser(Principal principle, @RequestBody User request) {
		RestResponse<User> resp = new RestResponse<User>();
		try {

			User user = userService.deleteSaveDraft(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(user);

		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;

	}

	@Secured("USER_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/password/reset", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<User> resetPassword(Principal principle, @RequestBody User request) {
		RestResponse<User> resp = new RestResponse<User>();
		try {
			String password = generatePassword();
			request.setPassword(password);

			User user = userService.resetPassword(request);
			
			//2. Call OSB to change password
			QName SERVICE_NAME = new QName("http://www.alsb.com/", "WebService");
			URL wsdlURL = new URL(OSBGateway);
			com.alsb.WebService_Service ss = new com.alsb.WebService_Service(wsdlURL, SERVICE_NAME);
			com.alsb.WebService port = ss.getWebService();
			
			String sourceRef, username, newPassword, maker, approver;
			sourceRef = user.getReferenceCode();
			username = user.getUsername();
			newPassword = password;
			maker = user.getCreatedBy().getUsername();
			approver = user.getApprovedBy().getUsername();
			
			String result = port.coreUserChangePassword(sourceRef, username, password, newPassword, maker, approver);
			if(!"0".equals(result) && !"00".equals(result)){
				resp.setStatus(RestResponse.STATUS_ERROR);
				   resp.setErrorMessage("OSB occurred problem");
				   resp.setContent(user);
			}else{
			   resp.setStatus(RestResponse.STATUS_SUCCESS);
			   resp.setErrorMessage("");
			   resp.setContent(user);
			}

		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;

	}

	@Secured("USER_ADD")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/password/change", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<User> changePassword(Principal principle, @RequestBody User request) {
		RestResponse<User> resp = new RestResponse<User>();
		try {

			User user = userService.changePassword(request);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(user);

		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;

	}

	/*
	 * Private functions
	 */
	private User getCurrentUser(Principal principle) {
		String username = "";
		if (principle != null) {
			username = principle.getName();
		}

		User user = userRepository.findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(username,
				Status.APPROVED);
		return user;

	}

	private String generatePassword() {
		String password;
		int count = 8;
		password = RandomStringUtils.randomAlphanumeric(count); // password
																// include
																// character &
																// number

		return password;
	}

	/*
	 * private void sendEmail() throws Exception { //maybe need to load
	 * properties from database/configuration file and set to properties String
	 * smtpHost = "localhost";
	 * 
	 * String subject; String toEmail = "aa@email.com.vn"; String fromEmail =
	 * "from@email.com.vn"; String emailSubject = "subjetc"; String emailContent
	 * = "<h1>content</h1>"; //load from html template
	 * 
	 * Properties props = new Properties();
	 * 
	 * props.put("mail.smtp.host", smtpHost);
	 * 
	 * try{
	 * 
	 * Session session = Session.getInstance(props); Message email = new
	 * MimeMessage(session);
	 * 
	 * email.addRecipient(Message.RecipientType.TO, new
	 * InternetAddress(toEmail)); email.setFrom(new InternetAddress(fromEmail));
	 * email.setSubject(emailSubject);
	 * email.setContent(emailContent,"text/html; charset=utf-8");
	 * 
	 * Transport.send(email);
	 * 
	 * }catch(Exception e){ throw e; }
	 * 
	 * }
	 */
}
