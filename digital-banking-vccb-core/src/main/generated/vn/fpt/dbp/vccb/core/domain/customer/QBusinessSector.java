package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBusinessSector is a Querydsl query type for BusinessSector
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBusinessSector extends EntityPathBase<BusinessSector> {

    private static final long serialVersionUID = -577462001L;

    public static final QBusinessSector businessSector = new QBusinessSector("businessSector");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QBusinessSector(String variable) {
        super(BusinessSector.class, forVariable(variable));
    }

    public QBusinessSector(Path<? extends BusinessSector> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBusinessSector(PathMetadata metadata) {
        super(BusinessSector.class, metadata);
    }

}

