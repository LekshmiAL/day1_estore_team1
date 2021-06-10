package in.stack.eStore.repository;

import java.sql.SQLException;

import in.stack.eStore.model.Order;

public interface OrderRepoInterface {

	public void addOrder(Order order) throws SQLException;
	
	
}
