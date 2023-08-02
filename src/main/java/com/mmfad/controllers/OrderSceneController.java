package com.mmfad.controllers;

import com.mmfad.Main;
import com.mmfad.model.Order;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderSceneController {

    // This class should contain general functionality that is common between all scenes which concern themselves
    // with taking and working with an order
    public void EndOrder(Stage parentPane) throws IOException {
        Order.DestroyInstance();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OrderCreationScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        parentPane.setScene(scene);
    }
}
