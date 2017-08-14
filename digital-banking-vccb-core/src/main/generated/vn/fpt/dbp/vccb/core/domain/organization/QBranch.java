package vn.fpt.dbp.vccb.core.domain.organization;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBranch is a Querydsl query type for Branch
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBranch extends EntityPathBase<Branch> {

    private static final long serialVersionUID = -1268035338L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBranch branch = new QBranch("branch");

    public final QArea area;

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final StringPath endTime = createString("endTime");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath startTime = createString("startTime");

    public QBranch(String variable) {
        this(Branch.class, forVariable(variable), INITS);
    }

    public QBranch(Path<? extends Branch> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBranch(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBranch(PathMetadata metadata, PathInits inits) {
        this(Branch.class, metadata, inits);
    }

    public QBranch(Class<? extends Branch> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.area = inits.isInitialized("area") ? new QArea(forProperty("area")) : null;
    }

}

