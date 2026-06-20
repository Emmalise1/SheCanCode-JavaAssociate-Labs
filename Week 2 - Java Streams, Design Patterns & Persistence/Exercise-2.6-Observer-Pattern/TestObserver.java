public class TestObserver {
    public static void main(String[] args) {

        OrderEventBus eventBus = new OrderEventBus();

        EmailNotifier emailNotifier = new EmailNotifier();
        InventoryUpdater inventoryUpdater = new InventoryUpdater();
        AuditLogger auditLogger = new AuditLogger();

        eventBus.subscribe(emailNotifier);
        eventBus.subscribe(inventoryUpdater);
        eventBus.subscribe(auditLogger);

        ObserverOrder order1 = new ObserverOrder("ORD001", "C001", 1500.00);
        ObserverOrder order2 = new ObserverOrder("ORD002", "C002", 750.00);

        System.out.println("\nTesting with Order ORD001:");
        eventBus.publish(order1, OrderEvent.ORDER_PLACED);
        eventBus.publish(order1, OrderEvent.ORDER_SHIPPED);
        eventBus.publish(order1, OrderEvent.ORDER_DELIVERED);

        System.out.println("\nUnsubscribing AuditLogger...");
        eventBus.unsubscribe(auditLogger);

        System.out.println("\nTesting with Order ORD002:");
        eventBus.publish(order2, OrderEvent.ORDER_PLACED);
        eventBus.publish(order2, OrderEvent.ORDER_CANCELLED);

    }
}
