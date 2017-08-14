package vn.fpt.dbp.vccb.core.domain.organization;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDistrict is a Querydsl query type for District
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDistrict extends EntityPathBase<District> {

    private static final long serialVersionUID = 1503123938L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDistrict district = new QDistrict("district");

    public final QCity city;

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final SetPath<Ward, QWard> wards = this.<Ward, QWard>createSet("wards", Ward.class, QWard.class, PathInits.DIRECT2);

    public QDistrict(String variable) {
        this(District.class, forVariable(variable), INITS);
    }

    public QDistrict(Path<? extends District> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDistrict(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDistrict(PathMetadata metadata, PathInits inits) {
        this(District.class, metadata, inits);
    }

    public QDistrict(Class<? extends District> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new QCity(forProperty("city"), inits.get("city")) : null;
    }

}

