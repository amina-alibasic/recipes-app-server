package com.app.recipes.service;

import com.app.recipes.dto.CategoryDTO;
import com.app.recipes.entity.Category;
import com.app.recipes.helper.CategoryMapper;
import com.app.recipes.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        return mapListToDTO(categoryRepository.findAll());
    }

    public CategoryDTO getCategoryById(Long id) {
        return CategoryMapper.INSTANCE.toDto(categoryRepository.findById(id).orElse(null));
    }

    public CategoryDTO getCategoryByName(String name) {
        return CategoryMapper.INSTANCE.toDto(categoryRepository.findCategoryByName(name));
    }

    private List<CategoryDTO> mapListToDTO(List<Category> categories) {
        return categories.stream()
                .map(CategoryMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
