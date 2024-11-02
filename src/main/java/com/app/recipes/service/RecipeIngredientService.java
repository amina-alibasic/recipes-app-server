package com.app.recipes.service;

import com.app.recipes.dto.IngredientDTO;
import com.app.recipes.dto.RecipeDTO;
import com.app.recipes.entity.Ingredient;
import com.app.recipes.entity.RecipeIngredient;
import com.app.recipes.entity.RecipeIngredientId;
import com.app.recipes.helper.RecipeMapper;
import com.app.recipes.repository.IngredientRepository;
import com.app.recipes.repository.RecipeIngredientsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class RecipeIngredientService {
    private final IngredientService ingredientService;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientsRepository recipeIngredientsRepository;

    public RecipeIngredientService(IngredientService ingredientService, IngredientRepository ingredientRepository, RecipeIngredientsRepository recipeIngredientsRepository) {
        this.ingredientService = ingredientService;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientsRepository = recipeIngredientsRepository;
    }

    @Transactional
    public void saveRecipeIngredients(RecipeDTO recipeDTO) {
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(RecipeMapper.INSTANCE.toEntity(recipeDTO));

        // init recipeIngredientId
        RecipeIngredientId recipeIngredientId = new RecipeIngredientId();
        recipeIngredientId.setRecipeId(recipeDTO.getId());

        // for every ingredient, check if it exists in the database already (checking by name)
        for (IngredientDTO ingredientDTO : recipeDTO.getIngredients()) {
            String ingredientQuantity = ingredientDTO.getQuantity();
            if (!ingredientService.isIngredientPresent(ingredientDTO)) {
                // ingredient not present - create a new Ingredient record
                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientDTO.getName());
                ingredient.setQuantity(ingredientQuantity);
                ingredientRepository.save(ingredient);
                // set new ingredient to the RecipeIngredient record
                recipeIngredient.setIngredient(ingredient);
                recipeIngredient.setQuantity(ingredientQuantity);
                // set new ingredient id
                recipeIngredientId.setIngredientId(ingredient.getId());
            } else {
                // ingredient already present in the DB
                // find it
                Ingredient ingredient = ingredientRepository.findByName(ingredientDTO.getName());
                ingredient.setQuantity(ingredientQuantity);
                recipeIngredient.setIngredient(ingredient);
                // set the quantity from recipe - not from ingredient table
                recipeIngredient.setQuantity(ingredientQuantity);
                // set existing ingredient id
                recipeIngredientId.setIngredientId(ingredientDTO.getId());
            }
            recipeIngredient.setId(recipeIngredientId);
            recipeIngredientsRepository.save(recipeIngredient);
        }
    }
}
