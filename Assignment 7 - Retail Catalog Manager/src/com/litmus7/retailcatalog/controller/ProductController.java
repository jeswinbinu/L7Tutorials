package com.litmus7.retailcatalog.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.litmus7.retailcatalog.dto.Product;
import com.litmus7.retailcatalog.dto.Response;
import com.litmus7.retailcatalog.dto.ResponseStatus;
import com.litmus7.retailcatalog.exception.ServiceException;
import com.litmus7.retailcatalog.service.ProductService;

public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public Response<Void> addProduct(Product product) {
        if (product.getPrice() < 0 || product.getStockQuantity() < 0) {
            return Response.error("Price and stock quantity must be non-negative.");
        }
        try {
            return productService.addProduct(product);
        } catch (ServiceException e) {
            return Response.error("An error occurred while adding the product: " + e.getMessage());
        }
    }

    public Response<List<Product>> getAllProducts() {
        try {
            return productService.getAllProducts();
        } catch (ServiceException e) {
            return Response.error("An error occurred while retrieving products: " + e.getMessage());
        }
    }

    public Response<Product> getProductById(int productId) {
        try {
            return productService.getProductById(productId);
        } catch (ServiceException e) {
            return Response.error("An error occurred while retrieving the product: " + e.getMessage());
        }
    }

    public Response<Void> updateProduct(Product product) {
        if (product.getPrice() < 0 || product.getStockQuantity() < 0) {
            return Response.error("Price and stock quantity must be non-negative.");
        }
        try {
            return productService.updateProduct(product);
        } catch (ServiceException e) {
            return Response.error("An error occurred while updating the product: " + e.getMessage());
        }
    }

    public Response<Void> deleteProduct(int productId) {
        try {
        	return productService.deleteProduct(productId);
        } catch (ServiceException e) {
        	return Response.error("An error occurred while deleting the product: " + e.getMessage());
        }
    }
    
    public Response<List<Product>> getProductsByCategory(String category) {
        try {
            return productService.getProductsByCategory(category);
        } catch (Exception e) {
            return Response.error("An error occurred while retrieving products by category: " + e.getMessage());
        }
    }

    public Response<List<Product>> getSortedProductsByPrice() {
        try {
            Response<List<Product>> response = productService.getAllProducts();
            if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
                List<Product> products = response.getData();
                Collections.sort(products, Comparator.comparingDouble(Product::getPrice));
                return Response.success("Products sorted by price.", products);
            } else {
                return response;
            }
        } catch (Exception e) {
            return Response.error("An error occurred while sorting products by price: " + e.getMessage());
        }
    }
}
