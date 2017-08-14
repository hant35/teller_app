package vn.fpt.dbp.vccb.core.domain.organization;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWard is a Querydsl query type for Ward
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWard extends EntityPathBase<Ward> {

    private static final long serialVersionUID = 1492026064L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWard ward = new QWard("ward");

    public final StringPath code = createString("code");

    public final QDistrict district;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QWard(String variable) {
        this(Ward.class, forVariable(variable), INITS);
    }

    public QWard(Path<? extends Ward> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QWard(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QWard(PathMetadata metadata, PathInits inits) {
        this(Ward.class, metadata, inits);
    }

    public QWard(Class<? extends Ward> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.district = inits.isInitialized("district") ? new QDistrict(forProperty("district"), inits.get("district")) : null;
    }

}

