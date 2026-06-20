import java.util.*;
import java.util.stream.*;

public class CollectorsDemo {

    public static Map<String, Long> countItemsByCategory(List<CollectorOrder> orders) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        CollectorLineItem::getCategory,
                        Collectors.counting()
                ));
    }

    public static Map<Boolean, List<CollectorOrder>> partitionByDeliveryStatus(List<CollectorOrder> orders) {
        return orders.stream()
                .collect(Collectors.partitioningBy(CollectorOrder::isDelivered));
    }

    public static Map<String, Double> productIdToAveragePrice(List<CollectorOrder> orders) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.toMap(
                        CollectorLineItem::getProductId,
                        CollectorLineItem::getPrice,
                        (price1, price2) -> (price1 + price2) / 2.0
                ));
    }
}
