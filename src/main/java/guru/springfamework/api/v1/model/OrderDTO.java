package guru.springfamework.api.v1.model;

import java.util.Date;

import guru.springfamework.domain.Customer;
import lombok.Data;

@Data
public class OrderDTO {

	private Long id;
	private Date createdAt;
	private Date updatedAt;
	private Customer customer;
	private Double total;
	
}
