package beyond.samdasoo.potentialcustomer.repository;

import java.time.LocalDate;

public interface PotentialCustomerRepositoryCustom {
    long getPotentialCustomerCount(LocalDate searchDate, Long userNo);
}
