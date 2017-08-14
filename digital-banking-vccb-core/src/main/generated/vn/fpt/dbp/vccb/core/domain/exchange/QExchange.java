package vn.fpt.dbp.vccb.core.domain.exchange;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExchange is a Querydsl query type for Exchange
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExchange extends EntityPathBase<Exchange> {

    private static final long serialVersionUID = -1249937913L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExchange exchange = new QExchange("exchange");

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

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency currencyFrom;

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency currencyTo;

    public final SetPath<ExchangeDetail, QExchangeDetail> exchangeDetails = this.<ExchangeDetail, QExchangeDetail>createSet("exchangeDetails", ExchangeDetail.class, QExchangeDetail.class, PathInits.DIRECT2);

    public final QExchangeUploadFile exchangeUploadFile;

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

    public QExchange(String variable) {
        this(Exchange.class, forVariable(variable), INITS);
    }

    public QExchange(Path<? extends Exchange> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExchange(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExchange(PathMetadata metadata, PathInits inits) {
        this(Exchange.class, metadata, inits);
    }

    public QExchange(Class<? extends Exchange> type, PathMetadata metadata, PathInits inits) {
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
        this.currencyFrom = inits.isInitialized("currencyFrom") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("currencyFrom")) : null;
        this.currencyTo = inits.isInitialized("currencyTo") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("currencyTo")) : null;
        this.exchangeUploadFile = inits.isInitialized("exchangeUploadFile") ? new QExchangeUploadFile(forProperty("exchangeUploadFile"), inits.get("exchangeUploadFile")) : null;
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

