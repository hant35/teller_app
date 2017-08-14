package vn.fpt.dbp.vccb.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1040079481L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel _super;

    // inherited
    public final QUser approvedBy;

    //inherited
    public final DateTimePath<java.util.Date> approvedDate;

    //inherited
    public final DateTimePath<java.util.Date> assignedDate;

    // inherited
    public final QUser assignee;

    //inherited
    public final StringPath assignGroup;

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    public final StringPath cifCode = createString("cifCode");

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.base.Comment, vn.fpt.dbp.vccb.core.domain.base.QComment> comments;

    // inherited
    public final QUser createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate;

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch currentBranch;

    public final StringPath customerName = createString("customerName");

    public final vn.fpt.dbp.vccb.core.domain.organization.QDepartment department;

    public final DateTimePath<java.util.Date> effectedDate = createDateTime("effectedDate", java.util.Date.class);

    public final StringPath email = createString("email");

    public final StringPath employeeCode = createString("employeeCode");

    public final StringPath employeeName = createString("employeeName");

    public final StringPath endTime = createString("endTime");

    public final DateTimePath<java.util.Date> expiredDate = createDateTime("expiredDate", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id;

    public final BooleanPath isAdmin = createBoolean("isAdmin");

    public final BooleanPath isTemplate = createBoolean("isTemplate");

    public final BooleanPath isWorkOnCif = createBoolean("isWorkOnCif");

    public final DateTimePath<java.util.Date> lastPasswordChangedDate = createDateTime("lastPasswordChangedDate", java.util.Date.class);

    public final StringPath level = createString("level");

    public final StringPath loginStatus = createString("loginStatus");

    //inherited
    public final NumberPath<Long> originalId;

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    //inherited
    public final SetPath<vn.fpt.dbp.vccb.core.domain.role.Role, vn.fpt.dbp.vccb.core.domain.role.QRole> potentialAssignees;

    //inherited
    public final StringPath processDeploymentId;

    //inherited
    public final NumberPath<Long> processInstanceId;

    //inherited
    public final StringPath referenceCode;

    public final SetPath<RestrictUserBranch, QRestrictUserBranch> restrictUserBranch = this.<RestrictUserBranch, QRestrictUserBranch>createSet("restrictUserBranch", RestrictUserBranch.class, QRestrictUserBranch.class, PathInits.DIRECT2);

    public final SetPath<RestrictUserCurrency, QRestrictUserCurrency> restrictUserCurrency = this.<RestrictUserCurrency, QRestrictUserCurrency>createSet("restrictUserCurrency", RestrictUserCurrency.class, QRestrictUserCurrency.class, PathInits.DIRECT2);

    public final SetPath<RestrictUserCustomerGroup, QRestrictUserCustomerGroup> restrictUserCustomerGroup = this.<RestrictUserCustomerGroup, QRestrictUserCustomerGroup>createSet("restrictUserCustomerGroup", RestrictUserCustomerGroup.class, QRestrictUserCustomerGroup.class, PathInits.DIRECT2);

    public final SetPath<RestrictUserFunction, QRestrictUserFunction> restrictUserFunction = this.<RestrictUserFunction, QRestrictUserFunction>createSet("restrictUserFunction", RestrictUserFunction.class, QRestrictUserFunction.class, PathInits.DIRECT2);

    public final SetPath<RestrictUserProduct, QRestrictUserProduct> restrictUserProduct = this.<RestrictUserProduct, QRestrictUserProduct>createSet("restrictUserProduct", RestrictUserProduct.class, QRestrictUserProduct.class, PathInits.DIRECT2);

    public final StringPath startTime = createString("startTime");

    public final StringPath status = createString("status");

    //inherited
    public final NumberPath<Long> taskId;

    public final SetPath<UserCurrency, QUserCurrency> userCurrency = this.<UserCurrency, QUserCurrency>createSet("userCurrency", UserCurrency.class, QUserCurrency.class, PathInits.DIRECT2);

    public final SetPath<UserCustomerGroup, QUserCustomerGroup> userCustomerGroup = this.<UserCustomerGroup, QUserCustomerGroup>createSet("userCustomerGroup", UserCustomerGroup.class, QUserCustomerGroup.class, PathInits.DIRECT2);

    public final SetPath<UserLimitGroup, QUserLimitGroup> userLimitGroup = this.<UserLimitGroup, QUserLimitGroup>createSet("userLimitGroup", UserLimitGroup.class, QUserLimitGroup.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public final SetPath<UserPermission, QUserPermission> userPermission = this.<UserPermission, QUserPermission>createSet("userPermission", UserPermission.class, QUserPermission.class, PathInits.DIRECT2);

    public final SetPath<UserRole, QUserRole> userRoles = this.<UserRole, QUserRole>createSet("userRoles", UserRole.class, QUserRole.class, PathInits.DIRECT2);

    public final SetPath<UserTill, QUserTill> userTills = this.<UserTill, QUserTill>createSet("userTills", UserTill.class, QUserTill.class, PathInits.DIRECT2);

    public final SetPath<UserVault, QUserVault> userVaults = this.<UserVault, QUserVault>createSet("userVaults", UserVault.class, QUserVault.class, PathInits.DIRECT2);

    //inherited
    public final StringPath workflowStatus;

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new vn.fpt.dbp.vccb.core.domain.base.QWorkFlowModel(type, metadata, inits);
        this.approvedBy = _super.approvedBy;
        this.approvedDate = _super.approvedDate;
        this.assignedDate = _super.assignedDate;
        this.assignee = _super.assignee;
        this.assignGroup = _super.assignGroup;
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.comments = _super.comments;
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.currentBranch = inits.isInitialized("currentBranch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("currentBranch"), inits.get("currentBranch")) : null;
        this.department = inits.isInitialized("department") ? new vn.fpt.dbp.vccb.core.domain.organization.QDepartment(forProperty("department"), inits.get("department")) : null;
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

