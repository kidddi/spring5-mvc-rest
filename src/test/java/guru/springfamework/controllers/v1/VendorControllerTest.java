package guru.springfamework.controllers.v1;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.services.VendorService;

public class VendorControllerTest extends AbstractRestControllerTest {

	private static final Long ID = 1L;

	private static final String NAME = "Some vendor";

	@Mock
	VendorService vendorService;

	@InjectMocks
	VendorController vendorController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
	}

	@Test
	public void testGetAllVendors() throws Exception {
		mockMvc.perform(get(VendorController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testGetVendorById() throws Exception {
		//given
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setId(ID);
		vendorDTO.setName(NAME);
		
		when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);
		
		//when
		mockMvc.perform(get(VendorController.BASE_URL + "/2")
				.contentType(MediaType.APPLICATION_JSON))
		//then
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(NAME)));
	}
	
	@Test
	public void testCreateVendor() throws Exception {
		
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setId(ID);
		vendorDTO.setName(NAME);

		VendorDTO returnVendorDTO = new VendorDTO();
		returnVendorDTO.setId(vendorDTO.getId());
		returnVendorDTO.setName(vendorDTO.getName());
		returnVendorDTO.setVendorUrl(VendorController.BASE_URL + "/1");
		
		when(vendorService.createNewVendor(any(VendorDTO.class))).thenReturn(returnVendorDTO);
		
		//when
		mockMvc.perform(post(VendorController.BASE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(vendorDTO)))
		//then
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", equalTo(NAME)))
				.andExpect(jsonPath("$.vendorUrl", equalTo(VendorController.BASE_URL + "/1")));
	}
	
	@Test
	public void testUpdateVendor() throws Exception {
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setId(ID);
		vendorDTO.setName(NAME);
		
		VendorDTO returnVendorDTO = new VendorDTO();
		returnVendorDTO.setId(vendorDTO.getId());
		returnVendorDTO.setName(vendorDTO.getName());
		returnVendorDTO.setVendorUrl(VendorController.BASE_URL + "/1");
		
		when(vendorService.updateVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnVendorDTO);
		
		//when
		mockMvc.perform(put(VendorController.BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(vendorDTO)))
		//then
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", equalTo(NAME)))
		.andExpect(jsonPath("$.vendorUrl", equalTo(VendorController.BASE_URL + "/1")));		
	}
	
	@Test
	public void testDeleteVendor() throws Exception {
		
		mockMvc.perform(delete(VendorController.BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());	
		
		verify(vendorService, times(1)).deleteVendor(anyLong());
	}
}
