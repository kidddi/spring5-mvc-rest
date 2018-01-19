package guru.springfamework.services;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.bootstrap.Bootstrap;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.OrderRepository;
import guru.springfamework.repositories.ProductRepository;
import guru.springfamework.repositories.VendorRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	CustomerService customerService;
	
	@Before
	public void setUp() throws Exception{
		System.out.println("Loading Customer Data");
		System.out.println(customerRepository.findAll().size());
		
		//setup data for testing
		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, 
				orderRepository, vendorRepository, productRepository);
		bootstrap.run();//load data
		
		
		customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
	}
	
	@Test
	public void patchCustomerUpdatedFirstName() throws Exception {
		String updatedName = "UpdatedName";
		long id = getCustomerIdValue();
		
		Customer originalCustomer = customerRepository.getOne(id);
		assertNotNull(originalCustomer);
		//save original first name
		String originalFirstName = originalCustomer.getFirstName();
		String originalLastName = originalCustomer.getLastName();
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName(updatedName);
		
		customerService.patchCustomer(id, customerDTO);
		
		Customer updatedCustomer = customerRepository.findById(id).get();
		
		assertNotNull(updatedCustomer);
		assertEquals(updatedName, updatedCustomer.getFirstName());
		assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
		assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
		System.out.println("***************");
	}
	
	@Test
	public void patchCustomerUpdatedLastName() throws Exception {
		String updatedName = "UpdatedName";
		long id = getCustomerIdValue();
		
		Customer originalCustomer = customerRepository.findById(id).get();
		assertNotNull(originalCustomer);
		//save original first name
		String originalFirstName = originalCustomer.getFirstName();
		String originalLastName = originalCustomer.getLastName();
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setLastName(updatedName);
		
		customerService.patchCustomer(id, customerDTO);
		
		Customer updatedCustomer = customerRepository.findById(id).get();
		
		assertNotNull(updatedCustomer);
		assertEquals(updatedName, updatedCustomer.getLastName());
		assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
		assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
	}
	
	private Long getCustomerIdValue() {
		List<Customer> customers = customerRepository.findAll();
		
		System.out.println("Customers Found: " + customers.size());
		
		//return first id
		return customers.get(0).getId();
	}
}
