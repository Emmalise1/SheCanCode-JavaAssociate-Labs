

public class JpaProduct {
    // These would be JPA annotations in a real project:
    // @Id
    private String id;

    // @Column(nullable = false)
    private String name;

    // @Column(nullable = false)
    private String category;

    // @Column(nullable = false)
    private double price;

    // @Column(nullable = false)
    private int stock;

    // @Version
    // @Column(name = "version")
    private int version;

    public JpaProduct() {}

    public JpaProduct(String id, String name, String category, double price, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.version = 0;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public int getVersion() { return version; }

    public void setStock(int stock) { this.stock = stock; }
    public void setVersion(int version) { this.version = version; }

    @Override
    public String toString() {
        return String.format("JpaProduct[id=%s, name=%s, category=%s, price=%.2f, stock=%d, version=%d]",
                id, name, category, price, stock, version);
    }
}