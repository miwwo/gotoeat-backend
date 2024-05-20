package com.project.crud.repository;

import com.project.crud.entity.ShoppingList;
import com.project.crud.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long>{
    ShoppingList findByUserAndActive(UserEntity user, boolean active);
}
