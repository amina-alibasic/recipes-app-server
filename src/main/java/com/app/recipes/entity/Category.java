package com.app.recipes.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_gen")
    @SequenceGenerator(name = "category_id_gen", sequenceName = "category_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Recipe> recipes = new LinkedHashSet<>();
}
