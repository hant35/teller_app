package vn.fpt.dbp.vccb.core.domain.organization;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCity is a Querydsl query type for City
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCity extends EntityPathBase<City> {

    private static final long serialVersionUID = 1491438015L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCity city = new QCity("city");

    public final StringPath code = createString("code");

    public final QCountry country;

    public final SetPath<District, QDistrict> districts = this.<District, QDistrict>createSet("districts", District.class, QDistrict.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QCity(String variable) {
        this(City.class, forVariable(variable), INITS);
    }

    public QCity(Path<? extends City> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCity(PathMetadata metadata, PathInits inits) {
        this(City.class, metadata, inits);
    }

    public QCity(Class<? extends City> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country")) : null;
    }

}

