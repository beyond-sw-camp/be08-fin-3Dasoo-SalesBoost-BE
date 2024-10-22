package beyond.samdasoo.proposal.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProposal is a Querydsl query type for Proposal
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProposal extends EntityPathBase<Proposal> {

    private static final long serialVersionUID = -383677383L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProposal proposal = new QProposal("proposal");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    public final StringPath cont = createString("cont");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final ListPath<beyond.samdasoo.estimate.entity.Estimate, beyond.samdasoo.estimate.entity.QEstimate> estimates = this.<beyond.samdasoo.estimate.entity.Estimate, beyond.samdasoo.estimate.entity.QEstimate>createList("estimates", beyond.samdasoo.estimate.entity.Estimate.class, beyond.samdasoo.estimate.entity.QEstimate.class, PathInits.DIRECT2);

    public final beyond.samdasoo.lead.Entity.QLead lead;

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final DatePath<java.time.LocalDate> prDate = createDate("prDate", java.time.LocalDate.class);

    public final NumberPath<Long> propNo = createNumber("propNo", Long.class);

    public final DatePath<java.time.LocalDate> reqDate = createDate("reqDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> submitDate = createDate("submitDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QProposal(String variable) {
        this(Proposal.class, forVariable(variable), INITS);
    }

    public QProposal(Path<? extends Proposal> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProposal(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProposal(PathMetadata metadata, PathInits inits) {
        this(Proposal.class, metadata, inits);
    }

    public QProposal(Class<? extends Proposal> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lead = inits.isInitialized("lead") ? new beyond.samdasoo.lead.Entity.QLead(forProperty("lead"), inits.get("lead")) : null;
    }

}

