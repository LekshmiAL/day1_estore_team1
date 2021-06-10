package in.stack.eStore.main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import in.stack.eStore.controller.EstoreController;
import in.stack.eStore.model.Order;
import in.stack.eStore.model.OrederDetails;
import in.stack.eStore.model.Product;
import in.stack.eStore.view.ProductView;

public class EstoreApp {
	
	public static void main(String[] args) {
		//product flow
		ProductView productView = new ProductView();
		productView.homeMenu();
		
		EstoreApp eStoreApp = new  EstoreApp();
		EstoreController estoreController = new EstoreController();
		Order order= eStoreApp.getOrderFromUser();
		estoreController.sendOrderDetails(order);

	}
	public Order getOrderFromUser()
	{
		Order order = new Order();
		OrederDetails orderDetails = new OrederDetails();
		EstoreController estoreController = new EstoreController();
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter customer name ");
		String customerName = scan.nextLine(); 
		List<Product> productsSelected = new ArrayList<Product>();
		int choice=0;
		do {
			int productId=0;
			int quantity=0;
			Product product = new Product();
			System.out.println("Please select a product ID");
			if(scan.hasNextInt())
				productId=scan.nextInt();
			System.out.println("Please select the quantity");
			if(scan.hasNextInt())
				{
				quantity=scan.nextInt();
				Product productById=estoreController.getProductById(productId);
				if(quantity<productById.getQuantity())
				{
					product.setQuantity(quantity);
					product.setPrice(productById.getPrice());
					product.setProductName(productById.getProductName());
					product.setProdutId(productId);
					product.setIsactive(true);
					productsSelected.add(product);
					System.out.println("Please enter 1 for continue shopping");
					if(scan.hasNextInt())
						choice=scan.nextInt();
				}else
				{
					System.out.println("Availabe quantity  for "+productById.getProductName() +"is "+productById.getQuantity());
					System.out.println(" Please select again");
					choice=1;
				}
				}
		}while(choice==1);
		
		orderDetails.setProducts(productsSelected);
		order.setOrderDetails(orderDetails);
		order.setCustomerName(customerName);
		order.setOrderStatus(1);
		order.setOrderDate(LocalDateTime.now());
		scan.close();
		return order;
	}
}
