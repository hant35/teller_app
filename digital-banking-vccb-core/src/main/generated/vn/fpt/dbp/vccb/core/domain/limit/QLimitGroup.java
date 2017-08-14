package vn.fpt.dbp.vccb.core.domain.limit;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLimitGroup is a Querydsl query type for LimitGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLimitGroup extends EntityPathBase<LimitGroup> {

    private static final long serialVersionUID = 1256168510L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLimitGroup limitGroup = new QLimitGroup("limitGroup");

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

    public final StringPath code = createString("code");

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.base.Comment, vn.fpt.dbp.vccb.core.domain.base.QComment> comments;

    // inherited
    public final vn.fpt.dbp.vccb.core.domain.user.QUser createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate;

    //inherited
    public final NumberPath<Long> id;

    public final SetPath<Limit, QLimit> limits = this.<Limit, QLimit>createSet("limits", Limit.class, QLimit.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

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

    public final StringPath status = createString("status");

    //inherited
    public final NumberPath<Long> taskId;

    //inherited
    public final StringPath workflowStatus;

    public QLimitGroup(String variable) {
        this(LimitGroup.class, forVariable(variable), INITS);
    }

    public QLimitGroup(Path<? extends LimitGroup> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLimitGroup(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLimitGroup(PathMetadata metadata, PathInits inits) {
        this(LimitGroup.class, metadata, inits);
    }

    public QLimitGroup(Class<? extends LimitGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel(type, metadata, inits);
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

