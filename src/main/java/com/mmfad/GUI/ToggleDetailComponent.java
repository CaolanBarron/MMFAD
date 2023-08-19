package com.mmfad.GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ToggleDetailComponent<T> extends AnchorPane {
    private T target;
    private Function<T, String> getter;
    private BiConsumer<T, String> setter;

    private String[] componentValues;

    String detailName;
    @FXML
    Label detailNameLabel;
    @FXML
    FlowPane contentAreaFlowPane;
    public ToggleDetailComponent(String detailName, T target, Function<T, String> getter, BiConsumer<T, String> setter, String[] values) {
        this.detailName = detailName;
        this.target = target;
        this.getter = getter;
        this.setter = setter;
        this.componentValues = values;

        initialiseFXML();
        initialiseElements();
    }

    private  void initialiseFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mmfad/ListDetailComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(ToggleDetailComponent.this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void initialiseElements() {

        detailNameLabel.setText(detailName);
        contentAreaFlowPane.setHgap(5);
        contentAreaFlowPane.setVgap(5);
        ToggleGroup tg = new ToggleGroup();
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                setter.accept(target, tg.getSelectedToggle().getUserData().toString());
                System.out.println(getter.apply(target));
            }
        });


        for (String value:
            componentValues) {
            RadioButton rb = new RadioButton(value);
            rb.setToggleGroup(tg);
            rb.setUserData(value);

            contentAreaFlowPane.getChildren().add(rb);
        }
    }
}
