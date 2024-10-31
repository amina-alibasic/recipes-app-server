package com.app.recipes.controller;

import com.app.recipes.dto.RecipeDTO;
import com.app.recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/all")
    public ResponseEntity<List<RecipeDTO>> getAllRecipes(
            @RequestParam(value = "sortBy", defaultValue = "date") String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "DESC") String sortOrder,
            @RequestParam(value = "searchValue", required = false) String searchValue,
            @RequestParam(value = "categoryIds", required = false) List<Integer> categoryIds,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        List<RecipeDTO> recipes = recipeService.getAll(sortBy, sortOrder, searchValue, categoryIds, page, size);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable("id") Long id) {
        RecipeDTO recipe = recipeService.getRecipeById(id);
        return recipe != null ? ResponseEntity.ok(recipe) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<RecipeDTO> saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        RecipeDTO savedRecipe = recipeService.saveRecipe(recipeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);
    }
}
