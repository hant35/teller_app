package vn.fpt.dbp.vccb.core.domain.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDataTableDetail is a Querydsl query type for DataTableDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDataTableDetail extends EntityPathBase<DataTableDetail> {

    private static final long serialVersionUID = -1684797155L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDataTableDetail dataTableDetail = new QDataTableDetail("dataTableDetail");

    public final StringPath coulmnName = createString("coulmnName");

    public final StringPath coulmnTitle = createString("coulmnTitle");

    public final QDataTable dataTable;

    public final BooleanPath enable = createBoolean("enable");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDataTableDetail(String variable) {
        this(DataTableDetail.class, forVariable(variable), INITS);
    }

    public QDataTableDetail(Path<? extends DataTableDetail> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDataTableDetail(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDataTableDetail(PathMetadata metadata, PathInits inits) {
        this(DataTableDetail.class, metadata, inits);
    }

    public QDataTableDetail(Class<? extends DataTableDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dataTable = inits.isInitialized("dataTable") ? new QDataTable(forProperty("dataTable")) : null;
    }

}

