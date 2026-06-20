package com.shecan.repository;

import com.shecan.model.ServiceProduct;
import java.sql.*;
import java.util.*;

public class JdbcProductRepository implements ProductRepository {
    private Connection connection;

    public JdbcProductRepository(Connection connection) {
        this.connection = connection;
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS products (" +
                "id VARCHAR(50) PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "category VARCHAR(100), " +
                "price DECIMAL(10,2), " +
                "stock INTEGER)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create table", e);
        }
    }

    @Override
    public void save(ServiceProduct product) {
        String sql = "INSERT INTO products (id, name, category, price, stock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getCategory());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getStock());
            ps.executeUpdate();
            System.out.println("  [JDBC] Saved: " + product.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save product", e);
        }
    }

    @Override
    public ServiceProduct findById(String id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("  [JDBC] Found: " + id);
                return new ServiceProduct(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find product", e);
        }
    }

    @Override
    public List<ServiceProduct> findAll() {
        List<ServiceProduct> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new ServiceProduct(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                ));
            }
            System.out.println("  [JDBC] Found all: " + products.size() + " products");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find all products", e);
        }
        return products;
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("  [JDBC] Deleted: " + id);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete product", e);
        }
    }
}