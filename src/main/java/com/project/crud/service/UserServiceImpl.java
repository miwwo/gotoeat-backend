package com.project.crud.service;

import com.project.crud.entity.Recipe;
import com.project.crud.entity.ShoppingList;
import com.project.crud.entity.UserEntity;
import com.project.crud.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return null;
    }

    @Override
    public Optional<UserEntity> getUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
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

    @Override
    public UserEntity updateUser(Long id, UserEntity user) {
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }
}
