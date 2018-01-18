package guru.springfamework.bootstrap;

import java.util.Calendar;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.OrderObj;
import guru.springfamework.domain.Product;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.OrderRepository;
import guru.springfamework.repositories.ProductRepository;
import guru.springfamework.repositories.VendorRepository;

@Component
@Profile({"dev"})
public class Bootstrap implements CommandLineRunner{

	private final CategoryRepository categoryRepository;
	private final CustomerRepository customerRepository;
	private final OrderRepository orderRepository;
	private final VendorRepository vendorRepository;
	private final ProductRepository productRepository;

	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
			OrderRepository orderRepository, VendorRepository vendorRepository, ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
		this.orderRepository = orderRepository;
		this.vendorRepository = vendorRepository;
		this.productRepository = productRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		loadCategories();
		
		loadCustomersAndOrders();
		
		loadVendorsAndProducts();
	}
	

	private void loadVendorsAndProducts() {
		Vendor vendor1 = new Vendor();
		vendor1.setId(1L);
		vendor1.setName("Western Tasty Fruits Ltd.");
		
		Vendor vendor2 = new Vendor();
		vendor2.setId(2L);
		vendor2.setName("Home Fruits");
		
		vendorRepository.save(vendor1);
		vendorRepository.save(vendor2);
		
		Product product1 = new Product();
		product1.setId(1L);
		product1.setName("Bananas");
		Product product2 = new Product();
		product2.setId(2L);
		product2.setName("Oranges");
		Product product3 = new Product();
		product3.setId(3L);
		product3.setName("Pineapples");
		
		vendor1.addProduct(product1);
		productRepository.save(product1);
		
		vendor1.addProduct(product2);
		productRepository.save(product2);
		
		vendor1.addProduct(product3);
		productRepository.save(product3);
		
		
		vendor2.addProduct(product1);
		product1.setId(4L);		
		productRepository.save(product1);
		
		vendor2.addProduct(product2);
		product2.setId(5L);
		productRepository.save(product2);
		
		
		System.out.println("Vendors Loaded: " + vendorRepository.count());		
		System.out.println("Products Loaded: " + productRepository.count());		
	}

	private void loadCustomersAndOrders() {
		Customer customer1 = new Customer();
		customer1.setId(1L);
		customer1.setFirstName("Alex");
		customer1.setLastName("Show");
		
		Customer customer2 = new Customer();
		customer2.setId(2L);
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

	private void loadCategories() {
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
	}	
}
