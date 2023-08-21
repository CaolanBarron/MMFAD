package com.mmfad.GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class BooleanDetailComponent<T> extends FlowPane {

    private T target;
    private Function<T, Boolean> getter;
    private BiConsumer<T, Boolean> setter;

    String detailName;
    @FXML
    Label detailNameLabel;
    @FXML
    RadioButton booleanRadioButton;
    public BooleanDetailComponent(String detailName,T target, Function<T, Boolean> getter, BiConsumer<T, Boolean> setter) {
        this.detailName = detailName;
        this.target = target;
        this.getter = getter;
        this.setter = setter;


        initialiseFXML();
        initialiseElements();
    }

    private void initialiseFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mmfad/BooleanDetailComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(BooleanDetailComponent.this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void initialiseElements() {
        detailNameLabel.setText(detailName);
        booleanRadioButton.selectedProperty().set(getter.apply(target));

        booleanRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                setter.accept(target, t1);
            }
        });

        //Style elements
        booleanRadioButton.getStyleClass().add("boolean-button");
    }
}
