package com.labProject;


import java.util.ArrayList;

public class Producer extends User {
    float revenue_earned = 0;
    public Producer( String S, String ID){
        super(S, ID);
    }
    public Producer(String ID, String name, String mno, String password){
        super(ID, name, mno, password);
    }
    public void editRevenueEarned(float rev){
        this.revenue_earned += rev;
    }
}
