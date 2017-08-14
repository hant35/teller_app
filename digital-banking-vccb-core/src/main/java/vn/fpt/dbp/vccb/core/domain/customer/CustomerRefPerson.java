package vn.fpt.dbp.vccb.core.domain.customer;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import org.kie.api.remote.Remotable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import vn.fpt.dbp.vccb.core.domain.organization.Address;
import vn.fpt.dbp.vccb.core.domain.organization.Nationality;

/*
 * Nguoi lien quan cua Customer
 */
@Entity
@Table(name = "CUSTOMER_REF_PERSON")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CustomerRefPerson.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Remotable
public class CustomerRefPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	public CustomerRefPerson() {
	}

	public CustomerRefPerson(Long id) {
		this.id = id;
	}

	public CustomerRefPerson(int id) {
		this.id = new Long(id);
	}

	public CustomerRefPerson(String id) {
		this.id = new Long(id);
	}

	@Id
	@SequenceGenerator(name = "ID_SEQUENCE_GENERATOR", sequenceName = "CUSTOMER_REF_PERSON_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE_GENERATOR")
	protected Long id;

	/**  **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER")
	private Customer customer;

	/** Người đại diện theo pháp luật **/
	@Column(name = "IS_REPRESENTATION_AS_LAW")
	private Boolean isRepresentationAsLaw;

	/** Mã khách hàng **/
	@Column(name = "CUSTOMER_CODE", length = 50)
	private String customerCode;

	/** Mối quan hệ **/
	@Column(name = "TYPE", length = 50)
	private String type;

	/** Mô tả mối quan hệ khác **/
	@Column(name = "TYPE2", length = 50)
	private String type2;

	/** Họ và tên (tên đầy đủ) **/
	@Column(name = "FULL_NAME", length = 200)
	private String fullName;

	/** Họ và tên (tên viết tắt) **/
	@Column(name = "SHORT_NAME", length = 200)
	private String shortName;

	/** Tên gọi khác **/
	@Column(name = "OTHER_NAME", length = 200)
	private String otherName;

	/** Giới tính **/
	@Column(name = "GENDER", length = 50)
	private String gender;

	/** Ngày sinh **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BIRTHDAY")
	private Date birthday;

	/** Nơi sinh **/
	@Column(name = "BIRTH_PLACE", length = 200)
	private String birthPlace;

	/** Điện thoại liên hệ **/
	@Column(name = "PHONE_NUMBER", length = 50)
	private String phoneNumber;

	/** Email **/
	@Column(name = "EMAIL_ADDRESS", length = 200)
	private String emailAddress;

	/** Nghề nghiệp **/
	@Column(name = "JOB", length = 50)
	private String job;

	// /** Chức vụ **/
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "JOB_POSITION")
	// private Position jobPosition;
	/** Chức vụ **/
	@Column(name = "POSITION", length = 200)
	private String position;

	/** Chức vụ (Khác) **/
	@Column(name = "JOB_POSITION_OTHER", length = 50)
	private String jobPositionOther;

	/** Quốc tịch 1 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NATIONALITY")
	private Nationality nationality;

	/** Cư trú **/
	@Column(name = "RESIDENT", length = 50)
	private String resident;

	/** Giấy tờ pháp lý **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEGAL_DOCS_TYPE")
	private LegalDocsType legalDocsType;

	/** Số giấy tờ pháp lý **/
	@Column(name = "LEGAL_DOCS_NUMBER", length = 50)
	private String legalDocsNumber;

	/** Ngày cấp **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LEGAL_DOCSVALID_DATE")
	private Date legalDocsValidDate;

	/** Ngày hết hạn **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LEGAL_DOCS_EXPIRED_DATE")
	private Date legalDocsExpiredDate;

	/** Nơi cấp **/
	@Column(name = "LEGAL_DOCS_REGIST_PLACE", length = 200)
	private String legalDocsRegistPlace;

	/** Địa chỉ thường trú tại quốc gia mang quốc tịch 1 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_NATIONAL_RESIDENT")
	private Address addressNationalResident;

	/** Địa chỉ đăng ký cư trú tại Việt Nam **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_VN_RESIDENT")
	private Address addressVnResident;

	/** Địa chỉ liên hệ **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS")
	private Address address;

	/** Thông tin kê khai theo luật thuế Hoa Kỳ **/
	@Column(name = "US_TAX_LAW_INFO", length = 50)
	private String usTaxLawInfo;

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

	/** Ngày hết hạn **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LEGAL_DOCS_EXPIRED_DATE2")
	private Date legalDocsExpiredDate2;

	/** Nơi cấp **/
	@Column(name = "LEGAL_DOCS_REGIST_PLACE2", length = 200)
	private String legalDocsRegistPlace2;

	/** Địa chỉ thường trú tại quốc gia mang quốc tịch 2 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_NATIONAL_RESIDENT2")
	private Address addressNationalResident2;

	/** Thị thực nhập cảnh **/
	@Column(name = "VISA", length = 50)
	private String visa;

	/** Ngày cấp Visa **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VISA_VALID_DATE")
	private Date visaValidDate;

	/** Ngày hết hạn Visa **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VISA_EXPIRED_DATE")
	private Date visaExpiredDate;

	/** Nơi cấp Visa **/
	@Column(name = "VISA_REGIST_PLACE", length = 200)
	private String visaRegistPlace;

	/** Thu nhập **/
	@Column(name = "INCOME_VALUE", length = 50)
	private String incomeValue;

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

	/** Ghi chú **/
	@Column(name = "DESCRIPTION", length = 500)
	private String description;

//	/** Cổ đông **/
//	@Column(name = "IS_SHAREHOLDER")
//	private Boolean isShareholder;
	/*
	 *  Loai nguoi lien quan 
	 *  1 - Người đại diện hợp pháp
	 *  2 - Cổ đông
	 *  3- Người liên quan
	 *  Tham khảo trong CODE_MASTER
	 */
	@Column(name = "REF_PERSON_TYPE",length = 50)
	private String refPersonType;
	

	/** Tỷ trọng sở hữu **/
	@Column(name = "OWNERSHIP_RATIO")
	private Float ownershipRatio;

	@XmlTransient
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CustomerRefPerson))
			return false;
		if (this.getId() == null || ((CustomerRefPerson) obj).getId() == null)
			return false;
		return this.getId().equals(((CustomerRefPerson) obj).getId());
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

}
