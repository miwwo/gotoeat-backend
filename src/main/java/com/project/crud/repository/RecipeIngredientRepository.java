package com.project.crud.repository;

import com.project.crud.entity.RecipeIngredient;
import com.project.crud.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
    List<RecipeIngredient> findAllByRecipe(Recipe recipe);
    void deleteAllByRecipe(Recipe recipe);
}
