package vn.fpt.dbp.vccb.core.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDepositType is a Querydsl query type for DepositType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDepositType extends EntityPathBase<DepositType> {

    private static final long serialVersionUID = 2067861106L;

    public static final QDepositType depositType = new QDepositType("depositType");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QDepositType(String variable) {
        super(DepositType.class, forVariable(variable));
    }

    public QDepositType(Path<? extends DepositType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDepositType(PathMetadata metadata) {
        super(DepositType.class, metadata);
    }

}

