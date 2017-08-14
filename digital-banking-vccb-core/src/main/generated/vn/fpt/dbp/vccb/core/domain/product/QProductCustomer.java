package vn.fpt.dbp.vccb.core.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductCustomer is a Querydsl query type for ProductCustomer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductCustomer extends EntityPathBase<ProductCustomer> {

    private static final long serialVersionUID = -429819801L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductCustomer productCustomer = new QProductCustomer("productCustomer");

    public final vn.fpt.dbp.vccb.core.domain.customer.QCustomerSize customerSize;

    public final vn.fpt.dbp.vccb.core.domain.customer.QCustomerType customerType;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProduct product;

    public QProductCustomer(String variable) {
        this(ProductCustomer.class, forVariable(variable), INITS);
    }

    public QProductCustomer(Path<? extends ProductCustomer> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductCustomer(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductCustomer(PathMetadata metadata, PathInits inits) {
        this(ProductCustomer.class, metadata, inits);
    }

    public QProductCustomer(Class<? extends ProductCustomer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customerSize = inits.isInitialized("customerSize") ? new vn.fpt.dbp.vccb.core.domain.customer.QCustomerSize(forProperty("customerSize"), inits.get("customerSize")) : null;
        this.customerType = inits.isInitialized("customerType") ? new vn.fpt.dbp.vccb.core.domain.customer.QCustomerType(forProperty("customerType")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

