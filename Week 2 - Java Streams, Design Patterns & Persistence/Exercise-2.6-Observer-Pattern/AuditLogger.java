public class AuditLogger implements OrderObserver {
    @Override
    public void onEvent(ObserverOrder order, OrderEvent event) {
        System.out.println("  AUDIT: Order " + order.getOrderId() +
                " is " + event + " - Logged at " + System.currentTimeMillis());
    }
}
