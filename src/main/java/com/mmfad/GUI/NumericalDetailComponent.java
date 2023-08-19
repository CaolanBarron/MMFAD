package com.mmfad.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class NumericalDetailComponent<T> extends AnchorPane {
    private T target;
    private Function<T, Integer> getter;
    private BiConsumer<T, Integer> setter;

    String detailName;
    @FXML
    Label detailNameLabel;
    @FXML
    Label numericalCounterLabel;
    @FXML
    Button incrementButton;
    @FXML
    Button decrementButton;

    public NumericalDetailComponent(String detailName, T target, Function<T, Integer> getter, BiConsumer<T, Integer> setter) {
        this.detailName = detailName;
        this.target = target;
        this.getter = getter;
        this.setter = setter;

        initialiseFXML();
        initialiseElements();
    }

    private void initialiseFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mmfad/NumericalDetailComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(NumericalDetailComponent.this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException();
        }
    }

    private void initialiseElements() {
        detailNameLabel.setText(detailName);
        updateNumberLabel();
        incrementButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int value = getter.apply(target);
                setter.accept(target, value + 1);
                updateNumberLabel();
            }
        });
        decrementButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int value = getter.apply(target);
                setter.accept(target, value - 1);
                updateNumberLabel();
            }
        });
    }

    private void updateNumberLabel() {
        numericalCounterLabel.setText(String.valueOf(getter.apply(target)));
    }
}
