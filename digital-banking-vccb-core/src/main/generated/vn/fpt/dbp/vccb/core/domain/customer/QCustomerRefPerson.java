package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerRefPerson is a Querydsl query type for CustomerRefPerson
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomerRefPerson extends EntityPathBase<CustomerRefPerson> {

    private static final long serialVersionUID = 2014163233L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerRefPerson customerRefPerson = new QCustomerRefPerson("customerRefPerson");

    public final vn.fpt.dbp.vccb.core.domain.organization.QAddress address;

    public final vn.fpt.dbp.vccb.core.domain.organization.QAddress addressNationalResident;

    public final vn.fpt.dbp.vccb.core.domain.organization.QAddress addressNationalResident2;

    public final vn.fpt.dbp.vccb.core.domain.organization.QAddress addressVnResident;

    public final DateTimePath<java.util.Date> birthday = createDateTime("birthday", java.util.Date.class);

    public final StringPath birthPlace = createString("birthPlace");

    public final QCustomer customer;

    public final StringPath customerCode = createString("customerCode");

    public final NumberPath<Integer> dependedPersionNumber = createNumber("dependedPersionNumber", Integer.class);

    public final StringPath description = createString("description");

    public final StringPath educationLevel = createString("educationLevel");

    public final StringPath emailAddress = createString("emailAddress");

    public final StringPath fullName = createString("fullName");

    public final StringPath gender = createString("gender");

    public final StringPath houseOwnerType = createString("houseOwnerType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath incomeValue = createString("incomeValue");

    public final BooleanPath isRepresentationAsLaw = createBoolean("isRepresentationAsLaw");

    public final StringPath job = createString("job");

    public final StringPath jobPositionOther = createString("jobPositionOther");

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

    public final vn.fpt.dbp.vccb.core.domain.organization.QNationality nationality;

    public final vn.fpt.dbp.vccb.core.domain.organization.QNationality nationality2;

    public final StringPath otherName = createString("otherName");

    public final NumberPath<Float> ownershipRatio = createNumber("ownershipRatio", Float.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath position = createString("position");

    public final StringPath refPersonType = createString("refPersonType");

    public final StringPath resident = createString("resident");

    public final StringPath resident2 = createString("resident2");

    public final StringPath shortName = createString("shortName");

    public final StringPath type = createString("type");

    public final StringPath type2 = createString("type2");

    public final StringPath usTaxLawInfo = createString("usTaxLawInfo");

    public final StringPath visa = createString("visa");

    public final DateTimePath<java.util.Date> visaExpiredDate = createDateTime("visaExpiredDate", java.util.Date.class);

    public final StringPath visaRegistPlace = createString("visaRegistPlace");

    public final DateTimePath<java.util.Date> visaValidDate = createDateTime("visaValidDate", java.util.Date.class);

    public final StringPath wedlock = createString("wedlock");

    public final vn.fpt.dbp.vccb.core.domain.organization.QAddress workCompanyAddress;

    public final StringPath workCompanyName = createString("workCompanyName");

    public QCustomerRefPerson(String variable) {
        this(CustomerRefPerson.class, forVariable(variable), INITS);
    }

    public QCustomerRefPerson(Path<? extends CustomerRefPerson> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerRefPerson(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerRefPerson(PathMetadata metadata, PathInits inits) {
        this(CustomerRefPerson.class, metadata, inits);
    }

    public QCustomerRefPerson(Class<? extends CustomerRefPerson> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new vn.fpt.dbp.vccb.core.domain.organization.QAddress(forProperty("address"), inits.get("address")) : null;
        this.addressNationalResident = inits.isInitialized("addressNationalResident") ? new vn.fpt.dbp.vccb.core.domain.organization.QAddress(forProperty("addressNationalResident"), inits.get("addressNationalResident")) : null;
        this.addressNationalResident2 = inits.isInitialized("addressNationalResident2") ? new vn.fpt.dbp.vccb.core.domain.organization.QAddress(forProperty("addressNationalResident2"), inits.get("addressNationalResident2")) : null;
        this.addressVnResident = inits.isInitialized("addressVnResident") ? new vn.fpt.dbp.vccb.core.domain.organization.QAddress(forProperty("addressVnResident"), inits.get("addressVnResident")) : null;
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.legalDocsType = inits.isInitialized("legalDocsType") ? new QLegalDocsType(forProperty("legalDocsType")) : null;
        this.legalDocsType2 = inits.isInitialized("legalDocsType2") ? new QLegalDocsType(forProperty("legalDocsType2")) : null;
        this.nationality = inits.isInitialized("nationality") ? new vn.fpt.dbp.vccb.core.domain.organization.QNationality(forProperty("nationality")) : null;
        this.nationality2 = inits.isInitialized("nationality2") ? new vn.fpt.dbp.vccb.core.domain.organization.QNationality(forProperty("nationality2")) : null;
        this.workCompanyAddress = inits.isInitialized("workCompanyAddress") ? new vn.fpt.dbp.vccb.core.domain.organization.QAddress(forProperty("workCompanyAddress"), inits.get("workCompanyAddress")) : null;
    }

}

