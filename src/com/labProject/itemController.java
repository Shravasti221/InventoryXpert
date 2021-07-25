package com.labProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class itemController implements Initializable
{
    public TableView<ItemBasic> productTable;

    public TableColumn<ItemBasic,String> itemIDProduct;
    public TableColumn<ItemBasic,String> itemNameProduct;
    public TableColumn<ItemBasic,Float> costProduct;
    public TableColumn<ItemBasic,Integer> unitProduct;
    public TableColumn<ItemBasic,Integer> qtyAvailableProduct;
    //public TableColumn<ItemBasic,Boolean> addToCartProduct;


    int assign_size(String type_of_obj){
        if(type_of_obj == "carton_large")
            return 100;
        else if(type_of_obj == "carton_medium")
            return 50;
        else if(type_of_obj == "carton_small")
            return 25;
        else if(type_of_obj == "Weight_KiloGrams")
            return 30;
        else if(type_of_obj == "Weight_Grams")
            return 3;
        else if(type_of_obj == "beverage_100ml")
            return 25;
        else if(type_of_obj == "beverage_250ml")
            return 30;
        else if(type_of_obj == "beverage_500ml")
            return 40;
        else if(type_of_obj == "beverage_1l")
            return 55;
        else if(type_of_obj == "beverage_2l")
            return 75;
        else if(type_of_obj == "beverage_5l")
            return 120;
        else if(type_of_obj == "sachets")
            return 3;
        else if(type_of_obj == "packets_small")
            return 10;
        else if(type_of_obj == "packets_medium")
            return 20;
        else if(type_of_obj == "packets_large")
            return 40;
        System.out.println("Unidentified item category. Initialising size as 0");
        return 0;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        itemIDProduct.setCellValueFactory(new PropertyValueFactory<>("Name"));
        itemNameProduct.setCellValueFactory(new PropertyValueFactory<>("ID"));
        costProduct.setCellValueFactory(new PropertyValueFactory<>("Price"));
        unitProduct.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        qtyAvailableProduct.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        //addToCartProduct.setCellValueFactory(new PropertyValueFactory<>("Name"));
        productTable.setItems(observableList);
    }

    ObservableList<ItemBasic> observableList= FXCollections.observableArrayList(
            //new ItemBasic("Ram","123",23,4,5,true)
    );
}
