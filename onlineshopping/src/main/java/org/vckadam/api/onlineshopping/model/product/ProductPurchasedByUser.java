package org.vckadam.api.onlineshopping.model.product;

import java.util.List;

import org.vckadam.api.onlineshopping.model.user.User;

public class ProductPurchasedByUser {
	private User user;
	private List<Product> products;
	public ProductPurchasedByUser(User user, List<Product> products) {
		super();
		this.user = user;
		this.products = products;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
