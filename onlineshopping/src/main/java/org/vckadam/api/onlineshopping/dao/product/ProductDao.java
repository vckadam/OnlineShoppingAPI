package org.vckadam.api.onlineshopping.dao.product;

import java.util.List;

import org.vckadam.api.onlineshopping.model.product.Product;

public interface ProductDao {
	List<Product> getAllProducts();
}
