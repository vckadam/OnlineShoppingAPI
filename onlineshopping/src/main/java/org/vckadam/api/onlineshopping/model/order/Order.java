package org.vckadam.api.onlineshopping.model.order;

import java.util.Date;

public class Order {
	private final Long orderId, userId;
	private Double payment;
	private Date processDate;
	public Order(Long orderId, Long userId, Double payment, Date processDate) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.payment = payment;
		this.processDate = processDate;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	public Long getOrderId() {
		return orderId;
	}
	public Long getUserId() {
		return userId;
	}
	
}
