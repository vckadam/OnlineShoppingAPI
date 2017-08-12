package org.vckadam.api.onlineshopping.service.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.vckadam.api.onlineshopping.dao.product.ProductDao;
import org.vckadam.api.onlineshopping.dao.product.ProductDaoImpl;
import org.vckadam.api.onlineshopping.dao.user.UserDao;
import org.vckadam.api.onlineshopping.dao.user.UserDaoImpl;
import org.vckadam.api.onlineshopping.dao.userhistory.UserHistoryDao;
import org.vckadam.api.onlineshopping.dao.userhistory.UserHistoryDaoImpl;
import org.vckadam.api.onlineshopping.model.product.Product;
import org.vckadam.api.onlineshopping.model.product.ProductPurchasedByUser;
import org.vckadam.api.onlineshopping.model.product.SuggestedProducts;
import org.vckadam.api.onlineshopping.model.user.User;
import org.vckadam.api.onlineshopping.model.user.UserHistory;

public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;
	private UserHistoryDao userHistoryDao;
	private UserDao userDao;
	public ProductServiceImpl() {
		this.productDao = new ProductDaoImpl();
		this.userHistoryDao = new UserHistoryDaoImpl();
		this.userDao = new UserDaoImpl();
	}
	public List<ProductPurchasedByUser> getPurchasedProducts() {
		List<Product> products = this.productDao.getAllProducts();
		List<UserHistory> userHistory = this.userHistoryDao.getAllUserHistory();
		List<User> users = this.userDao.getAllUser();
		return getPurchasedProducts(products, userHistory, users);
	}
	
	public List<ProductPurchasedByUser> getPurchasedProducts(List<Product> products, List<UserHistory> userHistory, List<User> users) {
		if(products == null || userHistory == null || users == null)
			throw new IllegalArgumentException("Illegal Argument");
		List<ProductPurchasedByUser> purProdList = new ArrayList<ProductPurchasedByUser>();
		Map<Long,Set<Long>> userToProdsMap = new HashMap<Long,Set<Long>>();
		for(UserHistory ele : userHistory) {
			if(ele != null) {
				Long currProdId = ele.getProductId();
				Long currUserId = ele.getUserId();
				if(currProdId != null && currUserId != null)  {
					if(!userToProdsMap.containsKey(currUserId))
						userToProdsMap.put(currUserId, new HashSet<Long>());
					userToProdsMap.get(currUserId).add(currProdId);
				}
			}
		}
		Map<Long,User> userMap = new HashMap<Long,User>();
		for(User ele : users) {
			if(ele != null) {
				putEntryInMap(userMap,ele.getUserId(),ele);
			}
		}
		Map<Long,Product> prodMap = new HashMap<Long,Product>();
		for(Product ele : products) {
			if(ele != null) {
				putEntryInMap(prodMap,ele.getProductId(),ele);
			}
		}
		
		for(Long userId : userToProdsMap.keySet()) {
			User curUser = userMap.get(userId);
			if(curUser != null) {
				ProductPurchasedByUser currPurProds = new ProductPurchasedByUser(curUser, new ArrayList<Product>());
				Set<Long> currProdIds = userToProdsMap.get(userId);
				if(currProdIds != null) {
					for(Long currProdId : currProdIds) {
						if(currProdId != null) {
							Product currProd = prodMap.get(currProdId);
							if(currProd != null) {
								currPurProds.getProducts().add(currProd);
							}
						}
					}
					purProdList.add(currPurProds);
				}
			}
		}
		return purProdList;
	}
	
	public <K,V> void putEntryInMap(Map<K,V> map, K key, V val) {
		if(map != null && key != null && val != null) {
			if(!map.containsKey(key)) 
				map.put(key, val);
		}
	}
	public List<SuggestedProducts> getSuggestProducts() {
		return null;
	}
	
}
