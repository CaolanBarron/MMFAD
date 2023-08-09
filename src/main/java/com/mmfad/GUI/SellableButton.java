package com.mmfad.GUI;

import com.mmfad.controllers.DetailsDialogController;
import com.mmfad.model.Sellable;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;

public class SellableButton extends Button {
    Sellable item;
    public SellableButton(Sellable item) {
        super(item.name);
        this.item = item;
        //TODO: This should be handled in an external stylesheet probably
        this.setStyle("-fx-min-height: 100; -fx-min-width: 200");

        this.addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            long startTime;
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
                    startTime = System.currentTimeMillis();
                } else if(mouseEvent.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
                    if(System.currentTimeMillis() - startTime > 600) {
                        System.out.println(System.currentTimeMillis() - startTime);
                        HeldDown();
                    }
                }
            }
        });
    }

    public Sellable getSellableItem() {
        return item;
    }
    
    private void HeldDown() {
        System.out.println("Opening drink menu");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/mmfad/SellableDetailsDialog.fxml"));
            DetailsDialogController popupController = new DetailsDialogController(this.item);
            loader.setController(popupController);
            DialogPane pane = loader.load();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(pane);
            // TODO Remove following line and control with CSS stylesheet
            pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
            dialog.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
