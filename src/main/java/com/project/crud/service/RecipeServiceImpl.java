package com.project.crud.service;

import com.project.crud.entity.Recipe;
import com.project.crud.exception.ResourceNotFoundException;
import com.project.crud.repository.RecipeRepository;
import com.project.crud.service.interfaces.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepository;
    @Override
    public Recipe createRecipe(Recipe recipe) {

        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe getRecipeById(Long recipeId) {

        return recipeRepository.findById(recipeId).
                orElseThrow(() -> new ResourceNotFoundException("Recipe does not exist with given id: " + recipeId));
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe updateRecipe(Long recipeId, Recipe updatedRecipeDTO) {
        Recipe recipe = recipeRepository.findById(recipeId).
                orElseThrow(() -> new ResourceNotFoundException("Recipe does not exist with given id: " + recipeId));

        recipe.setName(updatedRecipeDTO.getName());

        return recipeRepository.save(recipe);
    }

    @Override
    public boolean deleteRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).
                orElseThrow(() -> new ResourceNotFoundException("Recipe does not exist with given id: " + recipeId));

        recipeRepository.deleteById(recipeId);
        return false;
    }
}
