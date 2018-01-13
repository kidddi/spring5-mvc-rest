package guru.springfamework.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;

public class CustomerServiceTest {

	@Mock
	CustomerRepository customerRepository;
	
	CustomerService customerService;
	
	@Before
	public void setUp() throws Exception {
	
		MockitoAnnotations.initMocks(this);
		
		customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
	}

	@Test
	public void testGetAllCustomers() {
		//given
		List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
		
		when(customerRepository.findAll()).thenReturn(customers);
		
		//when
		List<CustomerDTO> customersDTO = customerService.getAllCustomers();
		
		assertEquals(3, customersDTO.size());
	}

	@Test
	public void testGetCustomerById() {
		//given
		Customer customer = new Customer();
		customer.setCustomerId("2");
		customer.setFirstName("Jeck");
		
		when(customerRepository.findByCustomerId(anyString())).thenReturn(customer);
		
		//when
		CustomerDTO customerDTO = customerService.getCustomerById("2");
		
		//then
		assertEquals("Jeck", customerDTO.getFirstName());	
	}

}
