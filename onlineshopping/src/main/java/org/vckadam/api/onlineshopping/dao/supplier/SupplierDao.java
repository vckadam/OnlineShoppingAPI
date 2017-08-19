package org.vckadam.api.onlineshopping.dao.supplier;

import java.util.List;

import org.vckadam.api.onlineshopping.model.supplier.Supplier;
import org.vckadam.api.onlineshopping.model.supplier.SupplierProduct;

public interface SupplierDao {
	List<Supplier> getAllSupplier();
	List<SupplierProduct> getAllSupplierProduct();
}
