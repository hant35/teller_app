package vn.fpt.dbp.vccb.core.domain.user;

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
import vn.fpt.dbp.vccb.core.domain.organization.Branch;

@Entity
@Table(name = "RESTRICT_USER_BRANCH")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=RestrictUserBranch.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
public class RestrictUserBranch implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public RestrictUserBranch(){}
	
	public RestrictUserBranch(Long id){
		this.id = id;
	}
	
	public RestrictUserBranch(int id){
		this.id = new Long(id);
	}
	
	public RestrictUserBranch(String id){
		this.id = new Long(id);
	}
	
	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "RESTRICT_USER_BRANCH_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID") //neu colume name = "USER" se bi loi oracle
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="BRANCH")
	private Branch branch;
	
	@Column(name = "IS_ALLOW")
	private Boolean isAllow;
	
	@XmlTransient
	public User getUser()
	{
		return this.user;
	}
	
	@XmlTransient
	public Branch getBranch()
	{
		return this.branch;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof RestrictUserBranch))
			return false;
		if (this.getId() == null || ((RestrictUserBranch) obj).getId() == null)
			return false;
		return this.getId().equals(((RestrictUserBranch) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
