public interface OrderObserver {
    void onEvent(ObserverOrder order, OrderEvent event);
}