package beyond.samdasoo.potentialcustomer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContactHistory is a Querydsl query type for ContactHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContactHistory extends EntityPathBase<ContactHistory> {

    private static final long serialVersionUID = -128934411L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContactHistory contactHistory = new QContactHistory("contactHistory");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    public final EnumPath<ContactHistory.CLS> cls = createEnum("cls", ContactHistory.CLS.class);

    public final DatePath<java.time.LocalDate> contactDate = createDate("contactDate", java.time.LocalDate.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPotentialCustomer pcustomer;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final beyond.samdasoo.user.entity.QUser user;

    public QContactHistory(String variable) {
        this(ContactHistory.class, forVariable(variable), INITS);
    }

    public QContactHistory(Path<? extends ContactHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContactHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContactHistory(PathMetadata metadata, PathInits inits) {
        this(ContactHistory.class, metadata, inits);
    }

    public QContactHistory(Class<? extends ContactHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pcustomer = inits.isInitialized("pcustomer") ? new QPotentialCustomer(forProperty("pcustomer")) : null;
        this.user = inits.isInitialized("user") ? new beyond.samdasoo.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

