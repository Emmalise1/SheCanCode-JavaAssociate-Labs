public class InventoryUpdater implements OrderObserver {
    @Override
    public void onEvent(ObserverOrder order, OrderEvent event) {
        System.out.println("  INVENTORY: Order " + order.getOrderId() +
                " is " + event + " - Stock updated");
    }
}

