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
import vn.fpt.dbp.vccb.core.domain.customer.CustomerGroup;
import vn.fpt.dbp.vccb.core.domain.customer.CustomerType;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;

@Entity
@Table(name = "RESTRICT_USER_CUSTOMERGROUP")
@Getter @Setter 
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=RestrictUserCustomerGroup.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Remotable
public class RestrictUserCustomerGroup implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public RestrictUserCustomerGroup(){}
	
	public RestrictUserCustomerGroup(Long id){
		this.id = id;
	}
	
	public RestrictUserCustomerGroup(int id){
		this.id = new Long(id);
	}
	
	public RestrictUserCustomerGroup(String id){
		this.id = new Long(id);
	}
	
	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "RESTRICT_USER_CUS_GR_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID") //neu colume name = "USER" se bi loi oracle
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)  
	@JoinColumn(name="BRANCH")
	private Branch branch;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_GROUP")
	private CustomerGroup customerGroup; //Nhom khach hang
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_TYPE")
	private CustomerType customerType; //Loai KH: Ca nhan, doanh nghiep, dinh che TC
	
	@Column(name = "IS_ALLOW")
	private Boolean isAllow;
	
	@XmlTransient
	public User getUser()
	{
		return this.user;
	}
	
	@XmlTransient
	public CustomerGroup getCustomerGroup()
	{
		return this.customerGroup;
	}
	
	@XmlTransient
	public CustomerType getCustomerType()
	{
		return this.customerType;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof RestrictUserCustomerGroup))
			return false;
		if (this.getId() == null || ((RestrictUserCustomerGroup) obj).getId() == null)
			return false;
		return this.getId().equals(((RestrictUserCustomerGroup) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
