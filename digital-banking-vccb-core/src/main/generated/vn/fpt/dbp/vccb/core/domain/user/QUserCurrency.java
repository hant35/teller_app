package vn.fpt.dbp.vccb.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCurrency is a Querydsl query type for UserCurrency
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserCurrency extends EntityPathBase<UserCurrency> {

    private static final long serialVersionUID = -1563852104L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCurrency userCurrency = new QUserCurrency("userCurrency");

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency currency;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public QUserCurrency(String variable) {
        this(UserCurrency.class, forVariable(variable), INITS);
    }

    public QUserCurrency(Path<? extends UserCurrency> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserCurrency(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserCurrency(PathMetadata metadata, PathInits inits) {
        this(UserCurrency.class, metadata, inits);
    }

    public QUserCurrency(Class<? extends UserCurrency> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.currency = inits.isInitialized("currency") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("currency")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

