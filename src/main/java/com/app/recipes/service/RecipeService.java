package com.app.recipes.service;

import com.app.recipes.dto.IngredientDTO;
import com.app.recipes.dto.RecipeDTO;
import com.app.recipes.entity.Category;
import com.app.recipes.entity.Recipe;
import com.app.recipes.repository.CategoryRepository;
import com.app.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<RecipeDTO> getAll(String orderBy, String orderType, String searchValue, String categoryName) {
        //  Validation for ordering
        orderBy = validateOrderBy(orderBy);
        orderType = validateOrderType(orderType);
        // Check if filtering by category is present
        Long categoryId = null;
        if (categoryName != null) {
            Category category = categoryRepository.getCategoryByName(categoryName);
            if (category != null) {
                categoryId = category.getId();
            }
        }
        List<Recipe> recipes = recipeRepository.findRecipesBy(orderBy, orderType, searchValue, categoryId);
        return mapListToDTO(recipes);
    }

    private RecipeDTO convertToDTO(Recipe recipe) {
        Set<IngredientDTO> ingredientDTOs = recipe.getRecipeIngredients()
                .stream()
                .map(recipeIngredient -> new IngredientDTO(
                        recipeIngredient.getIngredient().getId(),
                        recipeIngredient.getIngredient().getName()
                ))
                .collect(Collectors.toSet());
        return new RecipeDTO(
                recipe.getId(),
                recipe.getName(),
                recipe.getServings(),
                categoryService.getCategoryById(recipe.getCategory().getId()),
                ingredientDTOs,
                recipe.getPreparationSteps(),
                recipe.getDateAdded()
        );
    }

    private String validateOrderType(String orderType) {
        if (!"ASC".equals(orderType) && !"DESC".equals(orderType)) {
            return "DESC"; // Default to DESC if invalid
        } else return orderType;
    }

    private String validateOrderBy(String orderBy) {
        if (!"NAME".equals(orderBy) && !"DATE".equals(orderBy)) {
            return "DATE"; // Default to DATE if invalid
        } else return orderBy;
    }

    private String validateSearchBy(String searchBy) {
        if (!"CATEGORY".equals(searchBy) && !"NAME".equals(searchBy)) {
            return "NAME"; // Default to NAME if invalid
        } else return searchBy;
    }

    private List<RecipeDTO> mapListToDTO(List<Recipe> recipes) {
        return recipes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
