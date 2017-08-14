package vn.fpt.dbp.vccb.core.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductAccountClass is a Querydsl query type for ProductAccountClass
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductAccountClass extends EntityPathBase<ProductAccountClass> {

    private static final long serialVersionUID = 2022575956L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductAccountClass productAccountClass = new QProductAccountClass("productAccountClass");

    public final vn.fpt.dbp.vccb.core.domain.organization.QArea area;

    public final NumberPath<Double> balanceMin = createNumber("balanceMin", Double.class);

    public final StringPath code = createString("code");

    public final vn.fpt.dbp.vccb.core.domain.currency.QCurrency currency;

    public final StringPath customerGrade = createString("customerGrade");

    public final NumberPath<Double> depositMax = createNumber("depositMax", Double.class);

    public final NumberPath<Double> depositMin = createNumber("depositMin", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath matureForm = createString("matureForm");

    public final StringPath matureToAccountClass = createString("matureToAccountClass");

    public final StringPath name = createString("name");

    public final NumberPath<Double> periodDeposit = createNumber("periodDeposit", Double.class);

    public final NumberPath<Double> periodDepositMin = createNumber("periodDepositMin", Double.class);

    public final QProduct product;

    public final vn.fpt.dbp.vccb.core.domain.interest.QTimeRate timeRate;

    public QProductAccountClass(String variable) {
        this(ProductAccountClass.class, forVariable(variable), INITS);
    }

    public QProductAccountClass(Path<? extends ProductAccountClass> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductAccountClass(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProductAccountClass(PathMetadata metadata, PathInits inits) {
        this(ProductAccountClass.class, metadata, inits);
    }

    public QProductAccountClass(Class<? extends ProductAccountClass> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.area = inits.isInitialized("area") ? new vn.fpt.dbp.vccb.core.domain.organization.QArea(forProperty("area")) : null;
        this.currency = inits.isInitialized("currency") ? new vn.fpt.dbp.vccb.core.domain.currency.QCurrency(forProperty("currency")) : null;
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
        this.timeRate = inits.isInitialized("timeRate") ? new vn.fpt.dbp.vccb.core.domain.interest.QTimeRate(forProperty("timeRate")) : null;
    }

}

