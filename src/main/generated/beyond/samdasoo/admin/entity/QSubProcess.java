package beyond.samdasoo.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubProcess is a Querydsl query type for SubProcess
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubProcess extends EntityPathBase<SubProcess> {

    private static final long serialVersionUID = -80195389L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubProcess subProcess = new QSubProcess("subProcess");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    public final StringPath action = createString("action");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> expectedDuration = createNumber("expectedDuration", Integer.class);

    public final QProcess process;

    public final StringPath progressStep = createString("progressStep");

    public final StringPath subProcessName = createString("subProcessName");

    public final NumberPath<Long> subProcessNo = createNumber("subProcessNo", Long.class);

    public final NumberPath<Integer> successRate = createNumber("successRate", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSubProcess(String variable) {
        this(SubProcess.class, forVariable(variable), INITS);
    }

    public QSubProcess(Path<? extends SubProcess> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubProcess(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubProcess(PathMetadata metadata, PathInits inits) {
        this(SubProcess.class, metadata, inits);
    }

    public QSubProcess(Class<? extends SubProcess> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.process = inits.isInitialized("process") ? new QProcess(forProperty("process")) : null;
    }

}

