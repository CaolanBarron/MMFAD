package com.mmfad.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SelectionDetailComponent extends AnchorPane {
    public SelectionDetailComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mmfad/ListDetailComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(SelectionDetailComponent.this);

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
