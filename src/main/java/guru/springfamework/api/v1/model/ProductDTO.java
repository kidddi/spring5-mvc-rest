package guru.springfamework.api.v1.model;

import lombok.Data;

@Data
public class ProductDTO {
	
	private Long id;
	private String name; 
	
	private Long vendorId;

}
