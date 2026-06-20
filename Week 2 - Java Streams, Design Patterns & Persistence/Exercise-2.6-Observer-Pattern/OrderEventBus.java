import java.util.*;

public class OrderEventBus {
    private final List<OrderObserver> observers = new ArrayList<>();

    public void subscribe(OrderObserver observer) {
        observers.add(observer);
        System.out.println("Observer subscribed: " + observer.getClass().getSimpleName());
    }

    public void unsubscribe(OrderObserver observer) {
        observers.remove(observer);
        System.out.println("Observer unsubscribed: " + observer.getClass().getSimpleName());
    }

    public void publish(ObserverOrder order, OrderEvent event) {
        System.out.println("\n--- Publishing event: " + event + " ---");
        for (OrderObserver observer : observers) {
            observer.onEvent(order, event);
        }
    }
}