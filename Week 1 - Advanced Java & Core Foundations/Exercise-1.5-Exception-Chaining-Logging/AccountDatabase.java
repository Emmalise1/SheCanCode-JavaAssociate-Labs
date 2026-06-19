import java.util.HashMap;
import java.util.Map;

public class AccountDatabase {
    private static Map<String, Double> balances = new HashMap<>();

    static {
        balances.put("ACC001", 1000.00);
        balances.put("ACC002", 500.00);
        balances.put("ACC003", 2500.00);
    }

    public static double getBalance(String accountId) throws DatabaseException {
        try {
            if (accountId == null) {
                throw new IllegalArgumentException("Account ID cannot be null");
            }

            Double balance = balances.get(accountId);
            if (balance == null) {
                throw new IllegalArgumentException("Account not found: " + accountId);
            }

            return balance;
        } catch (IllegalArgumentException e) {
            throw new DatabaseException(
                    "Failed to retrieve balance for: " + accountId,
