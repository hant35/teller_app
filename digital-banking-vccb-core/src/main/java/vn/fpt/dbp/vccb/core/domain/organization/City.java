package vn.fpt.dbp.vccb.core.domain.organization;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "CITY")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = City.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	public City() {
	}

	public City(Long id) {
		this.id = id;
	}

	public City(int id) {
		this.id = new Long(id);
	}

	public City(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "CITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/** Trực thuộc nước **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTRY")
	private Country country;

	/** Mã thành phố/tỉnh **/
	@Column(name = "CODE", length = 50)
	private String code;

	/** Tên thành phố/tỉnh **/
	@Column(name = "NAME", length = 200)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
	private Set<District> districts;

	@XmlTransient
	public Country getCountry() {
		return this.country;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof City))
			return false;
		if (this.getId() == null || ((City) obj).getId() == null)
			return false;
		return this.getId().equals(((City) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
