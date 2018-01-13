package guru.springfamework.controllers.v1;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import aj.org.objectweb.asm.Type;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.services.CustomerService;

public class CustomerControllerTest {
	
	@Mock
	CustomerService customerService;
	
	@InjectMocks
	CustomerController customerController;

	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testGetAllCustomers() throws Exception{
		//given
		CustomerDTO customer1 = new CustomerDTO();
		customer1.setCustomerId("2");
		
		CustomerDTO customer2 = new CustomerDTO();
		customer2.setCustomerId("3");
		
		List<CustomerDTO> customersDTO = Arrays.asList(customer1, customer2);
		
		when(customerService.getAllCustomers()).thenReturn(customersDTO);
		
		mockMvc.perform(get("/api/v1/customers/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	
	}

	@Test
	public void testGetCustomerById() throws Exception{

		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerId("2");
		
		when(customerService.getCustomerById(anyString())).thenReturn(customer);
		
		mockMvc.perform(get("/api/v1/customers/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
