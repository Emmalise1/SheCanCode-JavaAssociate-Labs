public class InsufficientFundsException extends BankTransactionException {
    private double availableBalance;
    private double requestedAmount;

    public InsufficientFundsException(String message, String errorCode,
                                      double availableBalance, double requestedAmount) {
        super(message, errorCode);
        this.availableBalance = availableBalance;
        this.requestedAmount = requestedAmount;
    }

    public double getAvailableBalance() { return availableBalance; }
    public double getRequestedAmount() { return requestedAmount; }
}
