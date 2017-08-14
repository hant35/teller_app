package vn.fpt.dbp.vccb.core.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -1246687895L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel _super;

    public final SetPath<AccountClass, QAccountClass> accountClasses = this.<AccountClass, QAccountClass>createSet("accountClasses", AccountClass.class, QAccountClass.class, PathInits.DIRECT2);

    public final NumberPath<Integer> ageFrom = createNumber("ageFrom", Integer.class);

    public final NumberPath<Integer> ageTo = createNumber("ageTo", Integer.class);

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

    public final QAutoBlockType autoBlockType;

    public final StringPath code = createString("code");

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.base.Comment, vn.fpt.dbp.vccb.core.domain.base.QComment> comments;

    // inherited
    public final vn.fpt.dbp.vccb.core.domain.user.QUser createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate;

    public final QDepositType depositType;

    public final StringPath description = createString("description");

    public final DateTimePath<java.util.Date> effectedDate = createDateTime("effectedDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> expiredDate = createDateTime("expiredDate", java.util.Date.class);

    public final StringPath gender = createString("gender");

    //inherited
    public final NumberPath<Long> id;

    public final BooleanPath isAutoBlock = createBoolean("isAutoBlock");

    public final StringPath name = createString("name");

    //inherited
    public final NumberPath<Long> originalId;

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.role.Role, vn.fpt.dbp.vccb.core.domain.role.QRole> potentialAssignees;

    //inherited
    public final StringPath processDeploymentId;

    //inherited
    public final NumberPath<Long> processInstanceId;

    public final SetPath<ProductAccountClass, QProductAccountClass> productAccountClasses = this.<ProductAccountClass, QProductAccountClass>createSet("productAccountClasses", ProductAccountClass.class, QProductAccountClass.class, PathInits.DIRECT2);

    public final SetPath<ProductCustomer, QProductCustomer> productCustomers = this.<ProductCustomer, QProductCustomer>createSet("productCustomers", ProductCustomer.class, QProductCustomer.class, PathInits.DIRECT2);

    public final SetPath<ProductLimit, QProductLimit> productLimits = this.<ProductLimit, QProductLimit>createSet("productLimits", ProductLimit.class, QProductLimit.class, PathInits.DIRECT2);

    public final SetPath<ProductPromotion, QProductPromotion> productPromotions = this.<ProductPromotion, QProductPromotion>createSet("productPromotions", ProductPromotion.class, QProductPromotion.class, PathInits.DIRECT2);

    //inherited
    public final StringPath referenceCode;

    //inherited
    public final NumberPath<Long> taskId;

    public final BooleanPath transferable = createBoolean("transferable");

    //inherited
    public final StringPath workflowStatus;

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel(type, metadata, inits);
        this.approvedBy = _super.approvedBy;
        this.approvedDate = _super.approvedDate;
        this.assignedDate = _super.assignedDate;
        this.assignee = _super.assignee;
        this.assignGroup = _super.assignGroup;
        this.autoBlockType = inits.isInitialized("autoBlockType") ? new QAutoBlockType(forProperty("autoBlockType")) : null;
        this.comments = _super.comments;
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.depositType = inits.isInitialized("depositType") ? new QDepositType(forProperty("depositType")) : null;
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

