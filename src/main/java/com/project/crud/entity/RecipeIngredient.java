package com.project.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipe_ingredient")
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference // потому что двунаправленная связь, ставим чтобы не было рекурсии
    @ManyToOne // необязательно вообще иметь обратную двунаправленную связь
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false) // если у нас объект класса, то используем @JoinColumn
    private Ingredient ingredient;

    @Column
    private Integer quantity;

    // getters and setters
}