package org.vckadam.api.onlineshopping.dao.product;

import java.util.ArrayList;
import java.util.List;

import org.vckadam.api.onlineshopping.model.product.Product;

public class ProductDaoImpl implements ProductDao {
	
	private List<Product> products;
	
	public ProductDaoImpl() {
		products = new ArrayList<Product>();
	}

	public List<Product> getAllProducts() {
		return this.products;
	}

}
