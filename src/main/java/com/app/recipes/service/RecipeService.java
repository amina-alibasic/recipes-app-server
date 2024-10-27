package com.app.recipes.service;

import com.app.recipes.dto.RecipeDTO;
import com.app.recipes.entity.Recipe;
import com.app.recipes.helper.RecipeMapper;
import com.app.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private CategoryService categoryService;

    public List<RecipeDTO> getAll(String sortBy, String sortOrder, String searchValue, List<Integer> categoryIds, Integer page, Integer size) {
        //  Validation for ordering
        sortOrder = validateSortOrder(sortOrder);
        sortBy = validateSortBy(sortBy);
        // Handle sorting (filtering) and lazy loading
        Sort.Direction direction = Sort.Direction.fromString(sortOrder);
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Recipe> recipes = recipeRepository.findRecipesBy(searchValue, categoryIds, pageable);
        return mapListToDTO(recipes.getContent());
    }

    public RecipeDTO getRecipeById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            RecipeDTO recipeDTO = RecipeMapper.INSTANCE.toDto(recipe.get());
            recipeDTO.setIngredients(ingredientService.getRecipeIngredients(recipeDTO.getId()));
            return recipeDTO;
        }
        return null;
    }

    @Transactional
    public RecipeDTO saveRecipe(RecipeDTO recipeDTO) throws IllegalArgumentException {
        validateRecipeDTO(recipeDTO);
        Recipe recipe = RecipeMapper.INSTANCE.toEntity(recipeDTO);
        recipe.setDate(LocalDateTime.now());
        recipeRepository.save(recipe);
        return recipeDTO;
    }

    private String validateSortOrder(String sortOrder) {
        if (!"ASC".equals(sortOrder) && !"DESC".equals(sortOrder)) {
            return "DESC"; // Default to DESC if invalid
        } else return sortOrder;
    }

    private String validateSortBy(String sortBy) {
        if (!"name".equals(sortBy) && !"date".equals(sortBy)) {
            return "date"; // Default to DATE if invalid
        } else return sortBy;
    }

    private List<RecipeDTO> mapListToDTO(List<Recipe> recipes) {
        return recipes.stream()
                .map(RecipeMapper.INSTANCE::toDto)
                // no need to set ingredients on get all recipes (for now)
//                .peek(recipeDTO -> recipeDTO.setIngredients(
//                        ingredientService.getRecipeIngredients(recipeDTO.getId()))
//                )
                .collect(Collectors.toList());
    }

    private void validateRecipeDTO(RecipeDTO recipeDTO) throws IllegalArgumentException {
        if (recipeDTO.getName() == null || recipeDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be null or empty.");
        }
        if (recipeDTO.getServings() == null || recipeDTO.getServings() <= 0) {
            throw new IllegalArgumentException("Servings must be a positive number.");
        }
        if (recipeDTO.getCategory() == null || recipeDTO.getCategory().getId() == null) {
            throw new IllegalArgumentException("Category must be provided.");
        }
        if (categoryService.getCategoryByName(recipeDTO.getCategory().getName()) == null) {
            throw new IllegalArgumentException("Category does not exist.");
        }
    }
}
