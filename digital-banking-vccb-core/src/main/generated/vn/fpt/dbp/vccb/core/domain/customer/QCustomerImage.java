package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerImage is a Querydsl query type for CustomerImage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomerImage extends EntityPathBase<CustomerImage> {

    private static final long serialVersionUID = -1554405068L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerImage customerImage = new QCustomerImage("customerImage");

    public final QCustomer customer;

    public final StringPath customerName = createString("customerName");

    public final StringPath customerPosition = createString("customerPosition");

    public final StringPath description = createString("description");

    public final DateTimePath<java.util.Date> expiredDate = createDateTime("expiredDate", java.util.Date.class);

    public final StringPath fileUrl = createString("fileUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ArrayPath<byte[], Byte> image = createArray("image", byte[].class);

    public final StringPath type = createString("type");

    public final DateTimePath<java.util.Date> validDate = createDateTime("validDate", java.util.Date.class);

    public QCustomerImage(String variable) {
        this(CustomerImage.class, forVariable(variable), INITS);
    }

    public QCustomerImage(Path<? extends CustomerImage> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerImage(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerImage(PathMetadata metadata, PathInits inits) {
        this(CustomerImage.class, metadata, inits);
    }

    public QCustomerImage(Class<? extends CustomerImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer"), inits.get("customer")) : null;
    }

}

