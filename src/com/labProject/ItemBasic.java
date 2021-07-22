package com.labProject;


import java.io.Serializable;
import java.util.ArrayList;

public class ItemBasic implements Serializable {
    private static final long serialVersionUID = 1L;
    float CostPrice;
    float SellPrice;
    int space_per_unit;
    int qty;
    String whoProduced;
    type obj_type;
    String unit;
    // in case of producer, the price he is selling at;
    //in case of consumer the price he is buying at.
    String name;
    String ID;
    //Issued by the godown classHK,/LL
    enum type{
        carton_large, carton_medium, carton_small,
        Weight_KiloGrams,
        Weight_Grams,
        beverage_100ml,beverage_250ml, beverage_500ml, beverage_1l, beverage_2l, beverage_5l,
        sachets,
        packets_small, packets_medium, packets_large;

    }

    float getSpace(){
        return qty * space_per_unit;
    }

    int assign_size(){
        if(this.obj_type == type.carton_large)
            return 100;
        else if(this.obj_type == type.carton_medium)
            return 50;
        else if(this.obj_type == type.carton_small)
            return 25;
        else if(this.obj_type == type.Weight_KiloGrams)
            return 30;
        else if(this.obj_type == type.Weight_Grams)
            return 3;
        else if(this.obj_type == type.beverage_100ml)
            return 25;
        else if(this.obj_type == type.beverage_250ml)
            return 30;
        else if(this.obj_type == type.beverage_500ml)
            return 40;
        else if(this.obj_type == type.beverage_1l)
            return 55;
        else if(this.obj_type == type.beverage_2l)
            return 75;
        else if(this.obj_type == type.beverage_5l)
            return 120;
        else if(this.obj_type == type.sachets)
            return 3;
        else if(this.obj_type == type.packets_small)
            return 10;
        else if(this.obj_type == type.packets_medium)
            return 20;
        else if(this.obj_type == type.packets_large)
            return 40;
        System.out.println("Unidentified item category. Initialising size as 0");
        return 0;
    }

    @Override
    public String toString() {
        return "The item " + name + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity=" + qty +
                '}';
    }
    public String getName(){
        return this.name;
    }

    public String getID() {
        return ID;
    }

    public int getQty() {
        return qty;
    }

    public float getCostPrice() {
        return CostPrice;
    }

    public float getSellPrice() {
        return SellPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void addToCart(){

    }


    //use dynamic dispatch?
}
