public class MobileMoneyStrategy implements PaymentStrategy {
    private final String phoneNumber;
    private final String provider;

    public MobileMoneyStrategy(String phoneNumber, String provider) {
        this.phoneNumber = phoneNumber;
        this.provider = provider;
    }

    @Override
    public PaymentResult process(PaymentRequest request) {
        if (phoneNumber == null || phoneNumber.length() < 10) {
            return new PaymentResult(false, null, "Invalid phone number", 0);
        }
        if (request.getAmount() > 10000) {
            return new PaymentResult(false, null, "Mobile money limit exceeded", 0);
        }

        double fee = request.getAmount() * 0.015;
        String txId = "MM-" + System.currentTimeMillis();

        return new PaymentResult(true, txId, "Payment processed", fee);
    }
}
