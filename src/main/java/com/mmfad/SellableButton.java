package com.mmfad;

import javafx.scene.control.Button;

public class SellableButton extends Button {
    Sellable item;
    public SellableButton(Sellable item) {
        super(item.name);
        this.item = item;
        this.setStyle("-fx-min-height: 100; -fx-min-width: 200");
    }

    public Sellable getSellableItem() {
        return item;
    }

}
