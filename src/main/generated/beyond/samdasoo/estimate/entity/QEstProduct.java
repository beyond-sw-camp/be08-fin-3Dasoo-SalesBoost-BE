package beyond.samdasoo.estimate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEstProduct is a Querydsl query type for EstProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEstProduct extends EntityPathBase<EstProduct> {

    private static final long serialVersionUID = -1582957370L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEstProduct estProduct = new QEstProduct("estProduct");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> discount = createNumber("discount", Integer.class);

    public final QEstimate estimate;

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final beyond.samdasoo.admin.entity.QProduct product;

    public final NumberPath<Integer> qty = createNumber("qty", Integer.class);

    public final NumberPath<Integer> supplyPrice = createNumber("supplyPrice", Integer.class);

    public final NumberPath<Integer> tax = createNumber("tax", Integer.class);

    public final NumberPath<Integer> totalAmt = createNumber("totalAmt", Integer.class);

    public final NumberPath<Integer> unitAmt = createNumber("unitAmt", Integer.class);

    public final NumberPath<Integer> unitPropAmt = createNumber("unitPropAmt", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QEstProduct(String variable) {
        this(EstProduct.class, forVariable(variable), INITS);
    }

    public QEstProduct(Path<? extends EstProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEstProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEstProduct(PathMetadata metadata, PathInits inits) {
        this(EstProduct.class, metadata, inits);
    }

    public QEstProduct(Class<? extends EstProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.estimate = inits.isInitialized("estimate") ? new QEstimate(forProperty("estimate"), inits.get("estimate")) : null;
        this.product = inits.isInitialized("product") ? new beyond.samdasoo.admin.entity.QProduct(forProperty("product")) : null;
    }

}

