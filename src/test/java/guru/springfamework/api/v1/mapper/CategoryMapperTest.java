package guru.springfamework.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;

public class CategoryMapperTest {
	
	private static final long _1L = 1L;
	private static final String JOE = "Joe";
	CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
	
	@Test
	public void categoryToCategoryDTO() {
		
		//given
		Category category = new Category();
		category.setName(JOE);
		category.setId(_1L);
		
		//when
		CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
		
		//then
		assertEquals(Long.valueOf(_1L), categoryDTO.getId());
		assertEquals(JOE, categoryDTO.getName());
	}

}
