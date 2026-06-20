import java.util.List;

public class ParallelOrder {
    private final String orderId;
    private final String customerId;
    private final boolean delivered;
    private final List<ParallelLineItem> items;

    public ParallelOrder(String orderId, String customerId, boolean delivered, List<ParallelLineItem> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.delivered = delivered;
        this.items = items;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public boolean isDelivered() { return delivered; }
    public List<ParallelLineItem> getItems() { return items; }
}