package in.stack.eStore.model;

import java.time.LocalDateTime;

public class Order {
	
	private int orderId;
	private LocalDateTime orderDate;
	private OrederDetails orderDetails;
	private int status;
	private String customerName;
	
	public Order() {}
	
	public Order(int orderId, LocalDateTime orderDate, OrederDetails orderDetails, int orderStatus,
			String customerName) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderDetails = orderDetails;
		this.status = orderStatus;
		this.customerName = customerName;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public OrederDetails getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(OrederDetails orderDetails) {
		this.orderDetails = orderDetails;
	}
	public int getOrderStatus() {
		return status;
	}
	public void setOrderStatus(int orderStatus) {
		this.status = orderStatus;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", orderDetails=" + orderDetails
				+ ", orderStatus=" + status + ", customerName=" + customerName + "]";
	}	
	
}
