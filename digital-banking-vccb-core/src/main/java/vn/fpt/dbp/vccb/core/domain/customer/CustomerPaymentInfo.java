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
import vn.fpt.dbp.vccb.core.domain.currency.Currency;

@Entity
@Table(name = "CUSTOMER_PAYMENT_INFO")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CustomerPaymentInfo.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class CustomerPaymentInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	public CustomerPaymentInfo() {
	}

	public CustomerPaymentInfo(Long id) {
		this.id = id;
	}

	public CustomerPaymentInfo(int id) {
		this.id = new Long(id);
	}

	public CustomerPaymentInfo(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "CUSTOMER_PAYMENT_INFO_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/**  **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER")
	private Customer customer;

	/** Loại tiền **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY")
	private Currency currency;

	/** Số tài khoản **/
	@Column(name = "ACCOUNT_NUMBER", length = 50)
	private String accountNumber;

	/** Mã SWIFT **/
	@Column(name = "SWIFT_CODE", length = 50)
	private String swiftCode;

	/** Tên ngân hàng **/
	@Column(name = "BANK_NAME", length = 200)
	private String bankName;

	@XmlTransient
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CustomerPaymentInfo))
			return false;
		if (this.getId() == null || ((CustomerPaymentInfo) obj).getId() == null)
			return false;
		return this.getId().equals(((CustomerPaymentInfo) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
