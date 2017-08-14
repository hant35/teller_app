package vn.fpt.dbp.vccb.core.domain.organization;

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
@Table(name = "ADDRESS")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Address.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	public Address() {
	}

	public Address(Long id) {
		this.id = id;
	}

	public Address(int id) {
		this.id = new Long(id);
	}

	public Address(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "ADDRESS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/** Khu phố, đường phố, số nhà, số tầng **/
	@Column(name = "STREET", length = 200)
	private String street;

	/** Thành phố/tỉnh **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY")
	private City city;

	/** Quận/huyện **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISTRICT")
	private District district;

	/** Phường/xã **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WARD")
	private Ward ward;

	@XmlTransient
	public City getCity() {
		return this.city;
	}

	@XmlTransient
	public District getDistrict() {
		return this.district;
	}

	@XmlTransient
	public Ward getWard() {
		return this.ward;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Address))
			return false;
		if (this.getId() == null || ((Address) obj).getId() == null)
			return false;
		return this.getId().equals(((Address) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
