package beyond.samdasoo.estimate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEstimate is a Querydsl query type for Estimate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEstimate extends EntityPathBase<Estimate> {

    private static final long serialVersionUID = -1963076443L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEstimate estimate = new QEstimate("estimate");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> estDate = createDate("estDate", java.time.LocalDate.class);

    public final NumberPath<Long> estNo = createNumber("estNo", Long.class);

    public final ListPath<EstProduct, QEstProduct> estProducts = this.<EstProduct, QEstProduct>createList("estProducts", EstProduct.class, QEstProduct.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final NumberPath<Integer> prodCnt = createNumber("prodCnt", Integer.class);

    public final beyond.samdasoo.proposal.entity.QProposal proposal;

    public final NumberPath<Integer> supplyPrice = createNumber("supplyPrice", Integer.class);

    public final StringPath surtaxYn = createString("surtaxYn");

    public final NumberPath<Integer> tax = createNumber("tax", Integer.class);

    public final StringPath taxCls = createString("taxCls");

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QEstimate(String variable) {
        this(Estimate.class, forVariable(variable), INITS);
    }

    public QEstimate(Path<? extends Estimate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEstimate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEstimate(PathMetadata metadata, PathInits inits) {
        this(Estimate.class, metadata, inits);
    }

    public QEstimate(Class<? extends Estimate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.proposal = inits.isInitialized("proposal") ? new beyond.samdasoo.proposal.entity.QProposal(forProperty("proposal"), inits.get("proposal")) : null;
    }

}

