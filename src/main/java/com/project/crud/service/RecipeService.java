package com.project.crud.service;

import com.project.crud.dto.RecipeDTO;

import java.util.List;

public interface RecipeService {
    RecipeDTO createRecipe(RecipeDTO recipeDTO);

    RecipeDTO getRecipeById(Long recipeId);

    List<RecipeDTO> getAllRecipes();

    RecipeDTO updateRecipe(Long recipeId, RecipeDTO updatedRecipeDTO);

    void deleteRecipe(Long recipeId);
}
