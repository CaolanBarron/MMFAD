package com.mmfad.controllers;

import com.mmfad.GUI.BooleanDetailComponent;
import com.mmfad.GUI.NumericalDetailComponent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailsDialogController implements Initializable {
    @FXML
    FlowPane detailsFlowPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var component = new NumericalDetailComponent();
        detailsFlowPane.getChildren().add(component);
    }
}
