package com.labProject;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class itemController implements Initializable {


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
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
