package com.app.recipes.repository;

import com.app.recipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByOrderByNameAsc();
    List<Recipe> findByName(String name);
}
