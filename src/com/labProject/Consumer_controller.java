package com.labProject;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Consumer_controller implements Initializable {
    Consumer c;

    @FXML
    private VBox pnlButtons = null;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnCart;
    @FXML
    private Button btnSignout;
    @FXML
    private TitledPane signoutPane;
    @FXML
    private Button btnSignoutYes;
    @FXML
    private Button btnSignoutNo;

    @FXML
    private Label lblConsumerName;
    @FXML
    private Label lblConsumerID;
    @FXML
    private Button btnStartSession;

    @FXML
    private TableView cartTable;
    @FXML
    private Button btnRefresh;
    @FXML
    private HBox hBox;

    @FXML
    private Button btnReceipt;
    @FXML
    private TitledPane receiptPane;
    @FXML
    private Button btnPaid;
    @FXML
    private Label lblPaid;

    public TableView<ItemBasic> productTable;

    public TableColumn<ItemBasic,String> itemIDProduct;
    public TableColumn<ItemBasic,String> itemNameProduct;
    public TableColumn<ItemBasic,Float> costProduct;
    public TableColumn<ItemBasic,Integer> unitProduct;
    public TableColumn<ItemBasic,Integer> qtyAvailableProduct;


    public void initialize(URL location, ResourceBundle resources) {

        itemIDProduct.setCellValueFactory(new PropertyValueFactory<>("Name"));
        itemNameProduct.setCellValueFactory(new PropertyValueFactory<>("ID"));
        costProduct.setCellValueFactory(new PropertyValueFactory<>("Price"));
        unitProduct.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        qtyAvailableProduct.setCellValueFactory(new PropertyValueFactory<>("Qty"));

        ObservableList<ItemBasic> observableList = FXCollections.observableArrayList();
        for (ItemBasic iv : Main.godown.i) {
            observableList.add(iv);
        }
        productTable.setItems(observableList);
        signoutPane.setVisible(false);
        //-->
        cartTable.setVisible(false);
        productTable.setVisible(false);
        hBox.setVisible(false);
        receiptPane.setVisible(false);
        btnRefresh.setVisible(false);
    }


    public void setupConsumerSpeceficStage() {
        Stage thisStage = (Stage) btnStartSession.getScene().getWindow();
        c = (Consumer) thisStage.getUserData();
        lblConsumerName.setText(c.getName());
        lblConsumerID.setText(c.getID());
        btnStartSession.setVisible(false);
        signoutPane.setVisible(false);
        //-->
        cartTable.setVisible(false);
        productTable.setVisible(true);
        productTable.toFront();
        hBox.setVisible(true);
        receiptPane.setVisible(false);
        btnRefresh.setVisible(true);

    }

    @FXML
    private void signOutPaneHandler(ActionEvent actionEvent) {
        signoutPane.toFront();
        signoutPane.setVisible(true);
        if (actionEvent.getSource() == btnSignoutYes) {
            Stage stage = (Stage) btnSignout.getScene().getWindow();
            stage.close();
        } else if (actionEvent.getSource() == btnSignoutNo) {
            signoutPane.setVisible(false);
            signoutPane.toBack();
        }
    }

    @FXML
    public void ReceiptPane(){
        cartTable.setVisible(true);
        productTable.setVisible(false);
        receiptPane.toFront();
        receiptPane.setVisible(true);
        String s = new String("The following items could not be added:");
        try {
            ArrayList<String> itemsNotInCart = c.checkoutCart();
            try {
                for (String s_ : itemsNotInCart)
                    s += "\n" + s_;
            }catch(NullPointerException e_){
                s  = "All Items were successfully Added";
            }
        }catch(NullPointerException e){
            s = "No items in cart.";
        }
        s += "Please scan the QR Code\n and pay Rs." + c.getAmount();
        lblPaid.setText(s);
    }
    @FXML
    void ReceiptClose(){
        lblPaid.setText("Thank you! Please visit again.");
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void refreshProductsTable(){
        ObservableList<ItemBasic> observableList = FXCollections.observableArrayList();
        for (ItemBasic iv : Main.godown.i) {
            observableList.add(iv);
        }
        productTable.setItems(observableList);
    }


    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCart) {
            productTable.setVisible(false);
            cartTable.setVisible(true);
            cartTable.toFront();
        }
        if (actionEvent.getSource() == btnProducts) {
            cartTable.setVisible(false);
            productTable.setVisible(true);
            productTable.toFront();
        }
    }


}

