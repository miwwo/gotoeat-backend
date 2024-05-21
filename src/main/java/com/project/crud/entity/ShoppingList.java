package com.project.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_lists")
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "shopping_list_recipes",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> selectedRecipes = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL)
    private List<ShoppingListIngredient> shoppingListIngredients;


    /*@ElementCollection
    @CollectionTable(name = "shopping_list_ingredients", joinColumns = @JoinColumn(name = "shopping_list_id"))
    @MapKeyJoinColumn(name = "ingredient_name")
    @Column(name = "quantity")
    private Map<String, Integer> sumOfIngredients = new HashMap<>();

    public void addRecipe(Recipe recipe) {
        for (RecipeIngredient ingredientQuantity : recipe.getRecipeIngredients()) {
            String ingredientName = ingredientQuantity.getIngredient().getName();
            Integer quantity = ingredientQuantity.getQuantity();
            sumOfIngredients.put(
                    ingredientName,
                    sumOfIngredients.getOrDefault(ingredientName, 0) + quantity
            );
        }
        selectedRecipes.add(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        for (RecipeIngredient ingredientQuantity : recipe.getRecipeIngredients()) {
            String ingredientName = ingredientQuantity.getIngredient().getName();
            Integer quantity = ingredientQuantity.getQuantity();
            int newQuantity = sumOfIngredients.getOrDefault(ingredientName, 0) - quantity;
            if (newQuantity <= 0) {
                sumOfIngredients.remove(ingredientName);
            } else {
                sumOfIngredients.put(ingredientName, newQuantity);
            }
        }
        selectedRecipes.remove(recipe);
    }*/

    /*public Map<Ingredient, Integer> getSumOfAllIngredients() {
        Map<Ingredient, Integer> sumOfIngredients = new HashMap<>();

        for (Recipe recipe : selectedRecipes) {
            for (RecipeIngredient ingredientQuantity : recipe.getRecipeIngredients()) {
                Ingredient ingredient = ingredientQuantity.getIngredient();
                Integer quantity = ingredientQuantity.getQuantity();
                sumOfIngredients.put(
                        ingredient,
                        sumOfIngredients.getOrDefault(ingredient, 0) + quantity
                );
            }
        }
        return sumOfIngredients;
    }*/

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "shopping_list_ingredients",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;*/

}