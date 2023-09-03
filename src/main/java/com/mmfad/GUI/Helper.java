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
                    setText(item.DisplayItemOptions());
                }
            }
        });
    }


    // TODO THIS SHOULD NOT BE AROUND FOR LONG
    // DEBUG data for drinks and food
    // TODO: This should be removed and replaced with a database or read from file solution
    public static Drink[] drinks = {
            new Drink("Americano", 4.55f,false, 3, false),
            new Drink("Cappuccino", 5.75f,false, 2, false),
            new Drink("Latte", 5.75f,false,2, false),
            new Drink("Flat White", 3.55f, false, 2, false),
            new Drink("Cortado", 2.90f, false, 2, false),
            new Drink("Water", 0.99f,false, 0, false),
            new Drink("Mocha", 6.25f,false,2,true ),
            new Drink("White Mocha", 6.25f,false,2, true),
            new Drink("Cold Brew", 5.75f,true, 0, false),
            new Drink("Black Tea", 2.70f, false, 0, false),
            new Drink("Hibiscus Tea", 2.90f, false, 0, false),
            new Drink("Earl Grey Tea", 3.00f, false, 0, false),
    };
    public static Food[] food = {
            new Food("Sandwich", 5.50f),
            new Food("Panini", 4.70f),
            new Food("Croissant", 3.90f),
            new Food("Pancake", 5.90f),
            new Food("Sausage Bap", 6.50f),

    };
}
