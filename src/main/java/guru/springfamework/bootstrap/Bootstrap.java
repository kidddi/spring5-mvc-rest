package guru.springfamework.bootstrap;

import java.util.Calendar;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.OrderObj;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.OrderRepository;

@Component
@Profile({"dev"})
public class Bootstrap implements CommandLineRunner{

	private final CategoryRepository categoryRepository;
	private final CustomerRepository customerRepository;
	private final OrderRepository orderRepository;

	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
			OrderRepository orderRepository) {
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
		this.orderRepository = orderRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Category fruits = new Category();
		fruits.setName("Fruits");
		
		Category dried = new Category();
		dried.setName("Dried");
		
		Category fresh = new Category();
		fresh.setName("Fresh");
		
		Category exotic = new Category();
		exotic.setName("Exotic");
		
		Category nuts = new Category();
		nuts.setName("Nuts");
		
		categoryRepository.save(fruits);
		categoryRepository.save(dried);
		categoryRepository.save(fresh);
		categoryRepository.save(exotic);
		categoryRepository.save(nuts);
		
		System.out.println("Categories Loaded: " + categoryRepository.count());
		
		Customer customer1 = new Customer();
//		customer1.setCustomerId("1");
		customer1.setFirstName("Alex");
		customer1.setLastName("Show");
		
		Customer customer2 = new Customer();
//		customer2.setCustomerId("2");
		customer2.setFirstName("Igor");
		customer2.setLastName("Dovg");
				
		customerRepository.save(customer1);
		customerRepository.save(customer2);
		
		System.out.println("CustomersLoaded: " + customerRepository.count());
		
		OrderObj order1 = new OrderObj();
		order1.setTotal(3.5);
		order1.setCreatedAt(new Calendar.Builder().setDate(2017, 9, 29).build().getTime());
		customer1.addOrder(order1);
		
		OrderObj order2 = new OrderObj();
		order2.setTotal(12.5);
		order2.setCreatedAt(new Calendar.Builder().setDate(2015, 3, 9).build().getTime());
		customer1.addOrder(order2);
		
		OrderObj order3 = new OrderObj();
		order3.setTotal(12.5);
		order3.setCreatedAt(new Calendar.Builder().setDate(2015, 3, 9).build().getTime());
		customer2.addOrder(order3);
		
		orderRepository.save(order1);
		orderRepository.save(order2);
		orderRepository.save(order3);
		
		System.out.println("Orders Loaded: " + orderRepository.count());
	}	
}
