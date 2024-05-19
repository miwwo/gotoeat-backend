package com.project.crud.service;

import com.project.crud.entity.Ingredient;
import com.project.crud.service.interfaces.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    @Override
    public List<Ingredient> getAllIngredients() {
        return null;
    }

    @Override
    public Ingredient getIngredientById(Long id) {
        return null;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        return null;
    }

    @Override
    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        return null;
    }

    @Override
    public boolean deleteIngredient(Long id) {
        return false;
    }

    @Override
    public List<Ingredient> suggestIngredientsByPrefix(String prefix) {
        return null;
    }
}
