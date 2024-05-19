package com.project.crud.service;

import com.project.crud.entity.Ingredient;
import com.project.crud.entity.Recipe;
import com.project.crud.entity.UserEntity;
import com.project.crud.exception.ResourceNotFoundException;
import com.project.crud.repository.IngredientRepository;
import com.project.crud.repository.RecipeRepository;
import com.project.crud.repository.UserRepository;
import com.project.crud.service.interfaces.RecipeService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    @Transactional
    public Recipe createRecipe(Recipe recipe) {
        UserEntity user = userRepository.findById(recipe.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Recipe recipeToCreate = new Recipe();
        recipeToCreate.setName(recipe.getName());
        recipeToCreate.setDescription(recipe.getDescription());
        recipeToCreate.setPublic(recipe.isPublic());
        recipeToCreate.setOwner(user);

        Map<Ingredient, Integer> ingredients = new HashMap<>();
        for (Map.Entry<Ingredient, Integer> entry : recipe.getIngredients().entrySet()) {
            Ingredient ingredient = (Ingredient) ingredientRepository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Ingredient not found"));
            ingredients.put(ingredient, entry.getValue());
        }
        recipe.setIngredients(ingredients);

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

    @Override
    public List<Recipe> getPublicRecipes() {
        return recipeRepository.findAllByIsPublic(true);
    }
}
