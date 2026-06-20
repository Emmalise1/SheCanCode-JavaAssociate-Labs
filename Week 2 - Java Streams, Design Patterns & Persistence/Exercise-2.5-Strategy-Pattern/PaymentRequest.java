public class PaymentRequest {
    private final String orderId;
    private final String customerId;
    private final double amount;
    private final String currency;

    public PaymentRequest(String orderId, String customerId, double amount, String currency) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.currency = currency;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }
}
