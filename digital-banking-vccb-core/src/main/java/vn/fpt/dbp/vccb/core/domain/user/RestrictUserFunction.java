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
import vn.fpt.dbp.vccb.core.domain.system.Function;

@Entity
@Table(name = "RESTRICT_USER_FUNCTION")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=RestrictUserFunction.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
public class RestrictUserFunction implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public RestrictUserFunction(){}
	
	public RestrictUserFunction(Long id){
		this.id = id;
	}
	
	public RestrictUserFunction(int id){
		this.id = new Long(id);
	}
	
	public RestrictUserFunction(String id){
		this.id = new Long(id);
	}
	
	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "RESTRICT_USER_FUNCTION_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID") //neu colume name = "USER" se bi loi oracle
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="FUNCTION")
	private Function function;
	
	@Column(name = "IS_ALLOW")
	private Boolean isAllow;
	
	@XmlTransient
	public User getUser()
	{
		return this.user;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof RestrictUserFunction))
			return false;
		if (this.getId() == null || ((RestrictUserFunction) obj).getId() == null)
			return false;
		return this.getId().equals(((RestrictUserFunction) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
