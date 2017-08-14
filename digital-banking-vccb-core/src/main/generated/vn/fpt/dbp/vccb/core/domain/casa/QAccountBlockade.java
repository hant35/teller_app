package vn.fpt.dbp.vccb.core.domain.casa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccountBlockade is a Querydsl query type for AccountBlockade
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccountBlockade extends EntityPathBase<AccountBlockade> {

    private static final long serialVersionUID = -818772889L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccountBlockade accountBlockade = new QAccountBlockade("accountBlockade");

    public final vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel _super;

    public final SetPath<AccountBlockadeRaise, QAccountBlockadeRaise> accountBlockadeRaises = this.<AccountBlockadeRaise, QAccountBlockadeRaise>createSet("accountBlockadeRaises", AccountBlockadeRaise.class, QAccountBlockadeRaise.class, PathInits.DIRECT2);

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

    public final NumberPath<Double> blockadeAmount = createNumber("blockadeAmount", Double.class);

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.base.Comment, vn.fpt.dbp.vccb.core.domain.base.QComment> comments;

    // inherited
    public final vn.fpt.dbp.vccb.core.domain.user.QUser createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate;

    public final StringPath description = createString("description");

    public final DateTimePath<java.util.Date> expiredDate = createDateTime("expiredDate", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id;

    public final BooleanPath isLosedSavingBook = createBoolean("isLosedSavingBook");

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

    public final DateTimePath<java.util.Date> validDate = createDateTime("validDate", java.util.Date.class);

    //inherited
    public final StringPath workflowStatus;

    public QAccountBlockade(String variable) {
        this(AccountBlockade.class, forVariable(variable), INITS);
    }

    public QAccountBlockade(Path<? extends AccountBlockade> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountBlockade(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountBlockade(PathMetadata metadata, PathInits inits) {
        this(AccountBlockade.class, metadata, inits);
    }

    public QAccountBlockade(Class<? extends AccountBlockade> type, PathMetadata metadata, PathInits inits) {
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

