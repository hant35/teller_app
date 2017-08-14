package vn.fpt.dbp.vccb.core.domain.casa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccountInterest is a Querydsl query type for AccountInterest
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccountInterest extends EntityPathBase<AccountInterest> {

    private static final long serialVersionUID = 416210588L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccountInterest accountInterest = new QAccountInterest("accountInterest");

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

    public final NumberPath<Double> balance = createNumber("balance", Double.class);

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    public final DateTimePath<java.util.Date> caculatedDate = createDateTime("caculatedDate", java.util.Date.class);

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.base.Comment, vn.fpt.dbp.vccb.core.domain.base.QComment> comments;

    // inherited
    public final vn.fpt.dbp.vccb.core.domain.user.QUser createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate;

    //inherited
    public final NumberPath<Long> id;

    public final NumberPath<Double> interestMount = createNumber("interestMount", Double.class);

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

    public final NumberPath<Double> totalBalance = createNumber("totalBalance", Double.class);

    //inherited
    public final StringPath workflowStatus;

    public QAccountInterest(String variable) {
        this(AccountInterest.class, forVariable(variable), INITS);
    }

    public QAccountInterest(Path<? extends AccountInterest> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountInterest(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountInterest(PathMetadata metadata, PathInits inits) {
        this(AccountInterest.class, metadata, inits);
    }

    public QAccountInterest(Class<? extends AccountInterest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel(type, metadata, inits);
        this.approvedBy = _super.approvedBy;
        this.approvedDate = _super.approvedDate;
        this.assignedDate = _super.assignedDate;
        this.assignee = _super.assignee;
        this.assignGroup = _super.assignGroup;
        this.bAccount = inits.isInitialized("bAccount") ? new QBAccount(forProperty("bAccount"), inits.get("bAccount")) : null;
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.comments = _super.comments;
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
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

