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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AREA")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Area.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;

	public Area() {
	}

	public Area(Long id) {
		this.id = id;
	}

	public Area(int id) {
		this.id = new Long(id);
	}

	public Area(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "AREA_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	@Column(name = "CODE", updatable = true, insertable = true, length = 50)
	private String code;

	@Column(name = "NAME", updatable = true, insertable = true, length = 200)
	private String name;

	@Column(name = "DESCRIPTION", updatable = true, insertable = true, length = 200)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "area")
	private Set<Branch> branches;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Area))
			return false;
		if (this.getId() == null || ((Area) obj).getId() == null)
			return false;
		return this.getId().equals(((Area) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
