package org.vckadam.api.onlineshopping.model.category;

import java.util.List;

import org.vckadam.api.onlineshopping.model.product.Product;

public class ProductsInCategory {
	private Category category;
	private List<Product> prods;
	
	public ProductsInCategory(Category category, List<Product> prods) {
		super();
		this.category = category;
		this.prods = prods;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Product> getProds() {
		return prods;
	}

	public void setProds(List<Product> prods) {
		this.prods = prods;
	}
	
	
	
}
