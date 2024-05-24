package com.project.crud.service.interfaces;

import com.project.crud.entity.Recipe;
import com.project.crud.entity.ShoppingList;
import com.project.crud.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {


    UserEntity createUser(UserEntity userEntity);

    UserEntity getUserById(Long userId);

    Optional<UserEntity> getUserByEmail(String email);

    List<UserEntity> getAllUsers();

    ShoppingList getShoppingListByUser(Long id);


    UserEntity updateUser(Long id, UserEntity user);

    boolean deleteUser(Long id);
}
