package org.vckadam.api.onlineshopping.dao.category;

import java.util.ArrayList;
import java.util.List;

import org.vckadam.api.onlineshopping.model.category.Category;

public class CategoryDaoImpl implements CategoryDao {

	private List<Category> categories;
	
	public CategoryDaoImpl() {
		this.categories = new ArrayList<Category>();
	}
	public List<Category> getAllCategory() {
		return this.categories;
	}

}
