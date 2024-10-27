package com.app.recipes.repository;

import com.app.recipes.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value = "SELECT r FROM Recipe r " +
            "WHERE (:name IS NULL OR r.name ILIKE :name) " +
            "AND (:categoryId IS NULL OR r.category.id = :categoryId)"
    )
    Page<Recipe> findRecipesBy(@Param("name") String name,
                               @Param("categoryId") Integer categoryId,
                               Pageable pageable
    );

}
