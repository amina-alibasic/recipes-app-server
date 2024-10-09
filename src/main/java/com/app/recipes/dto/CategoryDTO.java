package com.app.recipes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private Set<RecipeDTO> recipes;


    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }


}
