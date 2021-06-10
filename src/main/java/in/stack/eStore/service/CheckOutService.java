package in.stack.eStore.service;

import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import in.stack.eStore.model.Order;
import in.stack.eStore.model.OrederDetails;
import in.stack.eStore.model.Product;
import in.stack.eStore.repository.OrderDetailsRepository;
import in.stack.eStore.repository.OrderRepository;

public class CheckOutService {

	OrderRepository orderRepo = new OrderRepository();
	OrderDetailsRepository orderDetailsRepo = new OrderDetailsRepository();
	
	public void createOrder(Order order) throws SQLException {

		updateOrderDB(order);
		updateOrderDetailsDB(order);
		displayDetails(order);
		
	}
	
	public void updateOrderDB(Order order) throws SQLException {
		
		orderRepo.addOrder(order);
		
	}
	
	public void updateOrderDetailsDB(Order order) throws SQLException {
		
		orderDetailsRepo.addOrderDetails(order);
	}

	public void displayDetails(Order order) {
		Date date = Date.from(order.getOrderDate().atZone(ZoneId.systemDefault()).toInstant());
		if (order != null) {
			System.out.println("\n**************************************************");
			System.out.println("                ORDER DETAILS                     ");
			System.out.println("**************************************************");
			System.out.println("Order ID:" + order.getOrderId());
			System.out.println("Customer Name:" + order.getCustomerName());
			System.out.println("Order Date:" + date);
			System.out.println("**************************************************");
			System.out.println("Pdt.Name\tUnitPrice\tQty\tTotal");
			OrederDetails orderDetails = order.getOrderDetails();
			List<Product> productsList = orderDetails.getProducts();

			productsList.forEach(product -> System.out.println(product.getProductName() + "\t\t" + product.getPrice()
					+ "\t\t" + product.getQuantity() + "\t" + product.getPrice() * product.getQuantity()));
			double total = 0;
			for (Product product : productsList) {
				total += product.getPrice() * product.getQuantity();
			}
			System.out.println("**************************************************");
			System.out.println("Total\t\t= " + total);
			System.out.println("Discount\t= " + orderDetails.getDiscount());
			System.out.println("Payable\t\t= " + (total - orderDetails.getDiscount()));
			System.out.println("**************************************************");
		}
	}

	//public void updateOrderDB(Order order) throws SQLException {
		
	//	orderRepo.addOrder(order);

	/*	String query = " insert into orders (order_id, order_status, customer_name, order_date)"
				+ " values (?, ?, ?, curdate())";
		PreparedStatement preparedStmt = con.prepareStatement(query);

		try {
			preparedStmt.setInt(1, order.getOrderId());
			preparedStmt.setInt(2, order.getOrderStatus());
			preparedStmt.setString(3, order.getCustomerName());
		} catch (SQLException e) {

			e.printStackTrace();
		}

		preparedStmt.execute();*/
	//}
	
	/*
	public int getOrderIdFromTable() throws SQLException {
		int order_id = 0;
		String query = "select max(order_id) from orders";
		stmt = con.createStatement();
		ResultSet rs1 = stmt.executeQuery(query);

		while (rs1.next()) {
			
			order_id = rs1.getInt(1);
		}

		return order_id;

	}*/
	

	//public void updateOrderDetailsDB(Order order) throws SQLException {
		
	//	orderDetailsRepo.addOrderDetails(order);

		/*int discount = 0;
		OrederDetails orderDetails = order.getOrderDetails();
		List<Product> productsList = orderDetails.getProducts();

		String query = " insert into order_details (order_id, product_id, discount)" + " values (?, ?, ?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		int order_id = getOrderIdFromTable();
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
		});*/

//	}

}
