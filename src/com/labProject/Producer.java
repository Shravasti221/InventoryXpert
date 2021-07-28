package com.labProject;


import java.util.ArrayList;

public class Producer extends User {
    float revenue_earned = 0;
    public ArrayList<ItemBasic> itemsProduced;

    public Producer(){
        super("", "");
        revenue_earned = 0;
        itemsProduced = new ArrayList<>();
    }

    public Producer( String S, String ID){
        super(S, ID);
        itemsProduced = new ArrayList<>();
    }
    public Producer(String ID, String name, String mno, String password){
        super(ID, name, mno, password);
        itemsProduced = new ArrayList<>();
    }
    public void editRevenueEarned(float rev){
        this.revenue_earned += rev;
    }
    public void addItem(ItemBasic i){
        this.itemsProduced.add(i);
        Main.godown.addItem(i);
        Main.godown.updateProducer(this);
        Main.godown.addItem(i);
    }

    @Override
    public String toString() {
        return super.toString()+"Producer{" +
                "revenue_earned=" + revenue_earned +
                ", itemsProduced=" + itemsProduced +
                '}';
    }
}
