package com.project.crud.controller;

import com.project.crud.entity.ShoppingList;
import com.project.crud.service.interfaces.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-lists")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

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
