package vn.fpt.dbp.vccb.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestrictUserProduct is a Querydsl query type for RestrictUserProduct
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRestrictUserProduct extends EntityPathBase<RestrictUserProduct> {

    private static final long serialVersionUID = 1997595916L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestrictUserProduct restrictUserProduct = new QRestrictUserProduct("restrictUserProduct");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAllow = createBoolean("isAllow");

    public final vn.fpt.dbp.vccb.core.domain.product.QProduct product;

    public final QUser user;

    public QRestrictUserProduct(String variable) {
        this(RestrictUserProduct.class, forVariable(variable), INITS);
    }

    public QRestrictUserProduct(Path<? extends RestrictUserProduct> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRestrictUserProduct(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRestrictUserProduct(PathMetadata metadata, PathInits inits) {
        this(RestrictUserProduct.class, metadata, inits);
    }

    public QRestrictUserProduct(Class<? extends RestrictUserProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new vn.fpt.dbp.vccb.core.domain.product.QProduct(forProperty("product"), inits.get("product")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

