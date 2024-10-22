package beyond.samdasoo.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProcess is a Querydsl query type for Process
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProcess extends EntityPathBase<Process> {

    private static final long serialVersionUID = 808000059L;

    public static final QProcess process = new QProcess("process");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Integer> expectedDuration = createNumber("expectedDuration", Integer.class);

    public final BooleanPath isDefault = createBoolean("isDefault");

    public final StringPath processName = createString("processName");

    public final NumberPath<Long> processNo = createNumber("processNo", Long.class);

    public final ListPath<SubProcess, QSubProcess> subProcesses = this.<SubProcess, QSubProcess>createList("subProcesses", SubProcess.class, QSubProcess.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QProcess(String variable) {
        super(Process.class, forVariable(variable));
    }

    public QProcess(Path<? extends Process> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProcess(PathMetadata metadata) {
        super(Process.class, metadata);
    }

}

