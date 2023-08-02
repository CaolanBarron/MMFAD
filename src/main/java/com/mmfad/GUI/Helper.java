package com.mmfad.GUI;

import com.mmfad.model.Drink;
import com.mmfad.model.Food;
import com.mmfad.model.Sellable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class Helper {
    // formats a listview to display Sellable items
    // Layout:
    // -> Name
    // -> Price
    // -> TODO: DETAILS
    public static void setCellFactoryListView(ListView<Sellable> listView) {
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Sellable item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.name == null || item.price == null) {
                    setText(null);
                } else {
                    setText(item.name + "\n" + item.price);
                }
            }
        });
    }


    // TODO THIS SHOULD NOT BE AROUND FOR LONG
    // DEBUG data for drinks and food
    // TODO: This should be removed and replaced with a database or read from file solution
    public static Drink[] drinks = {
            new Drink("Americano", 4.55f),
            new Drink("Cappuccino", 5.75f),
            new Drink("Latte", 5.75f),
            new Drink("Mocha", 6.25f),
            new Drink("White Mocha", 6.25f),
            new Drink("Cold Brew", 5.75f),
            new Drink("Black Tea", 2.70f),
            new Drink("Hibiscus Tea", 2.90f),
            new Drink("Earl Grey Tea", 3.00f),
    };
    public static Food[] food = {
            new Food("Sandwich", 5.50f),
            new Food("Panini", 4.70f),
            new Food("Croissant", 3.90f),
            new Food("Pancake", 5.90f),
            new Food("Sausage Bap", 6.50f),

    };
}
