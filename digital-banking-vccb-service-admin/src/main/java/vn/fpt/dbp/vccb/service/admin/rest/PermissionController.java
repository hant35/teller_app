package vn.fpt.dbp.vccb.service.admin.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.fpt.dbp.vccb.core.domain.role.RolePermission;
import vn.fpt.dbp.vccb.core.domain.system.Function;
import vn.fpt.dbp.vccb.core.domain.system.repository.FunctionRepository;
import vn.fpt.dbp.vccb.core.domain.user.RestrictUserFunction;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.dbp.vccb.core.domain.user.UserPermission;
import vn.fpt.dbp.vccb.core.domain.user.UserRole;
import vn.fpt.dbp.vccb.core.domain.user.repository.UserRepository;
import vn.fpt.dbp.vccb.core.util.Permissions;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class PermissionController {
	
	@Autowired
	private FunctionRepository functionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/user/getapprovallist/search", method = RequestMethod.GET, produces = "application/json")
	public List<User> findApprovalListByUserName(Principal principle, @RequestParam("function") String functionCode, @RequestParam("permission") String permission){
		List<User> approvalList = new ArrayList<User>();
		boolean flag = false;
		//QUser quser = QUser.user;
		
		Function function = functionRepository.findByCode(functionCode);
		
		List<User> users = userRepository.findAll();// need to get user of branch not all
		
		for(User user : users)
		{
			flag = checkPermissionForUser(user,function,permission);
			
			if(flag)
			{
				approvalList.add(user);
			}
		}
		
		return approvalList;
	}
	
	private boolean checkPermissionForUser(User user,Function function, String permission)
	{
		boolean flag = true;
		
		// loop on restrict user function
		for(RestrictUserFunction func : user.getRestrictUserFunction())
		{
			if (func.getFunction().equals(function))
			{
				flag = func.getIsAllow();
			}
		}
		
		if(!flag)
		{
			return flag;
		}
		
		flag = false;
		
		// loop on user permission 
		for(UserPermission per : user.getUserPermission())
		{
			flag = checkUserPermission(per,function,permission);
			
			if(flag){
				break;
			}
		}
					
		// loop on role of user
		for(UserRole role : user.getUserRoles())
		{
			flag = checkUserRole(role,function,permission);
			
			if(flag){
				break;
			}
		}
		
		return flag;
	}
	private boolean checkUserRole(UserRole userRole, Function function, String permission)
	{
		boolean flag = false;
		for(RolePermission per : userRole.getRole().getPermissions())
		{
			flag = checkRolePermission(per,function,permission);
			break;
		}
		return flag;
		
	}
	private boolean checkUserPermission(UserPermission per, Function function, String permission)
	{
		boolean flag = false;
		
		if (per.getFunction().equals(function))
		{
			if (Permissions.ADD.equalsIgnoreCase(permission))
			{
				if (per.getAdd())
				{
					flag =true;
				}
			}else if(Permissions.APPROVE.equalsIgnoreCase(permission))
			{
				if (per.getApprove())
				{
					flag =true;
				}
				
			}else if(Permissions.AUTO_AUTHORIZE.equalsIgnoreCase(permission))
			{
				if (per.getAutoAuthorize())
				{
					flag =true;
				}						
				
			}else if(Permissions.CANCEL.equalsIgnoreCase(permission))
			{
				if (per.getCancel())
				{
					flag =true;
				}
				
			}else if(Permissions.CLOSE.equalsIgnoreCase(permission))
			{
				if (per.getClose())
				{
					flag =true;
				}
				
			}else if(Permissions.CONFIRM.equalsIgnoreCase(permission))
			{
				if (per.getConfirm())
				{
					flag =true;
				}
			}
			else if(Permissions.COPY.equalsIgnoreCase(permission))
			{
				if (per.getCopy())
				{
					flag =true;
				}
			}else if(Permissions.DELETE.equalsIgnoreCase(permission))
			{
				if (per.getDelete())
				{
					flag =true;
				}
				
			}else if(Permissions.GENERATE.equalsIgnoreCase(permission))
			{
				if (per.getGenerate())
				{
					flag =true;
				}
				
			}else if(Permissions.HOLD.equalsIgnoreCase(permission))
			{
				if (per.getHold())
				{
					flag =true;
				}
				
			}else if(Permissions.PAYMENT.equalsIgnoreCase(permission))
			{
				if (per.getPayment())
				{
					flag =true;
				}
				
			}else if(Permissions.PRINT.equalsIgnoreCase(permission))
			{
				if (per.getPrint())
				{
					flag =true;
				}
				
			}else if(Permissions.RE_OPEN.equalsIgnoreCase(permission))
			{
				if (per.getReopen())
				{
					flag =true;
				}
				
			}else if(Permissions.ROLLOVER_COMPONENT.equalsIgnoreCase(permission))
			{
				if (per.getRolloverComponent())
				{
					flag =true;
				}
				
			}else if(Permissions.SUB_VIEW.equalsIgnoreCase(permission))
			{
				if (per.getSubView())
				{
					flag =true;
				}
				
			}else if(Permissions.TEMPLATE.equalsIgnoreCase(permission))
			{
				if (per.getTemplate())
				{
					flag =true;
				}
			}
			else if(Permissions.VIEW.equalsIgnoreCase(permission))
			{
				if (per.getView())
				{
					flag =true;
				}
				
			}else if(Permissions.UPDATE.equalsIgnoreCase(permission))
			{
				if (per.getUpdate())
				{
					flag =true;
				}
			}
		}
		
		return flag;
		
	}
	private boolean checkRolePermission(RolePermission per, Function function, String permission)
	{
		boolean flag = false;
		
		if (per.getFunction().equals(function))
		{
			if (Permissions.ADD.equalsIgnoreCase(permission))
			{
				if (per.getAdd())
				{
					flag =true;
				}
			}else if(Permissions.APPROVE.equalsIgnoreCase(permission))
			{
				if (per.getApprove())
				{
					flag =true;
				}
				
			}else if(Permissions.AUTO_AUTHORIZE.equalsIgnoreCase(permission))
			{
				if (per.getAutoAuthorize())
				{
					flag =true;
				}						
				
			}else if(Permissions.CANCEL.equalsIgnoreCase(permission))
			{
				if (per.getCancel())
				{
					flag =true;
				}
				
			}else if(Permissions.CLOSE.equalsIgnoreCase(permission))
			{
				if (per.getClose())
				{
					flag =true;
				}
				
			}else if(Permissions.CONFIRM.equalsIgnoreCase(permission))
			{
				if (per.getConfirm())
				{
					flag =true;
				}
			}
			else if(Permissions.COPY.equalsIgnoreCase(permission))
			{
				if (per.getCopy())
				{
					flag =true;
				}
			}else if(Permissions.DELETE.equalsIgnoreCase(permission))
			{
				if (per.getDelete())
				{
					flag =true;
				}
				
			}else if(Permissions.GENERATE.equalsIgnoreCase(permission))
			{
				if (per.getGenerate())
				{
					flag =true;
				}
				
			}else if(Permissions.HOLD.equalsIgnoreCase(permission))
			{
				if (per.getHold())
				{
					flag =true;
				}
				
			}else if(Permissions.PAYMENT.equalsIgnoreCase(permission))
			{
				if (per.getPayment())
				{
					flag =true;
				}
				
			}else if(Permissions.PRINT.equalsIgnoreCase(permission))
			{
				if (per.getPrint())
				{
					flag =true;
				}
				
			}else if(Permissions.RE_OPEN.equalsIgnoreCase(permission))
			{
				if (per.getReopen())
				{
					flag =true;
				}
				
			}else if(Permissions.ROLLOVER_COMPONENT.equalsIgnoreCase(permission))
			{
				if (per.getRolloverComponent())
				{
					flag =true;
				}
				
			}else if(Permissions.SUB_VIEW.equalsIgnoreCase(permission))
			{
				if (per.getSubView())
				{
					flag =true;
				}
				
			}else if(Permissions.TEMPLATE.equalsIgnoreCase(permission))
			{
				if (per.getTemplate())
				{
					flag =true;
				}
			}
			else if(Permissions.VIEW.equalsIgnoreCase(permission))
			{
				if (per.getView())
				{
					flag =true;
				}
				
			}else if(Permissions.UPDATE.equalsIgnoreCase(permission))
			{
				if (per.getUpdate())
				{
					flag =true;
				}
			}
		}
		
		return flag;
		
	}
}