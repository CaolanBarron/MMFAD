package com.mmfad.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Sellable {
    // GENERIC FUNCTIONS AND VARIABLES
    public String name;
    public BigDecimal price;

    public Sellable(String name, float price) {
        this.name = name;
        BigDecimal model = new BigDecimal(price);
        this.price = model.setScale(2, RoundingMode.HALF_EVEN);
    }

    // SELLABLE ITEM FUNCTIONALITY
    // This will contain functions and variables related to the item itself which will be displayed on the GUI
    abstract void DisplayOptions();
}
