public class CreditCardStrategy implements PaymentStrategy {
    private final String cardNumber;
    private final String expiryDate;
    private final String cvv;

    public CreditCardStrategy(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public PaymentResult process(PaymentRequest request) {
        if (cardNumber == null || cardNumber.length() < 16) {
            return new PaymentResult(false, null, "Invalid card number", 0);
        }
        if (request.getAmount() > 50000) {
            return new PaymentResult(false, null, "Credit card limit exceeded", 0);
        }

        double fee = request.getAmount() * 0.025;
        String txId = "CC-" + System.currentTimeMillis();

        return new PaymentResult(true, txId, "Payment approved", fee);
    }
}
