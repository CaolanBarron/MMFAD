package com.mmfad.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NumericalDetailComponent extends AnchorPane {
    public NumericalDetailComponent() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mmfad/NumericalDetailComponent.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(NumericalDetailComponent.this);

    try {
        fxmlLoader.load();
    } catch (IOException exception) {
        throw new RuntimeException();
    }
    initialiseElements();
}

    private void initialiseElements() {
    }
}
