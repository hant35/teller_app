package vn.fpt.dbp.vccb.core.domain.user;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.base.WorkFlowModel;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;
import vn.fpt.dbp.vccb.core.domain.organization.Department;
import vn.fpt.dbp.vccb.core.domain.role.RolePermission;
import vn.fpt.dbp.vccb.core.util.Permissions;

@Entity
@Table(name = "VCCB_USER") //Oracle occur error if table name = USER
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=User.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "permissionMap"})
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class User extends WorkFlowModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public User() {}
	
	public User(Long id) {
		this.id = id;
	}
	
	public User(int id) {
		this.id = new Long(id);
	}
	
	public User(String id) {
		this.id = new Long(id);
	}
	
	@Column(name = "IS_TEMPLATE", updatable=true, insertable=true)
	private Boolean isTemplate;
	
	@Column(name = "CIF_CODE", updatable=true, insertable=true, length = 20)
	private String cifCode;
	
	@Column(name = "CUSTOMER_NAME", updatable=true, insertable=true, length = 50)
	private String customerName;
	
	@Column(name = "EMPLOYEE_CODE", updatable=true, insertable=true, length = 20)
	private String employeeCode;
	
	@Column(name = "EMPLOYEE_NAME", updatable=true, insertable=true, length = 50)
	private String employeeName;
	
	@Column(name = "USERNAME", updatable=true, insertable=true, length = 50)
	private String username;
	
	@Column(name = "PASSWORD", updatable=true, insertable=true, length = 50)
	private String password; //current or old password
	
	@Transient
	@JsonProperty
	private String newPassword; //just for new password when change password
	
	@Column(name = "EMAIL", updatable=true, insertable=true, length = 50)
	private String email;
	
	@Column(name = "PHONE", updatable=true, insertable=true, length = 50)
	private String phone;
	
	@Column(name = "STATUS", updatable=true, insertable=true, length = 50)
	private String status; // trang thai nguoi dung
	
	@Column(name = "LOGIN_STATUS", updatable=true, insertable=true, length = 50)
	private String loginStatus; // trang thai đang nhap
	
	@Column(name = "USER_LEVEL", updatable=true, insertable=true, length = 20)
	private String level;
	
	@Column(name = "IS_ADMIN", updatable=true, insertable=true)
	private Boolean isAdmin;
	
	@Column(name = "IS_WORK_ON_CIF", updatable=true, insertable=true)
	private Boolean isWorkOnCif; // duoc thao tac tren chinh so cif cua user
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "DEPARTMENT")
	private Department department;
	
	@ManyToOne(fetch = FetchType.EAGER) //must be EAGER for UserDetailsService to work
	@JoinColumn(name = "BRANCH")
	private Branch branch;
	
	@ManyToOne(fetch = FetchType.EAGER) //must be EAGER for UserDetailsService to work
	@JoinColumn(name = "CURRENT_BRANCH")
	private Branch currentBranch;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_PASSWORD_CHANGED_DATE")
	private Date lastPasswordChangedDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EFFECTED_DATE")
	private Date effectedDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRED_DATE")
	private Date expiredDate;
	
	@Column(name = "START_TIME")
	private String startTime;
	
	@Column(name = "END_TIME")
	private String endTime;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	private Set<UserTill> userTills;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	private Set<UserVault> userVaults;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="user") //must be EAGER for UserDetailsService to work
	private Set<UserRole> userRoles; //nhom quyen cua user
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="user")//must be EAGER for UserDetailsService to work
	private Set<UserPermission> userPermission; //nhom chuc nang cua user
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	private Set<UserCurrency> userCurrency; //nhom tien te cua user
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	private Set<UserCustomerGroup> userCustomerGroup; //nhom khach hang cua user
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	private Set<UserLimitGroup> userLimitGroup; //nhom han muc cua user
	
	/*
	 * Restrict groups
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	private Set<RestrictUserBranch> restrictUserBranch; //restrict cac branch cua user
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	private Set<RestrictUserCurrency> restrictUserCurrency; //restrict nhom tien te cua user
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	private Set<RestrictUserCustomerGroup> restrictUserCustomerGroup; //restrict nhom khach hang cua user
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	private Set<RestrictUserFunction> restrictUserFunction; //restrict nhom tinh nang cua user
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	private Set<RestrictUserProduct> restrictUserProduct; //restrict nhom san pham cua user
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof User))
			return false;
		if (this.getId() == null || ((User) obj).getId() == null)
			return false;
		return this.getId().equals(((User) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}
	

	@Transient
	private Map<String, Boolean> permissionMap;
	
	@XmlTransient
	public Map<String, Boolean> getPermissionMap() {
		if (permissionMap != null && !permissionMap.isEmpty()) {
			return permissionMap;
		}
		permissionMap = new HashMap<String, Boolean>();
		//populate user role permission
		if (userRoles != null) {
			for (UserRole userRole : userRoles) {
				if (userRole != null && userRole.getRole() != null && userRole.getRole().getPermissions() != null) {
					for (RolePermission rolePermission : userRole.getRole().getPermissions()) {
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.VIEW, rolePermission.getView());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.ADD, rolePermission.getAdd());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.DELETE, rolePermission.getDelete());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.UPDATE, rolePermission.getUpdate());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.APPROVE, rolePermission.getApprove());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.PRINT, rolePermission.getPrint());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.CANCEL, rolePermission.getCancel());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.CLOSE, rolePermission.getClose());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.RE_OPEN, rolePermission.getReopen());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.COPY, rolePermission.getCopy());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.ROLLOVER_COMPONENT, rolePermission.getRolloverComponent());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.CONFIRM, rolePermission.getConfirm());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.PAYMENT, rolePermission.getPayment());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.HOLD, rolePermission.getHold());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.TEMPLATE, rolePermission.getTemplate());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.GENERATE, rolePermission.getGenerate());
						permissionMap.put(userRole.getBranch().getCode() + "@" + rolePermission.getFunction().getCode() + "@" + Permissions.AUTO_AUTHORIZE, rolePermission.getAutoAuthorize());
					}
				}
			}
		}
		//populate user specific permission
		if (userPermission != null) {
			for (UserPermission userPerm : userPermission) {
				if (userPerm != null && userPerm.getBranch() != null && userPerm.getFunction() != null) {
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.VIEW, userPerm.getView());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.ADD, userPerm.getAdd());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.DELETE, userPerm.getDelete());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.UPDATE, userPerm.getUpdate());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.APPROVE, userPerm.getApprove());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.PRINT, userPerm.getPrint());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.CANCEL, userPerm.getCancel());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.CLOSE, userPerm.getClose());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.RE_OPEN, userPerm.getReopen());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.COPY, userPerm.getCopy());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.ROLLOVER_COMPONENT, userPerm.getRolloverComponent());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.CONFIRM, userPerm.getConfirm());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.PAYMENT, userPerm.getPayment());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.HOLD, userPerm.getHold());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.TEMPLATE, userPerm.getTemplate());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.GENERATE, userPerm.getGenerate());
					permissionMap.put(userPerm.getBranch().getCode() + "@" + userPerm.getFunction().getCode() + "@" + Permissions.AUTO_AUTHORIZE, userPerm.getAutoAuthorize());
				}
			}
		}
		return permissionMap;
	}
	
	public boolean hasPermission(String functionCode, String permissionCode) {
		Branch checkBranch = currentBranch != null ? currentBranch : branch;
		if (checkBranch == null) {
			return false;
		}
		String permissionKey = checkBranch.getCode() + "@" + functionCode + "@" + permissionCode;
		Boolean checkPermission = getPermissionMap().containsKey(permissionKey) ? getPermissionMap().get(permissionKey) : Boolean.FALSE;
		if (checkPermission == null) {
			return false;
		}
		return checkPermission.booleanValue();
		
	}

}
