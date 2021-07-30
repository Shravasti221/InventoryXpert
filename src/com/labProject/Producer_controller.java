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

public class Producer_controller implements Initializable {
    Producer p;


    @FXML
    private Button btnProducts;
    @FXML
    private Button btnCollectRevenue;
    @FXML
    private Button btnSignout;
    @FXML
    private TitledPane signoutPane;
    @FXML
    private Button btnSignoutYes;
    @FXML
    private Button btnSignoutNo;


    @FXML
    private Button btnAddItem;
    @FXML
    private Label lblProducerName;
    @FXML
    private Label lblProducerID;
    @FXML
    private Label lblNoOfItems;
    @FXML
    private Button btnStartSession;
    @FXML
    private HBox hBox;

    @FXML
    private TitledPane itemInfoPane;
    @FXML
    private TextField txtItemName;
    @FXML
    private TextField txtItemQty;
    @FXML
    private ChoiceBox choiceItemType;
    @FXML
    private TextField txtItemPrice;
    @FXML
    private Button btnAddToInventory;
    @FXML
    private Label lblItemInfo;


    public TableView<ItemBasic> productTable;
    public TableColumn<ItemBasic,String> itemIDProduct;
    public TableColumn<ItemBasic,String> itemNameProduct;
    public TableColumn<ItemBasic,Float> costProduct;
    public TableColumn<ItemBasic,Integer> unitProduct;
    public TableColumn<ItemBasic,Integer> qtyAvailableProduct;


    public void initialize(URL location, ResourceBundle resources) {

        itemIDProduct.setCellValueFactory(new PropertyValueFactory<>("ID"));
        itemNameProduct.setCellValueFactory(new PropertyValueFactory<>("Name"));
        costProduct.setCellValueFactory(new PropertyValueFactory<>("Price"));
        unitProduct.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        qtyAvailableProduct.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        itemInfoPane.setVisible(false);
        signoutPane.setVisible(false);
        itemInfoPane.setVisible(false);
        productTable.setVisible(false);

        choiceItemType.getItems().addAll("large", "medium", "small", "Kg", "g", "100ml", "250ml", "500ml", "1l",
                "2l","5l", "sachet", "packet(small)", "packet(medium)","packet(large)" );
    }


    public void setupProducerSpeceficStage() {
        Stage thisStage = (Stage) btnStartSession.getScene().getWindow();
        p = (Producer) thisStage.getUserData();
        lblProducerName.setText(p.getName());
        lblProducerID.setText(p.getID());
        lblNoOfItems.setText(""+ p.itemsProduced().size());
        ObservableList<ItemBasic> observableList = FXCollections.observableArrayList();
        for (ItemBasic iv : p.itemsProduced()) {
            observableList.add(iv);
        }
        productTable.setItems(observableList);
        btnStartSession.setVisible(false);
        signoutPane.setVisible(false);
        itemInfoPane.setVisible(false);
        productTable.setVisible(true);
        productTable.toFront();
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
    void addItem(){
        String itemName = txtItemName.getText();
        if(itemName.isEmpty()) {
            lblItemInfo.setText("Select item type");
            return;
        }
        int itemQty;
        try{
            itemQty = Integer.parseInt(txtItemQty.getText());
        }catch(NumberFormatException e){
            lblItemInfo.setText("Invalid format for Quantity field.");
            txtItemQty.setText("");
            return;
        }
        float itemPrice;
        try{
            itemPrice = Float.parseFloat(txtItemQty.getText());
        }catch(NumberFormatException e){
            lblItemInfo.setText("Invalid format for Price field.");
            txtItemPrice.setText("");
            return;
        }
        String type_of_object = (String) choiceItemType.getValue();
        if(type_of_object.isEmpty()) {
            lblItemInfo.setText("Select item type");
            return;
        }
        try {
            p.addItem(new ItemBasic(itemName, p.getID(), type_of_object, itemQty, itemPrice, assign_size(type_of_object)));
        }catch (GodownError e){
            System.out.println(e);
            lblItemInfo.setText(e.toString());
        }
        lblItemInfo.setText("The Item was successfully added to the godown.");
    }

    int assign_size(String type_of_obj){
        if(type_of_obj.equals("large"))
            return 300;
        else if(type_of_obj.equals("medium"))
            return 100;
        else if(type_of_obj.equals("small"))
            return 50;
        else if(type_of_obj.equals("Kg"))
            return 30;
        else if(type_of_obj.equals("g"))
            return 3;
        else if(type_of_obj.equals("100ml"))
            return 10;
        else if(type_of_obj.equals("250ml"))
            return 25;
        else if(type_of_obj.equals("500ml"))
            return 50;
        else if(type_of_obj.equals("1l"))
            return 100;
        else if(type_of_obj.equals("2l"))
            return 200;
        else if(type_of_obj.equals("5l"))
            return 500;
        else if(type_of_obj.equals("sachet"))
            return 3;
        else if(type_of_obj.equals("packet(small)") )
            return 25;
        else if(type_of_obj.equals("packet(medium)"))
            return 50;
        else if(type_of_obj.equals("packet(large)"))
            return 100;
        System.out.println("Unidentified item category. Initialising size as 0");
        return 0;
    }

    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnAddItem) {
            productTable.setVisible(false);
            itemInfoPane.setVisible(true);
            itemInfoPane.toFront();
        }
        if (actionEvent.getSource() == btnProducts) {
            itemInfoPane.setVisible(false);
            productTable.setVisible(true);
            productTable.toFront();
        }
        if (actionEvent.getSource() == btnCollectRevenue){

        }

    }


}

