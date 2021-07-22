package com.labProject;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Consumer_controller implements Initializable{
    @FXML
    private VBox pnlButtons = null;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnCart;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlItems;
    @FXML
    private TitledPane signoutPane;
    @FXML
    private Button btnSignoutYes;
    @FXML
    private Button btnSignoutNo;
    @FXML
    private TableColumn addToCart;

    Godown g;

    @FXML
    private TableView searchTable;
    public void initialize( URL location, ResourceBundle resources) {
        ObservableList<ItemBasic> searchData = FXCollections.observableArrayList();
        for(ItemBasic item: g.i)
            searchData.add(item);

    }

    @FXML
    private boolean signoutPanehandler(ActionEvent actionEvent){
        signoutPane.toFront();
        if (actionEvent == null)
            signoutPane.setVisible(true);
        else if(actionEvent.getSource() == btnSignoutYes)
            return true;
        else if(actionEvent.getSource() == btnSignoutNo){
            signoutPane.setVisible(false);
        }
        return false;
    }
    @FXML
    private void close(){
        boolean signout = signoutPanehandler(null);
        if (signout) {
            Stage stage = (Stage) btnSignout.getScene().getWindow();
            stage.close();
        }

    }
    @FXML
    void qtyHandler(){

    }
    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCart) {
            pnlItems.setStyle("-fx-background-color : #1620A1");
        }
        if (actionEvent.getSource() == btnProducts) {
            pnlItems.setStyle("-fx-background-color : #53639F");
        }
    }
    /*private void setupTableViewColumn() {
        addToCart.setCellFactory(column -> new CheckBoxTableCell<>());
        addToCart.setCellValueFactory(cellData -> {
        boolean cellValue = cellData.getValue();
        BooleanProperty property = cellValue.choosedProperty();

            // Add listener to handler change
            property.addListener((observable, oldValue, newValue) -> cellValue.setChoosed(newValue));

            return property;
        });*/


}
