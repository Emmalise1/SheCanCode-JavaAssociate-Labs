import java.util.*;

public class TestCollectors {
    public static void main(String[] args) {

        List<CollectorOrder> orders = createTestOrders();

        System.out.println("Items by Category:");
        CollectorsDemo.countItemsByCategory(orders)
                .forEach((cat, count) -> System.out.println("  " + cat + ": " + count));

        Map<Boolean, List<CollectorOrder>> partitioned = CollectorsDemo.partitionByDeliveryStatus(orders);
        System.out.println("\nDelivered Orders: " + partitioned.get(true).size());
        System.out.println("Pending Orders: " + partitioned.get(false).size());

        System.out.println("\nAverage Prices (sample):");
        CollectorsDemo.productIdToAveragePrice(orders)
                .entrySet().stream().limit(3)
                .forEach(e -> System.out.println("  Product " + e.getKey() + ": $" +
                        String.format("%.2f", e.getValue())));
        
    }

    private static List<CollectorOrder> createTestOrders() {
        List<CollectorOrder> orders = new ArrayList<>();

        List<CollectorLineItem> items1 = Arrays.asList(
                new CollectorLineItem("P001", "Laptop", "Electronics", 10, 999.99),
                new CollectorLineItem("P002", "Mouse", "Electronics", 2, 29.99)
        );
        orders.add(new CollectorOrder("ORD001", "C001", true, items1));

        List<CollectorLineItem> items2 = Arrays.asList(
                new CollectorLineItem("P003", "Book", "Education", 7, 19.99),
                new CollectorLineItem("P004", "Phone", "Electronics", 3, 699.99)
        );
        orders.add(new CollectorOrder("ORD002", "C002", false, items2));

        return orders;
    }
}
