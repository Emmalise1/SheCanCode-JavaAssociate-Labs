import java.sql.*;
import java.util.*;

public class ProductJdbcRepository {
    private Connection connection;

    public ProductJdbcRepository(Connection connection) {
        this.connection = connection;
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS products (" +
                "id VARCHAR(50) PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "category VARCHAR(100), " +
                "price DECIMAL(10,2), " +
                "stock INTEGER" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create table", e);
        }
    }

    public void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (id, name, category, price, stock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getCategory());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getStock());
            ps.executeUpdate();
        }
    }

    public Product findById(String id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRowToProduct(rs);
            }
            return null;
        }
    }

    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(mapRowToProduct(rs));
            }
        }
        return products;
    }

    public void deleteById(String id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    public void transferStock(String fromId, String toId, int quantity) throws SQLException {
        connection.setAutoCommit(false);
        try {
            String deductSql = "UPDATE products SET stock = stock - ? WHERE id = ? AND stock >= ?";
            try (PreparedStatement ps = connection.prepareStatement(deductSql)) {
                ps.setInt(1, quantity);
                ps.setString(2, fromId);
                ps.setInt(3, quantity);
                int updated = ps.executeUpdate();
                if (updated == 0) {
                    throw new SQLException("Insufficient stock for product: " + fromId);
                }
            }

            String addSql = "UPDATE products SET stock = stock + ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(addSql)) {
                ps.setInt(1, quantity);
                ps.setString(2, toId);
                ps.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getDouble("price"),
                rs.getInt("stock")
        );
    }
}
