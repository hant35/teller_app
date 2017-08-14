package vn.fpt.dbp.vccb.core.domain.casa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccountServiceType is a Querydsl query type for AccountServiceType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccountServiceType extends EntityPathBase<AccountServiceType> {

    private static final long serialVersionUID = 152347357L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccountServiceType accountServiceType = new QAccountServiceType("accountServiceType");

    public final QAccountService accountService;

    public final QAccountServiceGroup accountServiceGroup;

    public final StringPath DataColumn01 = createString("DataColumn01");

    public final StringPath DataColumn02 = createString("DataColumn02");

    public final StringPath DataColumn03 = createString("DataColumn03");

    public final StringPath DataColumn04 = createString("DataColumn04");

    public final StringPath DataColumn05 = createString("DataColumn05");

    public final StringPath DataColumn06 = createString("DataColumn06");

    public final StringPath DataColumn07 = createString("DataColumn07");

    public final StringPath DataColumn08 = createString("DataColumn08");

    public final StringPath DataColumn09 = createString("DataColumn09");

    public final StringPath DataColumn10 = createString("DataColumn10");

    public final StringPath DataColumn11 = createString("DataColumn11");

    public final StringPath DataColumn12 = createString("DataColumn12");

    public final StringPath DataColumn13 = createString("DataColumn13");

    public final StringPath DataColumn14 = createString("DataColumn14");

    public final StringPath DataColumn15 = createString("DataColumn15");

    public final StringPath DataColumn16 = createString("DataColumn16");

    public final StringPath DataColumn17 = createString("DataColumn17");

    public final StringPath DataColumn18 = createString("DataColumn18");

    public final StringPath DataColumn19 = createString("DataColumn19");

    public final StringPath DataColumn20 = createString("DataColumn20");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QServiceType serviceType;

    public QAccountServiceType(String variable) {
        this(AccountServiceType.class, forVariable(variable), INITS);
    }

    public QAccountServiceType(Path<? extends AccountServiceType> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountServiceType(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountServiceType(PathMetadata metadata, PathInits inits) {
        this(AccountServiceType.class, metadata, inits);
    }

    public QAccountServiceType(Class<? extends AccountServiceType> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accountService = inits.isInitialized("accountService") ? new QAccountService(forProperty("accountService"), inits.get("accountService")) : null;
        this.accountServiceGroup = inits.isInitialized("accountServiceGroup") ? new QAccountServiceGroup(forProperty("accountServiceGroup"), inits.get("accountServiceGroup")) : null;
        this.serviceType = inits.isInitialized("serviceType") ? new QServiceType(forProperty("serviceType"), inits.get("serviceType")) : null;
    }

}

