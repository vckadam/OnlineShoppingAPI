package org.vckadam.api.onlineshopping.model.product;

public class Product {
	
	private final Long productId;
	private String productName;
	private Long categoryId;
	private Double price;
	
	public Product(Long productId, String productName, Long categoryId, Double price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.categoryId = categoryId;
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getProductId() {
		return productId;
	}
	
}
