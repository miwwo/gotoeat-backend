package com.project.crud.entity.enums;

public enum Unit {
    GRAM("g"),
    MILLILITER("ml"),
    PIECE("pcs");

    private final String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
