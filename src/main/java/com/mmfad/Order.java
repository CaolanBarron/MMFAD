package com.mmfad;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static Order orderInstance = null;

    List<Sellable> itemsInOrder;
    BigDecimal totalPrice;

    private Order() {
        itemsInOrder = new ArrayList<>();
        totalPrice = new BigDecimal("0.00");
    }

    public void UpdateOrderPrice() {
        totalPrice = new BigDecimal("0.00");
        for (Sellable item:
             itemsInOrder) {
            totalPrice = totalPrice.add(item.price);
        }
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public static synchronized Order getInstance() {
        if(orderInstance == null)
            orderInstance = new Order();

        return orderInstance;
    }
    public static synchronized void DestroyInstance() {
        orderInstance = null;
    }


    public void addItemToOrder(Sellable item) {
        itemsInOrder.add(item);
    }

    public void removeItemFromOrder(Sellable item) {
        itemsInOrder.remove(item);
    }

    public void DebugPrintReceipt() {
        System.out.println("------------------");
        System.out.println("Receipt for order");
        for (Sellable item:
             itemsInOrder) {
            System.out.println(item.name + "\t" + item.price);
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
            System.out.println("------------------");
            System.out.println(item.name + "\n" + item.price);
        }
        System.out.println("------------------");

    }
}
