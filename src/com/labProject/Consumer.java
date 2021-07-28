package com.labProject;

import java.util.ArrayList;

public class Consumer extends User{
    private ArrayList<ItemBasic> cart;
    private float amount = 0;
    public Consumer( String S, String ID){
        super(S, ID);
        this.cart = null;
    }
    public Consumer(String ID, String name, String mno, String password){
        super(ID, name, mno, password);
        cart = null;
    }
    ArrayList<String> checkoutCart() {
        ArrayList<String> ret_val = new ArrayList<String>();
        for (ItemBasic i : cart) {
            try {
                Main.godown.buyItem(i);
                this.amount += i.getAmount();
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
