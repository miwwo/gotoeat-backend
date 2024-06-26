package com.project.crud.controller;

import com.project.crud.entity.ShoppingList;
import com.project.crud.entity.UserEntity;
import com.project.crud.service.interfaces.ShoppingListService;
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
@RequestMapping("/shopping-list")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ShoppingList> getActiveShoppingList() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> userOpt = userService.getUserByEmail(userDetails.getUsername());

        ShoppingList shoppingList = shoppingListService.getActiveShoppingList(userOpt.get());

        if (shoppingList != null) {
            return new ResponseEntity<>(shoppingList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // метод добавления рецепта в список покупок
    @PostMapping("/add-recipe/{recipeId}")
    public ResponseEntity<ShoppingList> addRecipeToShoppingList(@PathVariable Long recipeId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> userOpt = userService.getUserByEmail(userDetails.getUsername());

        ShoppingList shoppingList = shoppingListService.addRecipeToShoppingList(userOpt.get(), recipeId);

        if (shoppingList != null) {
            return new ResponseEntity<>(shoppingList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // метод удаления рецепта из списка покупок
    @DeleteMapping("/remove-recipe/{recipeId}")
    public ResponseEntity<ShoppingList> removeRecipeFromShoppingList(@PathVariable Long recipeId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> userOpt = userService.getUserByEmail(userDetails.getUsername());

        ShoppingList shoppingList = shoppingListService.removeRecipeFromShoppingList(userOpt.get(), recipeId);

        if (shoppingList != null) {
            return new ResponseEntity<>(shoppingList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // метод завершения списка покупок (пометка о том, что список покупок куплен)
    @PutMapping("/complete")
    public ResponseEntity<ShoppingList> completeShoppingList() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> userOpt = userService.getUserByEmail(userDetails.getUsername());

        ShoppingList shoppingList = shoppingListService.completeShoppingList(userOpt.get());

        if (shoppingList != null) {
            return new ResponseEntity<>(shoppingList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // метод получения истории всех списков покупок
    @GetMapping("/history")
    public ResponseEntity<List<ShoppingList>> getShoppingListHistory() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> userOpt = userService.getUserByEmail(userDetails.getUsername());

        List<ShoppingList> shoppingLists = shoppingListService.getShoppingListHistory(userOpt.get());

        if (shoppingLists != null) {
            return new ResponseEntity<>(shoppingLists, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /*@GetMapping("/{id}")
    public ResponseEntity<ShoppingList> getShoppingListById(@PathVariable Long id) {
        ShoppingList shoppingList = shoppingListService.getShoppingListById(id);
        if (shoppingList != null) {
            return new ResponseEntity<>(shoppingList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    /*@PostMapping
    public ResponseEntity<ShoppingList> createShoppingList(@RequestBody ShoppingList shoppingList) {
        ShoppingList createdShoppingList = shoppingListService.createShoppingList(shoppingList);
        return new ResponseEntity<>(createdShoppingList, HttpStatus.CREATED);
    }*/

    /*@PutMapping("/{id}")
    public ResponseEntity<ShoppingList> updateShoppingList(@PathVariable Long id, @RequestBody ShoppingList shoppingList) {
        ShoppingList updatedShoppingList = shoppingListService.updateShoppingList(id, shoppingList);
        if (updatedShoppingList != null) {
            return new ResponseEntity<>(updatedShoppingList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoppingList(@PathVariable Long id) {
        boolean deleted = shoppingListService.deleteShoppingList(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}
