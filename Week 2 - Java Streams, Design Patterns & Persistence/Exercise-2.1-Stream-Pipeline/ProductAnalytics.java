import java.util.*;
import java.util.stream.*;

public class ProductAnalytics {


    public static List<LineItem> getAllLineItems(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.toList());
    }


    public static double calculateTotalRevenue(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .filter(item -> item.getQuantity() > 5)
                .mapToDouble(LineItem::getRevenue)
                .reduce(0, Double::sum);
    }


    public static List<Map.Entry<String, Double>> topNProductsByRevenue(List<Order> orders, int n) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        LineItem::getProductId,
                        Collectors.summingDouble(LineItem::getRevenue)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());
    }
}
