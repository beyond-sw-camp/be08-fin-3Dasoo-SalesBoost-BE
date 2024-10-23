package beyond.samdasoo.sales.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSales is a Querydsl query type for Sales
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSales extends EntityPathBase<Sales> {

    private static final long serialVersionUID = 2010938683L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSales sales = new QSales("sales");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    public final StringPath busiType = createString("busiType");

    public final StringPath busiTypeDetail = createString("busiTypeDetail");

    public final beyond.samdasoo.contract.entity.QContract contract;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> expArrivalDate = createDate("expArrivalDate", java.time.LocalDate.class);

    public final StringPath note = createString("note");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> productCount = createNumber("productCount", Integer.class);

    public final StringPath salesCls = createString("salesCls");

    public final DatePath<java.time.LocalDate> salesDate = createDate("salesDate", java.time.LocalDate.class);

    public final NumberPath<Long> salesNo = createNumber("salesNo", Long.class);

    public final NumberPath<Integer> supplyPrice = createNumber("supplyPrice", Integer.class);

    public final StringPath surtaxYn = createString("surtaxYn");

    public final NumberPath<Integer> tax = createNumber("tax", Integer.class);

    public final StringPath taxCls = createString("taxCls");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSales(String variable) {
        this(Sales.class, forVariable(variable), INITS);
    }

    public QSales(Path<? extends Sales> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSales(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSales(PathMetadata metadata, PathInits inits) {
        this(Sales.class, metadata, inits);
    }

    public QSales(Class<? extends Sales> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contract = inits.isInitialized("contract") ? new beyond.samdasoo.contract.entity.QContract(forProperty("contract"), inits.get("contract")) : null;
    }

}

