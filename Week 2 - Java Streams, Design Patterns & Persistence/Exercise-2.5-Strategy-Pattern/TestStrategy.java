public class TestStrategy {
    public static void main(String[] args) {

        PaymentRequest request = new PaymentRequest("ORD001", "C001", 1000.00, "USD");

        System.out.println("Test 1: Credit Card Strategy");
        PaymentStrategy ccStrategy = new CreditCardStrategy("1234567890123456", "12/25", "123");
        PaymentProcessor processor = new PaymentProcessor(ccStrategy);
        PaymentResult result = processor.processPayment(request);
        System.out.println("  Result: " + result);
        System.out.println("  Fee: $" + String.format("%.2f", result.getFee()));

        System.out.println("\nTest 2: Bank Transfer Strategy");
        PaymentStrategy btStrategy = new BankTransferStrategy("1234567890", "BANK001");
        processor.setStrategy(btStrategy);
        result = processor.processPayment(request);
        System.out.println("  Result: " + result);
        System.out.println("  Fee: $" + String.format("%.2f", result.getFee()));

        System.out.println("\nTest 3: Mobile Money Strategy");
        PaymentStrategy mmStrategy = new MobileMoneyStrategy("1234567890", "MTN");
        processor.setStrategy(mmStrategy);
        result = processor.processPayment(request);
        System.out.println("  Result: " + result);
        System.out.println("  Fee: $" + String.format("%.2f", result.getFee()));

    }
}
