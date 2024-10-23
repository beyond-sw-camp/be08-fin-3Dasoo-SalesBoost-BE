package beyond.samdasoo.customer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    private static final long serialVersionUID = -877634031L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomer customer = new QCustomer("customer");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    public final StringPath company = createString("company");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath dept = createString("dept");

    public final StringPath email = createString("email");

    public final EnumPath<Customer.Grade> grade = createEnum("grade", Customer.Grade.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isKeyMan = createBoolean("isKeyMan");

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final StringPath position = createString("position");

    public final beyond.samdasoo.potentialcustomer.entity.QPotentialCustomer potentialCustomer;

    public final StringPath tel = createString("tel");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final beyond.samdasoo.user.entity.QUser user;

    public QCustomer(String variable) {
        this(Customer.class, forVariable(variable), INITS);
    }

    public QCustomer(Path<? extends Customer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomer(PathMetadata metadata, PathInits inits) {
        this(Customer.class, metadata, inits);
    }

    public QCustomer(Class<? extends Customer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.potentialCustomer = inits.isInitialized("potentialCustomer") ? new beyond.samdasoo.potentialcustomer.entity.QPotentialCustomer(forProperty("potentialCustomer")) : null;
        this.user = inits.isInitialized("user") ? new beyond.samdasoo.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

