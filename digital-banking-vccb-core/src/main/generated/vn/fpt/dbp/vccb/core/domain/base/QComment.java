package vn.fpt.dbp.vccb.core.domain.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = -917454807L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment = new QComment("comment");

    public final DateTimePath<java.util.Date> commentDate = createDateTime("commentDate", java.util.Date.class);

    public final StringPath commentor = createString("commentor");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QWorkFlowModel workFlowModel;

    public final StringPath workflowStatus = createString("workflowStatus");

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QComment(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.workFlowModel = inits.isInitialized("workFlowModel") ? new QWorkFlowModel(forProperty("workFlowModel"), inits.get("workFlowModel")) : null;
    }

}

