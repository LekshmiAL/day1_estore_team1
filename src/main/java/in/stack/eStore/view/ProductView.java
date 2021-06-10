package in.stack.eStore.view;

import java.util.List;
import java.util.Scanner;

import in.stack.eStore.controller.EstoreController;
import in.stack.eStore.model.Product;

public class ProductView {

	Scanner inputScanner = null;
	private EstoreController estoreController = new EstoreController();
	
	/*
	 *  home options 
	 */
	public void homeMenu() {
		inputScanner = new Scanner(System.in);
		System.out.println("1. ADMIN");
		System.out.println("2. E-Store");
		System.out.print(" ENTER CHOICE : ");
		switch (inputScanner.nextInt()) {
		case 1: showAdminPage();
				break;
		case 2: goToEstore();
				break;
		default: System.out.println("enter correct choice");
		         break;
		}
	}
	public void showAdminPage() {
		System.out.println("-----WELCOME TO INVENTORY MANAGEMENT SYSTEM-----");
		System.out.println("----------PRODUCTS-----------");
		System.out.println("1. VIEW ");
		System.out.println("2. ADD ");
		System.out.println("3. UPDATE ");
		System.out.println("0. back ");
		System.out.print(" ENTER CHOICE : ");
		switch (inputScanner.nextInt()) {
		case 0: homeMenu();
				break;
		case 1: callOption("view");
				break;
		case 2: callOption("add");
				break;
		case 3: callOption("update");
				break;
		default : System.out.println("enter correct choice");
        		  break;
		}
	}



	public void callOption(String option) {
		if(option.equals("view")) {
			fetchAndDisplayProducts();
		}else if(option.equals("add")) {
			insertNewProduct();
		}else if(option.equals("update")) {
			updateProduct();
		}
		System.out.println("0. back ");
		if(inputScanner.nextInt()==0) {
			showAdminPage();
		}
	}

	public void fetchAndDisplayProducts() {
		List<Product> productList = estoreController.fetchProducts();
		if(productList == null || productList.isEmpty()) {
			System.out.println("NO PRODUCTS AVAILABLE");
		}else {
			productList.stream().forEach(product -> System.out.println(product.getProdutId()+". "+product.getProductName()+"     Rs "+product.getPrice()));
		}
	}
	
	public void insertNewProduct() {
		Product newProduct = new Product();
		System.out.println("enter product name :");
		newProduct.setProductName(inputScanner.next());
		System.out.println("enter product price");
		newProduct.setPrice(inputScanner.nextDouble()); 
		System.out.print("enter quantity : ");
		newProduct.setQuantity(inputScanner.nextInt());
		Product addedProduct = estoreController.addProduct(newProduct);
		if(addedProduct == null) {
			System.out.println("No product added to inventory ");
		}else {
			System.out.println(addedProduct.getQuantity()+" quantities of product "+addedProduct.getProductName()+" added to inventory");
		}
	}
	
	public void updateProduct() {
		fetchAndDisplayProducts();
		System.out.println("enter the Id of the product for updation");
		int id = inputScanner.nextInt();
		Product product = estoreController.getProductById(id);
		System.out.println("Product Details");
		System.out.println("Product Name : "+product.getProductName());
		System.out.println("Product Price : "+product.getPrice());
		System.out.println("Product Quantity : "+product.getQuantity());
		System.out.println("1. update product");
		System.out.println("2. delete product");
		System.out.println("enter updation choice");
		switch(inputScanner.nextInt()) {
		 case 1: modifyProductDetails(product);
		 		break;
		 case 2 : deleteProduct(id);
		 		break;
		}
		
	}
	
	
	public void modifyProductDetails(Product product) {
		
		System.out.println(" enter 1 to change price\n enter 2 to add/subtract quantitites\n enter 3 to change both");
		int choice = inputScanner.nextInt();
		if(choice == 1) {
		  System.out.println("enter new price : ");
		  product.setPrice(inputScanner.nextDouble());
		          
		}else if(choice == 2) {
			System.out.println("enter quantity for addition(+value)/subtraction(-value)");
			product.setQuantity(product.getQuantity()+inputScanner.nextInt());
		}else if(choice == 3) {
			 System.out.println("enter new price : ");
			 product.setPrice(inputScanner.nextDouble());
			 System.out.println("enter quantity for addition(+value)/subtraction(-value)");
			 product.setQuantity(product.getQuantity()+inputScanner.nextInt());
		}
		Product updatedProduct = estoreController.updateProduct(product);
		if(updatedProduct == null) {
			System.out.println("Not updated");
		}else {
			System.out.println(updatedProduct.getProductName()+" updated");
		}
	}
	public void deleteProduct(int id) {
		boolean isdeleted = estoreController.deleteProduct(id);
		if(isdeleted) {
			System.out.println("Product deleted");
		}else{
			System.out.println("Product not deleted");
		}
		
	}
	public void goToEstore() {
		fetchAndDisplayProducts();
	}
	
}
