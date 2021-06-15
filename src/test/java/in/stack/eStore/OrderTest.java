package in.stack.eStore;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.time.LocalDateTime;
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

import in.stack.eStore.model.Order;
import in.stack.eStore.model.OrederDetails;
import in.stack.eStore.model.Product;
import in.stack.eStore.repository.OrderDetailsRepository;
import in.stack.eStore.repository.OrderRepository;
import in.stack.eStore.repository.ProductRepository;
import in.stack.eStore.service.CheckOutService;
import in.stack.eStore.service.ProductService;

//@RunWith attaches a runner with the test class to initialize the test data
@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class OrderTest {
	
	Product product;
	int productId=1;
	/*
	 * Order order; OrederDetails orederDetails; Order orderDummy; OrederDetails
	 * orederDetailsDummy; List<Product> orderedProductList = null; List<Product>
	 * productList = null;
	 */
	//@Mock annotation is used to create the mock object to be injected
		@Mock
		ProductRepository productRepo;
		
		//@InjectMocks annotation is used to create and inject the mock object
		@InjectMocks
		ProductService productService;
		
		/*
		 * @Mock OrderRepository orderRepo;
		 */
		
		
		@BeforeAll
		public void createProduct()
		{
			this.product=new Product(1, "pen", 15, 10, true);
			//this.order=createOrderObject();
			//this.productList=createProducts();.
			
		}
		
		@Test
		public void testGetProductById() throws SQLException
		{
			Mockito.when(productRepo.getProductById(productId)).thenReturn(product);
			Product ProductById=productService.getProductById(productId);
			assertNotNull(ProductById); 
			assertEquals("pen", ProductById.getProductName(),"product names are different" );
		}
		
		
		
}
