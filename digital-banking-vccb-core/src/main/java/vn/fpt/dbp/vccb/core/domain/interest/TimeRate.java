package vn.fpt.dbp.vccb.core.domain.interest;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity: Kỳ hạn
 *
 */
@Entity
@Table(name = "TIME_RATE")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = TimeRate.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class TimeRate implements Serializable {
	private static final long serialVersionUID = 1L;

	public TimeRate() {
	}

	public TimeRate(Long id) {
		this.id = id;
	}

	public TimeRate(int id) {
		this.id = new Long(id);
	}

	public TimeRate(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "TIMERATE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/**
	 * Mã kỳ hạn
	 */
	@Column(name = "CODE", updatable = true, insertable = true, length = 50)
	private String code;

	/**
	 * Tên kỳ hạn
	 */
	@Column(name = "NAME", updatable = true, insertable = true, length = 200)
	private String name;

	/**
	 * Giá trị
	 */
	@Column(name = "VALUE")
	private Integer value;

	/**
	 * Ghi chú
	 */
	@Column(name = "DESCRIPTION", updatable = true, insertable = true, length = 500)
	private String description;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof TimeRate))
			return false;
		if (this.getId() == null || ((TimeRate) obj).getId() == null)
			return false;
		return this.getId().equals(((TimeRate) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
