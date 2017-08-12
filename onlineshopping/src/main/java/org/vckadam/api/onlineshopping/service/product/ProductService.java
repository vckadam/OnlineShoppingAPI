package org.vckadam.api.onlineshopping.service.product;

import java.util.List;

import org.vckadam.api.onlineshopping.model.product.SuggestedProducts;

public interface ProductService {
	public List<SuggestedProducts> getSuggestProducts();
}
