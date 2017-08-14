package vn.fpt.dbp.vccb.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLimitGroup is a Querydsl query type for UserLimitGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserLimitGroup extends EntityPathBase<UserLimitGroup> {

    private static final long serialVersionUID = -305177493L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLimitGroup userLimitGroup = new QUserLimitGroup("userLimitGroup");

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final vn.fpt.dbp.vccb.core.domain.limit.QLimitGroup limitGroup;

    public final QUser user;

    public QUserLimitGroup(String variable) {
        this(UserLimitGroup.class, forVariable(variable), INITS);
    }

    public QUserLimitGroup(Path<? extends UserLimitGroup> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserLimitGroup(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserLimitGroup(PathMetadata metadata, PathInits inits) {
        this(UserLimitGroup.class, metadata, inits);
    }

    public QUserLimitGroup(Class<? extends UserLimitGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.limitGroup = inits.isInitialized("limitGroup") ? new vn.fpt.dbp.vccb.core.domain.limit.QLimitGroup(forProperty("limitGroup"), inits.get("limitGroup")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

