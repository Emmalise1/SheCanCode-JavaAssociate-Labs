public class TestBankingSystem {
    public static void main(String[] args) {
        System.out.println("=== Exercise 1.4: Banking Exception Hierarchy ===\n");

        BankAccount alice = new BankAccount("A001", "Alice", 1000.00);
        BankAccount bob = new BankAccount("B001", "Bob", 500.00);

        try {
            System.out.println("Test 1: Successful Transfer");
            System.out.println("Before: Alice = $" + alice.getBalance() + ", Bob = $" + bob.getBalance());
            TransactionProcessor.transfer(alice, bob, 200.00, "TXN001");
            System.out.println("After:  Alice = $" + alice.getBalance() + ", Bob = $" + bob.getBalance());
            System.out.println("✓ Transfer successful!\n");
        } catch (BankTransactionException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            System.out.println("Test 2: Insufficient Funds");
            TransactionProcessor.transfer(alice, bob, 2000.00, "TXN002");
        } catch (InsufficientFundsException e) {
            System.out.println("✓ Caught: " + e.getMessage());
            System.out.println("  Error Code: " + e.getErrorCode());
            System.out.println("  Available: $" + e.getAvailableBalance());
            System.out.println("  Requested: $" + e.getRequestedAmount() + "\n");
        } catch (BankTransactionException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            System.out.println("Test 3: Fraud Detection");
            BankAccount fraud = new BankAccount("F001", "Fraudster", 10000.00);
            fraud.setFraudFlag(true);
            TransactionProcessor.transfer(fraud, bob, 5000.00, "TXN003");
        } catch (FraudException e) {
            System.out.println(" Caught: " + e.getMessage());
            System.out.println("  Error Code: " + e.getErrorCode());
            System.out.println("  Reason: " + e.getFlaggedReason());
            System.out.println("  Transaction ID: " + e.getTransactionId() + "\n");
        } catch (BankTransactionException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
