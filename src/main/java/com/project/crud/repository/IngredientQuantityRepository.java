package com.project.crud.repository;

import com.project.crud.entity.IngredientQuantity;
import com.project.crud.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientQuantityRepository extends JpaRepository<IngredientQuantity, Long> {
    List<IngredientQuantity> findAllByRecipe(Recipe recipe);
    void deleteAllByRecipe(Recipe recipe);
}
