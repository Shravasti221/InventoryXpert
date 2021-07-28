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


    int assign_size(String type_of_obj){
        if(type_of_obj.equals("carton_large"))
            return 100;
        else if(type_of_obj.equals("carton_medium"))
            return 50;
        else if(type_of_obj.equals("carton_small"))
            return 25;
        else if(type_of_obj.equals("Weight_KiloGrams"))
            return 30;
        else if(type_of_obj.equals("Weight_Grams"))
            return 3;
        else if(type_of_obj.equals("beverage_100ml"))
            return 25;
        else if(type_of_obj.equals("beverage_250ml"))
            return 30;
        else if(type_of_obj.equals("beverage_500ml"))
            return 40;
        else if(type_of_obj.equals("beverage_1l"))
            return 55;
        else if(type_of_obj.equals("beverage_2l"))
            return 75;
        else if(type_of_obj.equals("beverage_5l"))
            return 120;
        else if(type_of_obj.equals("sachets"))
            return 3;
        else if(type_of_obj.equals("packets_small") )
            return 10;
        else if(type_of_obj.equals("packets_medium"))
            return 20;
        else if(type_of_obj.equals("packets_large"))
            return 40;
        System.out.println("Unidentified item category. Initialising size as 0");
        return 0;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }


}
