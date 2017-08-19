package org.vckadam.api.onlineshopping.model.order;

public class ItemOrder {
	private final Long itemId, productId;
	private Long quantity;
	private Double unitPrice;
	public ItemOrder(Long itemId, Long productId, Long quantity, Double unitPrice) {
		super();
		this.itemId = itemId;
		this.productId = productId;
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
	public Long getItemId() {
		return itemId;
	}
	public Long getProductId() {
		return productId;
	}
	
}
