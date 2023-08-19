package com.mmfad.controllers;

import com.mmfad.model.Sellable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailsDialogController implements Initializable {

    Sellable item;

    @FXML
    Label itemNameLabel;
    @FXML
    Label itemPriceLabel;
    @FXML
    FlowPane detailsFlowPane;

    public DetailsDialogController(Sellable item) {
        this.item = item;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemNameLabel.setText(this.item.name);
        itemPriceLabel.setText(this.item.price.toString());
        for (AnchorPane pane:
             item.GetItemOptions()) {
            detailsFlowPane.getChildren().add(pane);
            detailsFlowPane.getChildren().add(new Separator());
        }
    }

}
