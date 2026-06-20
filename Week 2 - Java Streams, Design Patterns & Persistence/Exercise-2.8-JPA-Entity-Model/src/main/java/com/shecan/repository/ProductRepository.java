package src.main.java.com.shecan.repository;

import com.shecan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.price > :minPrice")
    List<Product> findByCategoryAndPriceGreaterThan(
            @Param("category") String category,
            @Param("minPrice") double minPrice
    );
}