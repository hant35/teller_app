package vn.fpt.dbp.vccb.core.domain.product;

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
import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.interest.TimeRate;
import vn.fpt.dbp.vccb.core.domain.organization.Area;

@Entity
@Table(name = "ACCOUNT_CLASS")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AccountClass.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@Deprecated
public class AccountClass implements Serializable {

	/**
	 * SerialVersion
	 */
	private static final long serialVersionUID = 1L;

	public AccountClass() {
	}

	public AccountClass(Long id) {
		this.id = id;
	}

	public AccountClass(int id) {
		this.id = new Long(id);
	}

	public AccountClass(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "ACCOUNT_CLASS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/**
	 * Mã account class
	 */
	@Column(name = "CODE", updatable = true, insertable = true, length = 50)
	private String code;

	/**
	 * Tên account class
	 */
	@Column(name = "NAME", updatable = true, insertable = true, length = 200)
	private String name;

	/**
	 * Kỳ hạn
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TIME_RATE")
	private TimeRate timeRate;

	/**
	 * Loại tiền<br>
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY")
	private Currency currency;

	/**
	 * Định kỳ gửi
	 */
	@Column(name = "PERIOD_DEPOSIT", updatable = true, insertable = true)
	private Double periodDeposit;

	/**
	 * Số tiền gửi tối thiểu cho mỗi định kỳ gửi
	 */
	@Column(name = "PERIOD_DEPOSIT_MIN", updatable = true, insertable = true)
	private Double periodDepositMin;

	/**
	 * Số tiền gửi tối thiểu
	 */
	@Column(name = "DEPOSIT_MIN", updatable = true, insertable = true)
	private Double depositMin;

	/**
	 * Số tiền gửi tối đa
	 */
	@Column(name = "DEPOSIT_MAX", updatable = true, insertable = true)
	private Double depositMax;

	/**
	 * Sản phẩm chuyển khi đáo hạn
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATURE_TO_ACCOUNT_CLASS")
	private AccountClass matureToAccountClass;

	/**
	 * Hình thức đáo hạn
	 */
	@Column(name = "MATURE_FORM", updatable = true, insertable = true, length = 200)
	private String matureForm;

	/**
	 * Xếp hạng khách hàng<br>
	 * 1: Vip vàng<br>
	 * 2: Vip bạc<br>
	 * 3: Thân thiết<br>
	 * 4: Tất cả
	 */
	@Column(name = "CUSTOMER_GRADE", updatable = true, insertable = true)
	private String customerGrade;

	/**
	 * Khu vực
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA")
	private Area area;

	/**
	 * Số dư duy trì tối thiểu
	 */
	@Column(name = "BALANCE_MIN", updatable = true, insertable = true)
	private Double balanceMin;

	/**
	 * Sản phầm
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT")
	private Product product;

	@XmlTransient
	public Product getProduct() {
		return this.product;
	}
	
	@XmlTransient
	public AccountClass getMatureToAccountClass() {
		return this.matureToAccountClass;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof AccountClass))
			return false;
		if (this.getId() == null || ((AccountClass) obj).getId() == null)
			return false;
		return this.getId().equals(((AccountClass) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
