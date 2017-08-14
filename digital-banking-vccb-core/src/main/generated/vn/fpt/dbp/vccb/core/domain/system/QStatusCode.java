package vn.fpt.dbp.vccb.core.domain.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStatusCode is a Querydsl query type for StatusCode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStatusCode extends EntityPathBase<StatusCode> {

    private static final long serialVersionUID = -977324393L;

    public static final QStatusCode statusCode = new QStatusCode("statusCode");

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath object = createString("object");

    public final StringPath value = createString("value");

    public QStatusCode(String variable) {
        super(StatusCode.class, forVariable(variable));
    }

    public QStatusCode(Path<? extends StatusCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStatusCode(PathMetadata metadata) {
        super(StatusCode.class, metadata);
    }

}

