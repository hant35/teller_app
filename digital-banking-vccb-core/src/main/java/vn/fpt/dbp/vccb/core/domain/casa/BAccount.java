package vn.fpt.dbp.vccb.core.domain.casa;

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
import javax.persistence.Transient;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.base.WorkFlowModel;
import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.customer.Customer;
import vn.fpt.dbp.vccb.core.domain.customer.ReferralUser;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;
import vn.fpt.dbp.vccb.core.domain.product.AutoBlockType;
import vn.fpt.dbp.vccb.core.domain.product.Product;

@Entity
@Table(name = "B_ACCOUNT")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = BAccount.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class BAccount extends WorkFlowModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4048941306193942423L;

	/**
	 * @Structor<br>
	 * 
	 */
	public BAccount() {
	}

	/**
	 * Structor<br>
	 * 
	 * @param Long
	 *            id
	 */
	public BAccount(Long id) {
		this.id = id;
	}

	/**
	 * @param int
	 *            id
	 */
	public BAccount(int id) {
		this.id = new Long(id);
	}

	/**
	 * Structor<br>
	 * 
	 * @param String
	 *            id
	 */
	public BAccount(String id) {
		this.id = new Long(id);
	}

	/** Mã Đơn vị **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH")
	private Branch branch;

	/** Mã khách hàng **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER")
	private Customer customer;

	/** Mã sản phẩm **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT")
	private Product product;

	/** Loại tiền **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY")
	private Currency currency;

	/** Số ấn chỉ **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SAVING_BOOK_SERIAL")
	private SavingBookSerial savingBookSerial;

	/** Số tài khoản **/
	@Column(name = "ACCOUNT_NO", length = 50)
	private String accountNo;

	/** Tên tài khoản **/
	@Column(name = "ACCOUNT_NAME", length = 200)
	private String accountName;

	/** Ngày mở tài khoản **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPEN_DATE")
	private Date openDate;

	/** Trạng thái tài khoản **/
	@Column(name = "ACCOUNT_STATUS", length = 200)
	private String accountStatus;

	/** Lãi suất niêm yết **/
	@Column(name = "INTEREST_RATE")
	private float interestRate;

	/** Biên độ **/
	@Column(name = "AMPLITUDE")
	private float amplitude;

	/** Lãi suất hiệu lực **/
	@Column(name = "VALID_RATE")
	private float validRate;

	/** Số dư tài khoản **/
	@Transient
	private double balance;

	/** Số dư phong toả **/
	@Transient
	private double blockadeAmount;

	/** Số dư khả dụng **/
	@Transient
	private double availableAmount;

	/** Số tiền giao dịch chưa được duyệt **/
	@Transient
	private double pendingAmount;

	/** Hình thức nhận sổ phụ **/
	@Column(name = "SUBSIDIARY_LEDGER_TYPE", length = 200)
	private String subsidiaryLedgerType;

	/** Hình thức nhận sổ phụ(Khác) **/
	@Column(name = "SUBSIDIARY_LEDGER_OTHER", length = 200)
	private String subsidiaryLedgerOther;

	/** Định kỳ nhận sổ phụ **/
	@Column(name = "SUBSIDIARY_LEDGER_PERIOD", length = 200)
	private String subsidiaryLedgerPeriod;

	/** Người giới thiệu **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REFERRAL_USER")
	private ReferralUser referralUser;

	/** Không ghi nợ **/
	@Column(name = "IS_NO_DEBIT")
	private boolean isNoDebit;

	/** Không ghi có **/
	@Column(name = "IS_NO_CREDIT")
	private boolean isNoCredit;

	/** Đóng băng **/
	@Column(name = "IS_FROZEN")
	private boolean isFrozen;

	/** Ngủ đông **/
	@Column(name = "IS_HIBERNATE")
	private boolean isHibernate;

	/** Thấu chi **/
	@Column(name = "IS_OVERDRAFF")
	private boolean isOverDraff;

	/** Tài khoản dừng thanh toán **/
	@Column(name = "IS_STOP_ACCOUNT")
	private boolean isStopAccount;

	/** Cho phép tài khoản ghi nhận bút toán **/
	@Column(name = "IS_ALLOW_ENTRY")
	private boolean isAllowEntry;

	/** Cho phép thay đổi trạng thái tự động **/
	@Column(name = "IS_ALLOW_CHANGE_STATUS")
	private boolean isAllowChangeStatus;

	/** Cho phép thay đổi trạng thái tự động(Tình trạng) **/
	@Column(name = "ALLOW_CHANGESTATUS_TYPE", length = 200)
	private String allowChangeStatusType;

	/** Cho phép thay đổi trạng thái tự động(Ngày hiệu lực) **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ALLOW_CHANGESTATUS_DATE")
	private Date allowChangeStatusDate;

	/** Tự động phong tỏa **/
	@Column(name = "IS_AUTO_BLOCK")
	private boolean isAutoBlock;

	/** Tự động phong toả(loại) **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTO_BLOCK_TYPE")
	private AutoBlockType autoBlockType;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bAccount")
	private Set<AccountBlockade> accountBlockades;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bAccount")
	private Set<AccountCheque> accountCheques;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bAccount")
	private Set<AccountInterest> accountInterests;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bAccount")
	private Set<AccountRefPerson> accountRefPersons;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bAccount")
	private Set<AccountService> accountServices;

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof BAccount))
			return false;
		if (this.getId() == null || ((BAccount) obj).getId() == null)
			return false;
		return this.getId().equals(((BAccount) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
