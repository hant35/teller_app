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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "COUNTRY")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Country.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	public Country() {
	}

	public Country(Long id) {
		this.id = id;
	}

	public Country(int id) {
		this.id = new Long(id);
	}

	public Country(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "COUNTRY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	@Column(name = "CODE", length = 50)
	private String code;

	@Column(name = "NAME", length = 200)
	private String name;

	@Column(name = "DESCRIPTION", length = 500)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	private Set<City> cities;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Country))
			return false;
		if (this.getId() == null || ((Country) obj).getId() == null)
			return false;
		return this.getId().equals(((Country) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
