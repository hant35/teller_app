package vn.fpt.dbp.vccb.core.domain.organization;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNationality is a Querydsl query type for Nationality
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNationality extends EntityPathBase<Nationality> {

    private static final long serialVersionUID = -1006205208L;

    public static final QNationality nationality = new QNationality("nationality");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QNationality(String variable) {
        super(Nationality.class, forVariable(variable));
    }

    public QNationality(Path<? extends Nationality> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNationality(PathMetadata metadata) {
        super(Nationality.class, metadata);
    }

}

