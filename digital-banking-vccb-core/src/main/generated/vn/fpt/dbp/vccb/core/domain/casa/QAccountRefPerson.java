package vn.fpt.dbp.vccb.core.domain.casa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccountRefPerson is a Querydsl query type for AccountRefPerson
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccountRefPerson extends EntityPathBase<AccountRefPerson> {

    private static final long serialVersionUID = -2022966122L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccountRefPerson accountRefPerson = new QAccountRefPerson("accountRefPerson");

    public final vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel _super;

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

    public final QBAccount bAccount;

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.base.Comment, vn.fpt.dbp.vccb.core.domain.base.QComment> comments;

    // inherited
    public final vn.fpt.dbp.vccb.core.domain.user.QUser createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate;

    public final vn.fpt.dbp.vccb.core.domain.customer.QCustomer customer;

    public final StringPath customerName = createString("customerName");

    public final DateTimePath<java.util.Date> expiredDate = createDateTime("expiredDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath legalDocsNumber = createString("legalDocsNumber");

    //inherited
    public final NumberPath<Long> originalId;

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.role.Role, vn.fpt.dbp.vccb.core.domain.role.QRole> potentialAssignees;

    //inherited
    public final StringPath processDeploymentId;

    //inherited
    public final NumberPath<Long> processInstanceId;

    //inherited
    public final StringPath referenceCode;

    public final StringPath referenceType = createString("referenceType");

    //inherited
    public final NumberPath<Long> taskId;

    public final DateTimePath<java.util.Date> validDate = createDateTime("validDate", java.util.Date.class);

    //inherited
    public final StringPath workflowStatus;

    public QAccountRefPerson(String variable) {
        this(AccountRefPerson.class, forVariable(variable), INITS);
    }

    public QAccountRefPerson(Path<? extends AccountRefPerson> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountRefPerson(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountRefPerson(PathMetadata metadata, PathInits inits) {
        this(AccountRefPerson.class, metadata, inits);
    }

    public QAccountRefPerson(Class<? extends AccountRefPerson> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel(type, metadata, inits);
        this.approvedBy = _super.approvedBy;
        this.approvedDate = _super.approvedDate;
        this.assignedDate = _super.assignedDate;
        this.assignee = _super.assignee;
        this.assignGroup = _super.assignGroup;
        this.bAccount = inits.isInitialized("bAccount") ? new QBAccount(forProperty("bAccount"), inits.get("bAccount")) : null;
        this.comments = _super.comments;
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.customer = inits.isInitialized("customer") ? new vn.fpt.dbp.vccb.core.domain.customer.QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.originalId = _super.originalId;
        this.potentialAssignees = _super.potentialAssignees;
        this.processDeploymentId = _super.processDeploymentId;
        this.processInstanceId = _super.processInstanceId;
        this.referenceCode = _super.referenceCode;
        this.taskId = _super.taskId;
        this.workflowStatus = _super.workflowStatus;
    }

}

