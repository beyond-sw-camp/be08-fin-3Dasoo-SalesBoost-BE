package beyond.samdasoo.lead.repository;

import beyond.samdasoo.customer.entity.QCustomer;
import beyond.samdasoo.lead.Entity.QLead;
import beyond.samdasoo.lead.dto.LeadStatusDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LeadRepositoryImpl implements LeadRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<LeadStatusDto> findLeadStatusGroupedByStatus(LocalDate searchDate, Long userNo) {
        QLead lead = QLead.lead;
        QCustomer customer = QCustomer.customer;

        BooleanBuilder whereClause = new BooleanBuilder();

        if (searchDate != null) {
            whereClause.and(lead.startDate.loe(searchDate));
        }

        if (userNo != null && userNo > 0) {
            whereClause.and(customer.user.id.eq(userNo));
        }

        return queryFactory
                .select(Projections.constructor(LeadStatusDto.class,
                        lead.status,
                        lead.count().as("count")
                ))
                .from(lead)
                .join(lead.customer, customer)
                .where(whereClause)
                .groupBy(lead.status)
                .fetch();
    }
}
