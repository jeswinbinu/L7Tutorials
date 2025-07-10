package com.litmus7.retailcatalog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.retailcatalog.dto.Product;
import com.litmus7.retailcatalog.exception.DAOException;
import com.litmus7.retailcatalog.util.DBUtil;

public class ProductDAO {

    public void addProduct(Product product) throws DAOException {
        String sql = "INSERT INTO products (productId, name, category, price, stockQuantity) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, product.getProductId());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getCategory());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getStockQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error adding product", e);
        }
    }

    public List<Product> getAllProducts() throws DAOException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT productId, name, category, price, stockQuantity FROM products";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("productId"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stockQuantity")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving products", e);
        }
        return products;
    }

    public Product getProductById(int productId) throws DAOException {
        String sql = "SELECT productId, name, category, price, stockQuantity FROM products WHERE productId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("productId"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stockQuantity")
                );
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving product", e);
        }
        return null;
    }
    
    public List<Product> getProductsByCategory(String category) throws DAOException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT productId, name, category, price, stockQuantity FROM products WHERE category = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("productId"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stockQuantity")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving products by category", e);
        }
        return products;
    }

    public void updateProduct(Product product) throws DAOException {
        String sql = "UPDATE products SET name=?, category=?, price=?, stockQuantity=? WHERE productId=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getCategory());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStockQuantity());
            pstmt.setInt(5, product.getProductId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error updating product", e);
        }
    }

    public void deleteProduct(int productId) throws DAOException {
        String sql = "DELETE FROM products WHERE productId=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error deleting product", e);
        }
    }
}