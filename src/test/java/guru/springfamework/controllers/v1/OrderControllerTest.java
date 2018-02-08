package guru.springfamework.controllers.v1;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.OrderDTO;
import guru.springfamework.domain.OrderObj;
import guru.springfamework.services.OrderService;

public class OrderControllerTest extends AbstractRestControllerTest{

	@Mock
	OrderService orderService;
	
	@InjectMocks
	OrderController orderController;
	
	MockMvc mockMvc;	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}

	@Test
	public void testGetAllOrders() throws Exception{		
		
		mockMvc.perform(get("/api/v1/orders/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetOrdersByCustomerId() throws Exception{
		
		mockMvc.perform(get("/api/v1/customers/2/orders/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetOrderDTOByCustomerId() throws Exception{
		
		mockMvc.perform(get("/api/v1/customers/2/orders/3")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testCreateOrder() throws Exception {
		
		OrderObj order = new OrderObj();
		
		when(orderService.createOrder(anyLong(), any(OrderDTO.class))).thenReturn(new CustomerDTO());
				
		mockMvc.perform(post(OrderController.BASE_URL_CUSTOMERS + "/2/orders/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(order)))
				.andExpect(status().isCreated());
	}
}
