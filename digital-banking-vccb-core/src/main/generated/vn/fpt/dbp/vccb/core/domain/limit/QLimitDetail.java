package vn.fpt.dbp.vccb.core.domain.limit;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLimitDetail is a Querydsl query type for LimitDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLimitDetail extends EntityPathBase<LimitDetail> {

    private static final long serialVersionUID = 188754546L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLimitDetail limitDetail = new QLimitDetail("limitDetail");

    public final vn.fpt.dbp.vccb.core.domain.organization.QBranch branch;

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency currency;

    public final vn.fpt.dbp.vccb.core.domain.customer.QCustomerGroup customerGroup;

    public final vn.fpt.dbp.vccb.core.domain.system.QFunction function;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isMustApprove = createBoolean("isMustApprove");

    public final BooleanPath isMustApproveOver = createBoolean("isMustApproveOver");

    public final BooleanPath isStopWhenOver = createBoolean("isStopWhenOver");

    public final BooleanPath isView = createBoolean("isView");

    public final QLimit limit;

    public final NumberPath<Long> limitValue = createNumber("limitValue", Long.class);

    public final vn.fpt.dbp.vccb.core.domain.product.QProduct product;

    public QLimitDetail(String variable) {
        this(LimitDetail.class, forVariable(variable), INITS);
    }

    public QLimitDetail(Path<? extends LimitDetail> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLimitDetail(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLimitDetail(PathMetadata metadata, PathInits inits) {
        this(LimitDetail.class, metadata, inits);
    }

    public QLimitDetail(Class<? extends LimitDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new vn.fpt.dbp.vccb.core.domain.organization.QBranch(forProperty("branch"), inits.get("branch")) : null;
        this.currency = inits.isInitialized("currency") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("currency")) : null;
        this.customerGroup = inits.isInitialized("customerGroup") ? new vn.fpt.dbp.vccb.core.domain.customer.QCustomerGroup(forProperty("customerGroup"), inits.get("customerGroup")) : null;
        this.function = inits.isInitialized("function") ? new vn.fpt.dbp.vccb.core.domain.system.QFunction(forProperty("function")) : null;
        this.limit = inits.isInitialized("limit") ? new QLimit(forProperty("limit"), inits.get("limit")) : null;
        this.product = inits.isInitialized("product") ? new vn.fpt.dbp.vccb.core.domain.product.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

