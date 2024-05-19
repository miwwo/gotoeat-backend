package com.project.crud.service.interfaces;

import com.project.crud.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getAllIngredients();

    Ingredient getIngredientById(Long id);

    Ingredient createIngredient(Ingredient ingredient);

    Ingredient updateIngredient(Long id, Ingredient ingredient);

    boolean deleteIngredient(Long id);

    List<Ingredient> suggestIngredientsByPrefix(String prefix);
}
