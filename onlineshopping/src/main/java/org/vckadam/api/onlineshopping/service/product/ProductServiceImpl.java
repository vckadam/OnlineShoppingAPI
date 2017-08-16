package org.vckadam.api.onlineshopping.service.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.vckadam.api.onlineshopping.dao.category.CategoryDao;
import org.vckadam.api.onlineshopping.dao.category.CategoryDaoImpl;
import org.vckadam.api.onlineshopping.dao.product.ProductDao;
import org.vckadam.api.onlineshopping.dao.product.ProductDaoImpl;
import org.vckadam.api.onlineshopping.dao.user.UserDao;
import org.vckadam.api.onlineshopping.dao.user.UserDaoImpl;
import org.vckadam.api.onlineshopping.dao.userhistory.UserHistoryDao;
import org.vckadam.api.onlineshopping.dao.userhistory.UserHistoryDaoImpl;
import org.vckadam.api.onlineshopping.model.category.Category;
import org.vckadam.api.onlineshopping.model.category.ProductsInCategory;
import org.vckadam.api.onlineshopping.model.product.Product;
import org.vckadam.api.onlineshopping.model.product.ProductPurchasedByUser;
import org.vckadam.api.onlineshopping.model.product.SuggestedProducts;
import org.vckadam.api.onlineshopping.model.product.TopProductsInCategory;
import org.vckadam.api.onlineshopping.model.user.TopUserCategories;
import org.vckadam.api.onlineshopping.model.user.User;
import org.vckadam.api.onlineshopping.model.user.UserHistory;

public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;
	private UserHistoryDao userHistoryDao;
	private UserDao userDao;
	private CategoryDao categoryDao;
	
	private static final int TOPCATCOUNT = 5, TOPPRODCOUNT = 5;
	
	public ProductServiceImpl() {
		this.productDao = new ProductDaoImpl();
		this.userHistoryDao = new UserHistoryDaoImpl();
		this.userDao = new UserDaoImpl();
		this.categoryDao = new CategoryDaoImpl();
	}
	public List<ProductPurchasedByUser> getPurchasedProducts() {
		List<Product> products = this.productDao.getAllProducts();
		List<UserHistory> userHistory = this.userHistoryDao.getAllUserHistory();
		List<User> users = this.userDao.getAllUser();
		return getPurchasedProducts(products, userHistory, users);
	}
	
	public List<TopUserCategories> getTopUserCategories() {
		List<ProductPurchasedByUser> prodsByUser = getPurchasedProducts();
		List<Category> categories = this.categoryDao.getAllCategory();
		return getTopUserCategories(prodsByUser, categories);
	}
	
	public List<TopUserCategories> getTopUserCategories(List<ProductPurchasedByUser> prodsByUser, List<Category> categories) {
		if(prodsByUser == null || categories == null)
			throw new IllegalArgumentException("Illegal Argument");
		List<TopUserCategories> topUserCatList = new ArrayList<TopUserCategories>();
		Map<Long,Category> catMap = new HashMap<Long,Category>();
		for(Category cat : categories) {
			if(cat != null) {
				putEntryInMap(catMap,cat.getCategoryId(),cat);
			}
		}
		for(ProductPurchasedByUser ele : prodsByUser) {
			if(ele != null) {
				User curUs = ele.getUser();
				List<Product> prods = ele.getProducts();
				if(curUs != null && prods != null) {
					List<Long> topCatIds = getTopCatIds(prods);
					List<Category> curTopCur = null;
					for(Long catId : topCatIds) {
						Category curCat = catMap.get(catId);
						if(curCat != null) {
							if(curTopCur == null)
								curTopCur = new ArrayList<Category>();
							curTopCur.add(curCat);
						}
					}
					if(curTopCur != null) {
						topUserCatList.add(new TopUserCategories(curUs,curTopCur));
					}
				}
			}
		}
		return topUserCatList;
	}
	
	public List<Long> getTopCatIds(List<Product> prods) {
		List<Long> catIds = new ArrayList<Long>();
		Map<Long,Integer> catCountMap = new HashMap<Long,Integer>();
		int max = 0;
		for(Product prod : prods) {
			if(prod != null) {
				Long key;
				int val;
				catCountMap.put((key = prod.getCategoryId()),(val = catCountMap.getOrDefault(key, 0)+1));
				max = Math.max(max, val);
			}
		}
		Long[] count = new Long[max+1];
		for(Long key : catCountMap.keySet()) {
			int val = catCountMap.get(key);
			count[val] = key;
		}
		for(int i = max; i >= 0; i--) {
			if(count[i] != null) {
				catIds.add(count[i]);
			}
		}
		return catIds;
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
		List<TopProductsInCategory> topProdsInCat = getTopProductsInCategory();
		List<TopUserCategories> topUserCats = getTopUserCategories();
		return getSuggestProducts(topProdsInCat, topUserCats);
	}
	
	public List<SuggestedProducts> getSuggestProducts(List<TopProductsInCategory> topProdsInCat, List<TopUserCategories> topUserCats) {
		if(topProdsInCat == null || topUserCats == null)
			throw new IllegalArgumentException("Illegal Argument Exception");
		Map<Long,List<Product>> catIdToProds = new HashMap<Long,List<Product>>();
		List<SuggestedProducts> suggProdList = new ArrayList<SuggestedProducts>();
		for(TopProductsInCategory ele : topProdsInCat) {
			if(ele != null) {
				Category cat = ele.getCategory();
				List<Product> prods = ele.getProducts();
				if(cat != null && prods != null) {
					catIdToProds.put(cat.getCategoryId(), prods);
				}
			}
		}
		for(TopUserCategories ele : topUserCats) {
			if(ele != null) {
				User user = ele.getUser();
				List<Category> cats = ele.getCategories();
				if(user != null && cats != null) {
					List<Product> prods = new ArrayList<Product>();
					for(int i = 0; i < Math.min(TOPCATCOUNT, cats.size()); i++) {
						prods.add(getProducts(cats.get(i).getCategoryId(),catIdToProds));
					}
					suggProdList.add(new SuggestedProducts(user,prods));
				}
			}
		}
		return suggProdList;
	}
	
	public List<ProductsInCategory> getProductsInCategory() {
		List<Product> prods = this.productDao.getAllProducts();
		List<Category> cats = this.categoryDao.getAllCategory();
		return getProductsInCategory(prods, cats);
	}
	
	public List<TopProductsInCategory> getTopProductsInCategory() {
		List<ProductsInCategory> productsInCategory = this.getProductsInCategory();
		List<UserHistory> userHistory = this.userHistoryDao.getAllUserHistory();
		return this.getTopProductsInCategory(productsInCategory,userHistory);
	}
	
	public List<TopProductsInCategory> getTopProductsInCategory(List<ProductsInCategory> productsInCategory, List<UserHistory> userHistory) {
		if(productsInCategory == null || userHistory == null)
			throw new IllegalArgumentException("Illegal Argument");
		Map<Long,Long> productCount  = new HashMap<Long,Long>();
		List<TopProductsInCategory> topProdCatList = new ArrayList<TopProductsInCategory>();
		Long max = 0L;
		for(UserHistory ele : userHistory) {
			if(ele != null) {
				Long key;
				productCount.put((key = ele.getProductId()),productCount.getOrDefault(key, 0L)+ele.getQuantity());
				max = Math.max(max, productCount.get(key));
			}
		}
		for(ProductsInCategory ele : productsInCategory) {
			if(ele != null) {
				Category currCat = ele.getCategory();
				List<Product> currProds = ele.getProds();
				if(currCat != null && currProds != null) {
					List<Product> topProds = getTopProducts(productCount, currProds, max);
					if(topProds.size() > 0) 
						topProdCatList.add(new TopProductsInCategory(currCat,topProds));
				}
			}
		}
		return topProdCatList;
	}
	
	public List<Product> getTopProducts(Map<Long,Long> productCount, List<Product> currProds, Long max) {
		@SuppressWarnings("unchecked")
		List<Product>[] products = new List[max.intValue()+1];
		List<Product> topProducts = new ArrayList<Product>();
		for(Product prod : currProds) {
			if(prod != null) {
				Long curCount = productCount.get(prod.getProductId());
				if(curCount != null) {
					if(products[curCount.intValue()] == null)
						products[curCount.intValue()] = new ArrayList<Product>();
					products[curCount.intValue()].add(prod);
				}
			}
		}
		for(int i = max.intValue(); i >= 0; i--)
			if(products[i] != null)
				topProducts.addAll(products[i]);
		return topProducts;
	}
	
	public List<ProductsInCategory> getProductsInCategory(List<Product> prods, List<Category> cats) {
		if(prods == null || cats == null)
			throw new IllegalArgumentException("Illegal Argument");
		List<ProductsInCategory> prodInCatList = new ArrayList<ProductsInCategory>();
		Map<Long,List<Product>> catToProds = new HashMap<Long,List<Product>>();
		Map<Long,Category> catMap = new HashMap<Long,Category>();
		Set<Long> prodIds = new HashSet<Long>();
		for(Category cat : cats) {
			if(cat != null) {
				putEntryInMap(catMap,cat.getCategoryId(),cat);
			}
		}
		for(Product prod : prods) {
			if(prod != null && !prodIds.contains(prod.getProductId())) {
				prodIds.add(prod.getProductId());
				Long catId;
				if(!catToProds.containsKey((catId = prod.getCategoryId())))
					catToProds.put(catId, new ArrayList<Product>());
				catToProds.get(catId).add(prod);
			}
		}
		for(Long catId : catToProds.keySet()) {
			Category currCat = catMap.get(catId);
			if(currCat != null) {
				List<Product> currProds = catToProds.get(catId);
				prodInCatList.add(new ProductsInCategory(currCat,currProds));
			}
		}
		return prodInCatList;
	}
}
