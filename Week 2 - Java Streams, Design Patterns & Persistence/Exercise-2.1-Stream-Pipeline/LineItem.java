public class LineItem {
    private  final String productId;
    private final String productName;
    private final String category;
    private final int quantity;
    private final double price;

    public LineItem(String productId, String productName, String category, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public double getRevenue() { return price * quantity; }
}
