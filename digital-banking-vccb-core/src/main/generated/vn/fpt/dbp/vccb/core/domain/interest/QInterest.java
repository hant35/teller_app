package vn.fpt.dbp.vccb.core.domain.interest;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterest is a Querydsl query type for Interest
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInterest extends EntityPathBase<Interest> {

    private static final long serialVersionUID = 38645223L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterest interest = new QInterest("interest");

    public final vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel _super;

    // inherited
    public final vn.fpt.dbp.vccb.core.domain.user.QUser approvedBy;

    //inherited
    public final DateTimePath<java.util.Date> approvedDate;

    public final vn.fpt.dbp.vccb.core.domain.organization.QArea area;

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

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency currency;

    public final StringPath description = createString("description");

    public final DateTimePath<java.util.Date> effectDate = createDateTime("effectDate", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id;

    public final SetPath<InterestParameter, QInterestParameter> interestParameters = this.<InterestParameter, QInterestParameter>createSet("interestParameters", InterestParameter.class, QInterestParameter.class, PathInits.DIRECT2);

    public final QInterestUploadFile interestUploadFile;

    //inherited
    public final NumberPath<Long> originalId;

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.role.Role, vn.fpt.dbp.vccb.core.domain.role.QRole> potentialAssignees;

    //inherited
    public final StringPath processDeploymentId;

    //inherited
    public final NumberPath<Long> processInstanceId;

    public final vn.fpt.dbp.vccb.core.domain.product.QProduct product;

    //inherited
    public final StringPath referenceCode;

    //inherited
    public final NumberPath<Long> taskId;

    public final QTimeRate timeRate;

    //inherited
    public final StringPath workflowStatus;

    public QInterest(String variable) {
        this(Interest.class, forVariable(variable), INITS);
    }

    public QInterest(Path<? extends Interest> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInterest(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInterest(PathMetadata metadata, PathInits inits) {
        this(Interest.class, metadata, inits);
    }

    public QInterest(Class<? extends Interest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel(type, metadata, inits);
        this.approvedBy = _super.approvedBy;
        this.approvedDate = _super.approvedDate;
        this.area = inits.isInitialized("area") ? new vn.fpt.dbp.vccb.core.domain.organization.QArea(forProperty("area")) : null;
        this.assignedDate = _super.assignedDate;
        this.assignee = _super.assignee;
        this.assignGroup = _super.assignGroup;
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.comments = _super.comments;
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.currency = inits.isInitialized("currency") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("currency")) : null;
        this.id = _super.id;
        this.interestUploadFile = inits.isInitialized("interestUploadFile") ? new QInterestUploadFile(forProperty("interestUploadFile"), inits.get("interestUploadFile")) : null;
        this.originalId = _super.originalId;
        this.potentialAssignees = _super.potentialAssignees;
        this.processDeploymentId = _super.processDeploymentId;
        this.processInstanceId = _super.processInstanceId;
        this.product = inits.isInitialized("product") ? new vn.fpt.dbp.vccb.core.domain.product.QProduct(forProperty("product"), inits.get("product")) : null;
        this.referenceCode = _super.referenceCode;
        this.taskId = _super.taskId;
        this.timeRate = inits.isInitialized("timeRate") ? new QTimeRate(forProperty("timeRate")) : null;
        this.workflowStatus = _super.workflowStatus;
    }

}

