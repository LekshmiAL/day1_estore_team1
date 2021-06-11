package in.stack.eStore.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.stack.eStore.model.Order;

public class OrderRepository implements OrderRepoInterface {

	DBConnection dbCon = new DBConnection();
	Connection con = dbCon.getConnection();

	@Override
	public void addOrder(Order order) throws SQLException {

		String query = " insert into orders (order_id, order_status, customer_name, order_date)"
				+ " values (?, ?, ?, curdate())";

		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setInt(1, order.getOrderId());
		preparedStmt.setInt(2, order.getOrderStatus());
		preparedStmt.setString(3, order.getCustomerName());
		preparedStmt.execute();
		con.close();

	}

}
