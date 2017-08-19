package org.vckadam.api.onlineshopping.service.supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vckadam.api.onlineshopping.dao.supplier.SupplierDao;
import org.vckadam.api.onlineshopping.dao.supplier.SupplierDaoImpl;
import org.vckadam.api.onlineshopping.model.supplier.ProductsBySupplier;
import org.vckadam.api.onlineshopping.model.supplier.Supplier;
import org.vckadam.api.onlineshopping.model.supplier.SupplierProduct;

public class SupplierServiceImpl implements SupplierService {
	
	private SupplierDao supplierDao;

	public SupplierServiceImpl() {
		this.supplierDao = new SupplierDaoImpl();
	}

	public List<ProductsBySupplier> getProductsBySupplier() {
		List<Supplier> suppliers = this.supplierDao.getAllSupplier();
		List<SupplierProduct> supplierProduct = this.supplierDao.getAllSupplierProduct();
		return getProductsBySupplier(suppliers, supplierProduct);
	}
	
	public List<ProductsBySupplier> getProductsBySupplier(List<Supplier> suppliers, List<SupplierProduct> supplierProduct) {
		if(suppliers == null || supplierProduct == null)
			throw new IllegalArgumentException("Illegal Argument");
		Map<Long,Supplier> supplierMap = null;
		if(suppliers.size() > 0) {
			supplierMap = new HashMap<Long,Supplier>();
			for(Supplier supplier : suppliers) {
				if(supplier != null) {
					if(!supplierMap.containsKey(supplier.getSupplierId()))
						supplierMap.put(supplier.getSupplierId(), supplier);
				}
			}
		}
		Map<Long,List<SupplierProduct>> supIdToSupProds = null;
		if(supplierProduct.size() > 0) {
			supIdToSupProds = new HashMap<Long,List<SupplierProduct>>();
			for(SupplierProduct ele : supplierProduct) {
				if(ele != null) {
					if(!supIdToSupProds.containsKey(ele.getSupplierId())) 
						supIdToSupProds.put(ele.getSupplierId(), new ArrayList<SupplierProduct>());
					supIdToSupProds.get(ele.getSupplierId()).add(ele);
				}
			}
		}
		List<ProductsBySupplier> prodsBySup = null;
		for(Long key : supIdToSupProds.keySet()) {
			Supplier supplier = supplierMap.get(key);
			if(supplier != null) {
				if(prodsBySup == null)
					prodsBySup = new ArrayList<ProductsBySupplier>();
				prodsBySup.add(new ProductsBySupplier(supplier,supIdToSupProds.get(key)));
			}
		}
		return prodsBySup;
	}

}
