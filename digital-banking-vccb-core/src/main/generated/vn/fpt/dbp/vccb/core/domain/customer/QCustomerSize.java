package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerSize is a Querydsl query type for CustomerSize
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomerSize extends EntityPathBase<CustomerSize> {

    private static final long serialVersionUID = -881131256L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerSize customerSize = new QCustomerSize("customerSize");

    public final StringPath code = createString("code");

    public final QCustomerType customerType;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QCustomerSize(String variable) {
        this(CustomerSize.class, forVariable(variable), INITS);
    }

    public QCustomerSize(Path<? extends CustomerSize> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerSize(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerSize(PathMetadata metadata, PathInits inits) {
        this(CustomerSize.class, metadata, inits);
    }

    public QCustomerSize(Class<? extends CustomerSize> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customerType = inits.isInitialized("customerType") ? new QCustomerType(forProperty("customerType")) : null;
    }

}

