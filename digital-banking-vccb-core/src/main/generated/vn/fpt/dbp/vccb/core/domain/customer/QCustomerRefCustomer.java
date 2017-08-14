package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerRefCustomer is a Querydsl query type for CustomerRefCustomer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomerRefCustomer extends EntityPathBase<CustomerRefCustomer> {

    private static final long serialVersionUID = -1256369494L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerRefCustomer customerRefCustomer = new QCustomerRefCustomer("customerRefCustomer");

    public final QCustomer customerFrom;

    public final QCustomer customerTo;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPosition jobPosition;

    public final StringPath jobPositionOther = createString("jobPositionOther");

    public final NumberPath<Float> ownershipRatio = createNumber("ownershipRatio", Float.class);

    public final StringPath referenceType = createString("referenceType");

    public final StringPath refernceTypeOther = createString("refernceTypeOther");

    public QCustomerRefCustomer(String variable) {
        this(CustomerRefCustomer.class, forVariable(variable), INITS);
    }

    public QCustomerRefCustomer(Path<? extends CustomerRefCustomer> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerRefCustomer(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerRefCustomer(PathMetadata metadata, PathInits inits) {
        this(CustomerRefCustomer.class, metadata, inits);
    }

    public QCustomerRefCustomer(Class<? extends CustomerRefCustomer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customerFrom = inits.isInitialized("customerFrom") ? new QCustomer(forProperty("customerFrom"), inits.get("customerFrom")) : null;
        this.customerTo = inits.isInitialized("customerTo") ? new QCustomer(forProperty("customerTo"), inits.get("customerTo")) : null;
        this.jobPosition = inits.isInitialized("jobPosition") ? new QPosition(forProperty("jobPosition")) : null;
    }

}

