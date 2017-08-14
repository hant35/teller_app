package vn.fpt.dbp.vccb.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestrictUserCustomerGroup is a Querydsl query type for RestrictUserCustomerGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRestrictUserCustomerGroup extends EntityPathBase<RestrictUserCustomerGroup> {

    private static final long serialVersionUID = 768446366L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestrictUserCustomerGroup restrictUserCustomerGroup = new QRestrictUserCustomerGroup("restrictUserCustomerGroup");

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    public final vn.fpt.dbp.vccb.core.domain.customer.QCustomerGroup customerGroup;

    public final vn.fpt.dbp.vccb.core.domain.customer.QCustomerType customerType;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAllow = createBoolean("isAllow");

    public final QUser user;

    public QRestrictUserCustomerGroup(String variable) {
        this(RestrictUserCustomerGroup.class, forVariable(variable), INITS);
    }

    public QRestrictUserCustomerGroup(Path<? extends RestrictUserCustomerGroup> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRestrictUserCustomerGroup(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRestrictUserCustomerGroup(PathMetadata metadata, PathInits inits) {
        this(RestrictUserCustomerGroup.class, metadata, inits);
    }

    public QRestrictUserCustomerGroup(Class<? extends RestrictUserCustomerGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.customerGroup = inits.isInitialized("customerGroup") ? new vn.fpt.dbp.vccb.core.domain.customer.QCustomerGroup(forProperty("customerGroup"), inits.get("customerGroup")) : null;
        this.customerType = inits.isInitialized("customerType") ? new vn.fpt.dbp.vccb.core.domain.customer.QCustomerType(forProperty("customerType")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

