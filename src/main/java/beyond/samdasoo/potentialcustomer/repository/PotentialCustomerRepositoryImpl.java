package beyond.samdasoo.potentialcustomer.repository;

import beyond.samdasoo.potentialcustomer.entity.QPotentialCustomer;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class PotentialCustomerRepositoryImpl implements PotentialCustomerRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public long getPotentialCustomerCount(LocalDate searchDate, Long userNo) {
        QPotentialCustomer potentialCustomer = QPotentialCustomer.potentialCustomer;

        BooleanBuilder builder = new BooleanBuilder();

        if (searchDate != null) {
            builder.and(potentialCustomer.createdAt.loe(searchDate.atTime(LocalTime.MAX)));
        }

//        if (userNo != null && userNo != 0) {
//            builder.and(potentialCustomer.user.id.eq(userNo));
//        }

        Long result = queryFactory
                .select(potentialCustomer.count())
                .from(potentialCustomer)
                .where(builder)
                .fetchOne();

        return Objects.requireNonNullElse(result, 0L);
    }
}
