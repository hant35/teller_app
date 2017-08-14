package vn.fpt.dbp.vccb.core.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccountClass is a Querydsl query type for AccountClass
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccountClass extends EntityPathBase<AccountClass> {

    private static final long serialVersionUID = 462651153L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccountClass accountClass = new QAccountClass("accountClass");

    public final vn.fpt.dbp.vccb.core.domain.organization.QArea area;

    public final NumberPath<Double> balanceMin = createNumber("balanceMin", Double.class);

    public final StringPath code = createString("code");

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency currency;

    public final StringPath customerGrade = createString("customerGrade");

    public final NumberPath<Double> depositMax = createNumber("depositMax", Double.class);

    public final NumberPath<Double> depositMin = createNumber("depositMin", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath matureForm = createString("matureForm");

    public final QAccountClass matureToAccountClass;

    public final StringPath name = createString("name");

    public final NumberPath<Double> periodDeposit = createNumber("periodDeposit", Double.class);

    public final NumberPath<Double> periodDepositMin = createNumber("periodDepositMin", Double.class);

    public final QProduct product;

    public final vn.fpt.dbp.vccb.core.domain.interest.QTimeRate timeRate;

    public QAccountClass(String variable) {
        this(AccountClass.class, forVariable(variable), INITS);
    }

    public QAccountClass(Path<? extends AccountClass> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountClass(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccountClass(PathMetadata metadata, PathInits inits) {
        this(AccountClass.class, metadata, inits);
    }

    public QAccountClass(Class<? extends AccountClass> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.area = inits.isInitialized("area") ? new vn.fpt.dbp.vccb.core.domain.organization.QArea(forProperty("area")) : null;
        this.currency = inits.isInitialized("currency") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("currency")) : null;
        this.matureToAccountClass = inits.isInitialized("matureToAccountClass") ? new QAccountClass(forProperty("matureToAccountClass"), inits.get("matureToAccountClass")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
        this.timeRate = inits.isInitialized("timeRate") ? new vn.fpt.dbp.vccb.core.domain.interest.QTimeRate(forProperty("timeRate")) : null;
    }

}

