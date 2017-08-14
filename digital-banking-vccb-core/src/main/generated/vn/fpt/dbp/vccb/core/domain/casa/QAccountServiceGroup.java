package vn.fpt.dbp.vccb.core.domain.casa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccountServiceGroup is a Querydsl query type for AccountServiceGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccountServiceGroup extends EntityPathBase<AccountServiceGroup> {

    private static final long serialVersionUID = 415586108L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccountServiceGroup accountServiceGroup = new QAccountServiceGroup("accountServiceGroup");

    public final QAccountService accountService;

    public final SetPath<AccountServiceType, QAccountServiceType> accountServiceTypes = this.<AccountServiceType, QAccountServiceType>createSet("accountServiceTypes", AccountServiceType.class, QAccountServiceType.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QServiceGroup serviceGroup;

    public QAccountServiceGroup(String variable) {
        this(AccountServiceGroup.class, forVariable(variable), INITS);
    }

    public QAccountServiceGroup(Path<? extends AccountServiceGroup> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountServiceGroup(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountServiceGroup(PathMetadata metadata, PathInits inits) {
        this(AccountServiceGroup.class, metadata, inits);
    }

    public QAccountServiceGroup(Class<? extends AccountServiceGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accountService = inits.isInitialized("accountService") ? new QAccountService(forProperty("accountService"), inits.get("accountService")) : null;
        this.serviceGroup = inits.isInitialized("serviceGroup") ? new QServiceGroup(forProperty("serviceGroup"), inits.get("serviceGroup")) : null;
    }

}

