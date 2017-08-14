package vn.fpt.dbp.vccb.core.domain.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFunction is a Querydsl query type for Function
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFunction extends EntityPathBase<Function> {

    private static final long serialVersionUID = 843351920L;

    public static final QFunction function = new QFunction("function");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath module = createString("module");

    public final StringPath name = createString("name");

    public final StringPath screen = createString("screen");

    public QFunction(String variable) {
        super(Function.class, forVariable(variable));
    }

    public QFunction(Path<? extends Function> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFunction(PathMetadata metadata) {
        super(Function.class, metadata);
    }

}

