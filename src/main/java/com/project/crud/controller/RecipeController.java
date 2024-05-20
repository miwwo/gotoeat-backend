package com.project.crud.controller;

import com.project.crud.entity.Recipe;
import com.project.crud.entity.UserEntity;
import com.project.crud.service.interfaces.RecipeService;
import com.project.crud.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final UserService userService;

    @GetMapping("/public")
    public ResponseEntity<List<Recipe>> getVisibleRecipes() {
        List<Recipe> publicRecipes = recipeService.getVisibleRecipes();
        return new ResponseEntity<>(publicRecipes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> owner = userService.getUserByUsername(userDetails.getUsername());

        Recipe createdRecipe = recipeService.createRecipe(recipe, owner.get());
        if (createdRecipe == null) {
            return new ResponseEntity<>(recipe, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(recipe, HttpStatus.CREATED);
        }

    }

    @GetMapping("/public/{id}")
    public ResponseEntity<Recipe> getVisibleRecipeById(@PathVariable Long id) {
        Boolean visible = true;
        Recipe recipe = recipeService.getVisibleRecipeById(id, visible);
        if (recipe != null) {
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> owner = userService.getUserByUsername(userDetails.getUsername());

        if (recipeService.getRecipeById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (owner.get().getId() != recipeService.getRecipeById(id).getOwnerId())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Recipe updatedRecipe = recipeService.updateRecipe(id, recipe);
        return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> owner = userService.getUserByUsername(userDetails.getUsername());

        if(recipeService.getRecipeById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            if (!owner.get().isAdmin() &&
                    owner.get().getId() != recipeService.getRecipeById(id).getOwnerId())
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            recipeService.deleteRecipe(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
}
