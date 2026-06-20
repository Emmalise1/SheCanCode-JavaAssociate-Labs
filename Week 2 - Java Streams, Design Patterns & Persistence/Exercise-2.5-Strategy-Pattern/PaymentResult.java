public class PaymentResult {
    private final boolean success;
    private final String transactionId;
    private final String message;
    private final double fee;

    public PaymentResult(boolean success, String transactionId, String message, double fee) {
        this.success = success;
        this.transactionId = transactionId;
        this.message = message;
        this.fee = fee;
    }

    public boolean isSuccess() { return success; }
    public String getTransactionId() { return transactionId; }
    public String getMessage() { return message; }
    public double getFee() { return fee; }

    @Override
    public String toString() {
        return String.format("PaymentResult{success=%s, fee=%.2f, message='%s'}",
                success, fee, message);
    }
}
