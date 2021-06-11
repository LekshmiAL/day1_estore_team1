package in.stack.eStore.repository;

import java.sql.SQLException;

import in.stack.eStore.model.Order;

public interface OrderDetailsRepoInterface {
	
	public void addOrderDetails(Order order) throws SQLException;
	public int getOrderId()throws SQLException;

}
