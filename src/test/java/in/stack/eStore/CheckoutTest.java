package in.stack.eStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
import in.stack.eStore.service.CheckOutService;

@ExtendWith(MockitoExtension.class)
public class CheckoutTest {

	@Mock
	OrderRepository orderRepo;

	@Mock
	OrderDetailsRepository orderDetailsRepo;

	@InjectMocks
	CheckOutService checkOutService;

	Order order;

	@Test
	public void testUpdateOrderDB() throws SQLException {

		order = createOrderObj();
		checkOutService = Mockito.spy(new CheckOutService());
		checkOutService.createOrder(order);

		verify(checkOutService, times(1)).updateOrderDB(order);
	}

	@Test
	public void testExceptionInUpdateOrderDB() throws SQLException {

		order = createOrderObj();
		order.setOrderId(1);
		checkOutService = Mockito.spy(new CheckOutService());
		Exception exception = Assertions.assertThrows(SQLException.class, () -> checkOutService.updateOrderDB(order));
		assertEquals("Duplicate entry '1' for key 'orders.PRIMARY'", exception.getMessage());

	}

	@Test
	public void testUpdateOrderDetailsDB() throws SQLException {

		order = createOrderObj();
		checkOutService = Mockito.spy(new CheckOutService());
		checkOutService.createOrder(order);

		verify(checkOutService, times(1)).updateOrderDetailsDB(order);
	}

	@Test
	public void testDisplayDetails() throws SQLException {

		order = createOrderObj();
		checkOutService = Mockito.spy(new CheckOutService());
		checkOutService.createOrder(order);

		verify(checkOutService, times(1)).displayDetails(order);
	}

	@Test
	public void testGetOrderId() throws SQLException {
		int orderId = 45;
		Mockito.when(orderDetailsRepo.getOrderId()).thenReturn(orderId);
		orderDetailsRepo.getOrderId();
		assertNotNull(orderId);
	}

	public Order createOrderObj() throws SQLException {

		List<Product> pList = createProductList();
		int orderId = orderDetailsRepo.getOrderId();
		LocalDateTime date = LocalDateTime.now();
		String customerName = "Asha";
		OrederDetails orderDetails = new OrederDetails(pList, 10);
		Order order = new Order(orderId, date, orderDetails, 1, customerName);

		return order;
	}

	public List<Product> createProductList() {

		Product p1 = new Product();
		p1.setProdutId(1);
		p1.setProductName("Biscuit");
		p1.setQuantity(1);
		p1.setPrice(25);
		p1.setIsactive(true);

		Product p2 = new Product();
		p2.setProdutId(2);
		p2.setProductName("Pen");
		p2.setQuantity(20);
		p2.setPrice(10);
		p2.setIsactive(true);

		Product p3 = new Product();
		p2.setProdutId(3);
		p2.setProductName("Book");
		p2.setQuantity(100);
		p2.setPrice(20);
		p2.setIsactive(true);

		List<Product> pList = new ArrayList<Product>();
		pList.add(p1);
		pList.add(p2);
		pList.add(p3);

		return pList;
	}

}
