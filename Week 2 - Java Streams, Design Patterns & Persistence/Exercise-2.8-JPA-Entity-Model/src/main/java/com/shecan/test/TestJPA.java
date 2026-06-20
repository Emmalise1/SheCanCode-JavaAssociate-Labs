package com.shecan.test;

import com.shecan.model.Product;
import com.shecan.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class TestJPA {
    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // Creating categories
            Category electronics = new Category("Electronics");
            Category education = new Category("Education");

            // Creating products
            Product laptop = new Product("P001", "Laptop", "Electronics", 999.99, 10);
            Product phone = new Product("P002", "Phone", "Electronics", 699.99, 15);
            Product book = new Product("P003", "Book", "Education", 19.99, 50);
            Product mouse = new Product("P004", "Mouse", "Electronics", 29.99, 100);
            Product monitor = new Product("P005", "Monitor", "Electronics", 249.99, 8);

            // Adding products to categories
            electronics.addProduct(laptop);
            electronics.addProduct(phone);
            electronics.addProduct(mouse);
            electronics.addProduct(monitor);
            education.addProduct(book);

            em.getTransaction().begin();
            em.persist(electronics);
            em.persist(education);
            em.persist(laptop);
            em.persist(phone);
            em.persist(book);
            em.persist(mouse);
            em.persist(monitor);
            em.getTransaction().commit();
            System.out.println(" 5 products and 2 categories saved successfully\n");

            // Test 1: Find All
            System.out.println("Test 1: findAll() - SELECT p FROM Product p");
            TypedQuery<Product> findAllQuery = em.createQuery("SELECT p FROM Product p", Product.class);
            List<Product> all = findAllQuery.getResultList();
            System.out.println("  Total products: " + all.size());
            for (Product p : all) {
                System.out.println("    " + p);
            }
            System.out.println();

            // Test 2: Custom JPQL Query
            System.out.println("Test 2: Custom JPQL Query");
            System.out.println("  Query: SELECT p FROM Product p WHERE p.category = :category AND p.price > :minPrice");

            TypedQuery<Product> query = em.createQuery(
                    "SELECT p FROM Product p WHERE p.category = :category AND p.price > :minPrice",
                    Product.class
            );
            query.setParameter("category", "Electronics");
            query.setParameter("minPrice", 100.0);

            List<Product> results = query.getResultList();
            System.out.println("  Found " + results.size() + " products (Electronics > $100):");
            for (Product p : results) {
                System.out.println("    " + p);
            }
            System.out.println();

            // Test 3: Optimistic Locking
            System.out.println("Test 3: Optimistic Locking with @Version");
            System.out.println("  Demonstrating OptimisticLockException with two EntityManager sessions...\n");

            EntityManager em1 = emf.createEntityManager();
            EntityManager em2 = emf.createEntityManager();

            try {
                em1.getTransaction().begin();
                Product product1 = em1.find(Product.class, "P001");
                System.out.println("  Session 1 loaded: " + product1);
                System.out.println("  Session 1 version: " + product1.getVersion());

                em2.getTransaction().begin();
                Product product2 = em2.find(Product.class, "P001");
                System.out.println("  Session 2 loaded: " + product2);
                System.out.println("  Session 2 version: " + product2.getVersion());

                // Session 1 updates
                product1.setStock(8);
                em1.flush();
                System.out.println("\n  Session 1 updated stock to 8");
                System.out.println("  Session 1 version after update: " + product1.getVersion());

                // Session 2 tries to update with old version - should fail!
                product2.setStock(5);
                System.out.println("\n  Session 2 trying to update stock to 5...");
                System.out.println("  Session 2 has version: " + product2.getVersion());

                try {
                    em2.flush();
                    System.out.println("   ERROR: Expected OptimisticLockException but none occurred!");
                } catch (OptimisticLockException e) {
                    System.out.println("   SUCCESS: Caught OptimisticLockException!");
                    System.out.println("     Message: " + e.getMessage());
                }

                em1.getTransaction().commit();
                System.out.println("\n  Session 1 transaction committed.");

            } catch (Exception e) {
                if (em1.getTransaction().isActive()) em1.getTransaction().rollback();
                if (em2.getTransaction().isActive()) em2.getTransaction().rollback();
            } finally {
                em1.close();
                em2.close();
            }

            // Test 4: Verify final state
            System.out.println("\nTest 4: Verify Final State");
            Product finalProduct = em.find(Product.class, "P001");
            System.out.println("  Final product: " + finalProduct);
            System.out.println("  Final stock: " + finalProduct.getStock());
            System.out.println("  Final version: " + finalProduct.getVersion());

            // Test 5: @OneToMany Relationship
            System.out.println("\nTest 5: @OneToMany Relationship");
            System.out.println("  Electronics category products:");
            TypedQuery<Product> electronicsQuery = em.createQuery(
                    "SELECT p FROM Product p WHERE p.category = 'Electronics'",
                    Product.class
            );
            List<Product> electronicsProducts = electronicsQuery.getResultList();
            for (Product p : electronicsProducts) {
                System.out.println("    - " + p.getName() + " (stock: " + p.getStock() + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}