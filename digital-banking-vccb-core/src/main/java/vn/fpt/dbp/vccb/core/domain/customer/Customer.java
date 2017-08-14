package vn.fpt.dbp.vccb.core.domain.customer;

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
import vn.fpt.dbp.vccb.core.domain.organization.Address;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;
import vn.fpt.dbp.vccb.core.domain.organization.City;
import vn.fpt.dbp.vccb.core.domain.organization.Country;
import vn.fpt.dbp.vccb.core.domain.organization.Language;
import vn.fpt.dbp.vccb.core.domain.organization.Nationality;

/**
 * Entity: Khách hàng
 * 
 */
@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Customer.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Customer extends WorkFlowModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * @Structor<br>
	 * 
	 */
	public Customer() {
	}

	/**
	 * Structor<br>
	 * 
	 * @param Long
	 *            id
	 */
	public Customer(Long id) {
		this.id = id;
	}

	/**
	 * @param int
	 *            id
	 */
	public Customer(int id) {
		this.id = new Long(id);
	}

	/**
	 * Structor<br>
	 * 
	 * @param String
	 *            id
	 */
	public Customer(String id) {
		this.id = new Long(id);
	}

	/** Tên đơn vị **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCH")
	private Branch branch;

	/** Mã khách hàng **/
	@Column(name = "CODE", length = 50)
	private String code;

	/** Loại khách hàng(I,C,B) **/
	@Column(name = "TYPE_CODE", length = 50)
	@Deprecated
	private String typeCode;

	/** Trạng thái khách hàng **/
	@Column(name = "STATUS", length = 50)
	private String status;

	/** Họ và tên khách hàng /Tên Pháp nhân/Tên định chế ( Tên đầy đủ) **/
	@Column(name = "FULL_NAME", length = 200)
	private String fullName;

	/** Họ và tên khách hàng /Tên Pháp nhân/Tên định chế( Tên viết tắt) **/
	@Column(name = "SHORT_NAME", length = 200)
	private String shortName;

	/** Số giấy tờ pháp lý **/
	@Column(name = "LEGAL_DOCS_NUMBER", length = 50)
	private String legalDocsNumber;

	/** Loại hình khách hàng **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_TYPE")
	private CustomerType customerType;

	/** Mã SWIFT **/
	@Column(name = "SWIFT_CODE", length = 50)
	private String swiftCode;

	/** Mã REUTER **/
	@Column(name = "REUTER_CODE", length = 50)
	private String reuterCode;

	/** Mã CITAD **/
	@Column(name = "CITAD_CODE", length = 50)
	private String citadCode;

	/** Địa chỉ liên hệ/Địa chỉ Đăng ký kinh doanh **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS")
	private Address address;

	/** Ngày mở mã khách hàng **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVE_DATE")
	private Date activeDate;

	/** Tên gọi khác/Tên nước ngoài **/
	@Column(name = "OTHER_NAME", length = 200)
	private String otherName;

	/** Giới tính **/
	@Column(name = "GENDER", length = 50)
	private String gender;

	/** Ngày sinh/Ngày tháng thành lập **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BIRTHDAY")
	private Date birthDay;

	/** Nơi sinh **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BIRTH_PLACE")
	private City birthPlace;

	/** Nước thành lập **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FOUNDED_COUNTRY")
	private Country foundedCountry;

	/** Nghề nghiệp **/
	@Column(name = "JOB", length = 200)
	private String job;

	/** Chức vụ **/
	@Column(name = "POSITION", length = 200)
	private String position;

	// /** Chức vụ **/
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "JOB_POSITION")
	// private Position jobPosition;
	//
	// /** Chức vụ (Khác) **/
	// @Column(name = "JOB_POSITION_OTHER", length = 50)
	// private String jobPositionOther;

	/** Người chưa thành niên/ hạn chế/ mất NLHVDS/khó khăn trong NT, LCHV **/
	@Column(name = "IS_LIMIT_PERSION")
	private Boolean isLimitPersion;

	/** Mã khách hàng người đại diện pháp luật **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPRESENTATION_CUSTOMER")
	private Customer representationCustomer;

	/** Cán bộ nhân viên **/
	@Column(name = "IS_VCCB_USER")
	private Boolean isVccbUser;

	/** Mã nhân viên **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VCCB_USER")
	private ReferralUser vccbUser;

	/** Quốc tịch 1/Nước thành lập **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NATIONALITY")
	private Nationality nationality;

	/** Cư trú **/
	@Column(name = "RESIDENT", length = 200)
	private String resident;

	/** Giấy tờ pháp lý **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEGAL_DOCS_TYPE")
	private LegalDocsType legalDocsType;

	/** Ngày cấp **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LEGAL_DOCS_VALID_DATE")
	private Date legalDocsValidDate;

	/** Nơi cấp **/
	@Column(name = "LEGAL_DOCS_REGIST_PLACE", length = 200)
	private String legalDocsRegistPlace;

	/** Ngày hết hạn **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LEGAL_DOCS_EXPIRED_DATE")
	private Date legalDocsExpiredDate;

	/** Địa chỉ thường trú tại quốc gia mang quốc tịch 1 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_NATIONAL_RESIDENT")
	private Address addressNationalResident;

	/** Địa chỉ đăng ký cư trú tại Việt Nam **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_VN_RESIDENT")
	private Address addressVnResident;

	/** SĐT cố định /Điện thoại **/
	@Column(name = "PHONE_NUMBER_HOME", length = 50)
	private String phoneNumberHome;

	/** SĐT di động **/
	@Column(name = "PHONE_NUMBER_CONTACT", length = 50)
	private String phoneNumberContact;

	/** Email **/
	@Column(name = "EMAIL_ADDRESS", length = 200)
	private String emailAddress;

	/** Nhóm Khách hàng **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_GROUP")
	private CustomerGroup customerGroup;

	/** Xếp hạng khách hàng **/
	@Column(name = "CUSTOMER_GRADE", length = 50)
	private String customerGrade;

	/** Quốc gia **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTRY")
	private Country country;

	/** Ngôn ngữ **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LANGUAGE")
	private Language language;

	/** Đối tượng hạn chế cấp tín dụng **/
	@Column(name = "LIMIT_CREDIT_SERVICE", length = 50)
	private String limitCreditService;

	/** Thông tin kê khai theo luật thuế Hoa Kỳ **/
	@Column(name = "US_TAX_LAW_INFO", length = 50)
	private String usTaxLawInfo;

	/** Loại người giới thiệu **/
	@Column(name = "REFERRAL_TYPE", length = 50)
	private String referralType;

	/** Mã người giới thiệu(Khách hàng) **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REFERRAL_CUSTOMER")
	private Customer referralCustomer;

	/** Mã người giới thiệu(CBNV) **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REFERRAL_USER")
	private ReferralUser referralUser;

	/** Tên Người giới thiệu **/
	@Column(name = "REFERRAL_USER_NAME", length = 200)
	private String referralUserName;

	/** Quốc tịch 2 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NATIONALITY2")
	private Nationality nationality2;

	/** Cư trú 2 **/
	@Column(name = "RESIDENT2", length = 200)
	private String resident2;

	/** Giấy tờ pháp lý 2 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEGAL_DOCS_TYPE2")
	private LegalDocsType legalDocsType2;

	/** Số giấy tờ pháp lý 2 **/
	@Column(name = "LEGAL_DOCS_NUMBER2", length = 50)
	private String legalDocsNumber2;

	/** Ngày cấp 2 **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LEGAL_DOCS_VALID_DATE2")
	private Date legalDocsValidDate2;

	/** Nơi cấp 2 **/
	@Column(name = "LEGAL_DOCS_REGIST_PLACE2", length = 200)
	private String legalDocsRegistPlace2;

	/** Ngày hết hạn 2 **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LEGAL_DOCS_EXPIRED_DATE2")
	private Date legalDocsExpiredDate2;

	/** Địa chỉ thường trú tại quốc gia mang quốc tịch 2 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_NATIONAL_RESIDENT2")
	private Address addressNationalResident2;

	/** Thu nhập/Doanh thu **/
	@Column(name = "INCOME_VALUE", length = 50)
	private String incomeValue;

	/** Thị thực nhập cảnh **/
	@Column(name = "VISA", length = 50)
	private String visa;

	/** Ngày cấp visa **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VISA_VALID_DATE")
	private Date visaValidDate;

	/** Ngày hết hạn visa **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VISA_EXPIRED_DATE")
	private Date visaExpiredDate;

	/** Nơi cấp visa **/
	@Column(name = "VISA_REGIST_PLACE", length = 200)
	private String visaRegistPlace;

	/** Tình trạng hôn nhân **/
	@Column(name = "WEDLOCK", length = 50)
	private String wedlock;

	/** Số người phụ thuộc **/
	@Column(name = "DEPENDED_PERSION_NUMBER")
	private Integer dependedPersionNumber;

	/** Trình độ học vấn **/
	@Column(name = "EDUCATION_LEVEL", length = 50)
	private String educationLevel;

	/** Hình thức sở hữu nhà **/
	@Column(name = "HOUSE_OWNER_TYPE", length = 50)
	private String houseOwnerType;

	/** Tên cơ quan **/
	@Column(name = "WORK_COMPANY_NAME", length = 200)
	private String workCompanyName;

	/** Địa chỉ cơ quan **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WORK_COMPANY_ADDRESS")
	private Address workCompanyAddress;

	/**
	 * Loại hình tổ chức (theo GCNĐKKD của khách hàng)/Loại hình định chế (theo
	 * GCNĐKKDcủa khách hàng)
	 **/
	@Column(name = "ORGANIZATION_TYPE", length = 200)
	private String organizationType;

	/** Lĩnh vực hoạt động, kinh doanh **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUSINESS_SECTOR")
	private BusinessSector businessSector;

	/** Địa chỉ giao dịch (nếu không trùng với địa chỉ đăng ký kinh doanh) **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_TRADING")
	private Address addressTrading;

	/** Số Fax **/
	@Column(name = "FAX_NUMBER", length = 50)
	private String faxNumber;

	/** Quy mô pháp nhân **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_SIZE")
	private CustomerSize customerSize;

	// /** Loại ĐCTC **/
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "FINANCIAL_INSTITUTIONS_TYPE")
	// private FinancialInstitutionsType financialInstitutionsType;

	/** Mã số thuế **/
	@Column(name = "TAX_NUMBER", length = 50)
	private String taxNumber;

	/** Mã GIIN **/
	@Column(name = "GIIN_CODE", length = 50)
	private String giinCode;

	/** Tài khoản tiền gửi thanh toán **/
	@Column(name = "IS_DEBIT_ACCOUNT")
	private Boolean isDebitAccount;

	/** Số tài khoản **/
	@Column(name = "ACCOUNT_NUMBER", length = 50)
	private String accountNumber;

	/** Loại tiền của TKTT **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENCY")
	private Currency currency;

	/** Người quản lý tài khoản **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_MANAGER")
	private ReferralUser accountManager;

	/** Đăng ký dịch vụ thông báo biến động số dư **/
	@Column(name = "IS_CHANGED_ALERT")
	private Boolean isChangedAlert;

	/** SĐT đăng ký thông báo biến động số dư **/
	@Column(name = "CHANGED_ALERT_NUMBER", length = 50)
	private String changedAlertNumber;

	/** Nhận các thông tin giới thiệu sản phẩm, dịch vụ của Ngân hàng **/
	@Column(name = "RECEIVED_ADS_INFO")
	private Boolean receivedAdsInfo;

	/**
	 * Nhận và gửi các yêu cầu liên quan đến việc thực hiện dịch vụ của Ngân
	 * hàng
	 **/
	@Column(name = "RECEIVED_SERVICE_INFO")
	private Boolean receivedServiceInfo;

	/** Miễn phí dịch vụ thông báo biến động số dư **/
	@Column(name = "IS_ALERT_FEE_FREE")
	private Boolean isAlertFeeFree;

	/** Miễn phí dịch vụ QLTK **/
	@Column(name = "IS_MANAGER_FEE_FREE")
	private Boolean isManagerFeeFree;

	/** Đăng ký dịch vụ Ngân hàng Điện tử **/
	@Column(name = "IS_REGISTED_EBANKING")
	private Boolean isRegistedEbanking;

	/** Số điện thoại Đăng ký Ngân hàng điện tử **/
	@Column(name = "EBANKING_NUMBER", length = 50)
	private String ebankingNumber;

	/** Email Đăng ký Ngân hàng điện tử **/
	@Column(name = "EBANKING_EMAIL", length = 200)
	private String ebankingEmail;

	/** Đăng ký dịch vụ thẻ **/
	@Column(name = "IS_REGISTED_CARD_SERVICE")
	private Boolean isRegistedCardService;

	/** Nhóm thu phí **/
	@Column(name = "FEE_TYPE", length = 50)
	private String feeType;

	/** Tài khoản thanh toán chung **/
	@Column(name = "IS_COMMON_DEBIT_ACCOUNT")
	private Boolean isCommonDebitAccount;

	/** Loại tiền của tài khoản thanh toán chung **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMMON_CURRENCY")
	private Currency commonCurrency;

	/** Đăng ký dịch vụ Chi lương hộ **/
	@Column(name = "IS_SALARY_DISTRIBUTE")
	private Boolean isSalaryDistribute;

	/** Đăng ký dịch vụ giao dịch qua Fax **/
	@Column(name = "IS_SERVICE_BY_FAX")
	private Boolean isServiceByFax;

	/** Định kỳ nhận sổ phụ **/
	@Column(name = "SUBSIDIARY_LEDGER_PERIOD", length = 50)
	private String subsidiaryLedgerPeriod;

	/** Hình thức nhận sổ phụ **/
	@Column(name = "SUBSIDIARY_LEDGER_TYPE", length = 50)
	private String subsidiaryLedgerType;

	/** Hình thức nhận sổ phụ(Khác) **/
	@Column(name = "SUBSIDIARY_LEDGER_OTHER", length = 50)
	private String subsidiaryLedgerOther;

	/** Đăng ký mở tài khoản khác(Checkbox) **/
	@Column(name = "IS_OPENED_ACCOUNT_INFO")
	private Boolean isOpenedAccountInfo;

	/** Đăng ký mở tài khoản khác **/
	@Column(name = "ACCOUNT_TYPE", length = 50)
	private String accountType;

	/** Loại tiền tài khoản khác **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_CURRENCY")
	private Currency accountCurrency;

	/** Thông tin mở Thẻ(Checkbox) **/
	@Column(name = "IS_OPENED_CARD")
	private Boolean isOpenedCard;

	/** Loại thẻ đăng ký **/
	@Column(name = "CARD_TYPE", length = 50)
	private String cardType;

	/** Số tài khoản (Khác) **/
	@Column(name = "ACCOUNT_NUMBER_OTHER", length = 50)
	private String accountNumberOther;

	/** Nơi nhận thẻ & PIN **/
	@Column(name = "PIN_RECEIVED_PLACE", length = 200)
	private String pinReceivedPlace;

	/** Tên đơn vị(nhận thẻ & PIN) **/
	@Column(name = "PIN_RECEIVED_BRANCH_NAME", length = 50)
	private String pinReceivedBranchName;

	/** Thông tin đăng ký chi lương(checkbox) **/
	@Column(name = "IS_SALARY_PAYMENT")
	private Boolean isSalaryPayment;

	/** Tên công ty **/
	@Column(name = "COMPANY_NAME", length = 200)
	private String companyName;

	/** Điện thoại công ty **/
	@Column(name = "COMPANY_PHONE_NUMBER", length = 50)
	private String companyPhoneNumber;

	/** Địa chỉ công ty **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPANY_ADDRESS")
	private Address companyAddress;

	/** Mã khách hàng CBNV **/
	@Column(name = "USER_CODE", length = 50)
	private String userCode;

	/** Số tài khoản người giới thiệu **/
	@Column(name = "REF_CUSTOMER_ACCOUNT_NUMBER", length = 50)
	private String refCustomerAccountNumber;

	/** Họ tên người giới thiệu **/
	@Column(name = "REF_CUSTOMER_NAME", length = 200)
	private String refCustomerName;

	/** Hình thức nhận thông báo(checkbox) **/
	@Column(name = "IS_NOTIFICATION")
	private Boolean isNotification;

	/** Hình thức nhận thông báo: địa chỉ **/
	@Column(name = "IS_NOTIFICATION_ADDRESS")
	private Boolean isNotificationAddress;

	/** Hình thức nhận thông báo: email **/
	@Column(name = "IS_NOTIFICATION_EMAIL")
	private Boolean isNotificationEmail;

	/** Hình thức nhận thông báo: ĐT di động **/
	@Column(name = "IS_NOTIFICATION_MOBILE")
	private Boolean isNotificationMobile;

	/** Hình thức nhận thông báo: Khác **/
	@Column(name = "IS_NOTIFICATION_OTHER")
	private Boolean isNotificationOther;

	/** Hình thức nhận thông báo(Khác) **/
	@Column(name = "NOTIFICATION_OTHER", length = 200)
	private String notificationOther;

	/** Thông tin Phân quyền Internet Banking(checkbox) **/
	@Column(name = "IS_AUTHOR_INTERNET_BANKING")
	private Boolean isAuthorInternetBanking;

	/** Họ tên **/
	@Column(name = "EBANKING_USER_NAME", length = 200)
	private String ebankingUserName;

	/** Ngày sinh **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EBANKING_USER_BIRTHDATE")
	private Date ebankingUserBirthdate;

	/** Email **/
	@Column(name = "EBANKING_USER_EMAIL", length = 200)
	private String ebankingUserEmail;

	/** ĐT di động **/
	@Column(name = "EBANKING_USER_MOBILE")
	private String ebankingUserMobile;

	/** Quyền trên Internet Banking_Xem **/
	@Column(name = "IS_EBANKING_AUTHORIZE_VIEW")
	private Boolean isEbankingAuthorizeView;

	/** Quyền trên Internet Banking_Nhập **/
	@Column(name = "IS_EBANKING_AUTHORIZE_INPUT")
	private Boolean isEbankingAuthorizeInput;

	/** Quyền trên Internet Banking_Duyệt **/
	@Column(name = "IS_EBANKING_AUTHORIZE_APPROVE")
	private Boolean isEbankingAuthorizeApprove;

	/** Quyền trên Internet Banking_Đi thẳng **/
	@Column(name = "IS_EBANKING_AUTHORIZE_THROW")
	private Boolean isEbankingAuthorizeThrow;

	/** Ghi chú **/
	@Column(name = "DESCRIPTION", length = 500)
	private String description;

	/** Tài khoản Sở KHĐT **/
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<CustomerAccountNo> customerAccountNos;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<CustomerFile> customerFiles;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<CustomerImage> customerImages;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<CustomerManager> customerManagers;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<CustomerPaymentInfo> customerPaymentInfos;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerFrom")
	private Set<CustomerRefCustomer> customerRefCustomers;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<CustomerRefPerson> customerRefPersons;

	@XmlTransient
	public Customer getReferralCustomer() {
		return this.referralCustomer;
	}

	@XmlTransient
	public Customer getRepresentationCustomer() {
		return this.representationCustomer;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Customer))
			return false;
		if (this.getId() == null || ((Customer) obj).getId() == null)
			return false;
		return this.getId().equals(((Customer) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
