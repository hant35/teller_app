package vn.fpt.dbp.vccb.core.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductPromotion is a Querydsl query type for ProductPromotion
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductPromotion extends EntityPathBase<ProductPromotion> {

    private static final long serialVersionUID = 1444681018L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductPromotion productPromotion = new QProductPromotion("productPromotion");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProduct product;

    public final QPromotion promotion;

    public QProductPromotion(String variable) {
        this(ProductPromotion.class, forVariable(variable), INITS);
    }

    public QProductPromotion(Path<? extends ProductPromotion> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductPromotion(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductPromotion(PathMetadata metadata, PathInits inits) {
        this(ProductPromotion.class, metadata, inits);
    }

    public QProductPromotion(Class<? extends ProductPromotion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
        this.promotion = inits.isInitialized("promotion") ? new QPromotion(forProperty("promotion"), inits.get("promotion")) : null;
    }

}

