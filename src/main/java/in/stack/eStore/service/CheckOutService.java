package in.stack.eStore.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
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
		
		updateOrderDetailsDB(order, order.getCustomerName());
		updateOrderDB(order);
		displayDetails(order);
		//stmt.close();
		//rs.close();
		con.close();
	}

	public void displayDetails(Order order) {
		if (order != null) {
			System.out.println("**************************************************");
			System.out.println("Order ID:" + order.getOrderId());
			System.out.println("Customer Name:"+order.getCustomerName());
			System.out.println("Order Date:" + order.getOrderDate());
			System.out.println("**************************************************");
			System.out.println("Pdt.Name\tUnitPrice\tQty\tTotal");
			OrederDetails orderDetails = order.getOrderDetails();
			List<Product> productsList = orderDetails.getProducts();

			productsList.forEach(product -> System.out.println(product.getProductName() + "\t" + product.getPrice() + "\t\t"
                    + product.getQuantity() + "\t" + product.getPrice() * product.getQuantity()));
			int total = 0;
			//productsList.forEach(product -> total+=product.getPrice() * product.getQuantity());
			for(Product product : productsList) {
				total +=product.getPrice() * product.getQuantity();
			}
			System.out.println("Total = "+total);
			System.out.println("Discount = "+orderDetails.getDiscount());
			System.out.println("Payable = "+(total-orderDetails.getDiscount()));
		}
	}
	
	public static Date convertLocalDateTimeToDateUsingTimestamp(LocalDateTime dateToConvert) {
	    return java.sql.Timestamp.valueOf(dateToConvert);
	}

	public void updateOrderDetailsDB(Order order, String customer) throws SQLException {
				
		Date date = convertLocalDateTimeToDateUsingTimestamp(order.getOrderDate());	
		String query = " insert into orders (order_id, order_status, customer_name, order_date)" + " values (?, ?, ?, curdate())";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
			try {
				preparedStmt.setInt(1, order.getOrderId());
				preparedStmt.setInt(2, order.getOrderStatus());
				preparedStmt.setString(3, order.getCustomerName());
				//preparedStmt.setDate(4, (java.sql.Date) date);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		
		preparedStmt.execute();

	}
	
public int getOrderIdFromTable(String customerName) throws SQLException 
{
	int order_id=0;
	String query="select order_id from orders where customer_name=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		try {
			preparedStmt.setString(1, customerName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs=null;
		try {
			rs = preparedStmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
				while(rs.next())
				{
				   order_id=rs.getInt(1); 
				   
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		order.setOrderId(order_id);
		productsList.stream().forEach(product ->{
		
			try {
				preparedStmt.setInt(1, order_id);
				preparedStmt.setInt(2, product.getProdutId());
				preparedStmt.setInt(3, discount);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		});
		
		preparedStmt.execute();

	}

	
}
