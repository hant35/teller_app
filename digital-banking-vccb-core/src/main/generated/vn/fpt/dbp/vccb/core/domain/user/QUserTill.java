package vn.fpt.dbp.vccb.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserTill is a Querydsl query type for UserTill
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserTill extends EntityPathBase<UserTill> {

    private static final long serialVersionUID = -163753764L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserTill userTill = new QUserTill("userTill");

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final vn.fpt.dbp.vccb.core.domain.currency.QTill till;

    public final QUser user;

    public QUserTill(String variable) {
        this(UserTill.class, forVariable(variable), INITS);
    }

    public QUserTill(Path<? extends UserTill> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserTill(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserTill(PathMetadata metadata, PathInits inits) {
        this(UserTill.class, metadata, inits);
    }

    public QUserTill(Class<? extends UserTill> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.till = inits.isInitialized("till") ? new vn.fpt.dbp.vccb.core.domain.currency.QTill(forProperty("till"), inits.get("till")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

