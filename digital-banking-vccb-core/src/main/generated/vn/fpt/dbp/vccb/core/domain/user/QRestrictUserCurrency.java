package vn.fpt.dbp.vccb.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestrictUserCurrency is a Querydsl query type for RestrictUserCurrency
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRestrictUserCurrency extends EntityPathBase<RestrictUserCurrency> {

    private static final long serialVersionUID = -919872620L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestrictUserCurrency restrictUserCurrency = new QRestrictUserCurrency("restrictUserCurrency");

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency currency;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAllow = createBoolean("isAllow");

    public final QUser user;

    public QRestrictUserCurrency(String variable) {
        this(RestrictUserCurrency.class, forVariable(variable), INITS);
    }

    public QRestrictUserCurrency(Path<? extends RestrictUserCurrency> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRestrictUserCurrency(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRestrictUserCurrency(PathMetadata metadata, PathInits inits) {
        this(RestrictUserCurrency.class, metadata, inits);
    }

    public QRestrictUserCurrency(Class<? extends RestrictUserCurrency> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.currency = inits.isInitialized("currency") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("currency")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

