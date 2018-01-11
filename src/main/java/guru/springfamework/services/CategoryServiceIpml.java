package guru.springfamework.services;

import java.util.List;
import java.util.stream.Collectors;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.repositories.CategoryRepository;

public class CategoryServiceIpml implements CategoryService {

	private final CategoryMapper categoryMapper;
	private final CategoryRepository categoryRepository;

	public CategoryServiceIpml(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
		this.categoryMapper = categoryMapper;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
				
		return categoryRepository.findAll()
			.stream()
			.map(category -> categoryMapper.categoryToCategoryDTO(category))
//			.map(categoryMapper::categoryToCategoryDTO)
			.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
				
		return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
	}

}
