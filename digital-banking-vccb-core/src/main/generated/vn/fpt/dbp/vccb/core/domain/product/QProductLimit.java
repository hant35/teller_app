package vn.fpt.dbp.vccb.core.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductLimit is a Querydsl query type for ProductLimit
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductLimit extends EntityPathBase<ProductLimit> {

    private static final long serialVersionUID = 394020818L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductLimit productLimit = new QProductLimit("productLimit");

    public final NumberPath<Double> availbleValue = createNumber("availbleValue", Double.class);

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency currency;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> limitValue = createNumber("limitValue", Double.class);

    public final QProduct product;

    public final NumberPath<Double> usedValue = createNumber("usedValue", Double.class);

    public QProductLimit(String variable) {
        this(ProductLimit.class, forVariable(variable), INITS);
    }

    public QProductLimit(Path<? extends ProductLimit> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductLimit(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductLimit(PathMetadata metadata, PathInits inits) {
        this(ProductLimit.class, metadata, inits);
    }

    public QProductLimit(Class<? extends ProductLimit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.currency = inits.isInitialized("currency") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("currency")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

