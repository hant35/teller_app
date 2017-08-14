package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPosition is a Querydsl query type for Position
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPosition extends EntityPathBase<Position> {

    private static final long serialVersionUID = -1695198798L;

    public static final QPosition position = new QPosition("position1");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath type = createString("type");

    public QPosition(String variable) {
        super(Position.class, forVariable(variable));
    }

    public QPosition(Path<? extends Position> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPosition(PathMetadata metadata) {
        super(Position.class, metadata);
    }

}

