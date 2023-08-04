package com.mmfad.controllers;

import com.mmfad.GUI.BooleanDetailComponent;
import com.mmfad.GUI.NumericalDetailComponent;
import com.mmfad.GUI.SelectionDetailComponent;
import com.mmfad.GUI.ToggleDetailComponent;
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
        var component1 = new NumericalDetailComponent();
        var component2 = new BooleanDetailComponent();
        var component3 = new ToggleDetailComponent();
        var component4 = new SelectionDetailComponent();
        detailsFlowPane.getChildren().add(component1);
        detailsFlowPane.getChildren().add(component2);
        detailsFlowPane.getChildren().add(component3);
        detailsFlowPane.getChildren().add(component4);
    }
}
