package vn.fpt.dbp.vccb.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestrictUserBranch is a Querydsl query type for RestrictUserBranch
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRestrictUserBranch extends EntityPathBase<RestrictUserBranch> {

    private static final long serialVersionUID = 1602885093L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestrictUserBranch restrictUserBranch = new QRestrictUserBranch("restrictUserBranch");

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAllow = createBoolean("isAllow");

    public final QUser user;

    public QRestrictUserBranch(String variable) {
        this(RestrictUserBranch.class, forVariable(variable), INITS);
    }

    public QRestrictUserBranch(Path<? extends RestrictUserBranch> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRestrictUserBranch(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRestrictUserBranch(PathMetadata metadata, PathInits inits) {
        this(RestrictUserBranch.class, metadata, inits);
    }

    public QRestrictUserBranch(Class<? extends RestrictUserBranch> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

