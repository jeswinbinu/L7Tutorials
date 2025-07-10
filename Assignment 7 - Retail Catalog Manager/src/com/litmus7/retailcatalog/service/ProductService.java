package com.litmus7.retailcatalog.service;

import java.util.List;

import com.litmus7.retailcatalog.dao.ProductDAO;
import com.litmus7.retailcatalog.dto.Product;
import com.litmus7.retailcatalog.dto.Response;
import com.litmus7.retailcatalog.exception.DAOException;
import com.litmus7.retailcatalog.exception.ServiceException;

public class ProductService {

    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public Response<Void> addProduct(Product product) throws ServiceException {
        try {
            productDAO.addProduct(product);
            return Response.success("Product added successfully!");
        } catch (DAOException e) {
            return Response.error("Error adding product: " + e.getMessage());
        }
    }

    public Response<List<Product>> getAllProducts() throws ServiceException {
        try {
            List<Product> products = productDAO.getAllProducts();
            return Response.success("Retrieved all products.", products);
        } catch (DAOException e) {
            return Response.error("Error retrieving products: " + e.getMessage(), null);
        }
    }

    public Response<Product> getProductById(int productId) throws ServiceException {
        try {
            Product product = productDAO.getProductById(productId);
            if (product != null) {
                return Response.success("Product found.", product);
            } else {
                return Response.success("Product not found.", null);
            }
        } catch (DAOException e) {
            return Response.error("Error retrieving product: " + e.getMessage(), null);
        }
    }
    
    public Response<List<Product>> getProductsByCategory(String category) {
        try {
            List<Product> products = productDAO.getProductsByCategory(category);
            return Response.success("Retrieved products by category.", products);
        } catch (DAOException e) {
            return Response.error("Error retrieving products by category: " + e.getMessage(), null);
        }
    }

    public Response<Void> updateProduct(Product product) throws ServiceException {
        try {
            productDAO.updateProduct(product);
            return Response.success("Product updated successfully!");
        } catch (DAOException e) {
            return Response.error("Error updating product: " + e.getMessage());
        }
    }

    public Response<Void> deleteProduct(int productId) throws ServiceException {
        try {
            productDAO.deleteProduct(productId);
            return Response.success("Product deleted successfully!");
        } catch (DAOException e) {
            return Response.error("Error deleting product: " + e.getMessage());
        }
    }
}