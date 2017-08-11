package org.vckadam.api.onlineshopping.dao.userhistory;

import java.util.List;

import org.vckadam.api.onlineshopping.model.user.UserHistory;

public interface UserHistoryDao {
	List<UserHistory> getAllUserHistory();
}
