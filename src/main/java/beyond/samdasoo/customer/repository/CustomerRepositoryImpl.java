package beyond.samdasoo.customer.repository;

import beyond.samdasoo.customer.entity.QCustomer;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public long getCustomerCount(LocalDate searchDate, Long userNo) {
        QCustomer customer = QCustomer.customer;

        BooleanBuilder builder = new BooleanBuilder();

        if (searchDate != null) {
            builder.and(customer.createdAt.loe(searchDate.atTime(LocalTime.MAX)));
        }

        if (userNo != null && userNo > 0) {
            builder.and(customer.user.id.eq(userNo));
        }

        Long result = queryFactory
                .select(customer.count())
                .from(customer)
                .where(builder)
                .fetchOne();

        return Objects.requireNonNullElse(result, 0L);
    }
}
