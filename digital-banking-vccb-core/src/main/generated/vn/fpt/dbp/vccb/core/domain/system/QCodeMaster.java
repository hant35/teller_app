package vn.fpt.dbp.vccb.core.domain.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCodeMaster is a Querydsl query type for CodeMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCodeMaster extends EntityPathBase<CodeMaster> {

    private static final long serialVersionUID = 1959685191L;

    public static final QCodeMaster codeMaster = new QCodeMaster("codeMaster");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDefault = createBoolean("isDefault");

    public final BooleanPath isDelete = createBoolean("isDelete");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> sortNo = createNumber("sortNo", Integer.class);

    public final StringPath value = createString("value");

    public QCodeMaster(String variable) {
        super(CodeMaster.class, forVariable(variable));
    }

    public QCodeMaster(Path<? extends CodeMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCodeMaster(PathMetadata metadata) {
        super(CodeMaster.class, metadata);
    }

}

