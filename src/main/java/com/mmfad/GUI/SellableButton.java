package com.mmfad.GUI;

import com.mmfad.Main;
import com.mmfad.controllers.DetailsDialogController;
import com.mmfad.model.Sellable;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Optional;

public class SellableButton extends Button {
    Sellable item;
    public SellableButton(Sellable item) {
        super(item.name);
        this.item = item;
        this.getStyleClass().add("sellable-button");
        //TODO: This should be handled in an external stylesheet probably
//        this.setStyle("-fx-min-height: 100; -fx-min-width: 200");


    }


    public Sellable getSellableItem() {
        return item;
    }


    
    public Sellable HeldDown() {
        System.out.println("Opening drink menu");

        Sellable clonedItem = this.item.clone();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/mmfad/SellableDetailsDialog.fxml"));
            DetailsDialogController popupController = new DetailsDialogController(clonedItem);
            loader.setController(popupController);
            DialogPane pane = loader.load();
            pane.getStylesheets().add(String.valueOf(Main.class.getResource("Styles/detailsDialog.css")));

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(pane);
            // TODO Remove following line and control with CSS stylesheet
//            pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

            Optional<ButtonType> buttonResult = dialog.showAndWait();

            if (buttonResult.isPresent() && buttonResult.get() == ButtonType.OK)
                return clonedItem;
            else if(buttonResult.isPresent() && buttonResult.get() == ButtonType.CANCEL)
                return null;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
