package in.stack.eStore.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import in.stack.eStore.model.Order;
import in.stack.eStore.model.OrederDetails;
import in.stack.eStore.model.Product;
import in.stack.eStore.repository.DBConnection;
import in.stack.eStore.repository.ProductRepository;

public class CheckOutService {

	ProductRepository product = new ProductRepository();
	DBConnection dbCon = new DBConnection();
	Connection con = dbCon.getConnection();

	Statement stmt = null;
	ResultSet rs = null;
	
	public void createOrder(Order order) throws SQLException {
		
		updateOrderDetailsDB(order);
		updateOrderDB(order);
		displayDetails(order);
		//stmt.close();
		//rs.close();
		con.close();
	}

	public void displayDetails(Order order) {
		Date date = Date.from(order.getOrderDate().atZone(ZoneId.systemDefault()).toInstant());
		if (order != null) {
			System.out.println("\n**************************************************");
			System.out.println("                ORDER DETAILS                     ");
			System.out.println("**************************************************");
			System.out.println("Order ID:" + order.getOrderId());
			System.out.println("Customer Name:"+order.getCustomerName());
			System.out.println("Order Date:" + date);
			System.out.println("**************************************************");
			System.out.println("Pdt.Name\tUnitPrice\tQty\tTotal");
			OrederDetails orderDetails = order.getOrderDetails();
			List<Product> productsList = orderDetails.getProducts();

			productsList.forEach(product -> System.out.println(product.getProductName() + "\t\t" + product.getPrice() + "\t\t"
                    + product.getQuantity() + "\t" + product.getPrice() * product.getQuantity()));
			int total = 0;
			for(Product product : productsList) {
				total +=product.getPrice() * product.getQuantity();
			}
			System.out.println("**************************************************");
			System.out.println("Total = "+total);
			System.out.println("Discount = "+orderDetails.getDiscount());
			System.out.println("Payable = "+(total-orderDetails.getDiscount()));
			System.out.println("**************************************************");
		}
	}
	
	public void updateOrderDetailsDB(Order order) throws SQLException {
				
		String query = " insert into orders (order_id, order_status, customer_name, order_date)" + " values (?, ?, ?, curdate())";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
			try {
				preparedStmt.setInt(1, order.getOrderId());
				preparedStmt.setInt(2, order.getOrderStatus());
				preparedStmt.setString(3, order.getCustomerName());
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		preparedStmt.execute();
	}
	
public int getOrderIdFromTable(String customerName) throws SQLException 
{
	int order_id=0;
	String query="select max(order_id) from orders";
	stmt=con.createStatement();  
	ResultSet rs1=stmt.executeQuery(query);  
	
	while(rs1.next())  {
		System.out.println(rs1.getInt(1));
		order_id = rs1.getInt(1);
	}
	
	return order_id;
	
}
	
public void updateOrderDB(Order order) throws SQLException {
		
		int discount = 0;
		OrederDetails orderDetails = order.getOrderDetails();
		List<Product> productsList = orderDetails.getProducts();
		
		String query = " insert into order_details (order_id, product_id, discount)" + " values (?, ?, ?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		int order_id=getOrderIdFromTable(order.getCustomerName());
		System.out.println("order_id"+order_id);
		order.setOrderId(order_id);
		productsList.stream().forEach(product ->{
		
			try {
				preparedStmt.setInt(1, order_id);
				preparedStmt.setInt(2, product.getProdutId());
				preparedStmt.setInt(3, discount);
				preparedStmt.execute();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		});
		
		

	}

	
}
