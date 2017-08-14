package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReferralUser is a Querydsl query type for ReferralUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReferralUser extends EntityPathBase<ReferralUser> {

    private static final long serialVersionUID = 299307281L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReferralUser referralUser = new QReferralUser("referralUser");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath legalDocsNumber = createString("legalDocsNumber");

    public final StringPath name = createString("name");

    public final QPosition position;

    public QReferralUser(String variable) {
        this(ReferralUser.class, forVariable(variable), INITS);
    }

    public QReferralUser(Path<? extends ReferralUser> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReferralUser(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReferralUser(PathMetadata metadata, PathInits inits) {
        this(ReferralUser.class, metadata, inits);
    }

    public QReferralUser(Class<? extends ReferralUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.position = inits.isInitialized("position") ? new QPosition(forProperty("position")) : null;
    }

}

