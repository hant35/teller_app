package vn.fpt.dbp.vccb.core.domain.casa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBAccount is a Querydsl query type for BAccount
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBAccount extends EntityPathBase<BAccount> {

    private static final long serialVersionUID = -2092173082L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBAccount bAccount = new QBAccount("bAccount");

    public final vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel _super;

    public final SetPath<AccountBlockade, QAccountBlockade> accountBlockades = this.<AccountBlockade, QAccountBlockade>createSet("accountBlockades", AccountBlockade.class, QAccountBlockade.class, PathInits.DIRECT2);

    public final SetPath<AccountCheque, QAccountCheque> accountCheques = this.<AccountCheque, QAccountCheque>createSet("accountCheques", AccountCheque.class, QAccountCheque.class, PathInits.DIRECT2);

    public final SetPath<AccountInterest, QAccountInterest> accountInterests = this.<AccountInterest, QAccountInterest>createSet("accountInterests", AccountInterest.class, QAccountInterest.class, PathInits.DIRECT2);

    public final StringPath accountName = createString("accountName");

    public final StringPath accountNo = createString("accountNo");

    public final SetPath<AccountRefPerson, QAccountRefPerson> accountRefPersons = this.<AccountRefPerson, QAccountRefPerson>createSet("accountRefPersons", AccountRefPerson.class, QAccountRefPerson.class, PathInits.DIRECT2);

    public final SetPath<AccountService, QAccountService> accountServices = this.<AccountService, QAccountService>createSet("accountServices", AccountService.class, QAccountService.class, PathInits.DIRECT2);

    public final StringPath accountStatus = createString("accountStatus");

    public final DateTimePath<java.util.Date> allowChangeStatusDate = createDateTime("allowChangeStatusDate", java.util.Date.class);

    public final StringPath allowChangeStatusType = createString("allowChangeStatusType");

    public final NumberPath<Float> amplitude = createNumber("amplitude", Float.class);

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

    public final vn.fpt.dbp.vccb.core.domain.product.QAutoBlockType autoBlockType;

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.base.Comment, vn.fpt.dbp.vccb.core.domain.base.QComment> comments;

    // inherited
    public final vn.fpt.dbp.vccb.core.domain.user.QUser createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate;

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency currency;

    public final vn.fpt.dbp.vccb.core.domain.customer.QCustomer customer;

    //inherited
    public final NumberPath<Long> id;

    public final NumberPath<Float> interestRate = createNumber("interestRate", Float.class);

    public final BooleanPath isAllowChangeStatus = createBoolean("isAllowChangeStatus");

    public final BooleanPath isAllowEntry = createBoolean("isAllowEntry");

    public final BooleanPath isAutoBlock = createBoolean("isAutoBlock");

    public final BooleanPath isFrozen = createBoolean("isFrozen");

    public final BooleanPath isHibernate = createBoolean("isHibernate");

    public final BooleanPath isNoCredit = createBoolean("isNoCredit");

    public final BooleanPath isNoDebit = createBoolean("isNoDebit");

    public final BooleanPath isOverDraff = createBoolean("isOverDraff");

    public final BooleanPath isStopAccount = createBoolean("isStopAccount");

    public final DateTimePath<java.util.Date> openDate = createDateTime("openDate", java.util.Date.class);

    //inherited
    public final NumberPath<Long> originalId;

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.role.Role, vn.fpt.dbp.vccb.core.domain.role.QRole> potentialAssignees;

    //inherited
    public final StringPath processDeploymentId;

    //inherited
    public final NumberPath<Long> processInstanceId;

    public final vn.fpt.dbp.vccb.core.domain.product.QProduct product;

    //inherited
    public final StringPath referenceCode;

    public final vn.fpt.dbp.vccb.core.domain.customer.QReferralUser referralUser;

    public final QSavingBookSerial savingBookSerial;

    public final StringPath subsidiaryLedgerOther = createString("subsidiaryLedgerOther");

    public final StringPath subsidiaryLedgerPeriod = createString("subsidiaryLedgerPeriod");

    public final StringPath subsidiaryLedgerType = createString("subsidiaryLedgerType");

    //inherited
    public final NumberPath<Long> taskId;

    public final NumberPath<Float> validRate = createNumber("validRate", Float.class);

    //inherited
    public final StringPath workflowStatus;

    public QBAccount(String variable) {
        this(BAccount.class, forVariable(variable), INITS);
    }

    public QBAccount(Path<? extends BAccount> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBAccount(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBAccount(PathMetadata metadata, PathInits inits) {
        this(BAccount.class, metadata, inits);
    }

    public QBAccount(Class<? extends BAccount> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel(type, metadata, inits);
        this.approvedBy = _super.approvedBy;
        this.approvedDate = _super.approvedDate;
        this.assignedDate = _super.assignedDate;
        this.assignee = _super.assignee;
        this.assignGroup = _super.assignGroup;
        this.autoBlockType = inits.isInitialized("autoBlockType") ? new vn.fpt.dbp.vccb.core.domain.product.QAutoBlockType(forProperty("autoBlockType")) : null;
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.comments = _super.comments;
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.currency = inits.isInitialized("currency") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("currency")) : null;
        this.customer = inits.isInitialized("customer") ? new vn.fpt.dbp.vccb.core.domain.customer.QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.id = _super.id;
        this.originalId = _super.originalId;
        this.potentialAssignees = _super.potentialAssignees;
        this.processDeploymentId = _super.processDeploymentId;
        this.processInstanceId = _super.processInstanceId;
        this.product = inits.isInitialized("product") ? new vn.fpt.dbp.vccb.core.domain.product.QProduct(forProperty("product"), inits.get("product")) : null;
        this.referenceCode = _super.referenceCode;
        this.referralUser = inits.isInitialized("referralUser") ? new vn.fpt.dbp.vccb.core.domain.customer.QReferralUser(forProperty("referralUser"), inits.get("referralUser")) : null;
        this.savingBookSerial = inits.isInitialized("savingBookSerial") ? new QSavingBookSerial(forProperty("savingBookSerial")) : null;
        this.taskId = _super.taskId;
        this.workflowStatus = _super.workflowStatus;
    }

}

