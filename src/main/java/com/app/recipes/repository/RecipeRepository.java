package com.app.recipes.repository;

import com.app.recipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value = "SELECT r FROM Recipe r " +
            "WHERE (:name IS NULL OR r.name ILIKE %:name%) " +
            "AND (:categoryId IS NULL OR r.category.id = :categoryId) " +
            "ORDER BY " +
            "CASE WHEN :orderBy = 'NAME' AND :orderType = 'ASC' THEN r.name END ASC, " +
            "CASE WHEN :orderBy = 'NAME' AND :orderType = 'DESC' THEN r.name END DESC, " +
            "CASE WHEN :orderBy = 'DATE' AND :orderType = 'ASC' THEN r.dateAdded END ASC, " +
            "CASE WHEN :orderBy = 'DATE' AND :orderType = 'DESC' THEN r.dateAdded END DESC")
    List<Recipe> findRecipesBy(@Param("orderBy") String orderBy,
                               @Param("orderType") String orderType,
                               @Param("name") String name,
                               @Param("categoryId") Long categoryId
    );
}
