package vn.fpt.dbp.vccb.core.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerType is a Querydsl query type for CustomerType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomerType extends EntityPathBase<CustomerType> {

    private static final long serialVersionUID = -881086399L;

    public static final QCustomerType customerType = new QCustomerType("customerType");

    public final StringPath code = createString("code");

    public final SetPath<CustomerGroup, QCustomerGroup> customerGroups = this.<CustomerGroup, QCustomerGroup>createSet("customerGroups", CustomerGroup.class, QCustomerGroup.class, PathInits.DIRECT2);

    public final SetPath<CustomerSize, QCustomerSize> customerSizes = this.<CustomerSize, QCustomerSize>createSet("customerSizes", CustomerSize.class, QCustomerSize.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QCustomerType(String variable) {
        super(CustomerType.class, forVariable(variable));
    }

    public QCustomerType(Path<? extends CustomerType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomerType(PathMetadata metadata) {
        super(CustomerType.class, metadata);
    }

}

