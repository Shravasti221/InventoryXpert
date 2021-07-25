package com.labProject;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;
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
    private TableView productTable;
    @FXML
    private HBox hBox;

    public void initialize(URL location, ResourceBundle resources) {
        /*SHRAVYA:
         * the godown object is named as "Main.godown"
         * the consumer object is named "this.c" or just "c"
         *this is the part that you need to do. We need to create a 2 tables.
         * table1 ID: cartTable
         * in this you need to print the items in c.cart which is an array of ItemBasic.
         * I've named the different columns as itemIDCart, itemNameCart, qtyCart, costCart, amountCart
         * you can get these values through ID (Getter Funtion getID), name (Getter Funtion getName), qty(getQty) and getAmount() of the ItemBasic class
         * so you need to use these functions for each item in c.cart to get the values for the different columns in cartTable
         *
         * table2 productTable
         *  in this you need to print the items in Main.godown.i which is an array of ItemBasic (godown is an object of Godown type and i is the items in the godown).
         *  I've named the different columns as itemIDProduct, itemNameProduct, qtyAvailableProduct, costProduct, unitProduct, addToCartProduct
         * you can get these values through ID:Getter Funtion getID, name:Getter Funtion getName, qty(getQty), getPrice  and getUnit() of the ItemBasic class
         * use these functions for each item in Main.godown.i to get the values for productTable. You only need to make the addToCart wala column editable so that they can add how many ever items they want
         * Everytime user edits that table you should be able to update no. of items in cart and amount.
         *
         * Also I haven't declared the column names in this class....so you need to do that to use the column name ids
         * you have to declare the variable like this. Suppose column id is itemIDCart then to use this id in the java file u need to first declare itemIDCart like this
         * @FXML
         * private TableColumn itemIDCart;
         *
         *
         * ALSO
         * if the code doesn't run here (shows error, shift it to setupConsumerSpeceficStage() where I have marked with arrow)
         * */


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
        hBox.setVisible(true);

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

