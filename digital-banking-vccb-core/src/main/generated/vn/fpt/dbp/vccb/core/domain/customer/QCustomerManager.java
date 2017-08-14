package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerManager is a Querydsl query type for CustomerManager
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomerManager extends EntityPathBase<CustomerManager> {

    private static final long serialVersionUID = -211321530L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerManager customerManager = new QCustomerManager("customerManager");

    public final QCustomer customer;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isKPICalculate = createBoolean("isKPICalculate");

    public final QReferralUser managerUser;

    public final QPosition position;

    public QCustomerManager(String variable) {
        this(CustomerManager.class, forVariable(variable), INITS);
    }

    public QCustomerManager(Path<? extends CustomerManager> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerManager(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerManager(PathMetadata metadata, PathInits inits) {
        this(CustomerManager.class, metadata, inits);
    }

    public QCustomerManager(Class<? extends CustomerManager> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.managerUser = inits.isInitialized("managerUser") ? new QReferralUser(forProperty("managerUser"), inits.get("managerUser")) : null;
        this.position = inits.isInitialized("position") ? new QPosition(forProperty("position")) : null;
    }

}

