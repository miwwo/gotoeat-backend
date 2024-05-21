package com.project.crud.service;

import com.project.crud.entity.Ingredient;
import com.project.crud.entity.RecipeIngredient;
import com.project.crud.entity.Recipe;
import com.project.crud.entity.UserEntity;
import com.project.crud.exception.ResourceNotFoundException;
import com.project.crud.repository.RecipeIngredientRepository;
import com.project.crud.repository.IngredientRepository;
import com.project.crud.repository.RecipeRepository;
import com.project.crud.repository.UserRepository;
import com.project.crud.service.interfaces.RecipeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Override
    @Transactional
    public Recipe createRecipe(Recipe recipe, UserEntity owner) {
        // Сохраняем рецепт в базе данных
        recipe.setOwnerId(owner.getId());
        if (recipe.getDescription() == null) recipe.setDescription("");
        Recipe savedRecipe = recipeRepository.save(recipe);

        // Для каждого RecipeIngredient в рецепте
        if (recipe.getRecipeIngredients() != null) {
            for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()) {
                // Устанавливаем ссылку на этот рецепт
                recipeIngredient.setRecipe(savedRecipe);

                // Проверяем, существует ли ингредиент с данным ID
                Ingredient ingredient = ingredientRepository.findById(recipeIngredient.getIngredient().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Ingredient does not exist with given id: " + recipeIngredient.getIngredient().getId()));
                if (ingredient == null)
                    return null;
                // Устанавливаем ссылку на ингредиент
                recipeIngredient.setIngredient(ingredient);

                // Сохраняем RecipeIngredient в базе данных
                recipeIngredientRepository.save(recipeIngredient);
            }

            // Устанавливаем список RecipeIngredient для рецепта
            savedRecipe.setRecipeIngredients(recipe.getRecipeIngredients());
        }

        // Сохраняем рецепт еще раз и возвращаем его
        return recipeRepository.save(savedRecipe);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> getVisibleRecipes() {
        return recipeRepository.findAllByVisible(true);
    }

    @Override
    public Recipe getVisibleRecipeById(Long recipeId, Boolean visible) {
        return recipeRepository.findRecipeByIdAndVisible(recipeId, visible);
    }

    @Override
    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findRecipeById(recipeId);
    }


    @Override
    @Transactional
    public Recipe updateRecipe(Long recipeId, Recipe updatedRecipeDTO) {
        Recipe recipeToUpdate = recipeRepository.findById(recipeId).
                orElseThrow(() -> new ResourceNotFoundException("Recipe does not exist with given id: " + recipeId));

        if (updatedRecipeDTO.getName() != null)
            recipeToUpdate.setName(updatedRecipeDTO.getName());
        if (updatedRecipeDTO.getDescription() != null)
            recipeToUpdate.setDescription(updatedRecipeDTO.getDescription());
        if (updatedRecipeDTO.getVisible() != null &&
                updatedRecipeDTO.getVisible() != recipeToUpdate.getVisible())
            recipeToUpdate.setVisible(updatedRecipeDTO.getVisible());

        if (updatedRecipeDTO.getRecipeIngredients() != null) {
            recipeIngredientRepository.deleteAllByRecipe(recipeToUpdate);
            for (RecipeIngredient recipeIngredient : updatedRecipeDTO.getRecipeIngredients()) {
                // Устанавливаем ссылку на этот рецепт
                recipeIngredient.setRecipe(recipeToUpdate);
                // Проверяем, существует ли ингредиент с данным ID
                Ingredient ingredient = ingredientRepository.findById(recipeIngredient.getIngredient().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Ingredient does not exist with given id: " + recipeIngredient.getIngredient().getId()));
                if (ingredient == null)
                    return null;

                // Устанавливаем ссылку на ингредиент
                recipeIngredient.setIngredient(ingredient);

                // Сохраняем RecipeIngredient в базе данных
                recipeIngredientRepository.save(recipeIngredient);
            }
            recipeToUpdate.setRecipeIngredients(updatedRecipeDTO.getRecipeIngredients());
        }

        return recipeRepository.save(recipeToUpdate);
    }

    @Override
    public boolean deleteRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);
        return true;
    }


}
