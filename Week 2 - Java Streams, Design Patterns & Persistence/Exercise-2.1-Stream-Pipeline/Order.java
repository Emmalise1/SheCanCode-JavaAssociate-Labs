import java.util.List;

public class Order {
    private String orderId;
    private String customerId;
    private boolean delivered;
    private List<LineItem> items;

    public Order(String orderId, String customerId, boolean delivered, List<LineItem> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.delivered = delivered;
        this.items = items;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public boolean isDelivered() { return delivered; }
    public List<LineItem> getItems() { return items; }
}
