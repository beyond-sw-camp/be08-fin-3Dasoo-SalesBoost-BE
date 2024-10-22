package beyond.samdasoo.lead.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStep is a Querydsl query type for Step
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStep extends EntityPathBase<Step> {

    private static final long serialVersionUID = -128357155L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStep step = new QStep("step");

    public final DatePath<java.time.LocalDate> completeDate = createDate("completeDate", java.time.LocalDate.class);

    public final StringPath completeYn = createString("completeYn");

    public final QLead lead;

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final beyond.samdasoo.admin.entity.QSubProcess subProcess;

    public QStep(String variable) {
        this(Step.class, forVariable(variable), INITS);
    }

    public QStep(Path<? extends Step> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStep(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStep(PathMetadata metadata, PathInits inits) {
        this(Step.class, metadata, inits);
    }

    public QStep(Class<? extends Step> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lead = inits.isInitialized("lead") ? new QLead(forProperty("lead"), inits.get("lead")) : null;
        this.subProcess = inits.isInitialized("subProcess") ? new beyond.samdasoo.admin.entity.QSubProcess(forProperty("subProcess"), inits.get("subProcess")) : null;
    }

}

