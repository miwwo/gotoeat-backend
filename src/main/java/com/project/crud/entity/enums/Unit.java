package com.project.crud.entity.enums;

import lombok.Getter;

@Getter
public enum Unit {
    GRAM("g"),
    MILLILITER("ml"),
    PIECE("pcs");

    private final String unit;
    Unit(String unit) {
        this.unit = unit;
    }

}
