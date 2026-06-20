import java.util.*;

public class TestStreamPipeline {
    public static void main(String[] args) {

        List<Order> orders = createTestOrders();

        System.out.println("Total Line Items: " + ProductAnalytics.getAllLineItems(orders).size());

        System.out.println("Total Revenue (quantity > 5): $" +
                String.format("%.2f", ProductAnalytics.calculateTotalRevenue(orders)));

        System.out.println("\nTop 3 Products by Revenue:");
        List<Map.Entry<String, Double>> top3 = ProductAnalytics.topNProductsByRevenue(orders, 3);
        for (int i = 0; i < top3.size(); i++) {
            System.out.println("  " + (i+1) + ". Product " + top3.get(i).getKey() +
                    " - $" + String.format("%.2f", top3.get(i).getValue()));
        }

    }

    private static List<Order> createTestOrders() {
        List<Order> orders = new ArrayList<>();

        List<LineItem> items1 = Arrays.asList(
                new LineItem("P001", "Laptop", "Electronics", 10, 999.99),
                new LineItem("P002", "Mouse", "Electronics", 2, 29.99),
                new LineItem("P003", "Book", "Education", 7, 19.99)
        );
        orders.add(new Order("ORD001", "C001", true, items1));

        List<LineItem> items2 = Arrays.asList(
                new LineItem("P001", "Laptop", "Electronics", 5, 999.99),
                new LineItem("P004", "Phone", "Electronics", 3, 699.99),
                new LineItem("P005", "Pen", "Office", 100, 1.99)
        );
        orders.add(new Order("ORD002", "C002", false, items2));

        List<LineItem> items3 = Arrays.asList(
                new LineItem("P006", "Chair", "Furniture", 8, 199.99),
                new LineItem("P003", "Book", "Education", 15, 19.99),
                new LineItem("P007", "Desk", "Furniture", 1, 499.99)
        );
        orders.add(new Order("ORD003", "C003", true, items3));

        return orders;
    }
}