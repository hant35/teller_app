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
@Table(name = "WARD")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Ward.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class Ward implements Serializable {
	private static final long serialVersionUID = 1L;

	public Ward() {
	}

	public Ward(Long id) {
		this.id = id;
	}

	public Ward(int id) {
		this.id = new Long(id);
	}

	public Ward(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "WARD_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/** Trực thuộc quận/huyện **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISTRICT")
	private District district;

	/** Mã phường/xã **/
	@Column(name = "CODE", length = 50)
	private String code;

	/** Tên phường/xã **/
	@Column(name = "NAME", length = 200)
	private String name;

	@XmlTransient
	public District getDistrict() {
		return this.district;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Ward))
			return false;
		if (this.getId() == null || ((Ward) obj).getId() == null)
			return false;
		return this.getId().equals(((Ward) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
