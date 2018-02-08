package guru.springfamework.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

	private final VendorRepository vendorRepository;

	private final VendorMapper vendorMapper;

	public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
		this.vendorRepository = vendorRepository;
		this.vendorMapper = vendorMapper;
	}

	@Override
	public List<VendorDTO> getAllVendors() {

		return vendorRepository.findAll()
				.stream()
				.map(vendor -> {
					VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
					vendorDTO.setVendorUrl(buildVendorUrl(vendor.getId()));
					return vendorDTO;
				})
				.collect(Collectors.toList());
	}

	@Override
	public VendorDTO getVendorById(Long id) {

		return vendorRepository.findById(id)
				.map(vendorMapper::vendorToVendorDTO)
				.map(vendorDTO -> {
					vendorDTO.setVendorUrl(buildVendorUrl(id));
					return vendorDTO;
				})
				.orElseThrow(ResourceNotFoundException::new);
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
		return vendorRepository.findById(id)
				.map(vendor -> {
					if (vendorDTO.getName() != null) {
						vendor.setName(vendorDTO.getName());
					}
					VendorDTO returnVendor = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
					return returnVendor;
				})
				.orElseThrow(ResourceNotFoundException::new);
	}

	private String buildVendorUrl(Long id) {
		return VendorController.BASE_URL + "/" + id;
	}
}
