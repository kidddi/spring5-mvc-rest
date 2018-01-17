package guru.springfamework.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {

		return customerRepository.findAll().stream().map(customer -> {
			CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
			customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
			return customerDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getById(Long id) {

		// return
		// customerMapper.customerToCustomerDTO(customerRepository.findById(id).get());
		CustomerDTO customerDTO = customerRepository.findById(id).map(customerMapper::customerToCustomerDTO)				
				.orElseThrow(RuntimeException::new);
		
		customerDTO.setCustomerUrl("/api/v1/customers/" + id);
		return customerDTO;
	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

		return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
	}

	private CustomerDTO saveAndReturnDTO(Customer customer) {
		
		Customer savedCustomer = customerRepository.save(customer);

		CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);

		returnDTO.setCustomerUrl("/api/v1/customers/" + savedCustomer.getId());

		return returnDTO;
	}

	@Override
	public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
		
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		customer.setId(id);
		
		return saveAndReturnDTO(customer);
	}

	@Override
	public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
		return customerRepository.findById(id).map(customer -> {
			
			if(customerDTO.getFirstName() != null) {
				customer.setFirstName(customerDTO.getFirstName());
			}
			
			if(customerDTO.getLastName() != null) {
				customer.setLastName(customerDTO.getLastName());
			}
			
			CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
			returnDTO.setCustomerUrl("/api/v1/customers/" + id);
			
			return returnDTO;
		}).orElseThrow(RuntimeException::new);
	}

	@Override
	public void deleteCustomerId(Long id) {
		customerRepository.deleteById(id);
		
	}
}
