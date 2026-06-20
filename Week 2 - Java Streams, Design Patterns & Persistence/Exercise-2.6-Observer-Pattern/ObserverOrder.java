public class ObserverOrder {
    private final String orderId;
    private final String customerId;
    private final double totalAmount;

    public ObserverOrder(String orderId, String customerId, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public double getTotalAmount() { return totalAmount; }
}

