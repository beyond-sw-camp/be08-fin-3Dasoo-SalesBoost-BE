package beyond.samdasoo.lead.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLead is a Querydsl query type for Lead
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLead extends EntityPathBase<Lead> {

    private static final long serialVersionUID = -128580243L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLead lead = new QLead("lead");

    public final beyond.samdasoo.common.entity.QBaseEntity _super = new beyond.samdasoo.common.entity.QBaseEntity(this);

    public final EnumPath<AwarePath> awarePath = createEnum("awarePath", AwarePath.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final beyond.samdasoo.customer.entity.QCustomer customer;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Integer> expMargin = createNumber("expMargin", Integer.class);

    public final NumberPath<Integer> expProfit = createNumber("expProfit", Integer.class);

    public final NumberPath<Integer> expSales = createNumber("expSales", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final StringPath note = createString("note");

    public final NumberPath<Long> process = createNumber("process", Long.class);

    public final ListPath<beyond.samdasoo.proposal.entity.Proposal, beyond.samdasoo.proposal.entity.QProposal> proposals = this.<beyond.samdasoo.proposal.entity.Proposal, beyond.samdasoo.proposal.entity.QProposal>createList("proposals", beyond.samdasoo.proposal.entity.Proposal.class, beyond.samdasoo.proposal.entity.QProposal.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final EnumPath<LeadStatus> status = createEnum("status", LeadStatus.class);

    public final ListPath<Step, QStep> steps = this.<Step, QStep>createList("steps", Step.class, QStep.class, PathInits.DIRECT2);

    public final NumberPath<Long> subProcess = createNumber("subProcess", Long.class);

    public final NumberPath<Integer> successPer = createNumber("successPer", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLead(String variable) {
        this(Lead.class, forVariable(variable), INITS);
    }

    public QLead(Path<? extends Lead> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLead(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLead(PathMetadata metadata, PathInits inits) {
        this(Lead.class, metadata, inits);
    }

    public QLead(Class<? extends Lead> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new beyond.samdasoo.customer.entity.QCustomer(forProperty("customer"), inits.get("customer")) : null;
    }

}

