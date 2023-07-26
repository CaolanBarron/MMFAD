package com.mmfad;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderScene {

    public void EndOrder(Stage parentPane) throws IOException {
        Order.DestroyInstance();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OrderCreationScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        parentPane.setScene(scene);
    }
}
