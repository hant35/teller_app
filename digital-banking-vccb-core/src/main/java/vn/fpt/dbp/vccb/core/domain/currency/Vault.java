package vn.fpt.dbp.vccb.core.domain.currency;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.base.WorkFlowModel;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;
import vn.fpt.dbp.vccb.core.domain.user.User;

@Entity
@Table(name = "VAULT")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=Vault.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Vault extends WorkFlowModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Vault(){}
	
	public Vault(Long id){
		this.id= id;
	}
	
	public Vault(int id){
		this.id= new Long(id);
	}
	
	public Vault(String id){
		this.id= new Long(id);
	}
	
	@Column(name = "CODE", updatable=true, insertable=true, length = 50)
	private String code;
	
	@Column(name = "NAME", updatable=true, insertable=true, length = 200)
	private String name;
	
	@Column(name = "STATUS", updatable=true, insertable=true, length = 50)
	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH")
	private Branch branch;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY")
	private Currency currency;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID") //neu colume name = "USER" se bi loi oracle
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="vault")
	private List<Till> tills;
	
	@XmlTransient
	public User getUser()
	{
		return this.user;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Vault))
			return false;
		if (this.getId() == null || ((Vault) obj).getId() == null)
			return false;
		return this.getId().equals(((Vault) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
