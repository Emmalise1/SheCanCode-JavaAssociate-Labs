public class BankTransferStrategy implements PaymentStrategy {
    private final String accountNumber;
    private final String bankCode;

    public BankTransferStrategy(String accountNumber, String bankCode) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
    }

    @Override
    public PaymentResult process(PaymentRequest request) {
        if (accountNumber == null || accountNumber.length() < 10) {
            return new PaymentResult(false, null, "Invalid account number", 0);
        }
        if (request.getAmount() > 100000) {
            return new PaymentResult(false, null, "Bank transfer limit exceeded", 0);
        }

        double fee = request.getAmount() * 0.005;
        String txId = "BT-" + System.currentTimeMillis();

        return new PaymentResult(true, txId, "Transfer initiated", fee);
    }
}
