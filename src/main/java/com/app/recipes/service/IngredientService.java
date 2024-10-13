package com.app.recipes.service;

import com.app.recipes.dto.IngredientDTO;
import com.app.recipes.entity.Ingredient;
import com.app.recipes.helper.IngredientMapper;
import com.app.recipes.repository.RecipeIngredientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private final RecipeIngredientsRepository recipeIngredientsRepository;

    public IngredientService(RecipeIngredientsRepository recipeIngredientsRepository) {
        this.recipeIngredientsRepository = recipeIngredientsRepository;
    }

    public List<IngredientDTO> getRecipeIngredients(Long recipeId) {
        return mapListToDTO(recipeIngredientsRepository.findRecipeIngredients(recipeId));
    }

    private List<IngredientDTO> mapListToDTO(List<Ingredient> ingredients) {
            return ingredients.stream()
                    .map(IngredientMapper.INSTANCE::toDto)
                    .collect(Collectors.toList());
    }
}
