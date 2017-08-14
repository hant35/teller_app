package vn.fpt.dbp.vccb.core.domain.casa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccountBlockadeRaise is a Querydsl query type for AccountBlockadeRaise
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccountBlockadeRaise extends EntityPathBase<AccountBlockadeRaise> {

    private static final long serialVersionUID = -734766139L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccountBlockadeRaise accountBlockadeRaise = new QAccountBlockadeRaise("accountBlockadeRaise");

    public final vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel _super;

    public final QAccountBlockade accountBlockade;

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

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.base.Comment, vn.fpt.dbp.vccb.core.domain.base.QComment> comments;

    // inherited
    public final vn.fpt.dbp.vccb.core.domain.user.QUser createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate;

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final NumberPath<Long> originalId;

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.role.Role, vn.fpt.dbp.vccb.core.domain.role.QRole> potentialAssignees;

    //inherited
    public final StringPath processDeploymentId;

    //inherited
    public final NumberPath<Long> processInstanceId;

    public final NumberPath<Double> raiseAmount = createNumber("raiseAmount", Double.class);

    public final NumberPath<Integer> raiseNumber = createNumber("raiseNumber", Integer.class);

    public final StringPath raiseType = createString("raiseType");

    //inherited
    public final StringPath referenceCode;

    //inherited
    public final NumberPath<Long> taskId;

    //inherited
    public final StringPath workflowStatus;

    public QAccountBlockadeRaise(String variable) {
        this(AccountBlockadeRaise.class, forVariable(variable), INITS);
    }

    public QAccountBlockadeRaise(Path<? extends AccountBlockadeRaise> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountBlockadeRaise(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountBlockadeRaise(PathMetadata metadata, PathInits inits) {
        this(AccountBlockadeRaise.class, metadata, inits);
    }

    public QAccountBlockadeRaise(Class<? extends AccountBlockadeRaise> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel(type, metadata, inits);
        this.accountBlockade = inits.isInitialized("accountBlockade") ? new QAccountBlockade(forProperty("accountBlockade"), inits.get("accountBlockade")) : null;
        this.approvedBy = _super.approvedBy;
        this.approvedDate = _super.approvedDate;
        this.assignedDate = _super.assignedDate;
        this.assignee = _super.assignee;
        this.assignGroup = _super.assignGroup;
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

