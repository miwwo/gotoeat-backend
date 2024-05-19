package com.project.crud.repository;

import com.project.crud.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Object> findById(Ingredient key);
}
