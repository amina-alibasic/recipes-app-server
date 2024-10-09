package com.app.recipes.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientDTO {
    private RecipeDTO recipe; // Reference to RecipeDTO
    private IngredientDTO ingredient; // Reference to IngredientDTO
    private String quantity; // Quantity of the ingredient

}
