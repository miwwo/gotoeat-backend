package com.project.crud.service;

import com.project.crud.entity.ShoppingList;
import com.project.crud.service.interfaces.ShoppingListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {
    @Override
    public List<ShoppingList> getAllShoppingLists() {
        return null;
    }

    @Override
    public ShoppingList getShoppingListById(Long id) {
        return null;
    }

    @Override
    public ShoppingList createShoppingList(ShoppingList shoppingList) {
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
