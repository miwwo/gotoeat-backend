package com.project.crud.controller;

import com.project.crud.entity.Recipe;
import com.project.crud.entity.ShoppingList;
import com.project.crud.service.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/shopping-list")
    public ResponseEntity<ShoppingList> getShoppingListByUser(@PathVariable Long id) {

        ShoppingList shoppingList = userService.getShoppingListByUser(id);

        return new ResponseEntity<>(shoppingList, HttpStatus.OK);
    }

    @GetMapping("/{id}/recipes")
    public ResponseEntity<List<Recipe>> getRecipesByUser(@PathVariable Long id) {

        List<Recipe> recipes = userService.getRecipesByUser(id);

        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }
}
