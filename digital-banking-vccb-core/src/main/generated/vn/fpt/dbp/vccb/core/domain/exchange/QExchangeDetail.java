package vn.fpt.dbp.vccb.core.domain.exchange;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExchangeDetail is a Querydsl query type for ExchangeDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExchangeDetail extends EntityPathBase<ExchangeDetail> {

    private static final long serialVersionUID = 1705613624L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExchangeDetail exchangeDetail = new QExchangeDetail("exchangeDetail");

    public final NumberPath<Double> buyBand = createNumber("buyBand", Double.class);

    public final NumberPath<Double> buyPrice = createNumber("buyPrice", Double.class);

    public final NumberPath<Integer> changedNumber = createNumber("changedNumber", Integer.class);

    public final DateTimePath<java.util.Date> effectedDate = createDateTime("effectedDate", java.util.Date.class);

    public final QExchange exchange;

    public final QExchangeType exchangeType;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> midRate = createNumber("midRate", Double.class);

    public final NumberPath<Double> sellBand = createNumber("sellBand", Double.class);

    public final NumberPath<Double> sellPrice = createNumber("sellPrice", Double.class);

    public QExchangeDetail(String variable) {
        this(ExchangeDetail.class, forVariable(variable), INITS);
    }

    public QExchangeDetail(Path<? extends ExchangeDetail> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExchangeDetail(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QExchangeDetail(PathMetadata metadata, PathInits inits) {
        this(ExchangeDetail.class, metadata, inits);
    }

    public QExchangeDetail(Class<? extends ExchangeDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exchange = inits.isInitialized("exchange") ? new QExchange(forProperty("exchange"), inits.get("exchange")) : null;
        this.exchangeType = inits.isInitialized("exchangeType") ? new QExchangeType(forProperty("exchangeType")) : null;
    }

}

