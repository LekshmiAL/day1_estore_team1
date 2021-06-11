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

}
