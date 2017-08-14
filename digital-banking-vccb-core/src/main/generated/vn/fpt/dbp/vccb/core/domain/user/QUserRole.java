package vn.fpt.dbp.vccb.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserRole is a Querydsl query type for UserRole
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserRole extends EntityPathBase<UserRole> {

    private static final long serialVersionUID = -163807587L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserRole userRole = new QUserRole("userRole");

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final vn.fpt.dbp.vccb.core.domain.role.QRole role;

    public final QUser user;

    public QUserRole(String variable) {
        this(UserRole.class, forVariable(variable), INITS);
    }

    public QUserRole(Path<? extends UserRole> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserRole(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserRole(PathMetadata metadata, PathInits inits) {
        this(UserRole.class, metadata, inits);
    }

    public QUserRole(Class<? extends UserRole> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.role = inits.isInitialized("role") ? new vn.fpt.dbp.vccb.core.domain.role.QRole(forProperty("role"), inits.get("role")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

