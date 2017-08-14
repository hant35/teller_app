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
@Table(name = "CUSTOMER_REF_CUSTOMER")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CustomerRefCustomer.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class CustomerRefCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	public CustomerRefCustomer() {
	}

	public CustomerRefCustomer(Long id) {
		this.id = id;
	}

	public CustomerRefCustomer(int id) {
		this.id = new Long(id);
	}

	public CustomerRefCustomer(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "CUSTOMER_REF_CUSTOMER_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/** Mã khách hàng đang thao tác **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_FROM")
	private Customer customerFrom;

	/** Mã khách hàng người liên quan **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_TO")
	private Customer customerTo;

	/** Mối quan hệ **/
	@Column(name = "REFERENCE_TYPE", length = 50)
	private String referenceType;

	/** Mối quan hệ(Khác) **/
	@Column(name = "REFERNCE_TYPE_OTHER", length = 50)
	private String refernceTypeOther;

	/** Chức vụ **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOB_POSITION")
	private Position jobPosition;

	/** Chức vụ (Khác) **/
	@Column(name = "JOB_POSITION_OTHER", length = 50)
	private String jobPositionOther;

	/** Ghi chú **/
	@Column(name = "DESCRIPTION", length = 500)
	private String description;

	/** Tỷ trọng sở hữu **/
	@Column(name = "OWNERSHIP_RATIO")
	private Float ownershipRatio;

	@XmlTransient
	public Customer getCustomerFrom() {
		return this.customerFrom;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CustomerRefCustomer))
			return false;
		if (this.getId() == null || ((CustomerRefCustomer) obj).getId() == null)
			return false;
		return this.getId().equals(((CustomerRefCustomer) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
