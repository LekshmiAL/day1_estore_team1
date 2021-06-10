package in.stack.eStore.controller;

import java.sql.SQLException;
import java.util.List;

import in.stack.eStore.model.Order;
import in.stack.eStore.model.Product;
import in.stack.eStore.service.CheckOutService;
import in.stack.eStore.service.ProductService;

public class EstoreController {
	/*
	 * productService
	 */
	private ProductService productService = null;
	
	public ProductService getProductService() {
		if(productService == null) {
			productService = new ProductService();
		}
		return productService;
	}
			
	public List<Product> fetchProducts() {
		List<Product> productlist = null;
		try {
			productlist =  getProductService().getAllProducts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productlist;
	}

	public Product addProduct(Product product) {
		Product addedProduct = null;
		try {
			addedProduct = getProductService().addProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addedProduct;
	}

	public Product getProductById(int id) {
		Product product = null;
		try {
			product = getProductService().getProductById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public Product updateProduct(Product product) {
		Product updProduct = null;
		try {
			updProduct = getProductService().updateProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updProduct;
	}

	public boolean deleteProduct(int id) {
		boolean isDeleted = false;
		try {
			isDeleted = getProductService().deleteProduct(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDeleted;
	}
	
	/*
	 * public Product getProductById(int productId){ return
	 * productService.getProductById(productId); }
	 */
	
	public void sendOrderDetails(Order order){
		CheckOutService checkOutService = new CheckOutService();
		try {
			checkOutService.createOrder(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
