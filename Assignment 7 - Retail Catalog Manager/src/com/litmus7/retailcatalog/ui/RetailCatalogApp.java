package com.litmus7.retailcatalog.ui;

import java.util.List;
import java.util.Scanner;

import com.litmus7.retailcatalog.controller.ProductController;
import com.litmus7.retailcatalog.dto.Product;
import com.litmus7.retailcatalog.dto.Response;
import com.litmus7.retailcatalog.dto.ResponseStatus;
import com.litmus7.retailcatalog.service.ProductService;

public class RetailCatalogApp {
	
	 private Scanner scanner = new Scanner(System.in);
	 private ProductService productService = new ProductService();
	 private ProductController productController = new ProductController(productService);
	
	 public void start() {
	     boolean exit = false;
	     while (!exit) {
			 System.out.println("\n--- Product Management System ---");
			 System.out.println("1. Add Product");
			 System.out.println("2. View All Products");
			 System.out.println("3. Find Product by ID");
			 System.out.println("4. View Products by Category");
			 System.out.println("5. Sort Products by Price");
			 System.out.println("6. Update Product");
			 System.out.println("7. Delete Product");
			 System.out.println("8. Exit");
	         System.out.print("Enter your choice: ");
	         int choice = scanner.nextInt();
	         scanner.nextLine();
	
	         switch (choice) {
	             case 1:
	                 addProduct();
	                 break;
	             case 2:
	                 viewAllProducts();
	                 break;
	             case 3:
	                 findProductById();
	                 break;
	             case 4:
	                 viewProductsByCategory();
	                 break;
	             case 5:
	                 sortProductsByPrice();
	                 break;
	             case 6:
	                 updateProduct();
	                 break;
	             case 7:
	                 deleteProduct();
	                 break;
	             case 8:
	                 exit = true;
	                 System.out.print("Thank you for using RetailMart Product Catalog Manager.\r\n"
	                 		+ "Goodbye!");
	                 break;
	             default:
	                 System.out.println("Invalid choice. Please try again.");
	         }
	     }
	 }
	
	 private void addProduct() {
	     System.out.print("Enter Product ID: ");
	     int productId = scanner.nextInt();
	     scanner.nextLine();
	
	     System.out.print("Enter Product Name: ");
	     String name = scanner.nextLine();
	
	     System.out.print("Enter Category: ");
	     String category = scanner.nextLine();
	
	     System.out.print("Enter Price: ");
	     double price = scanner.nextDouble();
	     scanner.nextLine();
	
	     System.out.print("Enter Stock Quantity: ");
	     int stockQuantity = scanner.nextInt();
	     scanner.nextLine();
	
	     Product product = new Product(productId, name, category, price, stockQuantity);
	     Response<Void> response = productController.addProduct(product);
	     System.out.println(response.getMessage());
	 }
	
    private void findProductById() {
        System.out.print("Enter Product ID to find: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        Response<Product> response = productController.getProductById(productId);
        if (response.getStatus() == ResponseStatus.SUCCESS) {
            Product product = response.getData();
            if (product != null) {
                System.out.println(product);
            } else {
                System.out.println("Product not found.");
            }
        } else {
            System.out.println(response.getMessage());
        }
    }
	 
	 private void viewAllProducts() {
	     Response<List<Product>> response = productController.getAllProducts();
	     if (response.getStatus() == ResponseStatus.SUCCESS) {
	         List<Product> products = response.getData();
	         if (products.isEmpty()) {
	             System.out.println("No products found.");
	         } else {
	             for (Product product : products) {
	                 System.out.println(product);
	             }
	         }
	     } else {
	         System.out.println(response.getMessage());
	     }
	 }
	
	 private void updateProduct() {
	     System.out.print("Enter Product ID to update: ");
	     int productId = scanner.nextInt();
	     scanner.nextLine();
	
	     System.out.print("New Name: ");
	     String name = scanner.nextLine();
	
	     System.out.print("New Category: ");
	     String category = scanner.nextLine();
	
	     System.out.print("New Price: ");
	     double price = scanner.nextDouble();
	     scanner.nextLine();
	
	     System.out.print("New Stock Quantity: ");
	     int stockQuantity = scanner.nextInt();
	     scanner.nextLine();
	
	     Product product = new Product(productId, name, category, price, stockQuantity);
	     Response<Void> response = productController.updateProduct(product);
	     System.out.println(response.getMessage());
	 }
	
	 private void deleteProduct() {
	     System.out.print("Enter Product ID to delete: ");
	     int productId = scanner.nextInt();
	     scanner.nextLine();
	
	     Response<Void> response = productController.deleteProduct(productId);
	     System.out.println(response.getMessage());
	 }
	 
    private void viewProductsByCategory() {
        System.out.print("Enter Category to search: ");
        String category = scanner.nextLine();

        Response<List<Product>> response = productController.getProductsByCategory(category);
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
            List<Product> products = response.getData();
            if (products.isEmpty()) {
                System.out.println("No products found in this category.");
            } else {
                for (Product product : products) {
                    System.out.println(product);
                }
            }
        } else {
            System.out.println(response.getMessage());
        }
    }
    
    private void sortProductsByPrice() {
        Response<List<Product>> response = productController.getSortedProductsByPrice();
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
            List<Product> products = response.getData();
            if (products.isEmpty()) {
                System.out.println("No products found.");
            } else {
                for (Product product : products) {
                    System.out.println(product);
                }
            }
        } else {
            System.out.println(response.getMessage());
        }
    }
	 
	 public static void main(String[] args) {
		    RetailCatalogApp app = new RetailCatalogApp();
		    app.start();
	}
}