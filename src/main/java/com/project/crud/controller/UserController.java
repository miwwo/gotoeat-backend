package com.project.crud.controller;

import com.project.crud.entity.Ingredient;
import com.project.crud.entity.Recipe;
import com.project.crud.entity.ShoppingList;
import com.project.crud.entity.UserEntity;
import com.project.crud.service.interfaces.IngredientService;
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
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @GetMapping("/recipes")///////////
    public ResponseEntity<List<Recipe>> getRecipesByUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> owner = userService.getUserByEmail(userDetails.getUsername());

        List<Recipe> recipes = recipeService.findRecipesByOwnerId(owner.get().getId());

        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }
}
