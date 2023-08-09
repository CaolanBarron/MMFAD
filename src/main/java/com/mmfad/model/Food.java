package com.mmfad.model;

import javafx.scene.layout.AnchorPane;

import java.util.List;

public class Food extends Sellable {

    public Food(String name, float price) {
        super(name, price);
    }

    @Override
    public List<AnchorPane> DisplayOptions() {

        return null;
    }
}
