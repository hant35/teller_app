package vn.fpt.dbp.vccb.core.domain.organization;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = -1939809504L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddress address = new QAddress("address");

    public final QCity city;

    public final QDistrict district;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath street = createString("street");

    public final QWard ward;

    public QAddress(String variable) {
        this(Address.class, forVariable(variable), INITS);
    }

    public QAddress(Path<? extends Address> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAddress(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAddress(PathMetadata metadata, PathInits inits) {
        this(Address.class, metadata, inits);
    }

    public QAddress(Class<? extends Address> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city"), inits.get("city")) : null;
        this.district = inits.isInitialized("district") ? new QDistrict(forProperty("district"), inits.get("district")) : null;
        this.ward = inits.isInitialized("ward") ? new QWard(forProperty("ward"), inits.get("ward")) : null;
    }

}

