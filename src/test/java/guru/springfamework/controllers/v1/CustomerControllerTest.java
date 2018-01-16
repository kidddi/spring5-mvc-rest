package guru.springfamework.controllers.v1;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.*;


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

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;

public class CustomerControllerTest extends AbstractRestControllerTest{
	
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
		customer1.setId(Long.valueOf(2));
		
		CustomerDTO customer2 = new CustomerDTO();
		customer2.setId(Long.valueOf(3));
		
		List<CustomerDTO> customersDTO = Arrays.asList(customer1, customer2);
		
		when(customerService.getAllCustomers()).thenReturn(customersDTO);
		
		mockMvc.perform(get("/api/v1/customers/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	
	}

	@Test
	public void testGetCustomerById() throws Exception{

		CustomerDTO customer = new CustomerDTO();
		customer.setId(Long.valueOf(2));
		
		when(customerService.getById(anyLong())).thenReturn(customer);
		
		mockMvc.perform(get("/api/v1/customers/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testCreateNewCustomer() throws Exception{
		//given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fread");
		customer.setLastName("Flingstone");
		
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setCustomerUrl("/api/v1/customers/1");
		
		when(customerService.createNewCustomer(customer)).thenReturn(returnDTO);
		
		mockMvc.perform(post("/api/v1/customers/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.firstName", equalTo("Fread")))
			.andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
	}

}
