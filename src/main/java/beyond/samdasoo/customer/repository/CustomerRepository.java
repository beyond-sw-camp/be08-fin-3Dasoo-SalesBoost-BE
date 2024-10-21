package beyond.samdasoo.customer.repository;

import beyond.samdasoo.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query("SELECT c FROM Customer c " +
            "JOIN c.user u " + // User와 조인
            "WHERE " +
            "(:selectedItem = '고객사명' AND c.company LIKE %:searchQuery%) OR " +
            "(:selectedItem = '고객명' AND c.name LIKE %:searchQuery%) OR " +
            "(:selectedItem = '전화번호' AND c.phone LIKE %:searchQuery%) OR " +
            "(:selectedItem = '메일' AND c.email LIKE %:searchQuery%) AND " +
            "(:selectedKey = '전체' OR " +
            "(:selectedKey = '키맨' AND c.isKeyMan = true) OR " +
            "(:selectedKey = '키맨아님' AND c.isKeyMan = false)) AND " +
            "(:personInCharge is null OR u.name = :personInCharge)")
    List<Customer> searchByCriteria(
            @Param("selectedItem") String selectedItem,
            @Param("searchQuery") String searchQuery,
            @Param("selectedKey") String selectedKey,
            @Param("personInCharge") String personInCharge
    );
}
