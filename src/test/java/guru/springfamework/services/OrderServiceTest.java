package guru.springfamework.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.mapper.OrderMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.OrderDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.OrderObj;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.OrderRepository;

public class OrderServiceTest {

	@Mock
	OrderRepository orderRepository;
	
	@Mock
	CustomerRepository customerRepository;
	
	OrderService orderService;	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		orderService = new OrderServiceIpml(
				orderRepository, OrderMapper.INSTANCE, customerRepository, CustomerMapper.INSTANCE);	
	}

	@Test
	public void testGetAllOrders() {
		//given
		List<OrderObj> orderList = Arrays.asList(new OrderObj(), new OrderObj());
		
		when(orderRepository.findAll()).thenReturn(orderList);
		
		//when
		List<OrderDTO> orderListDTO = orderService.getAllOrders();
		
		//then
		assertEquals(2, orderListDTO.size());
	}

	@Test
	public void testGetOrdersByCustomerId() {
		
		Customer customer = new Customer();
		OrderObj order1 = new OrderObj();
		OrderObj order2 = new OrderObj();
		customer.setOrders(Arrays.asList(order1, order2));
		
		Optional<Customer> customerOptional = Optional.of(customer);
		
		when(customerRepository.findById(anyLong())).thenReturn(customerOptional);
		
		//when
		List<OrderDTO> ordersDTO = orderService.getOrdersByCustomerId(anyLong());
		
		//then
		assertEquals(2, ordersDTO.size());
	}

	@Test
	public void testGetOrderDTOByCustomerId() {
		//given
		Customer customer = new Customer();
		OrderObj order1 = new OrderObj();
		order1.setId(4L);
		order1.setTotal(10.5);
		OrderObj order2 = new OrderObj();
		
		customer.setOrders(Arrays.asList(order1, order2));
		
		Optional<Customer> customerOptional = Optional.of(customer);
		
		when(customerRepository.findById(anyLong())).thenReturn(customerOptional);
		
		//when
		OrderDTO orderDTO = orderService.getOrderDTOByCustomerId(anyLong(), 4L);
		
		//then
		assertEquals(order1.getTotal(), orderDTO.getTotal());
	}
	
	@Test
	public void testCreateOrder() {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setFirstName("Joe");
		
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
		when(customerRepository.save(any(Customer.class))).thenReturn(customer);
		
		CustomerDTO savedCustomer = orderService.createOrder(1L, new OrderDTO());
		
		assertEquals("Joe", savedCustomer.getFirstName());
		verify(customerRepository, times(1)).save(any());
	}

}
