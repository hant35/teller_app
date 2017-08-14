package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerPaymentInfo is a Querydsl query type for CustomerPaymentInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomerPaymentInfo extends EntityPathBase<CustomerPaymentInfo> {

    private static final long serialVersionUID = 1865847021L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerPaymentInfo customerPaymentInfo = new QCustomerPaymentInfo("customerPaymentInfo");

    public final StringPath accountNumber = createString("accountNumber");

    public final StringPath bankName = createString("bankName");

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency currency;

    public final QCustomer customer;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath swiftCode = createString("swiftCode");

    public QCustomerPaymentInfo(String variable) {
        this(CustomerPaymentInfo.class, forVariable(variable), INITS);
    }

    public QCustomerPaymentInfo(Path<? extends CustomerPaymentInfo> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerPaymentInfo(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerPaymentInfo(PathMetadata metadata, PathInits inits) {
        this(CustomerPaymentInfo.class, metadata, inits);
    }

    public QCustomerPaymentInfo(Class<? extends CustomerPaymentInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.currency = inits.isInitialized("currency") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("currency")) : null;
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer"), inits.get("customer")) : null;
    }

}

