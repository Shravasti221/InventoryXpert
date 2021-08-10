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
    private Button btnRefresh;
    @FXML
    private HBox hBox;
    @FXML
    private Label lblDispCartCost;
    @FXML
    private Label lblDispCartItems;

    @FXML
    private TextField txtAddItem;
    @FXML
    private TextField txtAddItemQty;
    @FXML
    private TextField txtDelItem;
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

    public TableView<ItemBasic> cartTable;
    public TableColumn<ItemBasic,String> itemIDCart;
    public TableColumn<ItemBasic,String> itemNameCart;
    public TableColumn<ItemBasic,Float> costCart;
    public TableColumn<ItemBasic,Integer> unitCart;
    public TableColumn<ItemBasic,Integer> qtyCart;

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

        itemIDCart.setCellValueFactory(new PropertyValueFactory<>("ID"));
        itemNameCart.setCellValueFactory(new PropertyValueFactory<>("Name"));
        costCart.setCellValueFactory(new PropertyValueFactory<>("Price"));
        unitCart.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        qtyCart.setCellValueFactory(new PropertyValueFactory<>("Qty"));
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
        lblConsumerName.setText(c.getUserName());
        lblConsumerID.setText(c.getID());
        btnStartSession.setVisible(false);
        signoutPane.setVisible(false);
        cartTable.setVisible(false);
        receiptPane.setVisible(false);
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
            c.cart.clear();
            stage.close();
        } else if (actionEvent.getSource() == btnSignoutNo) {
            signoutPane.setVisible(false);
            signoutPane.toBack();
        }
    }

    @FXML
    public void ReceiptPane(){
        cartTable.setVisible(false);
        productTable.setVisible(false);
        receiptPane.toFront();
        receiptPane.setVisible(true);
        String s = "Nothing Set";
        ArrayList<String> itemsNotInCart = c.checkoutCart();
        if (itemsNotInCart.isEmpty())
            s  = "All Items were successfully Added.\n";
        else {
            s = "Not added:";
            for (String s_ : itemsNotInCart)
                s += "\n" + s_;
        }

        s += "\nScan the QR Code\n and pay Rs." + c.getAmount();
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
        c.cart.clear();
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

        PatternChecker verifier = new PatternChecker();
        String ItemId_toBeAdded = txtAddItem.getText();
        String ItemId_toBeDeleted = txtDelItem.getText();
        String ItemQty_toBeAdded = txtAddItemQty.getText();
        ItemBasic ret_val;

        if(ItemId_toBeAdded != null && verifier.ItemID().check(ItemId_toBeAdded)){
            ret_val = Main.godown.getItem(ItemId_toBeAdded);
            if(ret_val == null)
                System.out.println("Item of the ID in \"to be Added\" Text field not found");
            else {
                System.out.println("Is the retval in consumer_controller == ret_val.copy()?" + (ret_val == ret_val.copy()));
                ret_val = ret_val.copy();
                boolean itemNotInCart = true;
                for (ItemBasic i : c.cart)
                    if (i.getID().equals(ItemId_toBeAdded)) {
                        System.out.println("Item already present in cart. Please delete that and try again");
                        itemNotInCart = false;
                        break;
                    }
                if (verifier.isInt().check(ItemQty_toBeAdded) && itemNotInCart) {
                    ret_val.setQty(Integer.parseInt(ItemQty_toBeAdded));
                    c.cart.add(ret_val);
                    txtAddItem.clear();
                    txtAddItemQty.clear();
                }
                else
                    System.out.println("The type of quantity has to be Integer");
            }
        }

        if(ItemId_toBeDeleted != null && verifier.ItemID().check(ItemId_toBeDeleted)) {
            ret_val = new ItemBasic();
            for (ItemBasic i : c.cart)
                if (i.getID().equals(ItemId_toBeDeleted)) {
                    ret_val = i;
                    break;
                }
            if (c.cart.remove(ret_val) == true)
                txtDelItem.clear();
            else
                System.out.println("Item of the ID in \"to be deleted\" Text field not found");
        }
        observableList = FXCollections.observableArrayList();
        for (ItemBasic iv : c.cart) {
            observableList.add(iv);
        }

        cartTable.setItems(observableList);

        float amt = 0;
        int noOfItems = 0;
        for (ItemBasic i : c.cart) {
            amt += i.getAmount();
            noOfItems++;
        }
        lblDispCartCost.setText("Rs."+amt);
        lblDispCartItems.setText(""+noOfItems);
    }

    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        signoutPane.setVisible(false);
        signoutPane.toBack();
        if (actionEvent.getSource() == btnCart) {
            receiptPane.setVisible(false);
            productTable.setVisible(false);
            cartTable.setVisible(true);
            cartTable.toFront();
        }
        if (actionEvent.getSource() == btnProducts) {
            receiptPane.setVisible(false);
            cartTable.setVisible(false);
            productTable.setVisible(true);
            productTable.toFront();
        }
    }


}

