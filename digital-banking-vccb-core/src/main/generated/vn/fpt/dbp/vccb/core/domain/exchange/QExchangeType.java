package vn.fpt.dbp.vccb.core.domain.exchange;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QExchangeType is a Querydsl query type for ExchangeType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExchangeType extends EntityPathBase<ExchangeType> {

    private static final long serialVersionUID = 1566514657L;

    public static final QExchangeType exchangeType = new QExchangeType("exchangeType");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QExchangeType(String variable) {
        super(ExchangeType.class, forVariable(variable));
    }

    public QExchangeType(Path<? extends ExchangeType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExchangeType(PathMetadata metadata) {
        super(ExchangeType.class, metadata);
    }

}

