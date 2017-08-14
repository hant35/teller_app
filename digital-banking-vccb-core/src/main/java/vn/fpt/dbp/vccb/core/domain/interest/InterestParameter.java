package vn.fpt.dbp.vccb.core.domain.interest;

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

/**
 * Entity: Parameter<br>
 * Dữ liệu lấy từ core, lưu ở database của service
 * 
 */
@Entity
@Table(name = "INTEREST_PARAMETER")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = InterestParameter.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class InterestParameter implements Serializable {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public InterestParameter() {
	}

	public InterestParameter(Long id) {
		this.id = id;
	}

	public InterestParameter(int id) {
		this.id = new Long(id);
	}

	public InterestParameter(String id) {
		this.id = new Long(id);
	}

	/**
	 * Khoá chính, tự động phát sinh
	 */
	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "INTERESTPARAMETER_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/**
	 * Lãi suất tiền gửi
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INTEREST")
	private Interest interest;

	/**
	 * Mã tham số
	 */
	@Column(name = "PARAMETER")
	private String parameter;

	/**
	 * Mã lãi suất
	 */
	@Column(name = "CODE", length = 50)
	private String code;

	/**
	 * Lãi suất
	 */
	@Column(name = "VALUE")
	private Double value;

	@XmlTransient
	public Interest getInterest() {
		return this.interest;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof InterestParameter))
			return false;
		if (this.getId() == null || ((InterestParameter) obj).getId() == null)
			return false;
		return this.getId().equals(((InterestParameter) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
