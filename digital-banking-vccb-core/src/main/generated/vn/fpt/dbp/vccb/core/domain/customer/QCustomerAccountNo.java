package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerAccountNo is a Querydsl query type for CustomerAccountNo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomerAccountNo extends EntityPathBase<CustomerAccountNo> {

    private static final long serialVersionUID = 1725114407L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerAccountNo customerAccountNo = new QCustomerAccountNo("customerAccountNo");

    public final vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel _super;

    public final StringPath accountNo = createString("accountNo");

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

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.base.Comment, vn.fpt.dbp.vccb.core.domain.base.QComment> comments;

    // inherited
    public final vn.fpt.dbp.vccb.core.domain.user.QUser createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate;

    public final QCustomer customer;

    //inherited
    public final NumberPath<Long> id;

    public final BooleanPath isActive = createBoolean("isActive");

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

    //inherited
    public final NumberPath<Long> taskId;

    //inherited
    public final StringPath workflowStatus;

    public QCustomerAccountNo(String variable) {
        this(CustomerAccountNo.class, forVariable(variable), INITS);
    }

    public QCustomerAccountNo(Path<? extends CustomerAccountNo> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerAccountNo(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerAccountNo(PathMetadata metadata, PathInits inits) {
        this(CustomerAccountNo.class, metadata, inits);
    }

    public QCustomerAccountNo(Class<? extends CustomerAccountNo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel(type, metadata, inits);
        this.approvedBy = _super.approvedBy;
        this.approvedDate = _super.approvedDate;
        this.assignedDate = _super.assignedDate;
        this.assignee = _super.assignee;
        this.assignGroup = _super.assignGroup;
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.comments = _super.comments;
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.id = _super.id;
        this.originalId = _super.originalId;
        this.potentialAssignees = _super.potentialAssignees;
        this.processDeploymentId = _super.processDeploymentId;
        this.processInstanceId = _super.processInstanceId;
        this.referenceCode = _super.referenceCode;
        this.taskId = _super.taskId;
        this.workflowStatus = _super.workflowStatus;
    }

}

