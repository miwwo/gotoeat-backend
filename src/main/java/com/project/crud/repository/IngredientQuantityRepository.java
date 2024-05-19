package com.project.crud.repository;

import com.project.crud.entity.Ingredient;
import com.project.crud.entity.IngredientQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientQuantityRepository extends JpaRepository<IngredientQuantity, Long> {
}
