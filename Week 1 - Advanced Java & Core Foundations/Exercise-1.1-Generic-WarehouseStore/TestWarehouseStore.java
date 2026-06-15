public class TestWarehouseStore {
    public static void main(String[] args) {
        System.out.println("=== Exercise 1.1: Generic Warehouse Store ===\n");

        WarehouseStore<Electronics> store = new WarehouseStore<>();

        store.addItem(new Electronics("E001", "Dell XPS Laptop", "Electronics", 1299.99, "Dell", 24));
        store.addItem(new Electronics("E002", "Logitech Mouse", "Electronics", 29.99, "Logitech", 12));
        store.addItem(new Electronics("E003", "Corsair Keyboard", "Electronics", 89.99, "Corsair", 18));

        System.out.println("Total items: " + store.size());
        System.out.println("Electronics category: " + store.findByCategory("Electronics"));

        store.removeItem("E002");
        System.out.println("After removing mouse: " + store.size() + " items");

    }
}

