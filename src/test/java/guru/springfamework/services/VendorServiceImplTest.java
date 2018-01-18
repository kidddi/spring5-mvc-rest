package guru.springfamework.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;

public class VendorServiceImplTest {
	public final static String NAME = "Tasty vendor";	
	public final static Long ID = 1L;	

	@Mock
	private VendorRepository vendorRepository;
	
	private VendorService vendorService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANSE);
	}

	@Test
	public void testGetAllVendors() {
		//given
		when(vendorRepository.findAll()).thenReturn(Arrays.asList(new Vendor(), new Vendor()));
		
		//when
		List<VendorDTO> vendorsDTO = vendorService.getAllVendors();
		
		//then
		assertEquals(2, vendorsDTO.size());
	}

	@Test
	public void testGetVendorById() {
		//given
		Vendor vendor = new Vendor();
		vendor.setId(ID);
		vendor.setName(NAME);
		Optional<Vendor> vendorOptional = Optional.of(vendor);
		
		VendorDTO expectedVendorDTO = new VendorDTO();
		expectedVendorDTO.setId(vendor.getId());
		expectedVendorDTO.setName(vendor.getName());
		
		when(vendorRepository.findById(anyLong())).thenReturn(vendorOptional);
		
		//when
		VendorDTO returnVendorDto = vendorService.getVendorById(ID);
		
		//then
		assertEquals(expectedVendorDTO.getId(), returnVendorDto.getId());
		assertEquals(expectedVendorDTO.getName(), returnVendorDto.getName());
		assertEquals("/api/v1/vendors/" + ID, returnVendorDto.getVendorUrl());
	}

	@Test
	public void testCreateNewVendor() {
		//given
		Vendor vendor = new Vendor();
		vendor.setId(ID);
		vendor.setName(NAME);
		
		VendorDTO expectedVendorDTO = new VendorDTO();
		expectedVendorDTO.setId(vendor.getId());
		expectedVendorDTO.setName(vendor.getName());
		
		when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);
		
		//when
		VendorDTO returnVendorDto = vendorService.createNewVendor(expectedVendorDTO);
		
		//then
		assertEquals(expectedVendorDTO.getId(), returnVendorDto.getId());
		assertEquals(expectedVendorDTO.getName(), returnVendorDto.getName());
		assertEquals("/api/v1/vendors/" + ID, returnVendorDto.getVendorUrl());
	}

	@Test
	public void testDeleteVendor() {
				
		vendorService.deleteVendor(ID);
		
		verify(vendorRepository, times(1)).deleteById(anyLong());
	}

	@Test
	public void testUpdateVendor() {
		fail("Not yet implemented");
	}

	@Test
	public void testPatchVendor() {
		fail("Not yet implemented");
	}

}
