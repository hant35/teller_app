package vn.fpt.dbp.vccb.core.domain.casa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccountCheque is a Querydsl query type for AccountCheque
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccountCheque extends EntityPathBase<AccountCheque> {

    private static final long serialVersionUID = -1433182701L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccountCheque accountCheque = new QAccountCheque("accountCheque");

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

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

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

    public final NumberPath<Integer> pieceNumber = createNumber("pieceNumber", Integer.class);

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.role.Role, vn.fpt.dbp.vccb.core.domain.role.QRole> potentialAssignees;

    //inherited
    public final StringPath processDeploymentId;

    //inherited
    public final NumberPath<Long> processInstanceId;

    //inherited
    public final StringPath referenceCode;

    public final DateTimePath<java.util.Date> registedDate = createDateTime("registedDate", java.util.Date.class);

    public final QChequeSerial serialNoEnd;

    public final QChequeSerial serialNoStart;

    //inherited
    public final NumberPath<Long> taskId;

    public final DateTimePath<java.util.Date> validDate = createDateTime("validDate", java.util.Date.class);

    //inherited
    public final StringPath workflowStatus;

    public QAccountCheque(String variable) {
        this(AccountCheque.class, forVariable(variable), INITS);
    }

    public QAccountCheque(Path<? extends AccountCheque> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountCheque(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountCheque(PathMetadata metadata, PathInits inits) {
        this(AccountCheque.class, metadata, inits);
    }

    public QAccountCheque(Class<? extends AccountCheque> type, PathMetadata metadata, PathInits inits) {
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
        this.serialNoEnd = inits.isInitialized("serialNoEnd") ? new QChequeSerial(forProperty("serialNoEnd")) : null;
        this.serialNoStart = inits.isInitialized("serialNoStart") ? new QChequeSerial(forProperty("serialNoStart")) : null;
        this.taskId = _super.taskId;
        this.workflowStatus = _super.workflowStatus;
    }

}

