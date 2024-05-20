package com.project.crud.service.interfaces;

import com.project.crud.entity.ShoppingList;
import com.project.crud.entity.UserEntity;

import java.util.List;

public interface ShoppingListService {

    void initializeShoppingList(UserEntity user);

    ShoppingList getActiveShoppingList(UserEntity user);

    ShoppingList addRecipeToShoppingList(UserEntity userEntity, Long recipeId);

    ShoppingList removeRecipeFromShoppingList(UserEntity userEntity, Long recipeId);

    ShoppingList completeShoppingList(UserEntity userEntity);

    List<ShoppingList> getShoppingListHistory(UserEntity userEntity);

    List<ShoppingList> getAllShoppingLists();

    ShoppingList getShoppingListById(Long id);

    ShoppingList createShoppingList(ShoppingList shoppingList, UserEntity owner);

    ShoppingList updateShoppingList(Long id, ShoppingList shoppingList);

    boolean deleteShoppingList(Long id);
}
