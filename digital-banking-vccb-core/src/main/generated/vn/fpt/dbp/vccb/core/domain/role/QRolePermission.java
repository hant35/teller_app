package vn.fpt.dbp.vccb.core.domain.role;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRolePermission is a Querydsl query type for RolePermission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRolePermission extends EntityPathBase<RolePermission> {

    private static final long serialVersionUID = -1005034090L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRolePermission rolePermission = new QRolePermission("rolePermission");

    public final BooleanPath add = createBoolean("add");

    public final BooleanPath approve = createBoolean("approve");

    public final BooleanPath autoAuthorize = createBoolean("autoAuthorize");

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

    public final QRole role;

    public final BooleanPath rolloverComponent = createBoolean("rolloverComponent");

    public final BooleanPath subView = createBoolean("subView");

    public final BooleanPath template = createBoolean("template");

    public final BooleanPath update = createBoolean("update");

    public final BooleanPath view = createBoolean("view");

    public QRolePermission(String variable) {
        this(RolePermission.class, forVariable(variable), INITS);
    }

    public QRolePermission(Path<? extends RolePermission> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRolePermission(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRolePermission(PathMetadata metadata, PathInits inits) {
        this(RolePermission.class, metadata, inits);
    }

    public QRolePermission(Class<? extends RolePermission> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.function = inits.isInitialized("function") ? new vn.fpt.dbp.vccb.core.domain.system.QFunction(forProperty("function")) : null;
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role"), inits.get("role")) : null;
    }

}

