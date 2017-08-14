package vn.fpt.dbp.vccb.core.domain.casa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QServiceGroupType is a Querydsl query type for ServiceGroupType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QServiceGroupType extends EntityPathBase<ServiceGroupType> {

    private static final long serialVersionUID = -909462113L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QServiceGroupType serviceGroupType = new QServiceGroupType("serviceGroupType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QServiceGroup serviceGroup;

    public final QServiceType serviceType;

    public QServiceGroupType(String variable) {
        this(ServiceGroupType.class, forVariable(variable), INITS);
    }

    public QServiceGroupType(Path<? extends ServiceGroupType> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QServiceGroupType(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QServiceGroupType(PathMetadata metadata, PathInits inits) {
        this(ServiceGroupType.class, metadata, inits);
    }

    public QServiceGroupType(Class<? extends ServiceGroupType> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.serviceGroup = inits.isInitialized("serviceGroup") ? new QServiceGroup(forProperty("serviceGroup"), inits.get("serviceGroup")) : null;
        this.serviceType = inits.isInitialized("serviceType") ? new QServiceType(forProperty("serviceType"), inits.get("serviceType")) : null;
    }

}

