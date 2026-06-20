public interface PaymentStrategy {
    PaymentResult process(PaymentRequest request);
}

