package com.mmfad;

import javafx.scene.control.Button;

public class SellableButton extends Button {
    Sellable item;
    public SellableButton(Sellable item) {
        super(item.name);
        this.item = item;
    }

    public Sellable getSellableItem() {
        return item;
    }

}
