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

	/** M?? ????n v??? **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH")
	private Branch branch;

	/** M?? kh??ch h??ng **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER")
	private Customer customer;

	/** M?? s???n ph???m **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT")
	private Product product;

	/** Lo???i ti???n **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY")
	private Currency currency;

	/** S??? ???n ch??? **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SAVING_BOOK_SERIAL")
	private SavingBookSerial savingBookSerial;

	/** S??? t??i kho???n **/
	@Column(name = "ACCOUNT_NO", length = 50)
	private String accountNo;

	/** T??n t??i kho???n **/
	@Column(name = "ACCOUNT_NAME", length = 200)
	private String accountName;

	/** Ng??y m??? t??i kho???n **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPEN_DATE")
	private Date openDate;

	/** Tr???ng th??i t??i kho???n **/
	@Column(name = "ACCOUNT_STATUS", length = 200)
	private String accountStatus;

	/** L??i su???t ni??m y???t **/
	@Column(name = "INTEREST_RATE")
	private float interestRate;

	/** Bi??n ????? **/
	@Column(name = "AMPLITUDE")
	private float amplitude;

	/** L??i su???t hi???u l???c **/
	@Column(name = "VALID_RATE")
	private float validRate;

	/** S??? d?? t??i kho???n **/
	@Transient
	private double balance;

	/** S??? d?? phong to??? **/
	@Transient
	private double blockadeAmount;

	/** S??? d?? kh??? d???ng **/
	@Transient
	private double availableAmount;

	/** S??? ti???n giao d???ch ch??a ???????c duy???t **/
	@Transient
	private double pendingAmount;

	/** H??nh th???c nh???n s??? ph??? **/
	@Column(name = "SUBSIDIARY_LEDGER_TYPE", length = 200)
	private String subsidiaryLedgerType;

	/** H??nh th???c nh???n s??? ph???(Kh??c) **/
	@Column(name = "SUBSIDIARY_LEDGER_OTHER", length = 200)
	private String subsidiaryLedgerOther;

	/** ?????nh k??? nh???n s??? ph??? **/
	@Column(name = "SUBSIDIARY_LEDGER_PERIOD", length = 200)
	private String subsidiaryLedgerPeriod;

	/** Ng?????i gi???i thi???u **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REFERRAL_USER")
	private ReferralUser referralUser;

	/** Kh??ng ghi n??? **/
	@Column(name = "IS_NO_DEBIT")
	private boolean isNoDebit;

	/** Kh??ng ghi c?? **/
	@Column(name = "IS_NO_CREDIT")
	private boolean isNoCredit;

	/** ????ng b??ng **/
	@Column(name = "IS_FROZEN")
	private boolean isFrozen;

	/** Ng??? ????ng **/
	@Column(name = "IS_HIBERNATE")
	private boolean isHibernate;

	/** Th???u chi **/
	@Column(name = "IS_OVERDRAFF")
	private boolean isOverDraff;

	/** T??i kho???n d???ng thanh to??n **/
	@Column(name = "IS_STOP_ACCOUNT")
	private boolean isStopAccount;

	/** Cho ph??p t??i kho???n ghi nh???n b??t to??n **/
	@Column(name = "IS_ALLOW_ENTRY")
	private boolean isAllowEntry;

	/** Cho ph??p thay ?????i tr???ng th??i t??? ?????ng **/
	@Column(name = "IS_ALLOW_CHANGE_STATUS")
	private boolean isAllowChangeStatus;

	/** Cho ph??p thay ?????i tr???ng th??i t??? ?????ng(T??nh tr???ng) **/
	@Column(name = "ALLOW_CHANGESTATUS_TYPE", length = 200)
	private String allowChangeStatusType;

	/** Cho ph??p thay ?????i tr???ng th??i t??? ?????ng(Ng??y hi???u l???c) **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ALLOW_CHANGESTATUS_DATE")
	private Date allowChangeStatusDate;

	/** T??? ?????ng phong t???a **/
	@Column(name = "IS_AUTO_BLOCK")
	private boolean isAutoBlock;

	/** T??? ?????ng phong to???(lo???i) **/
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
