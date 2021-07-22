package com.labProject;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String userName ;
    private final String ID;
    private final String password;
    private String mobile;
    private ArrayList<ItemBasic> belongings;

    public User(){
        this.userName = null;
        this.ID = null;
        this.password = null;
        this. mobile = null;
        this.belongings = null;
    }
    public User(String ID, String password) {
        this.password = password ;
        this.ID = ID;
        belongings = null;
    }
    public User(String ID, String n, String mno, String password) {
        this.password = password ;
        this.ID = ID;
        this.mobile = mno;
        this.userName = n;
        belongings = null;
    }

    public String getUserName() {
        return userName ;
    }
    public String getID(){
        return this.ID ;
    }
    public String getMobile(){return this.mobile;}

    public void setUserName(String s){
        this.userName = s;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public boolean checkPassword(String pwd){
        return this.password.equals(pwd);
    }

    @Override
    public String toString() {
        return userName ;
    }




}
