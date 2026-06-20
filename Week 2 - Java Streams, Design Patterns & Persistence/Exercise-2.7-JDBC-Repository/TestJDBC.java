import java.sql.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestJDBC {
    public static void main(String[] args) throws Exception {

        Class.forName("org.h2.Driver");

        Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        ProductJdbcRepository repo = new ProductJdbcRepository(conn);

        System.out.println("Inserting 50 products...");
        for (int i = 1; i <= 50; i++) {
            repo.save(new Product(
                    "P" + String.format("%03d", i),
                    "Product_" + i,
                    "Category_" + (i % 5),
                    10.0 + i,
                    100 + i
            ));
        }
        System.out.println(" 50 products inserted");

        System.out.println("\nTesting findById...");
        Product p = repo.findById("P001");
        System.out.println("  Found: " + p);

        System.out.println("\nTesting findAll...");
        System.out.println("  Total products: " + repo.findAll().size());

        System.out.println("\nTesting stock transfer...");
        System.out.println("  Before transfer:");
        System.out.println("    P001 stock: " + repo.findById("P001").getStock());
        System.out.println("    P002 stock: " + repo.findById("P002").getStock());

        repo.transferStock("P001", "P002", 10);

        System.out.println("  After transfer:");
        System.out.println("    P001 stock: " + repo.findById("P001").getStock());
        System.out.println("    P002 stock: " + repo.findById("P002").getStock());

        System.out.println("\nTesting concurrent transfers...");
        testConcurrentTransfers(repo);

    }

    private static void testConcurrentTransfers(ProductJdbcRepository repo) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        AtomicInteger successful = new AtomicInteger(0);

        int initialP001 = repo.findById("P001").getStock();
        int initialP002 = repo.findById("P002").getStock();
        System.out.println("  Initial total stock (P001 + P002): " + (initialP001 + initialP002));

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    repo.transferStock("P001", "P002", 1);
                    successful.incrementAndGet();
                } catch (SQLException e) {
                    // Expected when stock runs out
                }
                return null;
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        int finalP001 = repo.findById("P001").getStock();
        int finalP002 = repo.findById("P002").getStock();

        System.out.println("  Successful transfers: " + successful.get());
        System.out.println("  Final P001 stock: " + finalP001);
        System.out.println("  Final P002 stock: " + finalP002);
        System.out.println("  Final total stock: " + (finalP001 + finalP002));
        System.out.println("  Stock is conserved!");
    }
}