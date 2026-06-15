public class BankAccount {
    private String accountId;
    private String holderName;
    private double balance;
    private boolean fraudFlag;

    public BankAccount(String accountId, String holderName, double balance) {
        this.accountId = accountId;
        this.holderName = holderName;
        this.balance = balance;
        this.fraudFlag = false;
    }

    public String getAccountId() { return accountId; }
    public double getBalance() { return balance; }
    public boolean isFraudFlag() { return fraudFlag; }

    public void setFraudFlag(boolean fraudFlag) { this.fraudFlag = fraudFlag; }
    public void debit(double amount) { this.balance -= amount; }
    public void credit(double amount) { this.balance += amount; }

    @Override
    public String toString() {
        return holderName + ": $" + balance;
    }
}

