package guru.springfamework.services;

import java.util.List;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.OrderDTO;

public interface OrderService {
	
	List<OrderDTO> getAllOrders();
	
	List<OrderDTO> getOrdersByCustomerId(Long customerId);
	
	OrderDTO getOrderDTOByCustomerId(Long customerId, Long orderId);
	
	CustomerDTO createOrder(Long customerId, OrderDTO orderDTO);
}
