package com.app.recipes.helper;

import com.app.recipes.dto.IngredientDTO;
import com.app.recipes.entity.Ingredient;
import org.mapstruct.factory.Mappers;

public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    IngredientDTO toDto(Ingredient ingredient);

    Ingredient toEntity(IngredientDTO ingredientDTO);
}
