package com.mmfad.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class BooleanDetailComponent extends AnchorPane {
    public BooleanDetailComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mmfad/BooleanDetailComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(BooleanDetailComponent.this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        initialiseElements();
    }

    private void initialiseElements() {
    }
}
