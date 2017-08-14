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
/*
 * Nhom Khach Hang
 */
@Entity
@Table(name = "CUSTOMER_GROUP")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CustomerGroup.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class CustomerGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	public CustomerGroup() {
	}

	public CustomerGroup(Long id) {
		this.id = id;
	}

	public CustomerGroup(int id) {
		this.id = new Long(id);
	}

	public CustomerGroup(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "CUSTOMER_GROUP_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/** Loại khách hàng(cá nhân, pháp nhân, định chế) **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_TYPE")
	private CustomerType customerType;

	@Column(name = "CODE", updatable = true, insertable = true, length = 50)
	private String code;

	@Column(name = "NAME", updatable = true, insertable = true, length = 200)
	private String name;

	@Column(name = "DESCRIPTION", updatable = true, insertable = true, length = 500)
	private String description;

	@XmlTransient
	public CustomerType getCustomerType() {
		return this.customerType;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CustomerGroup))
			return false;
		if (this.getId() == null || ((CustomerGroup) obj).getId() == null)
			return false;
		return this.getId().equals(((CustomerGroup) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
