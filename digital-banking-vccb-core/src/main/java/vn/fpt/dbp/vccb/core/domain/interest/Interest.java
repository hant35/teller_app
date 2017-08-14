package vn.fpt.dbp.vccb.core.domain.interest;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.base.WorkFlowModel;
import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.organization.Area;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;
import vn.fpt.dbp.vccb.core.domain.product.Product;

/**
 * Entity: Lãi suất tiền gửi
 * 
 */
@Entity
@Table(name = "INTEREST")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Interest.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Interest extends WorkFlowModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * @Structor<br>
	 * 
	 */
	public Interest() {
	}

	/**
	 * Structor<br>
	 * 
	 * @param Long
	 *            id
	 */
	public Interest(Long id) {
		this.id = id;
	}

	/**
	 * @param int
	 *            id
	 */
	public Interest(int id) {
		this.id = new Long(id);
	}

	/**
	 * Structor<br>
	 * 
	 * @param String
	 *            id
	 */
	public Interest(String id) {
		this.id = new Long(id);
	}

	/**
	 * Sản phẩm<br>
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT")
	private Product product;

	/**
	 * Loại tiền<br>
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY")
	private Currency currency;

	/**
	 * Kỳ hạn
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TIME_RATE")
	private TimeRate timeRate;

	/**
	 * Khu vực
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA")
	private Area area;

	/**
	 * Đơn vị(chi nhánh)
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH")
	private Branch branch;

	/**
	 * Ngày hiệu lực
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EFFECT_DATE")
	private Date effectDate;

	/** Ghi chú **/
	@Column(name = "DESCRIPTION", length = 500)
	private String description;

	/**
	 * Parameter:<br>
	 * Dữ liệu lấy từ core
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "interest")
	private Set<InterestParameter> interestParameters;

	/** The interest upload file. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INTEREST_UPLOAD_FILE")
	private InterestUploadFile interestUploadFile;

	@XmlTransient
	public InterestUploadFile getInterestUploadFile() {
		return this.interestUploadFile;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Interest))
			return false;
		if (this.getId() == null || ((Interest) obj).getId() == null)
			return false;
		return this.getId().equals(((Interest) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
