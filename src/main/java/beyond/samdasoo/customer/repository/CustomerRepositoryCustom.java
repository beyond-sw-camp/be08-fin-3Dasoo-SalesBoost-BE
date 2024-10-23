package beyond.samdasoo.customer.repository;

import java.time.LocalDate;

public interface CustomerRepositoryCustom {
    long getCustomerCount(LocalDate searchDate, Long userNo);
}
