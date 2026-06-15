public class BenchmarkItem {
    private final String sku;
    private final String name;
    private final double price;

    public BenchmarkItem(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getSku() { return sku; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}


