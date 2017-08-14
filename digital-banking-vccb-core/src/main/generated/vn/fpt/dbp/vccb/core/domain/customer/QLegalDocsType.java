package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLegalDocsType is a Querydsl query type for LegalDocsType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLegalDocsType extends EntityPathBase<LegalDocsType> {

    private static final long serialVersionUID = 142328581L;

    public static final QLegalDocsType legalDocsType = new QLegalDocsType("legalDocsType");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QLegalDocsType(String variable) {
        super(LegalDocsType.class, forVariable(variable));
    }

    public QLegalDocsType(Path<? extends LegalDocsType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLegalDocsType(PathMetadata metadata) {
        super(LegalDocsType.class, metadata);
    }

}

