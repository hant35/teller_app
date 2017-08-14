package vn.fpt.dbp.vccb.core.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCustomerGroup is a Querydsl query type for UserCustomerGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserCustomerGroup extends EntityPathBase<UserCustomerGroup> {

    private static final long serialVersionUID = 84852474L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCustomerGroup userCustomerGroup = new QUserCustomerGroup("userCustomerGroup");

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    public final vn.fpt.dbp.vccb.core.domain.customer.QCustomerGroup customerGroup;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public QUserCustomerGroup(String variable) {
        this(UserCustomerGroup.class, forVariable(variable), INITS);
    }

    public QUserCustomerGroup(Path<? extends UserCustomerGroup> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserCustomerGroup(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserCustomerGroup(PathMetadata metadata, PathInits inits) {
        this(UserCustomerGroup.class, metadata, inits);
    }

    public QUserCustomerGroup(Class<? extends UserCustomerGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.customerGroup = inits.isInitialized("customerGroup") ? new vn.fpt.dbp.vccb.core.domain.customer.QCustomerGroup(forProperty("customerGroup"), inits.get("customerGroup")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

