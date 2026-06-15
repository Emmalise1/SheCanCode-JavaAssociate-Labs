import java.util.*;

public class ComparatorTest {
    public static void main(String[] args) {
        System.out.println("=== Exercise 1.3: Custom Comparator ===\n");

        List<ComparableItem> items = new ArrayList<>();
        items.add(new ComparableItem("E001", "Budget Laptop", "Electronics", 499.99));
        items.add(new ComparableItem("E002", "Premium Laptop", "Electronics", 1999.99));
        items.add(new ComparableItem("E003", "Standard Laptop", "Electronics", 999.99));
        items.add(new ComparableItem("G001", "Organic Milk", "Grocery", 5.99));
        items.add(new ComparableItem("G002", "Regular Milk", "Grocery", 3.99));
        items.add(new ComparableItem("G003", "Premium Cheese", "Grocery", 12.99));
        items.add(new ComparableItem("F001", "T-Shirt", "Fashion", 19.99));
        items.add(new ComparableItem("F002", "Jeans", "Fashion", 59.99));
        items.add(new ComparableItem("F003", "Jacket", "Fashion", 129.99));

        System.out.println("Original order:");
        items.forEach(i -> System.out.println("  " + i));

        items.sort(Comparator
                .comparing(ComparableItem::getCategory)
                .thenComparing(Comparator.comparing(ComparableItem::getPrice).reversed()));

        System.out.println("\nSorted by Category (A-Z), then Price (Highest to Lowest):");
        String currentCat = "";
        for (ComparableItem i : items) {
            if (!currentCat.equals(i.getCategory())) {
                currentCat = i.getCategory();
                System.out.println("\n  [" + currentCat + "]");
            }
            System.out.println("    " + i);
        }

    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 1eec90cd047628bbfa03bc0fa14ccbb19fd2a3d6
