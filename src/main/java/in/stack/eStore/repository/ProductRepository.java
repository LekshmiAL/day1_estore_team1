package in.stack.eStore.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.stack.eStore.model.Product;

public class ProductRepository implements ProductRepoInterFace{
	
	DBConnection dbConnection = new DBConnection();
	Statement statement= null;
	PreparedStatement preparedStatement = null;
	
	public Connection getDBConnection() {
		return dbConnection.getConnection();
	}
	
	// Get All Products 
	public List<Product> getAllProducts() throws SQLException{
		List<Product> productList = new ArrayList<>();
		statement =  getDBConnection().createStatement();
		String FETCH_PRODUCTS_QUERY = "select product_id,product_name,product_price from products where is_active = 'Y'";
		ResultSet rs = statement.executeQuery(FETCH_PRODUCTS_QUERY);
		 while(rs.next()){
			 Product product = new Product();
			 product.setProdutId(rs.getInt("product_id"));
			 product.setProductName(rs.getString("product_name"));
			 product.setPrice(rs.getDouble("product_price"));
			 productList.add(product);
		 }
		
		 return productList;
	}

	@Override
	public Product getProductById(int id) throws SQLException {
		Product product = new Product();
		String GET_PRODUCT_BY_ID = "select product_id,product_name,product_price,quantity from products where is_active = 'Y' and product_id= ?";
		preparedStatement =  getDBConnection().prepareStatement(GET_PRODUCT_BY_ID);
		preparedStatement.setDouble(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			 product.setProdutId(rs.getInt("product_id"));
			 product.setProductName(rs.getString("product_name"));
			 product.setPrice(rs.getDouble("product_price"));
			 product.setQuantity(rs.getInt("quantity"));
		 }
		return product;
	}

	@Override
	public Product addProduct(Product product) throws SQLException {
		Product addedProduct = new Product();
		String ADD_PRODUCT ="insert into products (product_name,product_price,quantity,is_active)\r\n"
				+ "values (?,?,?,?)";
		preparedStatement =  getDBConnection().prepareStatement(ADD_PRODUCT);
		preparedStatement.setString(1, product.getProductName());
		preparedStatement.setDouble(2, product.getPrice());
		preparedStatement.setInt(3, product.getQuantity());
		preparedStatement.setString(4, "Y");
		int updatedRowCount = preparedStatement.executeUpdate();
		if(updatedRowCount == 1) {
			String FETCH_PRODUCT = "select product_name,quantity from products where product_name = ? and is_active = 'Y'";
			preparedStatement =  getDBConnection().prepareStatement(FETCH_PRODUCT);
			preparedStatement.setString(1, product.getProductName());
			ResultSet rs = preparedStatement.executeQuery();
			 while(rs.next()){
				 addedProduct.setProductName(rs.getString("product_name"));
				 addedProduct.setQuantity(rs.getInt("quantity"));
			 }
		}
		return addedProduct;
		
	}

	@Override
	public Product updateProduct(Product product) throws SQLException {
		Product updProduct = null;
		String UPDATE_PRODUCT = "update products set product_price = ? , quantity = ? where product_id= ?";
		preparedStatement =  getDBConnection().prepareStatement(UPDATE_PRODUCT);
		preparedStatement.setDouble(1, product.getPrice());
		preparedStatement.setInt(2, product.getQuantity());
		preparedStatement.setInt(3, product.getProdutId());
		int updatedRowCount = preparedStatement.executeUpdate();
		if(updatedRowCount == 1) {
			updProduct = getProductById(product.getProdutId());
		}
		return updProduct;
	}

	public boolean deleteProduct(int id) throws SQLException {
		boolean isDeleted = false;
		String DELETE_QUERY = "update products set quantity = 0, is_active = 'N' where product_id= ?";
		preparedStatement =  getDBConnection().prepareStatement(DELETE_QUERY);
		preparedStatement.setInt(1, id);
		int updatedRowCount = preparedStatement.executeUpdate();
		if(updatedRowCount==1) {
			isDeleted = true;
		}else {
			isDeleted = false;
		}
		return isDeleted;
	}

	

}
