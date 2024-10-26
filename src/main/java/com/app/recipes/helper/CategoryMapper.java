package com.app.recipes.helper;

import com.app.recipes.dto.CategoryDTO;
import com.app.recipes.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO toDto(Category category);

    Category toEntity(CategoryDTO categoryDTO);
}
