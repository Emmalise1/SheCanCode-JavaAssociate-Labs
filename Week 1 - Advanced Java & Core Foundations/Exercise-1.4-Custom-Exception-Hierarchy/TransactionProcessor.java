public class TransactionProcessor {

    public static void transfer(BankAccount from, BankAccount to, double amount, String transactionId)
            throws BankTransactionException {

        if (from == null || to == null) {
            throw new BankTransactionException("Account cannot be null", "TXN_001");
        }

        if (amount <= 0) {
            throw new BankTransactionException("Amount must be positive", "TXN_002");
        }

        if (from.isFraudFlag() || to.isFraudFlag()) {
            throw new FraudException(
                    "Account flagged for suspicious activity", "FRAUD_001",
                    "Account on watchlist", transactionId
            );
        }

        if (amount > 10000) {
            throw new FraudException(
                    "Transaction exceeds maximum limit", "FRAUD_002",
                    "Amount $" + amount + " exceeds $10,000 limit", transactionId
            );
        }

        if (from.getBalance() < amount) {
            throw new InsufficientFundsException(
                    "Insufficient funds", "FUNDS_001",
                    from.getBalance(), amount
            );
        }

        from.debit(amount);
        to.credit(amount);
    }
}

