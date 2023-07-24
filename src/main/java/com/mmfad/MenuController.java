package com.mmfad;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    //Arrays of generic test data to use
    Drink[] drinks = {
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
    Food[] food = {
            new Food("Sandwich", 5.50f),
            new Food("Panini", 4.70f),
            new Food("Croissant", 3.90f),
            new Food("Pancake", 5.90f),
            new Food("Sausage Bap", 6.50f),

    };

    @FXML
    Button foodButton;
    @FXML
    Button drinksButton;
    @FXML
    FlowPane foodPane;
    @FXML
    FlowPane drinksPane;
    @FXML
    Label orderTotalPrice;

    public void SwitchToDrinksPane() {
        foodPane.setDisable(true);
        foodPane.setVisible(false);
        drinksPane.setDisable(false);
        drinksPane.setVisible(true);
        System.out.println("Switching to drinks");
    }

    public void SwitchToFoodPane() {
        foodPane.setDisable(false);
        foodPane.setVisible(true);
        drinksPane.setDisable(true);
        drinksPane.setVisible(false);
        System.out.println("Switching to food");
    }

    List<SellableButton> foodItemButtons = new ArrayList<>();
    List<SellableButton> drinkItemButtons = new ArrayList<>();
    List<Sellable> currentlySelectedItems = new ArrayList<>();

    // Populate both the drinks pane and the food pane with buttons
    // depending on predetermined arrays
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Sellable drink :
                drinks) {
            drinkItemButtons.add(new SellableButton(drink));
        }

        for (Sellable food :
                food) {
            foodItemButtons.add(new SellableButton(food));
        }

        drinksPane.getChildren().addAll(drinkItemButtons);
        foodPane.getChildren().addAll(foodItemButtons);

        foodPane.setVisible(false);
        foodPane.setDisable(true);


        for (SellableButton button:
             foodItemButtons) {
            button.setOnAction(addToListEvent);
        }
        for (SellableButton button:
             drinkItemButtons) {
            button.setOnAction(addToListEvent);
        }

        selectedItemsListView.setCellFactory(param -> new ListCell<Sellable>() {
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

    @FXML
    ListView selectedItemsListView;

    public void RefreshCurrentlySelectedItemsView(){
        selectedItemsListView.getItems().clear();
        for (Sellable item:
             currentlySelectedItems) {
            //selectedItemsListView.getItems().add(item.name + "\n" + item.price);
            selectedItemsListView.getItems().add(item);
        }
        CalculateOrderPrice();
    }
    public void CalculateOrderPrice(){
        BigDecimal total = new BigDecimal("0.00");
        for (Sellable item:
             currentlySelectedItems) {
            total = total.add(item.price);
        }
        System.out.println(total);
        orderTotalPrice.setText(total.toString());
    }
    EventHandler addToListEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            SellableButton source = (SellableButton) event.getSource();
            currentlySelectedItems.add(source.getSellableItem());
            RefreshCurrentlySelectedItemsView();
        }
    };

    public void DeleteFromOrder(){
        Sellable item = (Sellable) selectedItemsListView.getSelectionModel().getSelectedItem();
        currentlySelectedItems.remove(item);
        RefreshCurrentlySelectedItemsView();
    }


    public void SwitchToPaymentScene(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PaymentScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
    }
}
