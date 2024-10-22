package beyond.samdasoo.calendar.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendar is a Querydsl query type for Calendar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCalendar extends EntityPathBase<Calendar> {

    private static final long serialVersionUID = 831034577L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalendar calendar = new QCalendar("calendar");

    public final ListPath<beyond.samdasoo.act.entity.Act, beyond.samdasoo.act.entity.QAct> acts = this.<beyond.samdasoo.act.entity.Act, beyond.samdasoo.act.entity.QAct>createList("acts", beyond.samdasoo.act.entity.Act.class, beyond.samdasoo.act.entity.QAct.class, PathInits.DIRECT2);

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final ListPath<beyond.samdasoo.plan.entity.Plan, beyond.samdasoo.plan.entity.QPlan> plans = this.<beyond.samdasoo.plan.entity.Plan, beyond.samdasoo.plan.entity.QPlan>createList("plans", beyond.samdasoo.plan.entity.Plan.class, beyond.samdasoo.plan.entity.QPlan.class, PathInits.DIRECT2);

    public final ListPath<beyond.samdasoo.todo.entity.Todo, beyond.samdasoo.todo.entity.QTodo> todos = this.<beyond.samdasoo.todo.entity.Todo, beyond.samdasoo.todo.entity.QTodo>createList("todos", beyond.samdasoo.todo.entity.Todo.class, beyond.samdasoo.todo.entity.QTodo.class, PathInits.DIRECT2);

    public final beyond.samdasoo.user.entity.QUser user;

    public QCalendar(String variable) {
        this(Calendar.class, forVariable(variable), INITS);
    }

    public QCalendar(Path<? extends Calendar> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalendar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalendar(PathMetadata metadata, PathInits inits) {
        this(Calendar.class, metadata, inits);
    }

    public QCalendar(Class<? extends Calendar> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new beyond.samdasoo.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

