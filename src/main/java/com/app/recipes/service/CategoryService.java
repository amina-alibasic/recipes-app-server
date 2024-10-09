package com.app.recipes.service;

import com.app.recipes.dto.CategoryDTO;
import com.app.recipes.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(category -> new CategoryDTO(category.getId(), category.getName()))
                .orElse(null);
    }
}
