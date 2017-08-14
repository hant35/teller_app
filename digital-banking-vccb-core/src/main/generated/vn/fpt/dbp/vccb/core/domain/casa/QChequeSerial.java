package vn.fpt.dbp.vccb.core.domain.casa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QChequeSerial is a Querydsl query type for ChequeSerial
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChequeSerial extends EntityPathBase<ChequeSerial> {

    private static final long serialVersionUID = 1178956656L;

    public static final QChequeSerial chequeSerial = new QChequeSerial("chequeSerial");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QChequeSerial(String variable) {
        super(ChequeSerial.class, forVariable(variable));
    }

    public QChequeSerial(Path<? extends ChequeSerial> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChequeSerial(PathMetadata metadata) {
        super(ChequeSerial.class, metadata);
    }

}

