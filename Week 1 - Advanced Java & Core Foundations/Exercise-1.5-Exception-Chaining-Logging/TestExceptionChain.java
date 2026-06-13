public class TestExceptionChain {
    public static void main(String[] args) {
        System.out.println("=== Exercise 1.5: Exception Chaining ===\n");

        // Test 1: Successful lookup
        try {
            System.out.println("Test 1: Getting balance for ACC001");
            double balance = AccountDatabase.getBalance("ACC001");
            System.out.println("✓ Balance: $" + balance + "\n");
        } catch (DatabaseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test 2: Account not found
        try {
            System.out.println("Test 2: Getting balance for INVALID");
            double balance = AccountDatabase.getBalance("INVALID");
        } catch (DatabaseException e) {
            System.out.println("✓ Caught DatabaseException");
            System.out.println("  Message: " + e.getMessage());
            System.out.println("  Error Code: " + e.getErrorCode());
            System.out.println("  Cause: " + e.getCause().getClass().getSimpleName());
            System.out.println("  Cause Message: " + e.getCause().getMessage() + "\n");
        }

        // Test 3: Null account ID
        try {
            System.out.println("Test 3: Getting balance for null");
            double balance = AccountDatabase.getBalance(null);
        } catch (DatabaseException e) {
            System.out.println("✓ Exception chain preserved!");
            System.out.println("\n  === Exception Chain ===");
            Throwable current = e;
            int level = 0;
            while (current != null) {
                System.out.println("  Level " + level + ": " + current.getClass().getSimpleName());
                System.out.println("    Message: " + current.getMessage());
                current = current.getCause();
                level++;
            }
        }

    }
}
