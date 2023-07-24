package com.mmfad;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentController {
    public void SwitchToMenuScene(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
    }
}
