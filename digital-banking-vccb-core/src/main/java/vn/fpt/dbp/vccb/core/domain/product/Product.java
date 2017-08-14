package vn.fpt.dbp.vccb.core.domain.product;

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

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.base.WorkFlowModel;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Product.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Product extends WorkFlowModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public Product() {
	}

	public Product(Long id) {
		this.id = id;
	}

	public Product(int id) {
		this.id = new Long(id);
	}

	public Product(String id) {
		this.id = new Long(id);
	}

	/**
	 * Mã sản phẩm
	 */
	@Column(name = "CODE", updatable = true, insertable = true, length = 50)
	private String code;

	/**
	 * Tên sản phẩm
	 */
	@Column(name = "NAME", updatable = true, insertable = true, length = 200)
	private String name;

	/**
	 * Loại hình tiền gửi<br>
	 * 1: Tiền gửi không kỳ hạn<br>
	 * 2: Tiết kiệm không kỳ hạn<br>
	 * 3: Tiền gửi, tiết kiệm có kỳ hạn
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPOSIT_TYPE")
	private DepositType depositType;

	/**
	 * Phong tỏa tự động<br>
	 * Checkbox
	 */
	@Column(name = "IS_AUTO_BLOCK", updatable = true, insertable = true)
	private Boolean isAutoBlock;

	/**
	 * Phong tỏa tự động<br>
	 * 1: Toàn bộ số dư <br>
	 * 2: Giao dịch ghi có
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTO_BLOCK_TYPE")
	private AutoBlockType autoBlockType;

	/**
	 * Chuyển nhượng<br>
	 * True: Cho phép<br>
	 * False: Không cho phép
	 */
	@Column(name = "TRANSFERABLE", updatable = true, insertable = true)
	private Boolean transferable;

	/**
	 * Ngày hiệu lực
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EFFECTED_DATE")
	private Date effectedDate;

	/**
	 * Ngày hết hạn
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRED_DATE")
	private Date expiredDate;

	/**
	 * Độ tuổi<br>
	 * Tối thiểu
	 */
	@Column(name = "AGE_FROM", updatable = true, insertable = true)
	private Integer ageFrom;

	/**
	 * Độ tuổi<br>
	 * Tối đa
	 */
	@Column(name = "AGE_TO", updatable = true, insertable = true)
	private Integer ageTo;

	/**
	 * Giới tính<br>
	 * 1: Nam<br>
	 * 2: Nữ<br>
	 * 3: Tất cả
	 */
	@Column(name = "GENDER", updatable = true, insertable = true, length = 20)
	private String gender;

	/**
	 * Ghi chú
	 */
	@Column(name = "DESCRIPTION", updatable = true, insertable = true, length = 200)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	@Deprecated
	private Set<AccountClass> accountClasses;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	private Set<ProductAccountClass> productAccountClasses;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	private Set<ProductCustomer> productCustomers;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	private Set<ProductLimit> productLimits;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	private Set<ProductPromotion> productPromotions;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Product))
			return false;
		if (this.getId() == null || ((Product) obj).getId() == null)
			return false;
		return this.getId().equals(((Product) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
