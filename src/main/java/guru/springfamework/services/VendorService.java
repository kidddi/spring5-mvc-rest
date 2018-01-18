package guru.springfamework.services;

import java.util.List;

import guru.springfamework.api.v1.model.VendorDTO;

public interface VendorService {

	List<VendorDTO> getAllVendors();
	
	VendorDTO getVendorById(Long id);
	
	VendorDTO createNewVendor(VendorDTO vendorDTO);
	
	void deleteVendor(Long id);
	
	VendorDTO updateVendor(Long id, VendorDTO vendor);
	
	VendorDTO patchVendor(Long id, VendorDTO vendor);	
}
