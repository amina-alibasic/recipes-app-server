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
            @RequestParam(value = "orderBy", defaultValue = "DATE") String orderBy,
            @RequestParam(value = "orderType", defaultValue = "DESC") String orderType,
            @RequestParam(value = "searchValue", required = false) String searchValue,
            @RequestParam(value = "categoryId", required = false) Long categoryId) {
        List<RecipeDTO> recipes = recipeService.getAll(orderBy, orderType, searchValue, categoryId);
        return ResponseEntity.ok(recipes);
    }

    @PostMapping
    public ResponseEntity<RecipeDTO> saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        RecipeDTO savedRecipe = recipeService.saveRecipe(recipeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);
    }
}
