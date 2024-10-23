package beyond.samdasoo.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 808044731L;

    public static final QProduct product = new QProduct("product");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    public final StringPath abbrName = createString("abbrName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath dept = createString("dept");

    public final StringPath engName = createString("engName");

    public final ListPath<beyond.samdasoo.estimate.entity.EstProduct, beyond.samdasoo.estimate.entity.QEstProduct> estProducts = this.<beyond.samdasoo.estimate.entity.EstProduct, beyond.samdasoo.estimate.entity.QEstProduct>createList("estProducts", beyond.samdasoo.estimate.entity.EstProduct.class, beyond.samdasoo.estimate.entity.QEstProduct.class, PathInits.DIRECT2);

    public final StringPath field = createString("field");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath prodCode = createString("prodCode");

    public final NumberPath<Long> prodNo = createNumber("prodNo", Long.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final DatePath<java.time.LocalDate> releaseDate = createDate("releaseDate", java.time.LocalDate.class);

    public final NumberPath<Integer> supplyPrice = createNumber("supplyPrice", Integer.class);

    public final ListPath<TargetSale, QTargetSale> targetSales = this.<TargetSale, QTargetSale>createList("targetSales", TargetSale.class, QTargetSale.class, PathInits.DIRECT2);

    public final NumberPath<Integer> taxRate = createNumber("taxRate", Integer.class);

    public final StringPath unit = createString("unit");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath uppGroup = createString("uppGroup");

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

