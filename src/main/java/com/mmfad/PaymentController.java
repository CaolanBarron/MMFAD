package com.mmfad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    ListView selectedItemsListView;
    @FXML
    Label orderTotalPrice;
    @FXML
    Label paymentTotalLabel;
    @FXML
    ListView cashTenderedListView;
    @FXML
    Button deleteCashButton;

    public void SwitchToMenuScene(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RefreshCurrentlySelectedItemsView();
        Helper.setCellFactoryListView(selectedItemsListView);
    }

    public void RefreshCurrentlySelectedItemsView(){
        selectedItemsListView.getItems().clear();
        for (Sellable item:
                Order.getInstance().itemsInOrder) {
            selectedItemsListView.getItems().add(item);
        }
        CalculateOrderPrice();
    }
    public void CalculateOrderPrice(){
        BigDecimal total = new BigDecimal("0.00");
        for (Sellable item:
                Order.getInstance().itemsInOrder) {
            total = total.add(item.price);
        }
        System.out.println(total);
        orderTotalPrice.setText(total.toString());
    }

    public void PaymentInput(ActionEvent event) {
        Button button = (Button)event.getSource();
        paymentTotalLabel.setText(paymentTotalLabel.getText() + button.getText());
    }

    BigDecimal totalTendered;

    public void TenderCash() {
        cashTenderedListView.getItems().add(new BigDecimal(paymentTotalLabel.getText()));
        UpdateTotalTendered();
        System.out.println(totalTendered);
        BackspaceText();
    }
    private void UpdateTotalTendered() {
        totalTendered = new BigDecimal(0.00);
        for (Object object:
                cashTenderedListView.getItems()) {
            BigDecimal tender = (BigDecimal) object;
            totalTendered = totalTendered.add(tender);
        }
    }

    public void DeleteTenderedCash() {
        cashTenderedListView.getItems().remove(cashTenderedListView.getSelectionModel().getSelectedItem());
        UpdateTotalTendered();
        System.out.println(totalTendered);
    }
    public void BackspaceText(){
        paymentTotalLabel.setText("");
    }

    public void CardPayment() {
        
    }


    public void FinishOrder(ActionEvent event) throws IOException {



        Order.DestroyInstance();
        Node node = (Node) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OrderCreationScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
    }
}
