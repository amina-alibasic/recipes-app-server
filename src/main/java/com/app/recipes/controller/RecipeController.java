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

    @GetMapping("/all")
    public ResponseEntity<List<RecipeDTO>> getAllRecipes(
            @RequestParam(value = "orderBy", defaultValue = "DATE") String orderBy,
            @RequestParam(value = "orderType", defaultValue = "DESC") String orderType,
            @RequestParam(value = "searchBy", required = false) String searchBy,
            @RequestParam(value = "searchValue", required = false) String searchValue) {
        List<RecipeDTO> recipes = recipeService.getAll(orderBy, orderType, searchBy, searchValue);
        return ResponseEntity.ok(recipes);
    }
}
