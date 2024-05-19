package com.project.crud.dto;


import com.project.crud.entity.IngredientQuantity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private String name;
    private String description;
    private boolean visible;
    private List<IngredientQuantity> ingredientQuantity = new ArrayList<>();
}
