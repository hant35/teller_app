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
@Table(name = "BRANCH")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Branch.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;

	public Branch() {
	}

	public Branch(Long id) {
		this.id = id;
	}

	public Branch(int id) {
		this.id = new Long(id);
	}

	public Branch(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "BRANCH_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	@Column(name = "CODE", updatable = true, insertable = true, length = 20)
	private String code;

	@Column(name = "NAME", updatable = true, insertable = true, length = 200)
	private String name;

	@Column(name = "DESCRIPTION", updatable = true, insertable = true, length = 200)
	private String description;

	@Column(name = "START_TIME", length = 100)
	private String startTime;
	
	@Column(name = "END_TIME", length = 100)
	private String endTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA")
	private Area area;
	
	@XmlTransient
	public Area getArea() {
		return this.area;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Branch))
			return false;
		if (this.getId() == null || ((Branch) obj).getId() == null)
			return false;
		return this.getId().equals(((Branch) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}
}