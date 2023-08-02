package com.mmfad.GUI;

import com.mmfad.model.Sellable;
import javafx.scene.control.Button;

public class SellableButton extends Button {
    Sellable item;
    public SellableButton(Sellable item) {
        super(item.name);
        this.item = item;
        //TODO: This should be handled in an external stylesheet probably
        this.setStyle("-fx-min-height: 100; -fx-min-width: 200");
    }

    public Sellable getSellableItem() {
        return item;
    }

}
