package com.app.recipes.service;

import com.app.recipes.dto.IngredientDTO;
import com.app.recipes.dto.RecipeDTO;
import com.app.recipes.entity.Recipe;
import com.app.recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CategoryService categoryService;

    public List<RecipeDTO> getAllRecipesOrderedByName() {
        return recipeRepository.findAllByOrderByNameAsc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public List<RecipeDTO> searchRecipesByName(String name) {
        return recipeRepository.findByName(name)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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
                ingredientDTOs
        );
    }
}
