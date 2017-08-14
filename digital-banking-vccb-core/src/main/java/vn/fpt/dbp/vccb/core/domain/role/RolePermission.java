package vn.fpt.dbp.vccb.core.domain.role;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.system.Function;

@Entity
@Table(name = "ROLE_PERMISSION")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=RolePermission.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
public class RolePermission implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public RolePermission(){}
	
	public RolePermission(Long id){
		this.id = id;
	}
	
	public RolePermission(int id){
		this.id = new Long(id);
	}
	
	public RolePermission(String id){
		this.id = new Long(id);
	}
	
	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "ROLE_PERMISSION_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;
	
	@ManyToOne(fetch = FetchType.LAZY) //just for testing
	@JoinColumn(name = "ROLE")
	private Role role;
	
	@ManyToOne(fetch = FetchType.EAGER)  //must be EAGER for UserDetailsService to work
	@JoinColumn(name = "FUNCTION")
	private Function function;
	
	@Column(name = "VIEW_PERM", updatable=true, insertable=true)
	private Boolean view;
	
	@Column(name = "SUBVIEW_PERM", updatable=true, insertable=true)
	private Boolean subView;
	
	@Column(name = "ADD_PERM", updatable=true, insertable=true)
	private Boolean add;
	
	@Column(name = "DELETE_PERM", updatable=true, insertable=true)
	private Boolean delete;
	
	@Column(name = "UPDATE_PERM", updatable=true, insertable=true)
	private Boolean update;
	
	@Column(name = "APPROVE_PERM", updatable=true, insertable=true)
	private Boolean approve;
	
	@Column(name = "PRINT_PERM", updatable=true, insertable=true)
	private Boolean print;
	
	@Column(name = "CANCEL_PERM", updatable=true, insertable=true)
	private Boolean cancel;
	
	@Column(name = "CLOSE_PERM", updatable=true, insertable=true)
	private Boolean close;
	
	@Column(name = "REOPEN_PERM", updatable=true, insertable=true)
	private Boolean reopen;
	
	@Column(name = "COPY_PERM", updatable=true, insertable=true)
	private Boolean copy;
	
	@Column(name = "ROLLOVERCOMPONENT_PERM", updatable=true, insertable=true)
	private Boolean rolloverComponent;
	
	@Column(name = "CONFIRM_PERM", updatable=true, insertable=true)
	private Boolean confirm;
	
	@Column(name = "PAYMENT_PERM", updatable=true, insertable=true)
	private Boolean payment;
	
	@Column(name = "HOLD_PERM", updatable=true, insertable=true)
	private Boolean hold;
	
	@Column(name = "TEMPLATE_PERM", updatable=true, insertable=true)
	private Boolean template;
	
	@Column(name = "GENERATE_PERM", updatable=true, insertable=true)
	private Boolean generate;
	
	@Column(name = "AUTO_AUTHORIZE_PERM", updatable=true, insertable=true)
	private Boolean autoAuthorize;
	
	@XmlTransient
	public Role getRole()
	{
		return this.role;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof RolePermission))
			return false;
		if (this.getId() == null || ((RolePermission) obj).getId() == null)
			return false;
		return this.getId().equals(((RolePermission) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
