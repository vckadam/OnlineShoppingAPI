package org.vckadam.api.onlineshopping.dao.user;

import java.util.ArrayList;
import java.util.List;

import org.vckadam.api.onlineshopping.model.user.User;

public class UserDaoImpl implements UserDao {

	private List<User> users;
	
	public UserDaoImpl() {
		this.users = new ArrayList<User>();
	}
	public List<User> getAllUser() {
		return this.users;
	}

}
