package org.vckadam.api.onlineshopping.service.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.vckadam.api.onlineshopping.model.category.Category;
import org.vckadam.api.onlineshopping.model.category.ProductsInCategory;
import org.vckadam.api.onlineshopping.model.product.Product;
import org.vckadam.api.onlineshopping.model.product.ProductPurchasedByUser;
import org.vckadam.api.onlineshopping.model.user.TopUserCategories;
import org.vckadam.api.onlineshopping.model.user.User;
import org.vckadam.api.onlineshopping.model.user.UserHistory;

import junit.framework.TestCase;

public class ProductServiceImplTest extends TestCase {

	private ProductServiceImpl productServiceImpl;
	
	protected void setUp() throws Exception {
		super.setUp();
		this.productServiceImpl = new ProductServiceImpl();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		this.productServiceImpl = null;
	}

	@Test
	public void testGetPurchasedProducts_BasicScenario() {
		Long[] userIds = {1L,2L,3L};
		List<User> users = prepUserListForGetPurchasedProducts(userIds);
		Long[] prodIds = {1L,2L,3L};
		List<Product> prods = prepProdListForGetPurchasedProducts(prodIds);
		Long[][] userProdIds = {{1L,1L},{1L,3L},{2L,2L},{3L,1L}};
		List<UserHistory> userHis = prepHistListForGetPurchasedProducts(userProdIds);
		List<ProductPurchasedByUser> prodPurByUser = this.productServiceImpl.getPurchasedProducts(prods, userHis, users);
		Map<Long,List<Long>> userToProdAct = prepActualMapForGetPurchasedProducts(prodPurByUser);
		
		Object[][] userProdsIdExp = {{1L,Arrays.asList(1L,3L)},{2L,Arrays.asList(2L)},{3L,Arrays.asList(1L)}};
		Map<Long,List<Long>> userToProdExp = prepExpectedMapForGetPurchasedProducts(userProdsIdExp);
		
		assertEquals(userProdsIdExp.length, prodPurByUser.size());
		assertEquals(userToProdExp, userToProdAct);
	}
	
	@Test
	public void testGetPurchasedProducts_ExtraUser() {
		Long[] userIds = {1L,2L,3L,4L}; //4L user is extra
		List<User> users = prepUserListForGetPurchasedProducts(userIds);
		Long[] prodIds = {1L,2L,3L};
		List<Product> prods = prepProdListForGetPurchasedProducts(prodIds);
		Long[][] userProdIds = {{1L,1L},{1L,3L},{2L,2L},{3L,1L}};
		List<UserHistory> userHis = prepHistListForGetPurchasedProducts(userProdIds);
		List<ProductPurchasedByUser> prodPurByUser = this.productServiceImpl.getPurchasedProducts(prods, userHis, users);
		Map<Long,List<Long>> userToProdAct = prepActualMapForGetPurchasedProducts(prodPurByUser);
		
		Object[][] userProdsIdExp = {{1L,Arrays.asList(1L,3L)},{2L,Arrays.asList(2L)},{3L,Arrays.asList(1L)}};
		Map<Long,List<Long>> userToProdExp = prepExpectedMapForGetPurchasedProducts(userProdsIdExp);
		
		assertEquals(userProdsIdExp.length, prodPurByUser.size());
		assertEquals(userToProdExp, userToProdAct);
	}
	
	@Test
	public void testGetPurchasedProducts_ExtraProduct() {
		Long[] userIds = {1L,2L,3L,4L}; 
		List<User> users = prepUserListForGetPurchasedProducts(userIds);
		Long[] prodIds = {1L,2L,3L,4L}; //4L is extra product
		List<Product> prods = prepProdListForGetPurchasedProducts(prodIds);
		Long[][] userProdIds = {{1L,1L},{1L,3L},{2L,2L},{3L,1L}};
		List<UserHistory> userHis = prepHistListForGetPurchasedProducts(userProdIds);
		List<ProductPurchasedByUser> prodPurByUser = this.productServiceImpl.getPurchasedProducts(prods, userHis, users);
		Map<Long,List<Long>> userToProdAct = prepActualMapForGetPurchasedProducts(prodPurByUser);
		
		Object[][] userProdsIdExp = {{1L,Arrays.asList(1L,3L)},{2L,Arrays.asList(2L)},{3L,Arrays.asList(1L)}};
		Map<Long,List<Long>> userToProdExp = prepExpectedMapForGetPurchasedProducts(userProdsIdExp);
		
		assertEquals(userProdsIdExp.length, prodPurByUser.size());
		assertEquals(userToProdExp, userToProdAct);
	}
	
	@Test
	public void testGetPurchasedProducts_ExtraUserHistory() {
		Long[] userIds = {1L,2L,3L,4L}; 
		List<User> users = prepUserListForGetPurchasedProducts(userIds);
		Long[] prodIds = {1L,2L,3L,4L}; //4L is extra product
		List<Product> prods = prepProdListForGetPurchasedProducts(prodIds);
		Long[][] userProdIds = {{1L,1L},{1L,3L},{2L,2L},{3L,1L},{5L,5L}}; //{5L,5L} is extra
		List<UserHistory> userHis = prepHistListForGetPurchasedProducts(userProdIds);
		List<ProductPurchasedByUser> prodPurByUser = this.productServiceImpl.getPurchasedProducts(prods, userHis, users);
		Map<Long,List<Long>> userToProdAct = prepActualMapForGetPurchasedProducts(prodPurByUser);
		
		Object[][] userProdsIdExp = {{1L,Arrays.asList(1L,3L)},{2L,Arrays.asList(2L)},{3L,Arrays.asList(1L)}};
		Map<Long,List<Long>> userToProdExp = prepExpectedMapForGetPurchasedProducts(userProdsIdExp);
		
		assertEquals(userProdsIdExp.length, prodPurByUser.size());
		assertEquals(userToProdExp, userToProdAct);
	}
	
	@Test
	public void testGetPurchasedProducts_NullUser() {
		Long[] userIds = {null,1L,2L,null,3L,4L}; 
		List<User> users = prepUserListForGetPurchasedProducts(userIds);
		Long[] prodIds = {1L,2L,3L,4L}; 
		List<Product> prods = prepProdListForGetPurchasedProducts(prodIds);
		Long[][] userProdIds = {{1L,1L},{1L,3L},{2L,2L},{3L,1L},{5L,5L}};
		List<UserHistory> userHis = prepHistListForGetPurchasedProducts(userProdIds);
		List<ProductPurchasedByUser> prodPurByUser = this.productServiceImpl.getPurchasedProducts(prods, userHis, users);
		Map<Long,List<Long>> userToProdAct = prepActualMapForGetPurchasedProducts(prodPurByUser);
		
		Object[][] userProdsIdExp = {{1L,Arrays.asList(1L,3L)},{2L,Arrays.asList(2L)},{3L,Arrays.asList(1L)}};
		Map<Long,List<Long>> userToProdExp = prepExpectedMapForGetPurchasedProducts(userProdsIdExp);
		
		assertEquals(userProdsIdExp.length, prodPurByUser.size());
		assertEquals(userToProdExp, userToProdAct);
	}
	
	@Test
	public void testGetPurchasedProducts_NullProduct() {
		Long[] userIds = {null,1L,2L,null,3L,4L}; 
		List<User> users = prepUserListForGetPurchasedProducts(userIds);
		Long[] prodIds = {1L,null,2L,3L,null,4L}; 
		List<Product> prods = prepProdListForGetPurchasedProducts(prodIds);
		Long[][] userProdIds = {{1L,1L},{1L,3L},{2L,2L},{3L,1L},{5L,5L}};
		List<UserHistory> userHis = prepHistListForGetPurchasedProducts(userProdIds);
		List<ProductPurchasedByUser> prodPurByUser = this.productServiceImpl.getPurchasedProducts(prods, userHis, users);
		Map<Long,List<Long>> userToProdAct = prepActualMapForGetPurchasedProducts(prodPurByUser);
		
		Object[][] userProdsIdExp = {{1L,Arrays.asList(1L,3L)},{2L,Arrays.asList(2L)},{3L,Arrays.asList(1L)}};
		Map<Long,List<Long>> userToProdExp = prepExpectedMapForGetPurchasedProducts(userProdsIdExp);
		
		assertEquals(userProdsIdExp.length, prodPurByUser.size());
		assertEquals(userToProdExp, userToProdAct);
	}
	
	@Test
	public void testGetPurchasedProducts_NullUserHistory() {
		Long[] userIds = {null,1L,2L,null,3L,4L}; 
		List<User> users = prepUserListForGetPurchasedProducts(userIds);
		Long[] prodIds = {1L,null,2L,3L,null,4L}; 
		List<Product> prods = prepProdListForGetPurchasedProducts(prodIds);
		Long[][] userProdIds = {{1L,1L},{1L,3L},null,{2L,2L},null,{3L,1L},{5L,5L}};
		List<UserHistory> userHis = prepHistListForGetPurchasedProducts(userProdIds);
		List<ProductPurchasedByUser> prodPurByUser = this.productServiceImpl.getPurchasedProducts(prods, userHis, users);
		Map<Long,List<Long>> userToProdAct = prepActualMapForGetPurchasedProducts(prodPurByUser);
		
		Object[][] userProdsIdExp = {{1L,Arrays.asList(1L,3L)},{2L,Arrays.asList(2L)},{3L,Arrays.asList(1L)}};
		Map<Long,List<Long>> userToProdExp = prepExpectedMapForGetPurchasedProducts(userProdsIdExp);
		
		assertEquals(userProdsIdExp.length, prodPurByUser.size());
		assertEquals(userToProdExp, userToProdAct);
	}
	
	@Test
	public void testGetPurchasedProducts_DuplicateUserHistory() {
		Long[] userIds = {null,1L,2L,null,3L,4L}; 
		List<User> users = prepUserListForGetPurchasedProducts(userIds);
		Long[] prodIds = {1L,null,2L,3L,null,4L}; 
		List<Product> prods = prepProdListForGetPurchasedProducts(prodIds);
		Long[][] userProdIds = {{1L,1L},{1L,1L},{1L,3L},null,{2L,2L},null,{3L,1L},{5L,5L}};
		List<UserHistory> userHis = prepHistListForGetPurchasedProducts(userProdIds);
		List<ProductPurchasedByUser> prodPurByUser = this.productServiceImpl.getPurchasedProducts(prods, userHis, users);
		Map<Long,List<Long>> userToProdAct = prepActualMapForGetPurchasedProducts(prodPurByUser);
		
		Object[][] userProdsIdExp = {{1L,Arrays.asList(1L,3L)},{2L,Arrays.asList(2L)},{3L,Arrays.asList(1L)}};
		Map<Long,List<Long>> userToProdExp = prepExpectedMapForGetPurchasedProducts(userProdsIdExp);
		
		assertEquals(userProdsIdExp.length, prodPurByUser.size());
		assertEquals(userToProdExp, userToProdAct);
	}
	
	private List<User> prepUserListForGetPurchasedProducts(Long[] userIds) {
		List<User> users = new ArrayList<User>();
		for(Long ele : userIds) {
			if(ele != null) {
				users.add(new User(ele, null, null, null, null));
			}
			else 
				users.add(null);
		}
		return users;
	}
	
	private List<Product> prepProdListForGetPurchasedProducts(Long[] prodIds) {
		List<Product> prods = new ArrayList<Product>();
		for(Long ele : prodIds) {
			if(ele != null) {
				prods.add(new Product(ele, null, null, null));
			}
			else 
				prods.add(null);
		}
		return prods;
	}

	private List<UserHistory> prepHistListForGetPurchasedProducts(Long[][] userProdIds) {
		List<UserHistory> hists = new ArrayList<UserHistory>();
		for(Long[] ele : userProdIds) {
			if(ele != null) {
				hists.add(new UserHistory(ele[0], ele[1], null, null, null));
			}
			else 
				hists.add(null);
		}
		return hists;
	}
	
	private Map<Long,List<Long>> prepActualMapForGetPurchasedProducts(List<ProductPurchasedByUser> prodPurByUser) {
		Map<Long,List<Long>> userProdsId = new HashMap<Long,List<Long>>();
		for(ProductPurchasedByUser ele : prodPurByUser) {
			if(ele != null) {
				User currUs = ele.getUser();
				List<Long> prodIds = new ArrayList<Long>();
				for(Product currProd : ele.getProducts()) {
					prodIds.add(currProd.getProductId());
				}
				userProdsId.put(currUs.getUserId(), prodIds);
			}
		}
		return userProdsId;
	}
	
	@SuppressWarnings("unchecked")
	private Map<Long,List<Long>> prepExpectedMapForGetPurchasedProducts(Object[][] userProdsIdExp) {
		Map<Long,List<Long>> userProdsId = new HashMap<Long,List<Long>>();
		for(Object[] ele : userProdsIdExp) {
			userProdsId.put((Long)ele[0], (List<Long>)ele[1]);
		}
		return userProdsId;
	}
	
	/*********************************************************************************************/
	
	@Test
	public void testGetTopCatIds() {
		Long[][] prodCatId = {{1L,1L},{2L,1L},{3L,2L}};
		List<Product> prods = creatProdListForGetTopCatIds(prodCatId);
		List<Long> actCatIds = this.productServiceImpl.getTopCatIds(prods);
		List<Long> expCatIds = new ArrayList<Long>(Arrays.asList(1L,2L));
		assertEquals(expCatIds, actCatIds);
	}
	
	private List<Product> creatProdListForGetTopCatIds(Long[][] prodCatIds) {
		List<Product> prods = new ArrayList<Product>();
		for(Long[] prodCatId : prodCatIds) {
			prods.add(new Product(prodCatId[0], null, prodCatId[1], null));
		}
		return prods;
	}
	
	/**********************************************************************************************/
	
	@Test
	public void testGetTopUserCategories() {
		Long[][] prodForU1 = {{1L,1L},{2L,1L},{3L,2L}};
		Long[][] prodForU2 = {{1L,2L},{2L,2L},{3L,1L}};
		Object[][] userProdIds = {{1L,prodForU1},{2L,prodForU2}};
		List<ProductPurchasedByUser> prodsByUser = prepProdsListForGetTopUserCategories(userProdIds);
		Long[] catIds = {1L,2L};
		List<Category> catList = prepCatListForGetTopUserCategories(catIds);
		List<TopUserCategories> userCatIdAct = this.productServiceImpl.getTopUserCategories(prodsByUser, catList);
		Map<Long,List<Long>> useCatIdsActMap = prepActualMapForGetTopUserCategories(userCatIdAct);
		
		Object[][] expUserCatIds = {{1L, Arrays.asList(1L,2L)},{2L,Arrays.asList(2L,1L)}};
		Map<Long,List<Long>> useCatIdsExpMap = prepExpectedMapForGetTopUserCategories(expUserCatIds);
		
		assertEquals(expUserCatIds.length, userCatIdAct.size());
		assertEquals(useCatIdsExpMap, useCatIdsActMap);
	}
	
	private List<ProductPurchasedByUser> prepProdsListForGetTopUserCategories(Object[][] userProdIds){
		List<ProductPurchasedByUser> prodsByUser = new ArrayList<ProductPurchasedByUser>();
		for(Object[] ele : userProdIds) {
			if(ele != null) {
				Long userId = (Long)ele[0];
				User user = null;
				if(userId != null)  {
					user = new User(userId,null,null,null,null);
				}
				Long[][] prodCatIds = (Long[][])ele[1];
				List<Product> prods = null;
				if(prodCatIds != null) {
					prods = new ArrayList<Product>();
					for(Long[] prodCatId : prodCatIds) {
						prods.add(new Product(prodCatId[0], null, prodCatId[1], null));
					}
				}
				prodsByUser.add(new ProductPurchasedByUser(user,prods));
			} 
			else 
				prodsByUser.add(null);
		}
		return prodsByUser;
	}
	
	private List<Category> prepCatListForGetTopUserCategories(Long[] catIds) {
		List<Category> cats = new ArrayList<Category>();
		for(Long catId : catIds) {
			if(catId != null)
				cats.add(new Category(catId, null, null));
			else 
				cats.add(null);
		}
		return cats;
	}
	
	private Map<Long,List<Long>> prepActualMapForGetTopUserCategories(List<TopUserCategories> userCatIdAct) {
		Map<Long,List<Long>> userCatIdMap = new HashMap<Long,List<Long>>();
		for(TopUserCategories ele : userCatIdAct) {
			Long userId = ele.getUser().getUserId();
			List<Long> catIds = new ArrayList<Long>();
			List<Category> currCats = ele.getCategories();
			for(Category cat : currCats) {
				catIds.add(cat.getCategoryId());
			}
			userCatIdMap.put(userId, catIds);
		}
		return userCatIdMap;
	}
	
	@SuppressWarnings("unchecked")
	private Map<Long,List<Long>> prepExpectedMapForGetTopUserCategories(Object[][] expUserCatIds) {
		Map<Long,List<Long>> userCatIdMap = new HashMap<Long,List<Long>>();
		for(Object[] ele : expUserCatIds) {
			userCatIdMap.put((Long)ele[0],(List<Long>)ele[1]);
		}
		return userCatIdMap;
	}
	
	/************************************************************************/
	
	@Test
	public void testGetProductsInCategory_BasicScenario() {
		Long[] catIds = {1L,2L,3L};
		List<Category> cats = prepCatListForGetTopUserCategories(catIds);
		Long[][] prodCatId = {{1L,1L},{2L,2L},{3L,3L},{4L,3L},{5L,2L},{6L,1L}};
		List<Product> prods = prepProdcts(prodCatId);
		List<ProductsInCategory> prodsInCat = this.productServiceImpl.getProductsInCategory(prods, cats);
		Map<Long,List<Long>> catToProdIdMapAct = prepActualMap(prodsInCat);
		
		Object[][] catToProdIdExp = {{1L,Arrays.asList(1L,6L)},{2L,Arrays.asList(2L,5L)},{3L,Arrays.asList(3L,4L)}};
		Map<Long,List<Long>> catToProdIdMapExp = prepExpectedMap(catToProdIdExp);
		
		assertEquals(catToProdIdExp.length, prodsInCat.size());
		assertEquals(catToProdIdMapExp, catToProdIdMapAct);
	}
	
	@Test
	public void testGetProductsInCategory_ExtraCategory() {
		Long[] catIds = {1L,2L,3L,4L};
		List<Category> cats = prepCatListForGetTopUserCategories(catIds);
		Long[][] prodCatId = {{1L,1L},{2L,2L},{3L,3L},{4L,3L},{5L,2L},{6L,1L}};
		List<Product> prods = prepProdcts(prodCatId);
		List<ProductsInCategory> prodsInCat = this.productServiceImpl.getProductsInCategory(prods, cats);
		Map<Long,List<Long>> catToProdIdMapAct = prepActualMap(prodsInCat);
		
		Object[][] catToProdIdExp = {{1L,Arrays.asList(1L,6L)},{2L,Arrays.asList(2L,5L)},{3L,Arrays.asList(3L,4L)}};
		Map<Long,List<Long>> catToProdIdMapExp = prepExpectedMap(catToProdIdExp);
		
		assertEquals(catToProdIdExp.length, prodsInCat.size());
		assertEquals(catToProdIdMapExp, catToProdIdMapAct);
	}
	
	@Test
	public void testGetProductsInCategory_ExtraProduct() {
		Long[] catIds = {1L,2L,3L,4L};
		List<Category> cats = prepCatListForGetTopUserCategories(catIds);
		Long[][] prodCatId = {{1L,1L},{2L,2L},{3L,3L},{4L,3L},{5L,2L},{6L,1L},{7L,9L}}; //9L invalid category
		List<Product> prods = prepProdcts(prodCatId);
		List<ProductsInCategory> prodsInCat = this.productServiceImpl.getProductsInCategory(prods, cats);
		Map<Long,List<Long>> catToProdIdMapAct = prepActualMap(prodsInCat);
		
		Object[][] catToProdIdExp = {{1L,Arrays.asList(1L,6L)},{2L,Arrays.asList(2L,5L)},{3L,Arrays.asList(3L,4L)}};
		Map<Long,List<Long>> catToProdIdMapExp = prepExpectedMap(catToProdIdExp);
		
		assertEquals(catToProdIdExp.length, prodsInCat.size());
		assertEquals(catToProdIdMapExp, catToProdIdMapAct);
	}
	
	@Test
	public void testGetProductsInCategory_DuplicateProduct() {
		Long[] catIds = {1L,2L,3L,4L};
		List<Category> cats = prepCatListForGetTopUserCategories(catIds);
		Long[][] prodCatId = {{1L,1L},{2L,2L},{3L,3L},{4L,3L},{5L,2L},{6L,1L},{6L,1L}}; 
		List<Product> prods = prepProdcts(prodCatId);
		List<ProductsInCategory> prodsInCat = this.productServiceImpl.getProductsInCategory(prods, cats);
		Map<Long,List<Long>> catToProdIdMapAct = prepActualMap(prodsInCat);
		
		Object[][] catToProdIdExp = {{1L,Arrays.asList(1L,6L)},{2L,Arrays.asList(2L,5L)},{3L,Arrays.asList(3L,4L)}};
		Map<Long,List<Long>> catToProdIdMapExp = prepExpectedMap(catToProdIdExp);
		
		assertEquals(catToProdIdExp.length, prodsInCat.size());
		assertEquals(catToProdIdMapExp, catToProdIdMapAct);
	}
	
	@Test
	public void testGetProductsInCategory_DuplicateCategory() {
		Long[] catIds = {1L,2L,3L,3L};
		List<Category> cats = prepCatListForGetTopUserCategories(catIds);
		Long[][] prodCatId = {{1L,1L},{2L,2L},{3L,3L},{4L,3L},{5L,2L},{6L,1L},{6L,1L}}; 
		List<Product> prods = prepProdcts(prodCatId);
		List<ProductsInCategory> prodsInCat = this.productServiceImpl.getProductsInCategory(prods, cats);
		Map<Long,List<Long>> catToProdIdMapAct = prepActualMap(prodsInCat);
		
		Object[][] catToProdIdExp = {{1L,Arrays.asList(1L,6L)},{2L,Arrays.asList(2L,5L)},{3L,Arrays.asList(3L,4L)}};
		Map<Long,List<Long>> catToProdIdMapExp = prepExpectedMap(catToProdIdExp);
		
		assertEquals(catToProdIdExp.length, prodsInCat.size());
		assertEquals(catToProdIdMapExp, catToProdIdMapAct);
	}
	
	@Test
	public void testGetProductsInCategory_NullCategory() {
		Long[] catIds = {1L,null,2L,3L,null,3L};
		List<Category> cats = prepCatListForGetTopUserCategories(catIds);
		Long[][] prodCatId = {{1L,1L},{2L,2L},{3L,3L},{4L,3L},{5L,2L},{6L,1L},{6L,1L}}; 
		List<Product> prods = prepProdcts(prodCatId);
		List<ProductsInCategory> prodsInCat = this.productServiceImpl.getProductsInCategory(prods, cats);
		Map<Long,List<Long>> catToProdIdMapAct = prepActualMap(prodsInCat);
		
		Object[][] catToProdIdExp = {{1L,Arrays.asList(1L,6L)},{2L,Arrays.asList(2L,5L)},{3L,Arrays.asList(3L,4L)}};
		Map<Long,List<Long>> catToProdIdMapExp = prepExpectedMap(catToProdIdExp);
		
		assertEquals(catToProdIdExp.length, prodsInCat.size());
		assertEquals(catToProdIdMapExp, catToProdIdMapAct);
	}
	
	@Test
	public void testGetProductsInCategory_NullProduct() {
		Long[] catIds = {1L,null,2L,3L,null,3L};
		List<Category> cats = prepCatListForGetTopUserCategories(catIds);
		Long[][] prodCatId = {{1L,1L},null,{2L,2L},{3L,3L},null,{4L,3L},{5L,2L},{6L,1L},{6L,1L}}; 
		List<Product> prods = prepProdcts(prodCatId);
		List<ProductsInCategory> prodsInCat = this.productServiceImpl.getProductsInCategory(prods, cats);
		Map<Long,List<Long>> catToProdIdMapAct = prepActualMap(prodsInCat);
		
		Object[][] catToProdIdExp = {{1L,Arrays.asList(1L,6L)},{2L,Arrays.asList(2L,5L)},{3L,Arrays.asList(3L,4L)}};
		Map<Long,List<Long>> catToProdIdMapExp = prepExpectedMap(catToProdIdExp);
		
		assertEquals(catToProdIdExp.length, prodsInCat.size());
		assertEquals(catToProdIdMapExp, catToProdIdMapAct);
	}
	
	private List<Product> prepProdcts(Long[][] prodCatId) {
		List<Product> prods = new ArrayList<Product>();
		for(Long[] ele : prodCatId) {
			if(ele != null) {
				prods.add(new Product(ele[0], null, ele[1], null));
			}
			else 
				prods.add(null);
		}
		return prods;
	}
	
	private Map<Long,List<Long>> prepActualMap(List<ProductsInCategory> prodsInCat) {
		Map<Long,List<Long>> catToProdIdMap = new HashMap<Long,List<Long>>();
		for(ProductsInCategory ele : prodsInCat) {
			Category cat = ele.getCategory();
			List<Product> prods = ele.getProds();
			List<Long> prodIds = new ArrayList<Long>();
			for(Product prod : prods) {
				prodIds.add(prod.getProductId());
			}
			catToProdIdMap.put(cat.getCategoryId(), prodIds);
		}
		return catToProdIdMap;
	}
	
	@SuppressWarnings("unchecked")
	private Map<Long,List<Long>> prepExpectedMap(Object[][] catToProdIdExp) {
		Map<Long,List<Long>> catToProdIdMap = new HashMap<Long,List<Long>>();
		for(Object[] ele : catToProdIdExp) {
			catToProdIdMap.put((Long)ele[0], (List<Long>)ele[1]);
		}
		return catToProdIdMap;
	}
}
