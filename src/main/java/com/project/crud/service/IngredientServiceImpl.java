package com.project.crud.service;

import com.project.crud.entity.Ingredient;
import com.project.crud.repository.IngredientRepository;
import com.project.crud.service.interfaces.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
    @Override
    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }


    @Override
    public Ingredient updateIngredient(Long id, Ingredient ingredient) {
        if(ingredientRepository.existsById(id)){
            ingredient.setId(id);
            return ingredientRepository.save(ingredient);
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(Long id) {
        boolean existed = ingredientRepository.existsById(id);
        boolean deleted = false;
        if(existed){
            ingredientRepository.deleteById(id);
            deleted = true;
            return deleted;
        }
        return deleted;
    }

    /*@Override
    public List<Ingredient> suggestIngredientsByPrefix(String prefix) {
        return null;
    }*/
}
