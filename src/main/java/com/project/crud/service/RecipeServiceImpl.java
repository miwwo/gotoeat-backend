package com.project.crud.service;

import com.project.crud.dto.RecipeDTO;
import com.project.crud.entity.Recipe;
import com.project.crud.exception.ResourceNotFoundException;
import com.project.crud.mapper.RecipeMapper;
import com.project.crud.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService{
    private RecipeRepository recipeRepository;
    @Override
    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = RecipeMapper.mapToRecipe(recipeDTO);
        Recipe savedRecipe = recipeRepository.save(recipe);

        return RecipeMapper.mapToRecipeDTO(savedRecipe);
    }

    @Override
    public RecipeDTO getRecipeById(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).
                orElseThrow(() -> new ResourceNotFoundException("Recipe does not exist with given id: " + recipeId));

        return RecipeMapper.mapToRecipeDTO(recipe);
    }

    @Override
    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(RecipeMapper::mapToRecipeDTO).collect(Collectors.toList());
    }

    @Override
    public RecipeDTO updateRecipe(Long recipeId, RecipeDTO updatedRecipeDTO) {
        Recipe recipe = recipeRepository.findById(recipeId).
                orElseThrow(() -> new ResourceNotFoundException("Recipe does not exist with given id: " + recipeId));

        recipe.setName(updatedRecipeDTO.getName());

        Recipe updatedRecipeObj = recipeRepository.save(recipe);

        return RecipeMapper.mapToRecipeDTO(updatedRecipeObj);
    }

    @Override
    public void deleteRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).
                orElseThrow(() -> new ResourceNotFoundException("Recipe does not exist with given id: " + recipeId));

        recipeRepository.deleteById(recipeId);
    }
}
