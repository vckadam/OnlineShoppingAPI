package org.vckadam.api.onlineshopping.model.supplier;

import java.util.List;


public class ProductsBySupplier {
	private Supplier supplier;
	private List<SupplierProduct> supProds;
	public ProductsBySupplier(Supplier supplier, List<SupplierProduct> supProds) {
		super();
		this.supplier = supplier;
		this.supProds = supProds;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public List<SupplierProduct> getSupplierProduct() {
		return supProds;
	}
	public void setSupplierProduct(List<SupplierProduct> supProds) {
		this.supProds = supProds;
	}
	
}
