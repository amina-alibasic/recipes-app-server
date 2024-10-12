package com.app.recipes.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
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
    private Set<IngredientDTO> ingredients; // List of ingredients for this recipe
    private String preparationSteps;
    private LocalDateTime dateAdded;

}
