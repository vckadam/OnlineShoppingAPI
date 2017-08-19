package org.vckadam.api.onlineshopping.model.supplier;

public class SupplierProduct {
	private final Long supplierId, productId;
	private Long stock;
	private int rating;
	public SupplierProduct(Long supplierId, Long productId, Long stock, int rating) {
		super();
		this.supplierId = supplierId;
		this.productId = productId;
		this.stock = stock;
		this.rating = rating;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public Long getProductId() {
		return productId;
	}
	
}
