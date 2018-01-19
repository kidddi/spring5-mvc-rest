package guru.springfamework.controllers.v1;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springfamework.services.VendorService;

public class VendorControllerTest {

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

}
