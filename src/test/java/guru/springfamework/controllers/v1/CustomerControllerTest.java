package guru.springfamework.controllers.v1;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import guru.springfamework.controllers.RestResponceEntityExceptionHandler;
import guru.springfamework.services.CustomerService;

public class CustomerControllerTest extends AbstractRestControllerTest {

	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(customerController)
				.setControllerAdvice(new RestResponceEntityExceptionHandler())
				.build();
	}

	@Test
	public void testGetAllCustomers() throws Exception {
		// given
		List<CustomerDTO> customersDTO = Arrays.asList(new CustomerDTO(), new CustomerDTO());

		when(customerService.getAllCustomers()).thenReturn(customersDTO);

		mockMvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

	}

	@Test
	public void testGetCustomerById() throws Exception {

		when(customerService.getById(anyLong())).thenReturn(new CustomerDTO());

		mockMvc.perform(get("/api/v1/customers/2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	public void testCreateNewCustomer() throws Exception {
		// given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fread");
		customer.setLastName("Flingstone");

		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setCustomerUrl("/api/v1/customers/1");

		when(customerService.createNewCustomer(any(CustomerDTO.class))).thenReturn(returnDTO);

		mockMvc.perform(post("/api/v1/customers/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", equalTo("Fread")))
				.andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		// given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fread");
		customer.setLastName("Flingstone");

		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setCustomerUrl("/api/v1/customers/1");

		when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

		mockMvc.perform(put("/api/v1/customers/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName", equalTo("Fread")))
				.andExpect(jsonPath("$.lastName", equalTo("Flingstone")))
				.andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
	}

	@Test
	public void testPatchCustomer() throws Exception {
		// given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fread");

		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName("Flingstone");
		returnDTO.setCustomerUrl("/api/v1/customers/1");
		
		when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

		mockMvc.perform(patch("/api/v1/customers/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName", equalTo("Fread")))
				.andExpect(jsonPath("$.lastName", equalTo("Flingstone")))
				.andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
	}
	
	@Test
	public void testDeleteCustomer() throws Exception {
		
		mockMvc.perform(delete("/api/v1/customers/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		verify(customerService).deleteCustomerId(anyLong());
	}

}
