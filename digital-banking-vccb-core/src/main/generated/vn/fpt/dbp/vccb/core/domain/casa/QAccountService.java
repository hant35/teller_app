package vn.fpt.dbp.vccb.core.domain.casa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccountService is a Querydsl query type for AccountService
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccountService extends EntityPathBase<AccountService> {

    private static final long serialVersionUID = -237577981L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccountService accountService = new QAccountService("accountService");

    public final vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel _super;

    public final SetPath<AccountServiceGroup, QAccountServiceGroup> accountServiceGroups = this.<AccountServiceGroup, QAccountServiceGroup>createSet("accountServiceGroups", AccountServiceGroup.class, QAccountServiceGroup.class, PathInits.DIRECT2);

    public final SetPath<AccountServiceType, QAccountServiceType> accountServiceTypes = this.<AccountServiceType, QAccountServiceType>createSet("accountServiceTypes", AccountServiceType.class, QAccountServiceType.class, PathInits.DIRECT2);

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

    //inherited
    public final StringPath referenceCode;

    //inherited
    public final NumberPath<Long> taskId;

    //inherited
    public final StringPath workflowStatus;

    public QAccountService(String variable) {
        this(AccountService.class, forVariable(variable), INITS);
    }

    public QAccountService(Path<? extends AccountService> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountService(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountService(PathMetadata metadata, PathInits inits) {
        this(AccountService.class, metadata, inits);
    }

    public QAccountService(Class<? extends AccountService> type, PathMetadata metadata, PathInits inits) {
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

