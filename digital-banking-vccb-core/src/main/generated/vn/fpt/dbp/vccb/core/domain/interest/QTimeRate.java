package vn.fpt.dbp.vccb.core.domain.interest;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimeRate is a Querydsl query type for TimeRate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTimeRate extends EntityPathBase<TimeRate> {

    private static final long serialVersionUID = 1685777418L;

    public static final QTimeRate timeRate = new QTimeRate("timeRate");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> value = createNumber("value", Integer.class);

    public QTimeRate(String variable) {
        super(TimeRate.class, forVariable(variable));
    }

    public QTimeRate(Path<? extends TimeRate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeRate(PathMetadata metadata) {
        super(TimeRate.class, metadata);
    }

}

