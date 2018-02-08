package guru.springfamework.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.mapper.OrderMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.OrderDTO;
import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceIpml implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	public OrderServiceIpml(OrderRepository orderRepository, OrderMapper orderMapper,
			CustomerRepository customerRepository, CustomerMapper customerMapper) {
		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public List<OrderDTO> getAllOrders() {

		return orderRepository.findAll()
				.stream()
				.map(orderMapper::orderObjToOrderDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
		Optional<Customer> customerOptional = customerRepository.findById(customerId);
		if (!customerOptional.isPresent()) {
			log.debug("Customer whith this ID is not found");
		}
		Customer customer = customerOptional.get();
		
		List<OrderDTO> ordersDTO = customer.getOrders()
				.stream()
				.map(orderMapper::orderObjToOrderDTO)
				.collect(Collectors.toList());
		
		return ordersDTO;
	}

	@Override
	public OrderDTO getOrderDTOByCustomerId(Long customerId, Long orderId) {
		
		return getOrdersByCustomerId(customerId)
				.stream()
				.filter(order -> order.getId().equals(orderId))
				.findFirst().get();
	}

	@Override
	public CustomerDTO createOrder(Long customerId, OrderDTO orderDTO) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(ResourceNotFoundException::new);
		customer.addOrder(orderMapper.orderDTOToOrderObj(orderDTO));
		
		CustomerDTO returnCustomerDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
		returnCustomerDTO.setCustomerUrl(CustomerController.BASE_URL + "/" + customerId); 
		
		return returnCustomerDTO;
	}
}
