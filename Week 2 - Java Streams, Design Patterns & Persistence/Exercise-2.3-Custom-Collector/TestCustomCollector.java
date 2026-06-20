import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class TestCustomCollector {
    public static void main(String[] args) {

        List<ParallelOrder> orders = createTestOrders();
        List<ParallelLineItem> allItems = getAllLineItems(orders);

        RevenueReport report = allItems.stream().collect(new RevenueCollector());
        System.out.println("Custom Collector Report: " + report);

        System.out.println("\nParallel Stream Verification:");
        List<Entry<String, Double>> sequential = topNProductsByRevenue(orders, 3);
        List<Entry<String, Double>> parallel = topNProductsByRevenueParallel(orders, 3);

        boolean equal = sequential.equals(parallel);
        System.out.println("Sequential vs Parallel results match: " + equal);

        System.out.println("\nPerformance Benchmark:");
        benchmarkPerformance(100000);

    }

    private static List<ParallelLineItem> getAllLineItems(List<ParallelOrder> orders) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private static List<Entry<String, Double>> topNProductsByRevenue(List<ParallelOrder> orders, int n) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        ParallelLineItem::getProductId,
                        Collectors.summingDouble(ParallelLineItem::getRevenue)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    private static List<Entry<String, Double>> topNProductsByRevenueParallel(List<ParallelOrder> orders, int n) {
        return orders.parallelStream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        ParallelLineItem::getProductId,
                        Collectors.summingDouble(ParallelLineItem::getRevenue)
                ))
                .entrySet().parallelStream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    private static void benchmarkPerformance(int size) {
        Random rand = new Random();
        List<ParallelOrder> largeOrders = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            List<ParallelLineItem> items = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                items.add(new ParallelLineItem(
                        "P" + rand.nextInt(100),
                        "Product_" + j,
                        "Category_" + rand.nextInt(5),
                        rand.nextInt(20) + 1,
                        10.0 + rand.nextDouble() * 990
                ));
            }
            largeOrders.add(new ParallelOrder("ORD" + i, "C" + i, rand.nextBoolean(), items));
        }

        long start = System.nanoTime();
        topNProductsByRevenue(largeOrders, 10);
        long seqTime = System.nanoTime() - start;

        start = System.nanoTime();
        topNProductsByRevenueParallel(largeOrders, 10);
        long parTime = System.nanoTime() - start;

        System.out.println("  Dataset: " + size + " orders");
        System.out.println("  Sequential: " + seqTime / 1_000_000 + " ms");
        System.out.println("  Parallel: " + parTime / 1_000_000 + " ms");
        System.out.println("  Speedup: " + String.format("%.2fx", (double)seqTime / parTime));
    }

    private static List<ParallelOrder> createTestOrders() {
        List<ParallelOrder> orders = new ArrayList<>();

        List<ParallelLineItem> items1 = Arrays.asList(
                new ParallelLineItem("P001", "Laptop", "Electronics", 10, 999.99),
                new ParallelLineItem("P002", "Mouse", "Electronics", 2, 29.99)
        );
        orders.add(new ParallelOrder("ORD001", "C001", true, items1));

        List<ParallelLineItem> items2 = Arrays.asList(
                new ParallelLineItem("P003", "Book", "Education", 7, 19.99),
                new ParallelLineItem("P004", "Phone", "Electronics", 3, 699.99)
        );
        orders.add(new ParallelOrder("ORD002", "C002", false, items2));

        return orders;
    }
}