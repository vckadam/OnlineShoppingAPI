package org.vckadam.api.onlineshopping.model.user;

public class User {
	private final Long userId;
	private String userFirstName, lastName, address;
	private Long phoneNumber;
	
	public User(Long userId, String userFirstName, String lastName, String address, Long phoneNumber) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Long getUserId() {
		return userId;
	}
	
	
}
