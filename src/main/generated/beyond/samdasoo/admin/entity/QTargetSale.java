package beyond.samdasoo.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTargetSale is a Querydsl query type for TargetSale
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTargetSale extends EntityPathBase<TargetSale> {

    private static final long serialVersionUID = -2097457620L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTargetSale targetSale = new QTargetSale("targetSale");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> month = createNumber("month", Integer.class);

    public final NumberPath<Integer> monthTarget = createNumber("monthTarget", Integer.class);

    public final QProduct product;

    public final NumberPath<Long> targetSaleNo = createNumber("targetSaleNo", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final beyond.samdasoo.user.entity.QUser user;

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QTargetSale(String variable) {
        this(TargetSale.class, forVariable(variable), INITS);
    }

    public QTargetSale(Path<? extends TargetSale> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTargetSale(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTargetSale(PathMetadata metadata, PathInits inits) {
        this(TargetSale.class, metadata, inits);
    }

    public QTargetSale(Class<? extends TargetSale> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product")) : null;
        this.user = inits.isInitialized("user") ? new beyond.samdasoo.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

