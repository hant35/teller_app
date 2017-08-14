package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    private static final long serialVersionUID = -1836828569L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomer customer = new QCustomer("customer");

    public final vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel _super;

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency accountCurrency;

    public final QReferralUser accountManager;

    public final StringPath accountNumber = createString("accountNumber");

    public final StringPath accountNumberOther = createString("accountNumberOther");

    public final StringPath accountType = createString("accountType");

    public final DateTimePath<java.util.Date> activeDate = createDateTime("activeDate", java.util.Date.class);

    public final vn.fpt.dbp.vccb.core.domain.organization.QAddress address;

    public final vn.fpt.dbp.vccb.core.domain.organization.QAddress addressNationalResident;

    public final vn.fpt.dbp.vccb.core.domain.organization.QAddress addressNationalResident2;

    public final vn.fpt.dbp.vccb.core.domain.organization.QAddress addressTrading;

    public final vn.fpt.dbp.vccb.core.domain.organization.QAddress addressVnResident;

    // inherited
    public final vn.fpt.dbp.vccb.core.domain.user.QUser approvedBy;

    //inherited
    public final DateTimePath<java.util.Date> approvedDate;

    //inherited
    public final DateTimePath<java.util.Date> assignedDate;

    // inherited
    public final vn.fpt.dbp.vccb.core.domain.user.QUser assignee;

    //inherited
    public final StringPath assignGroup;

    public final DateTimePath<java.util.Date> birthDay = createDateTime("birthDay", java.util.Date.class);

    public final vn.fpt.dbp.vccb.core.domain.organization.QCity birthPlace;

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    public final QBusinessSector businessSector;

    public final StringPath cardType = createString("cardType");

    public final StringPath changedAlertNumber = createString("changedAlertNumber");

    public final StringPath citadCode = createString("citadCode");

    public final StringPath code = createString("code");

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.base.Comment, vn.fpt.dbp.vccb.core.domain.base.QComment> comments;

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency commonCurrency;

    public final vn.fpt.dbp.vccb.core.domain.organization.QAddress companyAddress;

    public final StringPath companyName = createString("companyName");

    public final StringPath companyPhoneNumber = createString("companyPhoneNumber");

    public final vn.fpt.dbp.vccb.core.domain.organization.QCountry country;

    // inherited
    public final vn.fpt.dbp.vccb.core.domain.user.QUser createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate;

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency currency;

    public final SetPath<CustomerAccountNo, QCustomerAccountNo> customerAccountNos = this.<CustomerAccountNo, QCustomerAccountNo>createSet("customerAccountNos", CustomerAccountNo.class, QCustomerAccountNo.class, PathInits.DIRECT2);

    public final SetPath<CustomerFile, QCustomerFile> customerFiles = this.<CustomerFile, QCustomerFile>createSet("customerFiles", CustomerFile.class, QCustomerFile.class, PathInits.DIRECT2);

    public final StringPath customerGrade = createString("customerGrade");

    public final QCustomerGroup customerGroup;

    public final SetPath<CustomerImage, QCustomerImage> customerImages = this.<CustomerImage, QCustomerImage>createSet("customerImages", CustomerImage.class, QCustomerImage.class, PathInits.DIRECT2);

    public final SetPath<CustomerManager, QCustomerManager> customerManagers = this.<CustomerManager, QCustomerManager>createSet("customerManagers", CustomerManager.class, QCustomerManager.class, PathInits.DIRECT2);

    public final SetPath<CustomerPaymentInfo, QCustomerPaymentInfo> customerPaymentInfos = this.<CustomerPaymentInfo, QCustomerPaymentInfo>createSet("customerPaymentInfos", CustomerPaymentInfo.class, QCustomerPaymentInfo.class, PathInits.DIRECT2);

    public final SetPath<CustomerRefCustomer, QCustomerRefCustomer> customerRefCustomers = this.<CustomerRefCustomer, QCustomerRefCustomer>createSet("customerRefCustomers", CustomerRefCustomer.class, QCustomerRefCustomer.class, PathInits.DIRECT2);

    public final SetPath<CustomerRefPerson, QCustomerRefPerson> customerRefPersons = this.<CustomerRefPerson, QCustomerRefPerson>createSet("customerRefPersons", CustomerRefPerson.class, QCustomerRefPerson.class, PathInits.DIRECT2);

    public final QCustomerSize customerSize;

    public final QCustomerType customerType;

    public final NumberPath<Integer> dependedPersionNumber = createNumber("dependedPersionNumber", Integer.class);

    public final StringPath description = createString("description");

    public final StringPath ebankingEmail = createString("ebankingEmail");

    public final StringPath ebankingNumber = createString("ebankingNumber");

    public final DateTimePath<java.util.Date> ebankingUserBirthdate = createDateTime("ebankingUserBirthdate", java.util.Date.class);

    public final StringPath ebankingUserEmail = createString("ebankingUserEmail");

    public final StringPath ebankingUserMobile = createString("ebankingUserMobile");

    public final StringPath ebankingUserName = createString("ebankingUserName");

    public final StringPath educationLevel = createString("educationLevel");

    public final StringPath emailAddress = createString("emailAddress");

    public final StringPath faxNumber = createString("faxNumber");

    public final StringPath feeType = createString("feeType");

    public final vn.fpt.dbp.vccb.core.domain.organization.QCountry foundedCountry;

    public final StringPath fullName = createString("fullName");

    public final StringPath gender = createString("gender");

    public final StringPath giinCode = createString("giinCode");

    public final StringPath houseOwnerType = createString("houseOwnerType");

    //inherited
    public final NumberPath<Long> id;

    public final StringPath incomeValue = createString("incomeValue");

    public final BooleanPath isAlertFeeFree = createBoolean("isAlertFeeFree");

    public final BooleanPath isAuthorInternetBanking = createBoolean("isAuthorInternetBanking");

    public final BooleanPath isChangedAlert = createBoolean("isChangedAlert");

    public final BooleanPath isCommonDebitAccount = createBoolean("isCommonDebitAccount");

    public final BooleanPath isDebitAccount = createBoolean("isDebitAccount");

    public final BooleanPath isEbankingAuthorizeApprove = createBoolean("isEbankingAuthorizeApprove");

    public final BooleanPath isEbankingAuthorizeInput = createBoolean("isEbankingAuthorizeInput");

    public final BooleanPath isEbankingAuthorizeThrow = createBoolean("isEbankingAuthorizeThrow");

    public final BooleanPath isEbankingAuthorizeView = createBoolean("isEbankingAuthorizeView");

    public final BooleanPath isLimitPersion = createBoolean("isLimitPersion");

    public final BooleanPath isManagerFeeFree = createBoolean("isManagerFeeFree");

    public final BooleanPath isNotification = createBoolean("isNotification");

    public final BooleanPath isNotificationAddress = createBoolean("isNotificationAddress");

    public final BooleanPath isNotificationEmail = createBoolean("isNotificationEmail");

    public final BooleanPath isNotificationMobile = createBoolean("isNotificationMobile");

    public final BooleanPath isNotificationOther = createBoolean("isNotificationOther");

    public final BooleanPath isOpenedAccountInfo = createBoolean("isOpenedAccountInfo");

    public final BooleanPath isOpenedCard = createBoolean("isOpenedCard");

    public final BooleanPath isRegistedCardService = createBoolean("isRegistedCardService");

    public final BooleanPath isRegistedEbanking = createBoolean("isRegistedEbanking");

    public final BooleanPath isSalaryDistribute = createBoolean("isSalaryDistribute");

    public final BooleanPath isSalaryPayment = createBoolean("isSalaryPayment");

    public final BooleanPath isServiceByFax = createBoolean("isServiceByFax");

    public final BooleanPath isVccbUser = createBoolean("isVccbUser");

    public final StringPath job = createString("job");

    public final vn.fpt.dbp.vccb.core.domain.organization.QLanguage language;

    public final DateTimePath<java.util.Date> legalDocsExpiredDate = createDateTime("legalDocsExpiredDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> legalDocsExpiredDate2 = createDateTime("legalDocsExpiredDate2", java.util.Date.class);

    public final StringPath legalDocsNumber = createString("legalDocsNumber");

    public final StringPath legalDocsNumber2 = createString("legalDocsNumber2");

    public final StringPath legalDocsRegistPlace = createString("legalDocsRegistPlace");

    public final StringPath legalDocsRegistPlace2 = createString("legalDocsRegistPlace2");

    public final QLegalDocsType legalDocsType;

    public final QLegalDocsType legalDocsType2;

    public final DateTimePath<java.util.Date> legalDocsValidDate = createDateTime("legalDocsValidDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> legalDocsValidDate2 = createDateTime("legalDocsValidDate2", java.util.Date.class);

    public final StringPath limitCreditService = createString("limitCreditService");

    public final vn.fpt.dbp.vccb.core.domain.organization.QNationality nationality;

    public final vn.fpt.dbp.vccb.core.domain.organization.QNationality nationality2;

    public final StringPath notificationOther = createString("notificationOther");

    public final StringPath organizationType = createString("organizationType");

    //inherited
    public final NumberPath<Long> originalId;

    public final StringPath otherName = createString("otherName");

    public final StringPath phoneNumberContact = createString("phoneNumberContact");

    public final StringPath phoneNumberHome = createString("phoneNumberHome");

    public final StringPath pinReceivedBranchName = createString("pinReceivedBranchName");

    public final StringPath pinReceivedPlace = createString("pinReceivedPlace");

    public final StringPath position = createString("position");

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.role.Role, vn.fpt.dbp.vccb.core.domain.role.QRole> potentialAssignees;

    //inherited
    public final StringPath processDeploymentId;

    //inherited
    public final NumberPath<Long> processInstanceId;

    public final BooleanPath receivedAdsInfo = createBoolean("receivedAdsInfo");

    public final BooleanPath receivedServiceInfo = createBoolean("receivedServiceInfo");

    public final StringPath refCustomerAccountNumber = createString("refCustomerAccountNumber");

    public final StringPath refCustomerName = createString("refCustomerName");

    //inherited
    public final StringPath referenceCode;

    public final QCustomer referralCustomer;

    public final StringPath referralType = createString("referralType");

    public final QReferralUser referralUser;

    public final StringPath referralUserName = createString("referralUserName");

    public final QCustomer representationCustomer;

    public final StringPath resident = createString("resident");

    public final StringPath resident2 = createString("resident2");

    public final StringPath reuterCode = createString("reuterCode");

    public final StringPath shortName = createString("shortName");

    public final StringPath status = createString("status");

    public final StringPath subsidiaryLedgerOther = createString("subsidiaryLedgerOther");

    public final StringPath subsidiaryLedgerPeriod = createString("subsidiaryLedgerPeriod");

    public final StringPath subsidiaryLedgerType = createString("subsidiaryLedgerType");

    public final StringPath swiftCode = createString("swiftCode");

    //inherited
    public final NumberPath<Long> taskId;

    public final StringPath taxNumber = createString("taxNumber");

    public final StringPath typeCode = createString("typeCode");

    public final StringPath userCode = createString("userCode");

    public final StringPath usTaxLawInfo = createString("usTaxLawInfo");

    public final QReferralUser vccbUser;

    public final StringPath visa = createString("visa");

    public final DateTimePath<java.util.Date> visaExpiredDate = createDateTime("visaExpiredDate", java.util.Date.class);

    public final StringPath visaRegistPlace = createString("visaRegistPlace");

    public final DateTimePath<java.util.Date> visaValidDate = createDateTime("visaValidDate", java.util.Date.class);

    public final StringPath wedlock = createString("wedlock");

    public final vn.fpt.dbp.vccb.core.domain.organization.QAddress workCompanyAddress;

    public final StringPath workCompanyName = createString("workCompanyName");

    //inherited
    public final StringPath workflowStatus;

    public QCustomer(String variable) {
        this(Customer.class, forVariable(variable), INITS);
    }

    public QCustomer(Path<? extends Customer> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomer(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomer(PathMetadata metadata, PathInits inits) {
        this(Customer.class, metadata, inits);
    }

    public QCustomer(Class<? extends Customer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel(type, metadata, inits);
        this.accountCurrency = inits.isInitialized("accountCurrency") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("accountCurrency")) : null;
        this.accountManager = inits.isInitialized("accountManager") ? new QReferralUser(forProperty("accountManager"), inits.get("accountManager")) : null;
        this.address = inits.isInitialized("address") ? new vn.fpt.dbp.vccb.core.domain.organization.QAddress(forProperty("address"), inits.get("address")) : null;
        this.addressNationalResident = inits.isInitialized("addressNationalResident") ? new vn.fpt.dbp.vccb.core.domain.organization.QAddress(forProperty("addressNationalResident"), inits.get("addressNationalResident")) : null;
        this.addressNationalResident2 = inits.isInitialized("addressNationalResident2") ? new vn.fpt.dbp.vccb.core.domain.organization.QAddress(forProperty("addressNationalResident2"), inits.get("addressNationalResident2")) : null;
        this.addressTrading = inits.isInitialized("addressTrading") ? new vn.fpt.dbp.vccb.core.domain.organization.QAddress(forProperty("addressTrading"), inits.get("addressTrading")) : null;
        this.addressVnResident = inits.isInitialized("addressVnResident") ? new vn.fpt.dbp.vccb.core.domain.organization.QAddress(forProperty("addressVnResident"), inits.get("addressVnResident")) : null;
        this.approvedBy = _super.approvedBy;
        this.approvedDate = _super.approvedDate;
        this.assignedDate = _super.assignedDate;
        this.assignee = _super.assignee;
        this.assignGroup = _super.assignGroup;
        this.birthPlace = inits.isInitialized("birthPlace") ? new vn.fpt.dbp.vccb.core.domain.organization.QCity(forProperty("birthPlace"), inits.get("birthPlace")) : null;
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.businessSector = inits.isInitialized("businessSector") ? new QBusinessSector(forProperty("businessSector")) : null;
        this.comments = _super.comments;
        this.commonCurrency = inits.isInitialized("commonCurrency") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("commonCurrency")) : null;
        this.companyAddress = inits.isInitialized("companyAddress") ? new vn.fpt.dbp.vccb.core.domain.organization.QAddress(forProperty("companyAddress"), inits.get("companyAddress")) : null;
        this.country = inits.isInitialized("country") ? new vn.fpt.dbp.vccb.core.domain.organization.QCountry(forProperty("country")) : null;
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.currency = inits.isInitialized("currency") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("currency")) : null;
        this.customerGroup = inits.isInitialized("customerGroup") ? new QCustomerGroup(forProperty("customerGroup"), inits.get("customerGroup")) : null;
        this.customerSize = inits.isInitialized("customerSize") ? new QCustomerSize(forProperty("customerSize"), inits.get("customerSize")) : null;
        this.customerType = inits.isInitialized("customerType") ? new QCustomerType(forProperty("customerType")) : null;
        this.foundedCountry = inits.isInitialized("foundedCountry") ? new vn.fpt.dbp.vccb.core.domain.organization.QCountry(forProperty("foundedCountry")) : null;
        this.id = _super.id;
        this.language = inits.isInitialized("language") ? new vn.fpt.dbp.vccb.core.domain.organization.QLanguage(forProperty("language")) : null;
        this.legalDocsType = inits.isInitialized("legalDocsType") ? new QLegalDocsType(forProperty("legalDocsType")) : null;
        this.legalDocsType2 = inits.isInitialized("legalDocsType2") ? new QLegalDocsType(forProperty("legalDocsType2")) : null;
        this.nationality = inits.isInitialized("nationality") ? new vn.fpt.dbp.vccb.core.domain.organization.QNationality(forProperty("nationality")) : null;
        this.nationality2 = inits.isInitialized("nationality2") ? new vn.fpt.dbp.vccb.core.domain.organization.QNationality(forProperty("nationality2")) : null;
        this.originalId = _super.originalId;
        this.potentialAssignees = _super.potentialAssignees;
        this.processDeploymentId = _super.processDeploymentId;
        this.processInstanceId = _super.processInstanceId;
        this.referenceCode = _super.referenceCode;
        this.referralCustomer = inits.isInitialized("referralCustomer") ? new QCustomer(forProperty("referralCustomer"), inits.get("referralCustomer")) : null;
        this.referralUser = inits.isInitialized("referralUser") ? new QReferralUser(forProperty("referralUser"), inits.get("referralUser")) : null;
        this.representationCustomer = inits.isInitialized("representationCustomer") ? new QCustomer(forProperty("representationCustomer"), inits.get("representationCustomer")) : null;
        this.taskId = _super.taskId;
        this.vccbUser = inits.isInitialized("vccbUser") ? new QReferralUser(forProperty("vccbUser"), inits.get("vccbUser")) : null;
        this.workCompanyAddress = inits.isInitialized("workCompanyAddress") ? new vn.fpt.dbp.vccb.core.domain.organization.QAddress(forProperty("workCompanyAddress"), inits.get("workCompanyAddress")) : null;
        this.workflowStatus = _super.workflowStatus;
    }

}

