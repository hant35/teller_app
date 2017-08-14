package vn.fpt.dbp.vccb.core.domain.casa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSavingBookSerial is a Querydsl query type for SavingBookSerial
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSavingBookSerial extends EntityPathBase<SavingBookSerial> {

    private static final long serialVersionUID = -972034222L;

    public static final QSavingBookSerial savingBookSerial = new QSavingBookSerial("savingBookSerial");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QSavingBookSerial(String variable) {
        super(SavingBookSerial.class, forVariable(variable));
    }

    public QSavingBookSerial(Path<? extends SavingBookSerial> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSavingBookSerial(PathMetadata metadata) {
        super(SavingBookSerial.class, metadata);
    }

}

