package com.mmfad.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class SelectionDetailComponent<T> extends FlowPane {
    private T target;
    private Function<T,String[]> getter;
    private BiConsumer<T, String[]> setter;

    String detailName;
    @FXML
    Label detailNameLabel;
    @FXML
    FlowPane contentAreaFlowPane;

    ToggleButton[] toggleButtons;

    public SelectionDetailComponent(String detailName, T target, Function<T, String[]> getter,BiConsumer<T, String[]> setter, String[] values) {
        this.detailName = detailName;
        this.target = target;
        this.getter = getter;
        this.setter = setter;

        initialiseFXML();
        initialiseElements(values);
    }

    private void initialiseFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mmfad/ListDetailComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(SelectionDetailComponent.this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    private void initialiseElements(String[] values) {
        detailNameLabel.setText(detailName);

        contentAreaFlowPane.setHgap(5);
        contentAreaFlowPane.setVgap(5);

        toggleButtons = new ToggleButton[values.length];
        for (int i = 0; i < values.length; i++) {
            ToggleButton tb = new ToggleButton(values[i]);
            tb.setUserData(values[i]);
            tb.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String[] tempArray = new String[toggleButtons.length];
                    int i = 0;
                    for (ToggleButton button:
                         toggleButtons) {
                        if(button.isSelected()){
                            tempArray[i] = button.getUserData().toString();
                            i++;
                        }
                    }
                    setter.accept(target,tempArray);
                    System.out.println(Arrays.toString(getter.apply(target)));
                }
            });
            toggleButtons[i] = tb;
        }
        contentAreaFlowPane.getChildren().addAll(toggleButtons);

        // Style Components


    }

}
