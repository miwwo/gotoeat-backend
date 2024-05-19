package com.project.crud.service.interfaces;

import com.project.crud.entity.ShoppingList;

import java.util.List;

public interface ShoppingListService {
    List<ShoppingList> getAllShoppingLists();

    ShoppingList getShoppingListById(Long id);

    ShoppingList createShoppingList(ShoppingList shoppingList);

    ShoppingList updateShoppingList(Long id, ShoppingList shoppingList);

    boolean deleteShoppingList(Long id);
}
