package com.labProject;


import java.util.ArrayList;

public class Producer extends User {
    float revenue_earned = 0;

    public Producer(){
        super();
        revenue_earned = 0;
    }

    public Producer( String S, String ID){
        super(S, ID);
    }
    public Producer(String ID, String name, String mno, String password){
        super(ID, name, mno, password);
    }
    public void editRevenueEarned(float rev){
        this.revenue_earned += rev;
    }

    public void resetRevenue_earned(){
        this.revenue_earned = 0;
    }
    public void addItem(ItemBasic i){
        Main.godown.addItem(i);
        Main.godown.updateProducer(this);
    }

    public ArrayList<ItemBasic> itemsProduced(){
        ArrayList<ItemBasic> ret_val = new ArrayList<ItemBasic>();
        for(ItemBasic item: Main.godown.i){
            if (item.getProducerID().equals(this.getID())  )
                ret_val.add(item);
        }
        return ret_val;
    }

    @Override
    public String toString() {
        return super.toString()+ ", revenue_earned=" + revenue_earned;
    }
}
