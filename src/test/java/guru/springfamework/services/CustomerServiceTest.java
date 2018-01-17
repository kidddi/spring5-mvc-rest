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
		// given
		List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

		when(customerRepository.findAll()).thenReturn(customers);

		// when
		List<CustomerDTO> customersDTO = customerService.getAllCustomers();

		assertEquals(3, customersDTO.size());
	}

	@Test
	public void testGetCustomerById() {
		// given
		Customer customer = new Customer();
		customer.setId(Long.valueOf(2));
		customer.setFirstName("Jeck");
		Optional<Customer> customerOptional = Optional.of(customer);

		when(customerRepository.findById(anyLong())).thenReturn(customerOptional);

		// when
		CustomerDTO customerDTO = customerService.getById(Long.valueOf(2));

		// then
		assertEquals("Jeck", customerDTO.getFirstName());
	}

	@Test
	public void testCreateNewCustomer() throws Exception {
		// given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Joe");

		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());
		savedCustomer.setId(1L);

		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

		// when
		CustomerDTO savedDTO = customerService.createNewCustomer(customerDTO);

		// then
		assertEquals(customerDTO.getFirstName(), savedDTO.getFirstName());
		assertEquals("/api/v1/customers/1", savedDTO.getCustomerUrl());
	}

	@Test
	public void testSaveCustomerByDTO() throws Exception {
		// given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Joe");

		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());
		savedCustomer.setId(1L);

		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

		// when
		CustomerDTO savedDTO = customerService.saveCustomerByDTO(1L, customerDTO);

		// then
		assertEquals(customerDTO.getFirstName(), savedDTO.getFirstName());
		assertEquals("/api/v1/customers/1", savedDTO.getCustomerUrl());
	}
	
	@Test
	public void testDeleteCustomerById() throws Exception {
		Long id = 1L;
		
		customerService.deleteCustomerId(id);
		
		verify(customerRepository, times(1)).deleteById(anyLong());
	}
}
