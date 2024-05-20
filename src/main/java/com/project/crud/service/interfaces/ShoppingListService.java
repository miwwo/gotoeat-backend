package com.project.crud.service.interfaces;

import com.project.crud.entity.ShoppingList;
import com.project.crud.entity.UserEntity;

import java.util.List;

public interface ShoppingListService {
    List<ShoppingList> getAllShoppingLists();

    ShoppingList getShoppingListById(Long id);

    ShoppingList createShoppingList(ShoppingList shoppingList, UserEntity owner);

    ShoppingList updateShoppingList(Long id, ShoppingList shoppingList);

    boolean deleteShoppingList(Long id);
}
