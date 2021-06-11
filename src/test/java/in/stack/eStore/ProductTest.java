package in.stack.eStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import in.stack.eStore.model.Product;
import in.stack.eStore.repository.ProductRepository;
import in.stack.eStore.service.ProductService;

//@RunWith attaches a runner with the test class to initialize the test data
@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ProductTest {
List<Product> productList = null;
	
	//@Mock annotation is used to create the mock object to be injected
	@Mock
	ProductRepository productRepo;
	
	//@InjectMocks annotation is used to create and inject the mock object
	@InjectMocks
	ProductService productService;
	
	@BeforeAll
	public void createProductList() {
		productList = new ArrayList<>();
		productList.add(new Product(1, "pen", 15, 10, true));
		productList.add(new Product(2, "pencil", 20, 5, true));
		productList.add(new Product(3, "book", 25, 45, true));
		productList.add(new Product(4, "mug", 10, 60, true));
	}
	

	@Test  
	public void viewProductListTest() throws SQLException {
		
		Mockito.when(productRepo.getAllProducts()).thenReturn(productList);
		List<Product> fetchPrdtList = productService.getAllProducts();
		assertNotNull(fetchPrdtList); 
		assertEquals(productList.size(), fetchPrdtList.size(),"product list length not equal" );
						
	}
	
	@Test
	public void addProductToInventory() throws SQLException {
		Product addProduct = new Product(5, "kettle", 35, 100, true);
		Mockito.when(productRepo.addProduct(addProduct)).thenReturn(addProduct);
		Product returnProduct = productService.addProduct(addProduct);
		assertNotNull(returnProduct); 
		productList.add(returnProduct);
		assertEquals(addProduct.getProductName(), returnProduct.getProductName(), addProduct.getProductName()+" not inserted ");
	}
}
