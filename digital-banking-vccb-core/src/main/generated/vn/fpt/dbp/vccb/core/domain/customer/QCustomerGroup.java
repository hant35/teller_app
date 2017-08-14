package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerGroup is a Querydsl query type for CustomerGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomerGroup extends EntityPathBase<CustomerGroup> {

    private static final long serialVersionUID = -1556089256L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerGroup customerGroup = new QCustomerGroup("customerGroup");

    public final StringPath code = createString("code");

    public final QCustomerType customerType;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QCustomerGroup(String variable) {
        this(CustomerGroup.class, forVariable(variable), INITS);
    }

    public QCustomerGroup(Path<? extends CustomerGroup> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerGroup(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerGroup(PathMetadata metadata, PathInits inits) {
        this(CustomerGroup.class, metadata, inits);
    }

    public QCustomerGroup(Class<? extends CustomerGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customerType = inits.isInitialized("customerType") ? new QCustomerType(forProperty("customerType")) : null;
    }

}

