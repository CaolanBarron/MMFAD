package com.mmfad;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Sellable {
    public String name;
    public BigDecimal price;

    public Sellable(String name, float price) {
        this.name = name;
        BigDecimal model = new BigDecimal(price);
        this.price = model.setScale(2, RoundingMode.HALF_EVEN);
    }
    abstract void DisplayOptions();
}
