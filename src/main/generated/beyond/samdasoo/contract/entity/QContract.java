package beyond.samdasoo.contract.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContract is a Querydsl query type for Contract
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContract extends EntityPathBase<Contract> {

    private static final long serialVersionUID = 1393989753L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContract contract = new QContract("contract");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    public final StringPath arrivalNotificationDay = createString("arrivalNotificationDay");

    public final StringPath arrivalNotificationYn = createString("arrivalNotificationYn");

    public final StringPath contractCls = createString("contractCls");

    public final DatePath<java.time.LocalDate> contractDate = createDate("contractDate", java.time.LocalDate.class);

    public final NumberPath<Long> contractNo = createNumber("contractNo", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final beyond.samdasoo.estimate.entity.QEstimate estimate;

    public final DatePath<java.time.LocalDate> expectedArrivalDate = createDate("expectedArrivalDate", java.time.LocalDate.class);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final StringPath paymentTerms = createString("paymentTerms");

    public final NumberPath<Integer> productCount = createNumber("productCount", Integer.class);

    public final StringPath renewalNotificationDay = createString("renewalNotificationDay");

    public final StringPath renewalNotificationYn = createString("renewalNotificationYn");

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final NumberPath<Integer> supplyPrice = createNumber("supplyPrice", Integer.class);

    public final StringPath surtaxYn = createString("surtaxYn");

    public final NumberPath<Integer> tax = createNumber("tax", Integer.class);

    public final StringPath taxCls = createString("taxCls");

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Integer> warrenty = createNumber("warrenty", Integer.class);

    public QContract(String variable) {
        this(Contract.class, forVariable(variable), INITS);
    }

    public QContract(Path<? extends Contract> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContract(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContract(PathMetadata metadata, PathInits inits) {
        this(Contract.class, metadata, inits);
    }

    public QContract(Class<? extends Contract> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.estimate = inits.isInitialized("estimate") ? new beyond.samdasoo.estimate.entity.QEstimate(forProperty("estimate"), inits.get("estimate")) : null;
    }

}

