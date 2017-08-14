package vn.fpt.dbp.vccb.core.domain.role;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.base.WorkFlowModel;

@Entity
@Table(name = "VCCB_ROLE") //Oracle occur error if table name = ROLE
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=Role.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Role extends WorkFlowModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Role(){}
	
	public Role(Long id){
		this.id = id;
	}
	
	public Role(int id){
		this.id = new Long(id);
	}
	
	public Role(String id){
		this.id = new Long(id);
	}
	

	@Column(name = "CODE", updatable=true, insertable=true, length = 50)
	private String code;
	
	@Column(name = "NAME", updatable=true, insertable=true, length = 200)
	private String name;
	
	@Column(name = "STATUS", updatable=true, insertable=true, length = 50)
	private String status;
	
	@Column(name = "DESCRIPTION", updatable=true, insertable=true, length = 2000)
	private String description;
	
	@Column(name = "IS_ADMIN", updatable=true, insertable=true)
	private Boolean isAdmin;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="role") //must be EAGER for UserDetailsService to work
	private Set<RolePermission> permissions;
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Role))
			return false;
		if (this.getId() == null || ((Role) obj).getId() == null)
			return false;
		return this.getId().equals(((Role) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}
	
}