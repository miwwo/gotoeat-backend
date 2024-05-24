package com.project.crud.repository;

import com.project.crud.entity.Recipe;
import com.project.crud.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByVisible(Boolean b);

    Recipe findRecipeByIdAndVisible(Long id, boolean visible);

    Recipe findRecipeById(Long recipeId);

    List<Recipe> findRecipesByOwnerId(Long id);

}
