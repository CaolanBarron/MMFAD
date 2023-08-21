package com.mmfad.controllers;

import com.mmfad.GUI.Helper;
import com.mmfad.Main;
import com.mmfad.model.Order;
import com.mmfad.model.Sellable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentController extends OrderSceneController implements Initializable {

    // GUI components
    @FXML
    AnchorPane parentPane;
    @FXML
    ListView<Sellable> selectedItemsListView;
    @FXML
    ListView<BigDecimal> cashTenderedListView;
    @FXML
    Label orderTotalLabel;
    @FXML
    Label totalTenderedLabel;
    @FXML
    Label paymentDueLabel;
    @FXML
    Label customPaymentLabel;
   @FXML
    Button deleteCashButton;

   // INITIALIZATION FUNCTIONS
   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RefreshCurrentlySelectedItemsView();
        Helper.setCellFactoryListView(selectedItemsListView);
        UpdatePaymentLabels();
    }
    public void RefreshCurrentlySelectedItemsView(){
        selectedItemsListView.getItems().clear();
        for (Sellable item:
                Order.getInstance().getItemsInOrder()) {
            selectedItemsListView.getItems().add(item);
        }
    }

    // GUI CONTROL FUNCTIONS
    private void UpdatePaymentLabels() {
        orderTotalLabel.setText(Order.getInstance().getTotalPrice().toString());
        totalTenderedLabel.setText(totalTendered.toString());
        paymentDueLabel.setText(GetTotalDue().toString());
    }
    private BigDecimal GetTotalDue() {
        return Order.getInstance().getTotalPrice().subtract(totalTendered);
    }

    BigDecimal totalTendered = new BigDecimal("0.00");
    private void AddTenderedMoneyToListView(BigDecimal input) {
        cashTenderedListView.getItems().add(input);
    }
    public void RemoveTenderedMoneyFromListView() {
        cashTenderedListView.getItems().remove(cashTenderedListView.getSelectionModel().getSelectedItem());
        UpdateTotalTenderedFromListView();
        UpdatePaymentLabels();
    }
    private void UpdateTotalTenderedFromListView() {
        // update the totalTendered Variable using the list of tendered payments
        totalTendered = new BigDecimal("0.00");
        for (BigDecimal item:
             cashTenderedListView.getItems()) {
            totalTendered = totalTendered.add(item);
        }
    }

    // PAYMENT METHODS
    public void TenderCash(BigDecimal input) {
        System.out.println("PreInput");
        System.out.println("Total order price: " + Order.getInstance().getTotalPrice() );
        System.out.println("Total tendered: " + totalTendered);
        System.out.println("Total due: " + GetTotalDue());
        // add payment to list of cash tendered
        AddTenderedMoneyToListView(input);
        // update totalTendered using list of cash tendered
        UpdateTotalTenderedFromListView();
        // update labels displaying how much is paid/due
        UpdatePaymentLabels();
        // Check if totalDue is less than or equal to zero
        if (GetTotalDue().compareTo(new BigDecimal("0.00")) > 0) {
            // If totalDue is over zero do nothing
            System.out.println("Not paid in total");
        } else {
            // else display the change required and pas control to the ChangeRequiredPOPUP
            System.out.println("Order paid in full. Change required: " + GetTotalDue());
            ChangeRequiredPopup();
        }

        System.out.println("PostInput");
        System.out.println("Total order price: " + Order.getInstance().getTotalPrice() );
        System.out.println("Total tendered: " + totalTendered);
        System.out.println("Total due: " + GetTotalDue());

    }
    public void CustomPayment() {
        // Take the input using the label from the scene
        if(customPaymentLabel.getText().length() > 0) {
            BigDecimal cashValue = new BigDecimal(customPaymentLabel.getText());
            // Pass this value into TenderCash()
            TenderCash(cashValue);
        }
        // Reset the input in the scene
        BackspaceText();
    }
    public void ExactPayment() {
        // Call tender cash with the total due
        TenderCash(GetTotalDue());
    }
    public void CardPayment() {
        //TODO: IDK how to handle card stuff now il just write it as if the payment went through.
        TenderCash(GetTotalDue());
    }
    private void ChangeRequiredPopup() {
        Alert changeDueAlert = new Alert(Alert.AlertType.NONE,"Change due: " + GetTotalDue().abs(), ButtonType.OK);
//        changeDueAlert.getDialogPane().getStylesheets().add(String.valueOf(Main.class.getResource("Style/style.css")));
        changeDueAlert.setOnCloseRequest(dialogEvent -> FinishOrder());
        changeDueAlert.getDialogPane().setStyle("-fx-font-size: 35; -fx-border-width: 4; -fx-border-color: #844dc7;-fx-background-color: #0f151a;");
        changeDueAlert.getDialogPane().lookup(".content.label").setStyle("-fx-text-fill: #ecf3f9;");
        changeDueAlert.show();
    }

    // KEYBOARD CONTROLLER
    public void KeyboardInput(ActionEvent event) {
        Button button = (Button)event.getSource();
        customPaymentLabel.setText(customPaymentLabel.getText() + button.getText());
    }
    public void BackspaceText(){
        customPaymentLabel.setText("");
    }

    // Order & Scene Control
    public void SwitchToMenuScene(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(String.valueOf(Main.class.getResource("Styles/style.css")));
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
    }
    private void FinishOrder() {
        Order.getInstance().DebugPrintReceipt();
        Order.getInstance().DebugPrintTickets();
        try {
            EndOrder((Stage) parentPane.getScene().getWindow());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
