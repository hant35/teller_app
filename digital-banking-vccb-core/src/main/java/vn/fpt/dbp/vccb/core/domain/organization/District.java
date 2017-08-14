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
@Table(name = "DISTRICT")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = District.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class District implements Serializable {
	private static final long serialVersionUID = 1L;

	public District() {
	}

	public District(Long id) {
		this.id = id;
	}

	public District(int id) {
		this.id = new Long(id);
	}

	public District(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "DISTRICT_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/** Trực thuộc thành phố/tỉnh **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY")
	private City city;

	/** Mã quận/huyện **/
	@Column(name = "CODE", length = 50)
	private String code;

	/** Tên quận/huyện **/
	@Column(name = "NAME", length = 200)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "district")
	private Set<Ward> wards;

	@XmlTransient
	public City getCity() {
		return this.city;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof District))
			return false;
		if (this.getId() == null || ((District) obj).getId() == null)
			return false;
		return this.getId().equals(((District) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
