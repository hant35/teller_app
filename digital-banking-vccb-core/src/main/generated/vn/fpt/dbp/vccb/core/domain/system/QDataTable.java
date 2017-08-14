package vn.fpt.dbp.vccb.core.domain.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDataTable is a Querydsl query type for DataTable
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDataTable extends EntityPathBase<DataTable> {

    private static final long serialVersionUID = 140572076L;

    public static final QDataTable dataTable = new QDataTable("dataTable");

    public final SetPath<DataTableDetail, QDataTableDetail> dataTableDetails = this.<DataTableDetail, QDataTableDetail>createSet("dataTableDetails", DataTableDetail.class, QDataTableDetail.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath screenCode = createString("screenCode");

    public final StringPath tableCode = createString("tableCode");

    public QDataTable(String variable) {
        super(DataTable.class, forVariable(variable));
    }

    public QDataTable(Path<? extends DataTable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDataTable(PathMetadata metadata) {
        super(DataTable.class, metadata);
    }

}

