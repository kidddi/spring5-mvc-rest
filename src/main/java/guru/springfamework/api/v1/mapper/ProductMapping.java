package guru.springfamework.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import guru.springfamework.api.v1.model.ProductDTO;
import guru.springfamework.domain.Product;

@Mapper
public interface ProductMapping {
	
	ProductMapping INSTANSE = Mappers.getMapper(ProductMapping.class);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "vendor.id", target = "vendorId")
	public ProductDTO productTOproductDTO(Product product);

}
