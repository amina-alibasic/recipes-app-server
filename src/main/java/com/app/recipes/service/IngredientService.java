package com.app.recipes.service;

import com.app.recipes.dto.IngredientDTO;
import com.app.recipes.dto.RecipeDTO;
import com.app.recipes.entity.Ingredient;
import com.app.recipes.entity.RecipeIngredient;
import com.app.recipes.helper.IngredientMapper;
import com.app.recipes.helper.RecipeMapper;
import com.app.recipes.repository.IngredientRepository;
import com.app.recipes.repository.RecipeIngredientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private final RecipeIngredientsRepository recipeIngredientsRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientService(RecipeIngredientsRepository recipeIngredientsRepository, IngredientRepository ingredientRepository) {
        this.recipeIngredientsRepository = recipeIngredientsRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<IngredientDTO> getRecipeIngredients(Long recipeId) {
        return mapListToDTO(recipeIngredientsRepository.findRecipeIngredients(recipeId));
    }



    public Boolean isIngredientPresent(IngredientDTO ingredientDTO) {
        return ingredientRepository.findByName(ingredientDTO.getName()) != null;
    }

    private List<IngredientDTO> mapListToDTO(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(IngredientMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
