package com.project.crud.controller;

import com.project.crud.entity.Ingredient;
import com.project.crud.service.IngredientServiceImpl;
import com.project.crud.service.interfaces.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;


    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createdIngredient = ingredientService.createIngredient(ingredient);
        return new ResponseEntity<>(createdIngredient, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        if (ingredient != null) {
            return new ResponseEntity<>(ingredient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        Ingredient updatedIngredient = ingredientService.updateIngredient(id, ingredient);
        if (updatedIngredient != null) {
            return new ResponseEntity<>(updatedIngredient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        boolean deleted = ingredientService.deleteIngredient(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*@GetMapping("/ingredients_suggestions")
    public ResponseEntity<List<Ingredient>> suggestIngredientsByPrefix(@RequestParam String prefix) {
        List<Ingredient> ingredients = ingredientService.suggestIngredientsByPrefix(prefix);
        if (!ingredients.isEmpty()) {
            return new ResponseEntity<>(ingredients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

}
