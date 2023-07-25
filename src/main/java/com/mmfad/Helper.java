package com.mmfad;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class Helper {
    // formats a listview to display Sellable items
    // Layout:
    // -> Name
    // -> Price
    public static void setCellFactoryListView(ListView<Sellable> listView) {
        listView.setCellFactory(param -> new ListCell<Sellable>() {
            @Override
            protected void updateItem(Sellable item, boolean empty) {
                super.updateItem(item, empty);

                if(empty || item == null || item.name == null || item.price == null) {
                    setText(null);
                } else {
                    setText(item.name + "\n" + item.price);
                }
            }
        });
    }
}
