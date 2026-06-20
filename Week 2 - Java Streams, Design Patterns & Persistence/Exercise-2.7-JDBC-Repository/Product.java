public class Product {
    private final String id;
    private final String name;
    private final String category;
    private final double price;
    private int stock;

    public Product(String id, String name, String category, double price, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return String.format("Product[id=%s, name=%s, price=%.2f, stock=%d]",
                id, name, price, stock);
    }
}
