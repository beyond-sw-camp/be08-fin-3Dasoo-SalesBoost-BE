package beyond.samdasoo.act.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAct is a Querydsl query type for Act
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAct extends EntityPathBase<Act> {

    private static final long serialVersionUID = -156947653L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAct act = new QAct("act");

    public final StringPath actCont = createString("actCont");

    public final DatePath<java.time.LocalDate> actDate = createDate("actDate", java.time.LocalDate.class);

    public final beyond.samdasoo.calendar.entity.QCalendar calendar;

    public final EnumPath<ActStatus> cls = createEnum("cls", ActStatus.class);

    public final StringPath completeYn = createString("completeYn");

    public final StringPath endTime = createString("endTime");

    public final beyond.samdasoo.lead.Entity.QLead lead;

    public final StringPath name = createString("name");

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final StringPath planCont = createString("planCont");

    public final StringPath purpose = createString("purpose");

    public final StringPath startTime = createString("startTime");

    public QAct(String variable) {
        this(Act.class, forVariable(variable), INITS);
    }

    public QAct(Path<? extends Act> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAct(PathMetadata metadata, PathInits inits) {
        this(Act.class, metadata, inits);
    }

    public QAct(Class<? extends Act> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.calendar = inits.isInitialized("calendar") ? new beyond.samdasoo.calendar.entity.QCalendar(forProperty("calendar"), inits.get("calendar")) : null;
        this.lead = inits.isInitialized("lead") ? new beyond.samdasoo.lead.Entity.QLead(forProperty("lead"), inits.get("lead")) : null;
    }

}

