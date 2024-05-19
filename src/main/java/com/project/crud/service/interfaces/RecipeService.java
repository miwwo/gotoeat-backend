package com.project.crud.service.interfaces;

import com.project.crud.dto.RecipeDTO;
import com.project.crud.entity.Recipe;
import com.project.crud.entity.UserEntity;

import java.util.List;

public interface RecipeService {
    Recipe createRecipe(Recipe recipe, UserEntity owner);

    Recipe getRecipeById(Long recipeId);

    List<Recipe> getAllRecipes();

    Recipe updateRecipe(Long recipeId, Recipe updatedRecipe);

    boolean deleteRecipe(Long recipeId);

    List<Recipe> getVisibleRecipes();
}
