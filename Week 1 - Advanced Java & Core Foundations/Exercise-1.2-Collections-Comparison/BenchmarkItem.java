public class BenchmarkItem {
    private String sku;
    private String name;
    private double price;

    public BenchmarkItem(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getSku() { return sku; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}
