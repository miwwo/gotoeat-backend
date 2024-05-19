package com.project.crud.service.interfaces;

import com.project.crud.entity.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe createRecipe(Recipe recipe);

    Recipe getRecipeById(Long recipeId);

    List<Recipe> getAllRecipes();

    Recipe updateRecipe(Long recipeId, Recipe updatedRecipe);

    boolean deleteRecipe(Long recipeId);

    List<Recipe> getPublicRecipes();
}
