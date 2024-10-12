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
    private CategoryDTO category;
    private Set<RecipeIngredientDTO> recipeIngredients;
    private String preparationInstruction;
    private LocalDateTime dateAdded;

}
