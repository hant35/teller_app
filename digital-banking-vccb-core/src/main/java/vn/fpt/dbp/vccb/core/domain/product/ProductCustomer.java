package vn.fpt.dbp.vccb.core.domain.product;

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
import vn.fpt.dbp.vccb.core.domain.customer.CustomerSize;
import vn.fpt.dbp.vccb.core.domain.customer.CustomerType;

@Entity
@Table(name = "PRODUCT_CUSTOMER")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ProductCustomer.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class ProductCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	public ProductCustomer() {
	}

	public ProductCustomer(Long id) {
		this.id = id;
	}

	public ProductCustomer(int id) {
		this.id = new Long(id);
	}

	public ProductCustomer(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "PRODUCT_CUSTOMER_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/**
	 * Sản phầm
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT")
	private Product product;

	/**
	 * Loai hình Khách hàng
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_TYPE")
	private CustomerType customerType;

	/**
	 * Quy mô pháp nhân
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_SIZE")
	private CustomerSize customerSize;

	@XmlTransient
	public Product getProduct() {
		return this.product;
	}
	
	@XmlTransient
	public CustomerType getCustomerType() {
		return this.customerType;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ProductCustomer))
			return false;
		if (this.getId() == null || ((ProductCustomer) obj).getId() == null)
			return false;
		return this.getId().equals(((ProductCustomer) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
