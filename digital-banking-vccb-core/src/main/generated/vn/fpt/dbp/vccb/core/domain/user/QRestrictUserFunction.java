package vn.fpt.dbp.vccb.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestrictUserFunction is a Querydsl query type for RestrictUserFunction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRestrictUserFunction extends EntityPathBase<RestrictUserFunction> {

    private static final long serialVersionUID = -114335909L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestrictUserFunction restrictUserFunction = new QRestrictUserFunction("restrictUserFunction");

    public final vn.fpt.dbp.vccb.core.domain.system.QFunction function;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAllow = createBoolean("isAllow");

    public final QUser user;

    public QRestrictUserFunction(String variable) {
        this(RestrictUserFunction.class, forVariable(variable), INITS);
    }

    public QRestrictUserFunction(Path<? extends RestrictUserFunction> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRestrictUserFunction(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRestrictUserFunction(PathMetadata metadata, PathInits inits) {
        this(RestrictUserFunction.class, metadata, inits);
    }

    public QRestrictUserFunction(Class<? extends RestrictUserFunction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.function = inits.isInitialized("function") ? new vn.fpt.dbp.vccb.core.domain.system.QFunction(forProperty("function")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

