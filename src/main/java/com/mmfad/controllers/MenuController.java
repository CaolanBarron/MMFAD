package com.mmfad.controllers;

import com.mmfad.GUI.Helper;
import com.mmfad.GUI.SellableButton;
import com.mmfad.Main;
import com.mmfad.model.Order;
import com.mmfad.model.Sellable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController extends OrderSceneController implements Initializable {
    // GUI Components
    @FXML
    AnchorPane parentPane;
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
    @FXML
    ListView<Sellable> selectedItemsListView;


    // GENERAL CONTROLLING FUNCTIONS
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        RefreshCurrentlySelectedItemsView();


        // Add buttons to the respective items pane from a lists
        // TODO: Will need to initialize these lists when reading from file/database
        for (Sellable drink :
                Helper.drinks) {
            SellableButton button = new SellableButton(drink);
            drinkItemButtons.add(button);
        }
        for (Sellable food :
                Helper.food) {
            foodItemButtons.add(new SellableButton(food));
        }

        initializeButtonBehaviours(drinkItemButtons);

        initializeButtonBehaviours(foodItemButtons);

        drinksPane.getChildren().addAll(drinkItemButtons);
        foodPane.getChildren().addAll(foodItemButtons);
        //Drinks pane visible and intractable by default
        //TODO: Find out if setting visibility to false Disables interaction by default
        foodPane.setVisible(false);
        foodPane.setDisable(true);



        Helper.setCellFactoryListView(selectedItemsListView);

        // Style initialization
        drinksPane.getStyleClass().add("flow-tile");
        foodPane.getStyleClass().add("flow-tile");

    }

    public void initializeButtonBehaviours(List<SellableButton> itemButtons) {
        for (SellableButton itemButton:
                itemButtons) {
             itemButton.addEventHandler(MouseEvent.ANY, new EventHandler<>() {
                 long startTime;

                 @Override
                 public void handle(MouseEvent mouseEvent) {
                     if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                         startTime = System.currentTimeMillis();
                     } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                         if (System.currentTimeMillis() - startTime > 600) {
                             System.out.println(System.currentTimeMillis() - startTime);
                             Sellable item = itemButton.HeldDown();
                             System.out.println("hit");
                             if (item != null)
                                 Order.getInstance().addItemToOrder(item);
                             RefreshCurrentlySelectedItemsView();
                         } else {
                             Order.getInstance().addItemToOrder(itemButton.getSellableItem());
                             RefreshCurrentlySelectedItemsView();
                         }
                     }
                 }
             });
        }
    }

    // SCENE & PANE SWITCHING FUNCTIONS
    public void SwitchToDrinksPane() {
        //TODO: SEE PREVIOUS ON VISIBILITY DISABILITY
        foodPane.setDisable(true);
        foodPane.setVisible(false);
        drinksPane.setDisable(false);
        drinksPane.setVisible(true);
        System.out.println("Switching to drinks pane");
    }
    public void SwitchToFoodPane() {
        //TODO: SEE PREVIOUS ON VISIBILITY DISABILITY
        foodPane.setDisable(false);
        foodPane.setVisible(true);
        drinksPane.setDisable(true);
        drinksPane.setVisible(false);
        System.out.println("Switching to food pane");
    }
    public void SwitchToPaymentScene(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PaymentScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(String.valueOf(Main.class.getResource("Styles/style.css")));
        Stage stage = (Stage) node.getScene().getWindow();
        System.out.println("Switching to payment scene");
        stage.setScene(scene);
    }


    // ORDER AND ORDER ITEMS FUNCTIONS & VARIABLES
    List<SellableButton> foodItemButtons = new ArrayList<>();
    List<SellableButton> drinkItemButtons = new ArrayList<>();

    public void RefreshCurrentlySelectedItemsView(){
        selectedItemsListView.getItems().clear();
        for (Sellable item:
             Order.getInstance().getItemsInOrder()) {
            selectedItemsListView.getItems().add(item);
        }
        orderTotalPrice.setText(Order.getInstance().getTotalPrice().toString());
    }



    public void DeleteFromOrder(){
        Sellable item = selectedItemsListView.getSelectionModel().getSelectedItem();
        Order.getInstance().removeItemFromOrder(item);
        RefreshCurrentlySelectedItemsView();
    }

    public void CancelOrder() {
        try {
            EndOrder((Stage)parentPane.getScene().getWindow());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
