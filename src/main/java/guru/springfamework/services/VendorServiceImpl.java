package guru.springfamework.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {
	
	public static final String BASE_URL = "/api/v1/vendors";

	private final VendorRepository vendorRepository;
	
	private final VendorMapper vendorMapper;
	
	public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
		this.vendorRepository = vendorRepository;
		this.vendorMapper = vendorMapper;
	}

	@Override
	public List<VendorDTO> getAllVendors() {
		
		return vendorRepository.findAll().stream().map(vendor -> {
			VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
			vendorDTO.setVendorUrl(buildVendorUrl(vendor.getId()));
			return vendorDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public VendorDTO getVendorById(Long id) {
		VendorDTO vendorDTO = vendorRepository.findById(id)
				.map(vendorMapper::vendorToVendorDTO)				
				.orElseThrow(ResourceNotFoundException::new); 
		
		vendorDTO.setVendorUrl(buildVendorUrl(vendorDTO.getId()));
		
		return vendorDTO;
	}

	@Override
	public VendorDTO createNewVendor(VendorDTO vendorDTO) {
		
		return saveAndReturnVendorDTO(vendorDTO);
	}

	private VendorDTO saveAndReturnVendorDTO(VendorDTO vendorDTO) {
		Vendor returnVendor = vendorRepository.save(vendorMapper.vendorDTOToVendor(vendorDTO));
		VendorDTO returnVendorDTO = vendorMapper.vendorToVendorDTO(returnVendor);
		returnVendorDTO.setVendorUrl(buildVendorUrl(returnVendorDTO.getId()));
		
		return returnVendorDTO;
	}

	@Override
	public void deleteVendor(Long id) {
		vendorRepository.deleteById(id);
	}

	@Override
	public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
		vendorDTO.setId(id);
		
		return saveAndReturnVendorDTO(vendorDTO);
	}

	@Override
	public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
		Vendor vendor = vendorRepository.findById(id)
				.orElseThrow(ResourceNotFoundException::new);
		
		return null;
	}	

	private String buildVendorUrl(Long id) {
		return BASE_URL + "/" + id;
	}
}
