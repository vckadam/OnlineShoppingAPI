package org.vckadam.api.onlineshopping.model.user;

public class UserHistory {
	private final Long userId, productId, orderId;
	private Long quantity;
	private Double unitPrice;
	
	public UserHistory(Long userId, Long productId, Long orderId, Long quantity, Double unitPrice) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getProductId() {
		return productId;
	}

	public Long getOrderId() {
		return orderId;
	}
	
	
}
