package com.app.recipes.controller;

import com.app.recipes.dto.RecipeDTO;
import com.app.recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    /**
     * Get all recipes ordered alphabetically by name.
     *
     * @return a list of RecipeDTOs ordered by name.
     */
    @GetMapping("/all")
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
        List<RecipeDTO> recipes = recipeService.getAllRecipesOrderedByName();
        return ResponseEntity.ok(recipes);
    }

    /**
     * Search recipes by name (optional parameter).
     * If the name is not provided, returns all recipes ordered by name.
     *
     * @param name the optional name of the recipe to search.
     * @return a list of RecipeDTOs matching the search criteria.
     */
    @GetMapping("/search")
    public ResponseEntity<List<RecipeDTO>> searchRecipes(@RequestParam(value = "name", required = false) String name) {
        List<RecipeDTO> recipes;
        if (name != null && !name.isEmpty()) {
            recipes = recipeService.searchRecipesByName(name);
        } else {
            recipes = recipeService.getAllRecipesOrderedByName();
        }
        return ResponseEntity.ok(recipes);
    }
}
