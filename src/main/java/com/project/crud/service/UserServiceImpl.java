package com.project.crud.service;

import com.project.crud.entity.Recipe;
import com.project.crud.entity.ShoppingList;
import com.project.crud.entity.UserEntity;
import com.project.crud.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public UserEntity getUserById(Long id) {
        return null;
    }

    @Override
    public Optional<UserEntity> getUserByUsername(String username) {
        return Optional.empty();
    }

    public List<UserEntity> getAllUsers() {
            return null;
    }

    @Override
    public ShoppingList getShoppingListByUser(Long id) {
        return null;
    }

    @Override
    public List<Recipe> getRecipesByUser(Long id) {
        return null;
    }

    public UserEntity createUser(UserEntity user) {
            return null;
    }

    public UserEntity updateUser(Long id, UserEntity user) {
        return null;
    }

    public boolean deleteUser(Long id) {
            return false;
    }
}
