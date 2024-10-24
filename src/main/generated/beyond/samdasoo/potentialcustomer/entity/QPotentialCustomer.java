package beyond.samdasoo.potentialcustomer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPotentialCustomer is a Querydsl query type for PotentialCustomer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPotentialCustomer extends EntityPathBase<PotentialCustomer> {

    private static final long serialVersionUID = 947326171L;

    public static final QPotentialCustomer potentialCustomer = new QPotentialCustomer("potentialCustomer");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    public final StringPath addr = createString("addr");

    public final StringPath cls = createString("cls");

    public final StringPath company = createString("company");

    public final EnumPath<PotentialCustomer.ContactStatus> contactStatus = createEnum("contactStatus", PotentialCustomer.ContactStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath dept = createString("dept");

    public final StringPath email = createString("email");

    public final StringPath fax = createString("fax");

    public final EnumPath<PotentialCustomer.Grade> grade = createEnum("grade", PotentialCustomer.Grade.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> modifyDate = createDate("modifyDate", java.time.LocalDate.class);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final StringPath phone = createString("phone");

    public final StringPath position = createString("position");

    public final DatePath<java.time.LocalDate> registerDate = createDate("registerDate", java.time.LocalDate.class);

    public final StringPath tel = createString("tel");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPotentialCustomer(String variable) {
        super(PotentialCustomer.class, forVariable(variable));
    }

    public QPotentialCustomer(Path<? extends PotentialCustomer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPotentialCustomer(PathMetadata metadata) {
        super(PotentialCustomer.class, metadata);
    }

}

