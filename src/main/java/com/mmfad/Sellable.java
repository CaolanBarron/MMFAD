package com.mmfad;

import java.math.BigDecimal;

public abstract class Sellable {
    private String name;
    private BigDecimal price;

    abstract void DisplayOptions();
}
