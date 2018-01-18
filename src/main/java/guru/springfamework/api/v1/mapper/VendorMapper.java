package guru.springfamework.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;

@Mapper
public interface VendorMapper {
	
	VendorMapper INSTANSE = Mappers.getMapper(VendorMapper.class);
	
	@Mapping(source = "id", target = "id")
	public VendorDTO vendorToVendorDTO(Vendor vendor);
	
	@Mapping(source = "id", target = "id")
	public Vendor vendorDTOToVendor(VendorDTO vendorDTO);

}
