package vn.fpt.dbp.vccb.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserVault is a Querydsl query type for UserVault
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserVault extends EntityPathBase<UserVault> {

    private static final long serialVersionUID = -779781909L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserVault userVault = new QUserVault("userVault");

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public final vn.fpt.dbp.vccb.core.domain.currency.QVault vault;

    public QUserVault(String variable) {
        this(UserVault.class, forVariable(variable), INITS);
    }

    public QUserVault(Path<? extends UserVault> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserVault(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserVault(PathMetadata metadata, PathInits inits) {
        this(UserVault.class, metadata, inits);
    }

    public QUserVault(Class<? extends UserVault> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
        this.vault = inits.isInitialized("vault") ? new vn.fpt.dbp.vccb.core.domain.currency.QVault(forProperty("vault"), inits.get("vault")) : null;
    }

}

