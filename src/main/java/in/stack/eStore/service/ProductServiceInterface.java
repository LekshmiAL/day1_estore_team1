package in.stack.eStore.service;

import java.sql.SQLException;
import java.util.List;

import in.stack.eStore.model.Product;

public interface ProductServiceInterface {
	
	public List<Product> getAllProducts() throws SQLException;
	public Product getProductById(int id) throws SQLException;
	public Product addProduct(Product p) throws SQLException;
	public Product updateProduct(Product p) throws SQLException;
}
