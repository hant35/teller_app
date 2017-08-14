package vn.fpt.dbp.vccb.core.domain.interest;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterestParameter is a Querydsl query type for InterestParameter
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInterestParameter extends EntityPathBase<InterestParameter> {

    private static final long serialVersionUID = 1073404290L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterestParameter interestParameter = new QInterestParameter("interestParameter");

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInterest interest;

    public final StringPath parameter = createString("parameter");

    public final NumberPath<Double> value = createNumber("value", Double.class);

    public QInterestParameter(String variable) {
        this(InterestParameter.class, forVariable(variable), INITS);
    }

    public QInterestParameter(Path<? extends InterestParameter> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInterestParameter(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInterestParameter(PathMetadata metadata, PathInits inits) {
        this(InterestParameter.class, metadata, inits);
    }

    public QInterestParameter(Class<? extends InterestParameter> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.interest = inits.isInitialized("interest") ? new QInterest(forProperty("interest"), inits.get("interest")) : null;
    }

}

