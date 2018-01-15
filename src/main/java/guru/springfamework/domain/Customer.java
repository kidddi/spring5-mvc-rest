package guru.springfamework.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
//	private String customerId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<OrderObj> orders = new ArrayList();
	
	public Customer addOrder(OrderObj order) {
		this.orders.add(order);
		order.setCustomer(this);
		return this;
	}
}
