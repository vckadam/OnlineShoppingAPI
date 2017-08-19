package org.vckadam.api.onlineshopping.dao.supplier;

import java.util.ArrayList;
import java.util.List;

import org.vckadam.api.onlineshopping.model.supplier.Supplier;
import org.vckadam.api.onlineshopping.model.supplier.SupplierProduct;

public class SupplierDaoImpl implements SupplierDao {
	private List<Supplier> suppliers;
	private List<SupplierProduct> supplierProds;
	
	public SupplierDaoImpl() {
		this.suppliers = new ArrayList<Supplier>();
		this.supplierProds = new ArrayList<SupplierProduct>();
	}

	public List<Supplier> getAllSupplier() {
		return this.suppliers;
	}

	public List<SupplierProduct> getAllSupplierProduct() {
		return this.supplierProds;
	}

}
