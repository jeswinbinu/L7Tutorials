package com.litmus7.retailcatalog.dto;

public class Product {
    private int productId;
    private String name;
    private String category;
    private double price;
    private int stockQuantity;
    
    public Product() {
    	
    }
    
	/**
	 * @param productId
	 * @param name
	 * @param category
	 * @param price
	 * @param stockQuantity
	 */
	public Product(int productId, String name, String category, double price, int stockQuantity) {
		this.productId = productId;
		this.name = name;
		this.category = category;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the stockQuantity
	 */
	public int getStockQuantity() {
		return stockQuantity;
	}

	/**
	 * @param stockQuantity the stockQuantity to set
	 */
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	@Override
	public String toString() {
	    return " productId: " + productId + ",\n" +
	           " name: \"" + name + "\",\n" +
	           " category: \"" + category + "\",\n" +
	           " price: " + price + ",\n" +
	           " stockQuantity: " + stockQuantity + "\n" +
	           " ";
	}

    
}
