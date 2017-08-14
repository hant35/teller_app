package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFinancialInstitutionsType is a Querydsl query type for FinancialInstitutionsType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFinancialInstitutionsType extends EntityPathBase<FinancialInstitutionsType> {

    private static final long serialVersionUID = -439879467L;

    public static final QFinancialInstitutionsType financialInstitutionsType = new QFinancialInstitutionsType("financialInstitutionsType");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QFinancialInstitutionsType(String variable) {
        super(FinancialInstitutionsType.class, forVariable(variable));
    }

    public QFinancialInstitutionsType(Path<? extends FinancialInstitutionsType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFinancialInstitutionsType(PathMetadata metadata) {
        super(FinancialInstitutionsType.class, metadata);
    }

}

