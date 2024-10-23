package beyond.samdasoo.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -5060629L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final beyond.samdasoo.admin.entity.QDepartment department;

    public final StringPath email = createString("email");

    public final StringPath employeeId = createString("employeeId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> joinDate = createDate("joinDate", java.time.LocalDate.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final EnumPath<beyond.samdasoo.user.dto.UserRole> role = createEnum("role", beyond.samdasoo.user.dto.UserRole.class);

    public final ListPath<beyond.samdasoo.admin.entity.TargetSale, beyond.samdasoo.admin.entity.QTargetSale> targetSales = this.<beyond.samdasoo.admin.entity.TargetSale, beyond.samdasoo.admin.entity.QTargetSale>createList("targetSales", beyond.samdasoo.admin.entity.TargetSale.class, beyond.samdasoo.admin.entity.QTargetSale.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.department = inits.isInitialized("department") ? new beyond.samdasoo.admin.entity.QDepartment(forProperty("department"), inits.get("department")) : null;
    }

}

