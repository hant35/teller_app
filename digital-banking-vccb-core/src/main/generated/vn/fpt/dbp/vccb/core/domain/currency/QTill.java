package vn.fpt.dbp.vccb.core.domain.currency;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTill is a Querydsl query type for Till
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTill extends EntityPathBase<Till> {

    private static final long serialVersionUID = -1615368341L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTill till = new QTill("till");

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

    public final vn.fpt.dbp.vccb.core.domain.user.QUser user;

    public final QVault vault;

    //inherited
    public final StringPath workflowStatus;

    public QTill(String variable) {
        this(Till.class, forVariable(variable), INITS);
    }

    public QTill(Path<? extends Till> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTill(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTill(PathMetadata metadata, PathInits inits) {
        this(Till.class, metadata, inits);
    }

    public QTill(Class<? extends Till> type, PathMetadata metadata, PathInits inits) {
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
        this.user = inits.isInitialized("user") ? new vn.fpt.dbp.vccb.core.domain.user.QUser(forProperty("user"), inits.get("user")) : null;
        this.vault = inits.isInitialized("vault") ? new QVault(forProperty("vault"), inits.get("vault")) : null;
        this.workflowStatus = _super.workflowStatus;
    }

}

