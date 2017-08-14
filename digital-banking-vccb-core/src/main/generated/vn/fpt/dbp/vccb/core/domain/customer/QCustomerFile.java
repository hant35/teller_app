package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerFile is a Querydsl query type for CustomerFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomerFile extends EntityPathBase<CustomerFile> {

    private static final long serialVersionUID = -881518973L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerFile customerFile = new QCustomerFile("customerFile");

    public final QCustomer customer;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> fileAmount = createNumber("fileAmount", Integer.class);

    public final StringPath fileType = createString("fileType");

    public final StringPath fileTypeOther = createString("fileTypeOther");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isFileCopy = createBoolean("isFileCopy");

    public final BooleanPath isFileOriginal = createBoolean("isFileOriginal");

    public final BooleanPath isFileProvided = createBoolean("isFileProvided");

    public QCustomerFile(String variable) {
        this(CustomerFile.class, forVariable(variable), INITS);
    }

    public QCustomerFile(Path<? extends CustomerFile> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerFile(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerFile(PathMetadata metadata, PathInits inits) {
        this(CustomerFile.class, metadata, inits);
    }

    public QCustomerFile(Class<? extends CustomerFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer"), inits.get("customer")) : null;
    }

}

