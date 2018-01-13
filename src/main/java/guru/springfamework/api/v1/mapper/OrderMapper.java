package guru.springfamework.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import guru.springfamework.api.v1.model.OrderDTO;
import guru.springfamework.domain.OrderObj;

@Mapper
public interface OrderMapper {
	
	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
	
	@Mapping(source = "id", target = "id")
	OrderDTO orderObjToOrderDTO(OrderObj order);

}
