package com.app.recipes.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private Long id;
    private String name;
    private Integer servings;
    private CategoryDTO category; // Reference to CategoryDTO
    private Set<RecipeIngredientDTO> recipeIngredients; // List of ingredients for this recipe

}
