package com.project.crud.service;

import com.project.crud.dto.RecipeDTO;
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
    public Recipe createRecipe(RecipeDTO recipeDTO, UserEntity owner) {
        Recipe recipeToCreate = new Recipe();
        recipeToCreate.setName(recipeDTO.getName());
        recipeToCreate.setDescription(recipeDTO.getDescription());
        recipeToCreate.setVisible(recipeDTO.isVisible());
        recipeToCreate.setOwnerId(owner.getId());

        recipeRepository.save(recipeToCreate);

        for (IngredientQuantity iQDTO : recipeDTO.getIngredientQuantity()) {
            if (ingredientRepository.findById(iQDTO.getIngredientId()).isEmpty())
                return null;

            IngredientQuantity newIQ = new IngredientQuantity();
            newIQ.setIngredientId(iQDTO.getIngredientId());
            newIQ.setQuantity(iQDTO.getQuantity());
            newIQ.setRecipeId(recipeToCreate.getId());
            ingredientQuantityRepository.save(newIQ);
        }
        return recipeToCreate;
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
    public List<Recipe> getVisibleRecipes() {
        return recipeRepository.findAllByVisible(true);
    }
}
