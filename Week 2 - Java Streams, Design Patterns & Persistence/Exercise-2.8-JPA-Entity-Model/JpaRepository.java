import java.util.*;

public interface JpaRepository {
    void save(JpaProduct product);
    JpaProduct findById(String id);
    List<JpaProduct> findAll();
    List<JpaProduct> findByCategoryAndPriceGreaterThan(String category, double minPrice);
    void deleteById(String id);
}
