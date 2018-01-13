package guru.springfamework.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;

@Component
@Profile({"dev"})
public class Bootstrap implements CommandLineRunner{

	private final CategoryRepository categoryRepository;
	private final CustomerRepository customerRepository;

	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
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
		customer1.setCustomerId("1");
		customer1.setFirstName("Alex");
		customer1.setLastName("Show");
		
		Customer customer2 = new Customer();
		customer2.setCustomerId("2");
		customer2.setFirstName("Igor");
		customer2.setLastName("Dovg");
		
		customerRepository.save(customer1);
 		customerRepository.save(customer2);
		
		System.out.println("CustomersLoaded: " + customerRepository.count());		
	}	
	
}
