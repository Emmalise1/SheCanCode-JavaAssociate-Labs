import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class JpaRepositoryImpl implements JpaRepository {
    private Map<String, JpaProduct> database = new ConcurrentHashMap<>();

    @Override
    public void save(JpaProduct product) {
        database.put(product.getId(), product);
    }

    @Override
    public JpaProduct findById(String id) {
        return database.get(id);
    }

    @Override
    public List<JpaProduct> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public List<JpaProduct> findByCategoryAndPriceGreaterThan(String category, double minPrice) {
        return database.values().stream()
                .filter(p -> p.getCategory().equals(category) && p.getPrice() > minPrice)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        database.remove(id);
    }
}
