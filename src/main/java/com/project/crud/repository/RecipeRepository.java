package com.project.crud.repository;

import com.project.crud.entity.Recipe;
import com.project.crud.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByOwner(UserEntity owner);
    List<Recipe> findByNameContainingIgnoreCase(String name);

    List<Recipe> findAllByIsPublic(boolean b);
}
