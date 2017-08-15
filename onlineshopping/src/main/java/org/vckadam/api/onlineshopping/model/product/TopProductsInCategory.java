package org.vckadam.api.onlineshopping.model.product;

import java.util.List;

import org.vckadam.api.onlineshopping.model.category.Category;

public class TopProductsInCategory {
	private Category category;
	private List<Product> products;
	public TopProductsInCategory(Category category, List<Product> products) {
		super();
		this.category = category;
		this.products = products;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
