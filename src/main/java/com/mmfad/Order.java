package com.mmfad;

import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class Order {
        private static Order orderInstance = null;



    List<Sellable> itemsInOrder;
    private Order() {
        itemsInOrder = new ArrayList<>();
    }

    public static synchronized Order getInstance() {
        if(orderInstance == null)
            orderInstance = new Order();

        return orderInstance;
    }
    public static synchronized void DestroyInstance() {
        orderInstance = null;
    }

    public List<Sellable> getItemsInOrder() {
        return itemsInOrder;
    }
    public void setItemsInOrder(List<Sellable> itemsInOrder) {
        this.itemsInOrder = itemsInOrder;
    }


    public void addItemToOrder(Sellable item) {
        itemsInOrder.add(item);
    }

    public void removeItemFromOrder(Sellable item) {
        itemsInOrder.remove(item);
    }
}
