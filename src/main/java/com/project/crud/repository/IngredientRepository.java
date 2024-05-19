package com.project.crud.repository;

import com.project.crud.entity.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    Optional<Object> findById(Ingredient key);
}
