package in.stack.eStore.service;

import java.sql.SQLException;
import java.util.List;

import in.stack.eStore.model.Product;
import in.stack.eStore.repository.ProductRepository;

public class ProductService implements ProductServiceInterface {
	ProductRepository productRepository= null; 
	
	public ProductRepository getProductRepository() {
		if(productRepository == null) {
			productRepository = new ProductRepository();
		}
		return productRepository;
	}
	
	@Override
	public List<Product> getAllProducts() throws SQLException {
		return getProductRepository().getAllProducts();
	}

	@Override
	public Product getProductById(int id) throws SQLException {
		return getProductRepository().getProductById(id);
	}

	@Override
	public Product addProduct(Product p) throws SQLException {
		return getProductRepository().addProduct(p);
	}

	@Override
	public Product updateProduct(Product p) throws SQLException {
		return getProductRepository().updateProduct(p);
	}

	public boolean deleteProduct(int id) throws SQLException {
		return getProductRepository().deleteProduct(id);
	}

}
