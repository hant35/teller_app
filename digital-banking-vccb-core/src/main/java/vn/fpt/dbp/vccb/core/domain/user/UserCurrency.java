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
import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;

@Entity
@Table(name = "USER_CURRENCY")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=UserCurrency.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
public class UserCurrency implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public UserCurrency(){}
	
	public UserCurrency(Long id){
		this.id = id;
	}
	
	public UserCurrency(int id){
		this.id = new Long(id);
	}
	
	public UserCurrency(String id){
		this.id = new Long(id);
	}
	
	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "USER_CURRENCY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID") //neu colume name = "USER" se bi loi oracle
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CURRENCY")
	private Currency currency;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="BRANCH")
	private Branch branch;
	
	@XmlTransient
	public User getUser()
	{
		return this.user;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof UserCurrency))
			return false;
		if (this.getId() == null || ((UserCurrency) obj).getId() == null)
			return false;
		return this.getId().equals(((UserCurrency) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
