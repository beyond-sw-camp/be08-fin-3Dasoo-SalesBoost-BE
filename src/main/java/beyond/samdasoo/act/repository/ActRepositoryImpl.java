package beyond.samdasoo.act.repository;

import beyond.samdasoo.act.dto.ActStatusDto;
import beyond.samdasoo.act.entity.QAct;
import beyond.samdasoo.customer.entity.QCustomer;
import beyond.samdasoo.lead.Entity.QLead;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ActRepositoryImpl implements ActRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public ActStatusDto findActStatus(LocalDate searchDate, Long userNo) {
        QAct act = QAct.act;
        QLead lead = QLead.lead;
        QCustomer customer = QCustomer.customer;

        BooleanBuilder builder = new BooleanBuilder();

        if (searchDate != null) {
            builder.and(act.actDate.loe(searchDate));
        }

        if (userNo != null && userNo > 0) {
            builder.and(customer.user.id.eq(userNo));
        }

        Long planCount = queryFactory
                .select(act.count())
                .from(act)
                .join(act.lead, lead)
                .join(lead.customer, customer)
                .where(builder)
                .fetchOne();

        long planCnt = Objects.requireNonNullElse(planCount, 0L);

        Long completeCount = queryFactory
                .select(act.count())
                .from(act)
                .join(act.lead, lead)
                .join(lead.customer, customer)
                .where(builder
                        .and(act.completeYn.eq("Y"))
                )
                .fetchOne();

        long completeCnt = Objects.requireNonNullElse(completeCount, 0L);

        long completePercent = planCnt > 0 ? (completeCnt * 100) / planCnt : 0;

        return new ActStatusDto(planCnt, completeCnt, completePercent);
    }
}
