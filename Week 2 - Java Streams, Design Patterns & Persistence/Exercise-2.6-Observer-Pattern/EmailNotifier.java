public class EmailNotifier implements OrderObserver {
    @Override
    public void onEvent(ObserverOrder order, OrderEvent event) {
        System.out.println("  EMAIL: Order " + order.getOrderId() +
                " is " + event + " - Notification sent to " + order.getCustomerId());
    }
}
