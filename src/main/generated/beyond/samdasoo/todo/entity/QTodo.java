package beyond.samdasoo.todo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodo is a Querydsl query type for Todo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodo extends EntityPathBase<Todo> {

    private static final long serialVersionUID = 155967585L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodo todo = new QTodo("todo");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    public final beyond.samdasoo.calendar.entity.QCalendar calendar;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> dueDate = createDate("dueDate", java.time.LocalDate.class);

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final StringPath priority = createString("priority");

    public final StringPath privateYn = createString("privateYn");

    public final EnumPath<TodoStatus> status = createEnum("status", TodoStatus.class);

    public final StringPath title = createString("title");

    public final StringPath todoCls = createString("todoCls");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QTodo(String variable) {
        this(Todo.class, forVariable(variable), INITS);
    }

    public QTodo(Path<? extends Todo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodo(PathMetadata metadata, PathInits inits) {
        this(Todo.class, metadata, inits);
    }

    public QTodo(Class<? extends Todo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.calendar = inits.isInitialized("calendar") ? new beyond.samdasoo.calendar.entity.QCalendar(forProperty("calendar"), inits.get("calendar")) : null;
    }

}

