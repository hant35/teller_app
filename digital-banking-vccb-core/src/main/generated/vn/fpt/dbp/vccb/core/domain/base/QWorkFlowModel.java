package vn.fpt.dbp.vccb.core.domain.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkFlowModel is a Querydsl query type for WorkFlowModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWorkFlowModel extends EntityPathBase<WorkFlowModel> {

    private static final long serialVersionUID = 1471685844L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWorkFlowModel workFlowModel = new QWorkFlowModel("workFlowModel");

    public final vn.fpt.dbp.vccb.core.domain.user.QUser approvedBy;

    public final DateTimePath<java.util.Date> approvedDate = createDateTime("approvedDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> assignedDate = createDateTime("assignedDate", java.util.Date.class);

    public final vn.fpt.dbp.vccb.core.domain.user.QUser assignee;

    public final StringPath assignGroup = createString("assignGroup");

    public final SetPath<Comment, QComment> comments = this.<Comment, QComment>createSet("comments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final vn.fpt.dbp.vccb.core.domain.user.QUser createdBy;

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> originalId = createNumber("originalId", Long.class);

    public final SetPath<vn.fpt.dbp.vccb.core.domain.role.Role, vn.fpt.dbp.vccb.core.domain.role.QRole> potentialAssignees = this.<vn.fpt.dbp.vccb.core.domain.role.Role, vn.fpt.dbp.vccb.core.domain.role.QRole>createSet("potentialAssignees", vn.fpt.dbp.vccb.core.domain.role.Role.class, vn.fpt.dbp.vccb.core.domain.role.QRole.class, PathInits.DIRECT2);

    public final StringPath processDeploymentId = createString("processDeploymentId");

    public final NumberPath<Long> processInstanceId = createNumber("processInstanceId", Long.class);

    public final StringPath referenceCode = createString("referenceCode");

    public final NumberPath<Long> taskId = createNumber("taskId", Long.class);

    public final StringPath workflowStatus = createString("workflowStatus");

    public QWorkFlowModel(String variable) {
        this(WorkFlowModel.class, forVariable(variable), INITS);
    }

    public QWorkFlowModel(Path<? extends WorkFlowModel> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QWorkFlowModel(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QWorkFlowModel(PathMetadata metadata, PathInits inits) {
        this(WorkFlowModel.class, metadata, inits);
    }

    public QWorkFlowModel(Class<? extends WorkFlowModel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.approvedBy = inits.isInitialized("approvedBy") ? new vn.fpt.dbp.vccb.core.domain.user.QUser(forProperty("approvedBy"), inits.get("approvedBy")) : null;
        this.assignee = inits.isInitialized("assignee") ? new vn.fpt.dbp.vccb.core.domain.user.QUser(forProperty("assignee"), inits.get("assignee")) : null;
        this.createdBy = inits.isInitialized("createdBy") ? new vn.fpt.dbp.vccb.core.domain.user.QUser(forProperty("createdBy"), inits.get("createdBy")) : null;
    }

}

