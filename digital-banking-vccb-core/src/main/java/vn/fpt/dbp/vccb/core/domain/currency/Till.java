package vn.fpt.dbp.vccb.core.domain.currency;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import vn.fpt.dbp.vccb.core.domain.user.User;

@Entity
@Table(name = "TILL") 
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=Till.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Till extends WorkFlowModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Till(){}
	
	public Till(Long id){
		this.id= id;
	}
	
	public Till(int id){
		this.id= new Long(id);
	}
	
	public Till(String id){
		this.id= new Long(id);
	}
	
	@Column(name = "CODE", updatable=true, insertable=true, length = 50)
	private String code;
	
	@Column(name = "NAME", updatable=true, insertable=true, length = 200)
	private String name;
	
	@Column(name = "STATUS", updatable=true, insertable=true, length = 50)
	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VAULT")
	private Vault vault;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID") //neu colume name = "USER" se bi loi oracle
	private User user;
	
	@XmlTransient
	public User getUser()
	{
		return this.user;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Till))
			return false;
		if (this.getId() == null || ((Till) obj).getId() == null)
			return false;
		return this.getId().equals(((Till) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
