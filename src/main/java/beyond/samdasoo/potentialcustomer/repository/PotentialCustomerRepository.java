package beyond.samdasoo.potentialcustomer.repository;

import beyond.samdasoo.potentialcustomer.entity.PotentialCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PotentialCustomerRepository extends JpaRepository<PotentialCustomer, Long> {


    @Query("SELECT p FROM PotentialCustomer p " +
            "WHERE " +
            // 선택된 필드에 따라 검색 조건을 적용
            "((:selectedItem = '고객사명' AND p.company LIKE CONCAT('%', :searchQuery, '%')) OR " +
            "(:selectedItem = '고객명' AND p.name LIKE CONCAT('%', :searchQuery, '%')) OR " +
            "(:selectedItem = '전화번호' AND p.phone LIKE CONCAT('%', :searchQuery, '%')) OR " +
            "(:selectedItem = '메일' AND p.email LIKE CONCAT('%', :searchQuery, '%'))) AND " +
            // 접촉상태에 대한 조건 처리
            "(:selectedContact IS NULL OR p.contactStatus = :selectedContact)")
    List<PotentialCustomer> searchByCriteria(
            @Param("selectedItem") String selectedItem,
            @Param("searchQuery") String searchQuery,
            @Param("selectedContact") PotentialCustomer.ContactStatus selectedContact
    );

}
