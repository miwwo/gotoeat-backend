package com.project.crud.service;

import com.project.crud.entity.*;
import com.project.crud.repository.RecipeRepository;
import com.project.crud.repository.ShoppingListIngredientRepository;
import com.project.crud.repository.ShoppingListRepository;
import com.project.crud.service.interfaces.ShoppingListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingListServiceImpl implements ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final RecipeRepository recipeRepository;
    private final ShoppingListIngredientRepository shoppingListIngredientRepository;
    @Override
    public void initializeShoppingList(UserEntity user) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setUser(user);
        shoppingList.setActive(true);
        shoppingListRepository.save(shoppingList);
    }

    @Override
    public ShoppingList getActiveShoppingList(UserEntity user) {
        return shoppingListRepository.findByUserAndActive(user, true);
    }

    @Override
    @Transactional
    public ShoppingList addRecipeToShoppingList(UserEntity userEntity, Long recipeId) {
        ShoppingList shoppingList = getActiveShoppingList(userEntity);
        Recipe foundRecipe = recipeRepository.findRecipeById(recipeId);
        if (shoppingList == null || foundRecipe == null) {
            return null;
        }

        for (RecipeIngredient recipeIngredient : foundRecipe.getRecipeIngredients()) {
            String ingredientName = recipeIngredient.getIngredient().getName();
            Integer quantity = recipeIngredient.getQuantity();

            ShoppingListIngredient shoppingListIngredient = shoppingList.getShoppingListIngredients().stream()
                    .filter(i -> i.getIngredient().getName().equals(ingredientName))
                    .findFirst()
                    .orElse(null);

            if (shoppingListIngredient == null) {
                shoppingListIngredient = new ShoppingListIngredient();
                shoppingListIngredient.setIngredient(recipeIngredient.getIngredient());
                shoppingListIngredient.setQuantity(quantity);
                shoppingListIngredient.setShoppingList(shoppingList);
                shoppingList.getShoppingListIngredients().add(shoppingListIngredient);
            } else {
                shoppingListIngredient.setQuantity(shoppingListIngredient.getQuantity() + quantity);
            }
            shoppingListIngredientRepository.save(shoppingListIngredient);
        }
        shoppingList.getSelectedRecipes().add(foundRecipe);

        return shoppingListRepository.save(shoppingList);
    }

    @Override
    @Transactional
    public ShoppingList removeRecipeFromShoppingList(UserEntity userEntity, Long recipeId) {
        ShoppingList shoppingList = getActiveShoppingList(userEntity);
        Recipe foundRecipe = recipeRepository.findRecipeById(recipeId);
        if (shoppingList == null || foundRecipe == null || !shoppingList.getSelectedRecipes().contains(foundRecipe)) {
            return null;
        }

        for (RecipeIngredient recipeIngredient : foundRecipe.getRecipeIngredients()) {
            String ingredientName = recipeIngredient.getIngredient().getName();
            Integer quantity = recipeIngredient.getQuantity();

            ShoppingListIngredient shoppingListIngredient = shoppingList.getShoppingListIngredients().stream()
                    .filter(i -> i.getIngredient().getName().equals(ingredientName))
                    .findFirst()
                    .orElse(null);

            if (shoppingListIngredient != null) {
                int newQuantity = shoppingListIngredient.getQuantity() - quantity;
                if (newQuantity <= 0) {
                    shoppingList.getShoppingListIngredients().remove(shoppingListIngredient);
                    shoppingListIngredientRepository.delete(shoppingListIngredient);
                } else {
                    shoppingListIngredient.setQuantity(newQuantity);
                    shoppingListIngredientRepository.save(shoppingListIngredient);
                }
            }
        }
        shoppingList.getSelectedRecipes().remove(foundRecipe);
        return shoppingListRepository.save(shoppingList);
    }

    @Override
    public ShoppingList completeShoppingList(UserEntity userEntity) {
        ShoppingList shoppingList = getActiveShoppingList(userEntity);
        if (shoppingList == null) {
            return null;
        }
        shoppingList.setActive(false);
        shoppingListRepository.save(shoppingList);
        initializeShoppingList(userEntity);
        return shoppingList;
    }

    @Override
    public List<ShoppingList> getShoppingListHistory(UserEntity userEntity) {
        return shoppingListRepository.findAllByUser(userEntity);
    }

    @Override
    public List<ShoppingList> getAllShoppingLists() {
        return null;
    }

    @Override
    public ShoppingList getShoppingListById(Long id) {
        return null;
    }

    @Override
    public ShoppingList createShoppingList(ShoppingList shoppingList, UserEntity owner) {
        return null;
    }

    @Override
    public ShoppingList updateShoppingList(Long id, ShoppingList shoppingList) {
        return null;
    }

    @Override
    public boolean deleteShoppingList(Long id) {
        return false;
    }
}
