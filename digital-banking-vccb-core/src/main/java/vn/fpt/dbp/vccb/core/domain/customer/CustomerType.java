package vn.fpt.dbp.vccb.core.domain.customer;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

/*
 * Loai Khach Hang
 */

@Entity
@Table(name = "CUSTOMER_TYPE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CustomerType.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
//@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerType implements Serializable {
	private static final long serialVersionUID = 1L;

	public CustomerType() {
	}

	public CustomerType(Long id) {
		this.id = id;
	}

	public CustomerType(int id) {
		this.id = new Long(id);
	}

	public CustomerType(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "CUSTOMER_TYPE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	@Column(name = "CODE", length = 50)
	private String code;

	@Column(name = "NAME", length = 200)
	private String name;

	@Column(name = "DESCRIPTION", length = 500)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerType")
	private Set<CustomerSize> customerSizes;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerType")
	private Set<CustomerGroup> customerGroups;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CustomerType))
			return false;
		if (this.getId() == null || ((CustomerType) obj).getId() == null)
			return false;
		return this.getId().equals(((CustomerType) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
