package com.project.crud.repository;

import com.project.crud.entity.ShoppingListIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingListIngredientRepository extends JpaRepository<ShoppingListIngredient, Long> {
}
