package com.mmfad.controllers;

import com.mmfad.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderCreationController {

    // ORDER is no created her which may cause problems in the future. If such problems arise this class should control
    // creation of the order singleton

    public void SwitchToOrderScreen(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
    }
}
