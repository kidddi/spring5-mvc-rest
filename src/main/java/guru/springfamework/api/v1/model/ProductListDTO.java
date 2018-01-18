package guru.springfamework.api.v1.model;

import lombok.Data;

import java.util.List;

import guru.springfamework.domain.Product;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ProductListDTO {
	
	private List<Product> products;

}
