public class FraudException extends BankTransactionException {
    private String flaggedReason;
    private String transactionId;

    public FraudException(String message, String errorCode,
                          String flaggedReason, String transactionId) {
        super(message, errorCode);
        this.flaggedReason = flaggedReason;
        this.transactionId = transactionId;
    }

    public String getFlaggedReason() { return flaggedReason; }
    public String getTransactionId() { return transactionId; }
}

