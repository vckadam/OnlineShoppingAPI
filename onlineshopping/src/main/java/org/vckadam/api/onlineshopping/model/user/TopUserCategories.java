package org.vckadam.api.onlineshopping.model.user;

import java.util.List;

import org.vckadam.api.onlineshopping.model.category.Category;

public class TopUserCategories {
	private User user;
	private List<Category> categories;
	public TopUserCategories(User user, List<Category> categories) {
		super();
		this.user = user;
		this.categories = categories;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
