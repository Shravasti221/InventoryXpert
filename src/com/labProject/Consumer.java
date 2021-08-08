package com.labProject;

import java.util.ArrayList;

public class Consumer extends User{
    ArrayList<ItemBasic> cart;
    private float amount = 0;
    public Consumer( String S, String ID){
        super(S, ID);
        this.cart = new ArrayList();
    }
    public Consumer(String ID, String name, String mno, String password){
        super(ID, name, mno, password);
        cart = new ArrayList();
    }
    ArrayList<String> checkoutCart() {
        this.amount = 0;
        ArrayList<String> ret_val = new ArrayList<String>();
        System.out.println("Items in cart in consumer.java : ");
        for (ItemBasic i : cart) {
            try {
                System.out.println(i);
                System.out.println("Moving to godown to buy items");
                Main.godown.buyItem(i.copy());
                this.amount += i.getAmount();
                System.out.println("Amount for the item " + i + " is " + i.getAmount());
            } catch (ItemError e) {
                ret_val.add(i.getID());
            }
        }
        return ret_val;
    }
    public float getAmount() {
        return amount;
    }
}
