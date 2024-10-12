package com.app.recipes.repository;

import com.app.recipes.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getCategoryByName(String categoryName);
}
