package vn.fpt.dbp.vccb.core.domain.casa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QServiceType is a Querydsl query type for ServiceType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QServiceType extends EntityPathBase<ServiceType> {

    private static final long serialVersionUID = 1821247540L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QServiceType serviceType = new QServiceType("serviceType");

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

    public final StringPath DataColumn01 = createString("DataColumn01");

    public final StringPath DataColumn02 = createString("DataColumn02");

    public final StringPath DataColumn03 = createString("DataColumn03");

    public final StringPath DataColumn04 = createString("DataColumn04");

    public final StringPath DataColumn05 = createString("DataColumn05");

    public final StringPath DataColumn06 = createString("DataColumn06");

    public final StringPath DataColumn07 = createString("DataColumn07");

    public final StringPath DataColumn08 = createString("DataColumn08");

    public final StringPath DataColumn09 = createString("DataColumn09");

    public final StringPath DataColumn10 = createString("DataColumn10");

    public final StringPath DataColumn11 = createString("DataColumn11");

    public final StringPath DataColumn12 = createString("DataColumn12");

    public final StringPath DataColumn13 = createString("DataColumn13");

    public final StringPath DataColumn14 = createString("DataColumn14");

    public final StringPath DataColumn15 = createString("DataColumn15");

    public final StringPath DataColumn16 = createString("DataColumn16");

    public final StringPath DataColumn17 = createString("DataColumn17");

    public final StringPath DataColumn18 = createString("DataColumn18");

    public final StringPath DataColumn19 = createString("DataColumn19");

    public final StringPath DataColumn20 = createString("DataColumn20");

    public final StringPath dataList = createString("dataList");

    public final DateTimePath<java.util.Date> expiredDate = createDateTime("expiredDate", java.util.Date.class);

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

    public final StringPath serviceTypeForm = createString("serviceTypeForm");

    public final StringPath status = createString("status");

    //inherited
    public final NumberPath<Long> taskId;

    public final DateTimePath<java.util.Date> validDate = createDateTime("validDate", java.util.Date.class);

    //inherited
    public final StringPath workflowStatus;

    public QServiceType(String variable) {
        this(ServiceType.class, forVariable(variable), INITS);
    }

    public QServiceType(Path<? extends ServiceType> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QServiceType(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QServiceType(PathMetadata metadata, PathInits inits) {
        this(ServiceType.class, metadata, inits);
    }

    public QServiceType(Class<? extends ServiceType> type, PathMetadata metadata, PathInits inits) {
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

