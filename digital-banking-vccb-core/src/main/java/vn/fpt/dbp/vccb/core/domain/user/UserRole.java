package vn.fpt.dbp.vccb.core.domain.user;

import java.io.Serializable;

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
import vn.fpt.dbp.vccb.core.domain.organization.Branch;
import vn.fpt.dbp.vccb.core.domain.role.Role;

@Entity
@Table(name = "USER_ROLE")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=UserRole.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
public class UserRole implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public UserRole(){}
	
	public UserRole(Long id){
		this.id = id;
	}
	
	public UserRole(int id){
		this.id = new Long(id);
	}
	
	public UserRole(String id){
		this.id = new Long(id);
	}
	
	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "USER_ROLE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID") //neu colume name = "USER" se bi loi oracle
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER) //must be EAGER for UserDetailsService to work
	@JoinColumn(name="ROLE")
	private Role role;
	
	@ManyToOne(fetch = FetchType.EAGER)  //must be EAGER for UserDetailsService to work
	@JoinColumn(name="BRANCH")
	private Branch branch;
	
	@XmlTransient
	public User getUser()
	{
		return this.user;
	}
	
	@XmlTransient
	public Role getRole()
	{
		return this.role;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof UserRole))
			return false;
		if (this.getId() == null || ((UserRole) obj).getId() == null)
			return false;
		return this.getId().equals(((UserRole) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
