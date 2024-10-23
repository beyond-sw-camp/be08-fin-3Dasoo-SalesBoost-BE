package beyond.samdasoo.admin.repository;

import beyond.samdasoo.admin.entity.Product;
import beyond.samdasoo.admin.entity.TargetSale;
import beyond.samdasoo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TargetSaleRepository extends JpaRepository<TargetSale, Long>, TargetSaleRepositoryCustom {

    List<TargetSale> findByUserAndYear(User user, int year);

    List<TargetSale> findByUserAndProductAndYear(User user, Product product, int year);
}
