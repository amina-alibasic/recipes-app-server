package com.app.recipes.repository;

import com.app.recipes.entity.Ingredient;
import com.app.recipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RecipeIngredientsRepository extends JpaRepository<Recipe, Ingredient> {
    @Query(value = "SELECT i FROM Ingredient i " +
            "JOIN RecipeIngredient ri on ri.ingredient.id = i.id " +
            "WHERE ri.recipe.id = :recipeId")
    List<Ingredient> findRecipeIngredients(@Param("recipeId") Long recipeId);
}
