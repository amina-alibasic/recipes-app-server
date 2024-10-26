package com.app.recipes.service;

import com.app.recipes.dto.RecipeDTO;
import com.app.recipes.entity.Recipe;
import com.app.recipes.helper.RecipeMapper;
import com.app.recipes.repository.CategoryRepository;
import com.app.recipes.repository.RecipeIngredientsRepository;
import com.app.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<RecipeDTO> getAll(String orderBy, String orderType, String searchValue, Long categoryId) {
        //  Validation for ordering
        orderBy = validateOrderBy(orderBy);
        orderType = validateOrderType(orderType);
        List<Recipe> recipes = recipeRepository.findRecipesBy(orderBy, orderType, searchValue, categoryId);
        return mapListToDTO(recipes);
    }

    @Transactional
    public RecipeDTO saveRecipe(RecipeDTO recipeDTO) throws IllegalArgumentException {
        validateRecipeDTO(recipeDTO);
        Recipe recipe = RecipeMapper.INSTANCE.toEntity(recipeDTO);
        recipe.setDateAdded(LocalDateTime.now());
        recipeRepository.save(recipe);
        return recipeDTO;
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

    private List<RecipeDTO> mapListToDTO(List<Recipe> recipes) {
        return recipes.stream()
                .map(RecipeMapper.INSTANCE::toDto)
                // no need to set ingredients on get all recipes
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
    }
}
