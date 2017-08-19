package org.vckadam.api.onlineshopping.model.supplier;

public class Supplier {
	private final Long supplierId;
	private String name, address, city, country;
	public Supplier(Long supplierId, String name, String address, String city, String country) {
		super();
		this.supplierId = supplierId;
		this.name = name;
		this.address = address;
		this.city = city;
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	
}
