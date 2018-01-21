package guru.springfamework.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.OrderDTO;
import guru.springfamework.api.v1.model.OrderListDTO;
import guru.springfamework.services.OrderService;

@Controller
//@RequestMapping(OrderController.BASE_URL_ORDERS)
public class OrderController {

	public static final String BASE_URL_ORDERS= "/api/v1/orders";
	public static final String BASE_URL_CUSTOMERS= "/api/v1/customers";
	
	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping(BASE_URL_ORDERS)
	public ResponseEntity<OrderListDTO> getAllOrders(){
		
		return new ResponseEntity<OrderListDTO>(new OrderListDTO(orderService.getAllOrders()), HttpStatus.OK);
	}
	
	@GetMapping(BASE_URL_CUSTOMERS + "/{customerId}/orders/")
	public ResponseEntity<OrderListDTO> getOrdersByCustomerId(@PathVariable String customerId){
		
		return new ResponseEntity<OrderListDTO>(new OrderListDTO(orderService.getOrdersByCustomerId(Long.valueOf(customerId))), HttpStatus.OK);
	}
	
	@GetMapping(BASE_URL_CUSTOMERS + "/{customerId}/orders/{id}")
	public ResponseEntity<OrderDTO> getOrderDTOByCustomerId(@PathVariable String customerId, @PathVariable String id){
		
		return new ResponseEntity<OrderDTO>(orderService.getOrderDTOByCustomerId(Long.valueOf(customerId), Long.valueOf(id)), HttpStatus.OK);
	}
	
	@PostMapping(BASE_URL_CUSTOMERS + "/{customerId}/orders/")
	public ResponseEntity<CustomerDTO> createOrder(@PathVariable Long customerId, @RequestBody OrderDTO orderDTO){
		
		return new ResponseEntity<CustomerDTO>(orderService.createOrder(customerId, orderDTO), HttpStatus.CREATED);
	}
}
