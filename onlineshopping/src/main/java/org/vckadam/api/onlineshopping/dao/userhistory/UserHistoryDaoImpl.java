package org.vckadam.api.onlineshopping.dao.userhistory;

import java.util.ArrayList;
import java.util.List;

import org.vckadam.api.onlineshopping.model.user.UserHistory;

public class UserHistoryDaoImpl implements UserHistoryDao {

	private List<UserHistory> userHistory;
	
	public UserHistoryDaoImpl() {
		this.userHistory = new ArrayList<UserHistory>();
	}
	public List<UserHistory> getAllUserHistory() {
		return this.userHistory;
	}

}
