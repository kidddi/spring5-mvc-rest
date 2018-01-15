package guru.springfamework.controllers.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springfamework.services.OrderService;

public class OrderControllerTest {

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
}
