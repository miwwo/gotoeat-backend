package com.project.crud.service;

import com.project.crud.entity.Ingredient;
import com.project.crud.entity.IngredientQuantity;
import com.project.crud.entity.Recipe;
import com.project.crud.entity.UserEntity;
import com.project.crud.exception.ResourceNotFoundException;
import com.project.crud.repository.IngredientQuantityRepository;
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
    private final IngredientQuantityRepository ingredientQuantityRepository;

    @Override
    @Transactional
    public Recipe createRecipe(Recipe recipe, UserEntity owner) {
        // Сохраняем рецепт в базе данных
        recipe.setOwnerId(owner.getId());
        Recipe savedRecipe = recipeRepository.save(recipe);

        // Для каждого IngredientQuantity в рецепте
        for (IngredientQuantity ingredientQuantity : recipe.getIngredientsQuantity()) {
            // Устанавливаем ссылку на этот рецепт
            ingredientQuantity.setRecipe(savedRecipe);

            // Проверяем, существует ли ингредиент с данным ID
            Ingredient ingredient = ingredientRepository.findById(ingredientQuantity.getIngredient().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ingredient does not exist with given id: " + ingredientQuantity.getIngredient().getId()));
            if (ingredient == null)
                return null;
            // Устанавливаем ссылку на ингредиент
            ingredientQuantity.setIngredient(ingredient);

            // Сохраняем IngredientQuantity в базе данных
            ingredientQuantityRepository.save(ingredientQuantity);
        }

        // Устанавливаем список IngredientQuantity для рецепта
        savedRecipe.setIngredientsQuantity(recipe.getIngredientsQuantity());

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
    public Recipe updateRecipe(Long recipeId, Recipe updatedRecipeDTO) {
        Recipe recipeToUpdate = recipeRepository.findById(recipeId).
                orElseThrow(() -> new ResourceNotFoundException("Recipe does not exist with given id: " + recipeId));
        if (recipeToUpdate == null)
            return null;
        if (updatedRecipeDTO.getName() != null)
            recipeToUpdate.setName(updatedRecipeDTO.getName());
        if (updatedRecipeDTO.getDescription() != null)
            recipeToUpdate.setDescription(updatedRecipeDTO.getDescription());
        if (updatedRecipeDTO.getIngredientsQuantity() != null) {
            ingredientQuantityRepository.deleteAllByRecipe(recipeToUpdate);
            for (IngredientQuantity ingredientQuantity : updatedRecipeDTO.getIngredientsQuantity()) {
                // Устанавливаем ссылку на этот рецепт
                ingredientQuantity.setRecipe(recipeToUpdate);
                // Проверяем, существует ли ингредиент с данным ID
                Ingredient ingredient = ingredientRepository.findById(ingredientQuantity.getIngredient().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Ingredient does not exist with given id: " + ingredientQuantity.getIngredient().getId()));
                if (ingredient == null)
                    return null;

                // Устанавливаем ссылку на ингредиент
                ingredientQuantity.setIngredient(ingredient);

                // Сохраняем IngredientQuantity в базе данных
                ingredientQuantityRepository.save(ingredientQuantity);
            }
            recipeToUpdate.setIngredientsQuantity(updatedRecipeDTO.getIngredientsQuantity());
        }
        if (updatedRecipeDTO.isVisible() != recipeToUpdate.isVisible())
            recipeToUpdate.setVisible(updatedRecipeDTO.isVisible());

        return recipeRepository.save(recipeToUpdate);
    }

    @Override
    public boolean deleteRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).
                orElseThrow(() -> new ResourceNotFoundException("Recipe does not exist with given id: " + recipeId));

        recipeRepository.deleteById(recipeId);
        return false;
    }


}
