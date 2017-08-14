package vn.fpt.dbp.vccb.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserPermission is a Querydsl query type for UserPermission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserPermission extends EntityPathBase<UserPermission> {

    private static final long serialVersionUID = 948521654L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserPermission userPermission = new QUserPermission("userPermission");

    public final BooleanPath add = createBoolean("add");

    public final BooleanPath approve = createBoolean("approve");

    public final BooleanPath autoAuthorize = createBoolean("autoAuthorize");

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    public final BooleanPath cancel = createBoolean("cancel");

    public final BooleanPath close = createBoolean("close");

    public final BooleanPath confirm = createBoolean("confirm");

    public final BooleanPath copy = createBoolean("copy");

    public final BooleanPath delete = createBoolean("delete");

    public final vn.fpt.dbp.vccb.core.domain.system.QFunction function;

    public final BooleanPath generate = createBoolean("generate");

    public final BooleanPath hold = createBoolean("hold");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath payment = createBoolean("payment");

    public final BooleanPath print = createBoolean("print");

    public final BooleanPath reopen = createBoolean("reopen");

    public final BooleanPath rolloverComponent = createBoolean("rolloverComponent");

    public final BooleanPath subView = createBoolean("subView");

    public final BooleanPath template = createBoolean("template");

    public final BooleanPath update = createBoolean("update");

    public final QUser user;

    public final BooleanPath view = createBoolean("view");

    public QUserPermission(String variable) {
        this(UserPermission.class, forVariable(variable), INITS);
    }

    public QUserPermission(Path<? extends UserPermission> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserPermission(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserPermission(PathMetadata metadata, PathInits inits) {
        this(UserPermission.class, metadata, inits);
    }

    public QUserPermission(Class<? extends UserPermission> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.function = inits.isInitialized("function") ? new vn.fpt.dbp.vccb.core.domain.system.QFunction(forProperty("function")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

