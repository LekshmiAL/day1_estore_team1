package in.stack.eStore.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import in.stack.eStore.model.Order;
import in.stack.eStore.model.OrederDetails;
import in.stack.eStore.model.Product;

public class OrderDetailsRepository implements OrderDetailsRepoInterface {

	DBConnection dbCon = new DBConnection();
	Connection con = dbCon.getConnection();

	@Override
	public void addOrderDetails(Order order) throws SQLException {

		int discount = 0;
		OrederDetails orderDetails = order.getOrderDetails();
		List<Product> productsList = orderDetails.getProducts();
		String query = " insert into order_details (order_id, product_id, discount)" + " values (?, ?, ?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		int order_id = getOrderId();
		order.setOrderId(order_id);
		productsList.stream().forEach(product -> {

			try {
				preparedStmt.setInt(1, order_id);
				preparedStmt.setInt(2, product.getProdutId());
				preparedStmt.setInt(3, discount);
				preparedStmt.execute();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		});
		con.close();
	}

	@Override
	public int getOrderId() throws SQLException {

		int order_id = 0;
		String query = "select max(order_id) from orders";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {

			order_id = rs.getInt(1);
		}

		return order_id;
	}

}
