package com.app.recipes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @ColumnDefault("nextval('recipe_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "servings", nullable = false)
    private Integer servings;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "preparation_instruction", nullable = false, length = Integer.MAX_VALUE)
    private String preparationInstruction;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

}