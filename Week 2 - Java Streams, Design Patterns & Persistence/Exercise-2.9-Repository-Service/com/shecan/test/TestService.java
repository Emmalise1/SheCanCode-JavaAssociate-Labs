package com.shecan.test;

import com.shecan.model.ServiceProduct;
import com.shecan.repository.ProductRepository;
import com.shecan.repository.JdbcProductRepository;
import com.shecan.repository.JpaProductRepository;
import com.shecan.service.ProductService;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class TestService {
    public static void main(String[] args) {


        try {
            System.out.println("Test 1: JDBC Implementation");
            System.out.println("----------------------------");
            testWithJDBC();

            System.out.println("\nTest 2: JPA Implementation (Swapped!)");
            System.out.println("--------------------------------------");
            testWithJPA();

            System.out.println("\nTest 3: Bulk Import with Transaction");
            System.out.println("-------------------------------------");
            testBulkImport();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testWithJDBC() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testdb_jdbc;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        Connection connection = dataSource.getConnection();
        ProductRepository jdbcRepo = new JdbcProductRepository(connection);
        ProductService service = new ProductService(jdbcRepo);

        System.out.println("  Creating JDBC repository and service...");
        service.save(new ServiceProduct("P001", "Laptop", "Electronics", 999.99, 10));
        service.save(new ServiceProduct("P002", "Phone", "Electronics", 699.99, 15));
        service.save(new ServiceProduct("P003", "Book", "Education", 19.99, 50));
        System.out.println("   3 products saved");

        List<ServiceProduct> products = service.findAll();
        System.out.println("  Total products: " + products.size());

        ServiceProduct found = service.findById("P001");
        System.out.println("  Found P001: " + found);

        service.deleteById("P003");
        System.out.println("  Deleted P003");

        products = service.findAll();
        System.out.println("  Remaining products: " + products.size());
        for (ServiceProduct p : products) {
            System.out.println("    - " + p.getId() + ": " + p.getName());
        }
        connection.close();
    }

    private static void testWithJPA() {
        ProductRepository jpaRepo = new JpaProductRepository();
        ProductService service = new ProductService(jpaRepo);

        System.out.println("  Creating JPA repository and service...");
        System.out.println("  (Service uses the SAME class - only repository changed)");

        service.save(new ServiceProduct("P001", "Laptop", "Electronics", 999.99, 10));
        service.save(new ServiceProduct("P002", "Phone", "Electronics", 699.99, 15));
        service.save(new ServiceProduct("P003", "Book", "Education", 19.99, 50));
        System.out.println("  3 products saved");

        List<ServiceProduct> products = service.findAll();
        System.out.println("  Total products: " + products.size());

        ServiceProduct found = service.findById("P002");
        System.out.println("  Found P002: " + found);
    }

    private static void testBulkImport() {
        ProductRepository repo = new JpaProductRepository();
        ProductService service = new ProductService(repo);

        List<ServiceProduct> validProducts = Arrays.asList(
                new ServiceProduct("B001", "Product 1", "Category A", 10.99, 100),
                new ServiceProduct("B002", "Product 2", "Category B", 20.99, 200),
                new ServiceProduct("B003", "Product 3", "Category C", 30.99, 300),
                new ServiceProduct("B004", "Product 4", "Category A", 40.99, 400),
                new ServiceProduct("B005", "Product 5", "Category B", 50.99, 500)
        );

        System.out.println("  Bulk importing 5 valid products...");
        service.bulkImport(validProducts);
        System.out.println("  All 5 products imported successfully");

        System.out.println("\n  Testing validation failures...");
        try {
            List<ServiceProduct> invalidProducts = Arrays.asList(
                    new ServiceProduct("", "Invalid Product", "Category", 10.00, 100)
            );
            service.bulkImport(invalidProducts);
            System.out.println("  Should have thrown exception for blank ID!");
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught expected exception: " + e.getMessage());
        }

        try {
            List<ServiceProduct> invalidProducts = Arrays.asList(
                    new ServiceProduct("P100", "Invalid Product", "Category", -10.00, 100)
            );
            service.bulkImport(invalidProducts);
            System.out.println("  Should have thrown exception for negative price!");
        } catch (IllegalArgumentException e) {
            System.out.println(" Caught expected exception: " + e.getMessage());
        }

        System.out.println("\n  Testing all-or-nothing transaction...");
        int beforeCount = service.findAll().size();
        System.out.println("  Products before import: " + beforeCount);

        List<ServiceProduct> mixedProducts = Arrays.asList(
                new ServiceProduct("B010", "Good Product", "Category A", 10.00, 100),
                new ServiceProduct("B011", "", "Category B", 20.00, 200),
                new ServiceProduct("B012", "Good Product 2", "Category C", 30.00, 300)
        );

        try {
            service.bulkImport(mixedProducts);
            System.out.println("  Should have thrown exception!");
        } catch (IllegalArgumentException e) {
            System.out.println("  Caught expected exception: " + e.getMessage());
        }

        int afterCount = service.findAll().size();
        System.out.println("  Products after import: " + afterCount);

        if (beforeCount == afterCount) {
            System.out.println("  Transaction rolled back - no partial saves!");
        }
    }
}