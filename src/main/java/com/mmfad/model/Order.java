package com.mmfad.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {


    // SINGLETON/OBJECT FUNCTIONALITY
    private static Order orderInstance = null;
    private Order() {
        itemsInOrder = new ArrayList<>();
        totalPrice = new BigDecimal("0.00");
    }
    public static synchronized Order getInstance() {
        if(orderInstance == null)
            orderInstance = new Order();

        return orderInstance;
    }
    public static synchronized void DestroyInstance() {
        orderInstance = null;
    }


    // ORDER LOGIC METHODS & VARIABLES
    public List<Sellable> itemsInOrder;
    BigDecimal totalPrice;

    private void UpdateOrderPrice() {
        totalPrice = new BigDecimal("0.00");
        for (Sellable item:
             itemsInOrder) {
            totalPrice = totalPrice.add(item.price);
        }
    }
    public BigDecimal getTotalPrice() {
        UpdateOrderPrice();
        return totalPrice;
    }
    public void addItemToOrder(Sellable item) {
        itemsInOrder.add(item);
    }
    public List<Sellable> getItemsInOrder() {
        return itemsInOrder;
    }
    public void removeItemFromOrder(Sellable item) {
        itemsInOrder.remove(item);
    }



    // DEBUG METHODS
    // Right now contain simple ways to represent receipts and tickets
    public void DebugPrintReceipt() {
        System.out.println("------------------");
        System.out.println("Receipt for order");
        for (Sellable item:
             itemsInOrder) {
            System.out.println(item.DisplayItemOptions());
            System.out.println();
        }
        System.out.println("Total price: " + orderInstance.getTotalPrice());
        System.out.println("------------------");
    }

    public void DebugPrintTickets() {
        System.out.println("------------------");
        System.out.println("Tickets for order");
        for (Sellable item:
                itemsInOrder) {
            System.out.println(item.DisplayItemOptions());
        }
        System.out.println("------------------");

    }

}
