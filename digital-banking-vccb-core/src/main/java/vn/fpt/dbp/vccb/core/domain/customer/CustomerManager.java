package vn.fpt.dbp.vccb.core.domain.customer;

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

@Entity
@Table(name = "CUSTOMER_MANAGER")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CustomerManager.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class CustomerManager implements Serializable {
	private static final long serialVersionUID = 1L;

	public CustomerManager() {
	}

	public CustomerManager(Long id) {
		this.id = id;
	}

	public CustomerManager(int id) {
		this.id = new Long(id);
	}

	public CustomerManager(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "CUSTOMER_MANAGER_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/**  **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER")
	private Customer customer;

	/**  **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_USER")
	private ReferralUser managerUser;

	/** Chức vụ **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POSITION")
	private Position position;

	/**  **/
	@Column(name = "IS_KPI_CALCULATE")
	private Boolean isKPICalculate;

	@XmlTransient
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CustomerManager))
			return false;
		if (this.getId() == null || ((CustomerManager) obj).getId() == null)
			return false;
		return this.getId().equals(((CustomerManager) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
