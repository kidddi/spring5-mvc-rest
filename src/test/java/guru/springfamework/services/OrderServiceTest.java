//package guru.springfamework.services;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import guru.springfamework.api.v1.mapper.OrderMapper;
//import guru.springfamework.api.v1.model.OrderDTO;
//import guru.springfamework.domain.OrderObj;
//import guru.springfamework.repositories.OrderRepository;
//
//public class OrderServiceTest {
//
//	@Mock
//	OrderRepository orderRepository;
//	
//	OrderService orderService;	
//
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//		
//		orderService = new OrderServiceIpml(orderRepository, OrderMapper.INSTANCE);	
//	}
//
//	@Test
//	public void testGetAllOrders() {
//		//given
//		List<OrderObj> orderList = Arrays.asList(new OrderObj(), new OrderObj());
//		
//		when(orderRepository.findAll()).thenReturn(orderList);
//		
//		//when
//		List<OrderDTO> orderListDTO = orderService.getAllOrders();
//		
//		//then
//		assertEquals(2, orderListDTO.size());
//	}
//
//	@Test
//	public void testGetOrdersByCustomerId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetOrderDTOByCustomerId() {
//		fail("Not yet implemented");
//	}
//
//}
