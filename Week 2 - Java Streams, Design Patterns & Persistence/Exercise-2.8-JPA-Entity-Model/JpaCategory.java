import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "categories")
public class JpaCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<JpaProduct> products = new ArrayList<>();

    public JpaCategory() {}

    public JpaCategory(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public List<JpaProduct> getProducts() { return products; }

    public void addProduct(JpaProduct product) {
        products.add(product);
    }
}
