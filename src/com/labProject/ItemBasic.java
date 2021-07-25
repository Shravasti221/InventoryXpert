package com.labProject;


import java.io.Serializable;
import java.util.ArrayList;

public class ItemBasic{
    float price;
    int space_per_unit;
    int qty;
    String whoProduced;
    String unit;
    // in case of producer, the price he is selling at;
    //in case of consumer the price he is buying at.
    String name;
    String ID;
    //Issued by the godown classHK,/LL

    float getSpace(){
        return qty * space_per_unit;
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

    public float getAmount(){
        return qty*price;
    }

    public float getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }

    public void setQty(int q) {
        this.qty = q;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
