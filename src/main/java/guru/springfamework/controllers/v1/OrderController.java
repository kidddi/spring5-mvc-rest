package guru.springfamework.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import guru.springfamework.api.v1.model.OrderDTO;
import guru.springfamework.api.v1.model.OrderListDTO;
import guru.springfamework.services.OrderService;

@Controller
//@RequestMapping("/api/v1/orders/")
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/api/v1/orders/")
	public ResponseEntity<OrderListDTO> getAllOrders(){
		
		return new ResponseEntity<OrderListDTO>(new OrderListDTO(orderService.getAllOrders()), HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/customers/{customerId}/orders/")
	public ResponseEntity<OrderListDTO> getOrdersByCustomerId(@PathVariable String customerId){
		
		return new ResponseEntity<OrderListDTO>(new OrderListDTO(orderService.getOrdersByCustomerId(Long.valueOf(customerId))), HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/customers/{customerId}/orders/{id}")
	public ResponseEntity<OrderDTO> getOrderDTOByCustomerId(@PathVariable String customerId, @PathVariable String id){
		
		return new ResponseEntity<OrderDTO>(orderService.getOrderDTOByCustomerId(Long.valueOf(customerId), Long.valueOf(id)), HttpStatus.OK);
	}
}
