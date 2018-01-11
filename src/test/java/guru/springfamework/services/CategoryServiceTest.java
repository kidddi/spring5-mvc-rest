package guru.springfamework.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;

public class CategoryServiceTest {

	private static final Long ID = 1L;
	private static final String NAME = "Joe";
	CategoryService categoryService;

	@Mock
	CategoryRepository categoryRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		categoryService = new CategoryServiceIpml(CategoryMapper.INSTANCE, categoryRepository);
	}

	@Test
	public void getAllCategories() {
		// given
		List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

		when(categoryRepository.findAll()).thenReturn(categories);

		// when
		List<CategoryDTO> categoriesDTO = categoryService.getAllCategories();

		// then
		assertEquals(3, categoriesDTO.size());
	}

	@Test
	public void getCategoryByName() {
		// given
		Category category = new Category();
		category.setId(ID);
		category.setName(NAME);

		when(categoryRepository.findByName(anyString())).thenReturn(category);

		// when
		CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

		// then
		assertEquals(ID, categoryDTO.getId());
		assertEquals(NAME, categoryDTO.getName());
	}

}
