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
<<<<<<< HEAD
}
=======
}
>>>>>>> 1eec90cd047628bbfa03bc0fa14ccbb19fd2a3d6
