package vn.fpt.dbp.vccb.core.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAutoBlockType is a Querydsl query type for AutoBlockType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAutoBlockType extends EntityPathBase<AutoBlockType> {

    private static final long serialVersionUID = -1725076110L;

    public static final QAutoBlockType autoBlockType = new QAutoBlockType("autoBlockType");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QAutoBlockType(String variable) {
        super(AutoBlockType.class, forVariable(variable));
    }

    public QAutoBlockType(Path<? extends AutoBlockType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAutoBlockType(PathMetadata metadata) {
        super(AutoBlockType.class, metadata);
    }

}

