import java.util.List;

public class CollectorOrder {
    private final String orderId;
    private final String customerId;
    private final boolean delivered;
    private final List<CollectorLineItem> items;

    public CollectorOrder(String orderId, String customerId, boolean delivered, List<CollectorLineItem> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.delivered = delivered;
        this.items = items;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public boolean isDelivered() { return delivered; }
    public List<CollectorLineItem> getItems() { return items; }
}