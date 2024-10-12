package com.app.recipes.helper;

import com.app.recipes.dto.RecipeDTO;
import com.app.recipes.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeDTO toDto(Recipe recipe);

    Recipe toEntity(RecipeDTO recipeDTO);
}