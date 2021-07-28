package com.labProject;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String userName ;
    private final String ID;
    private final String password;
    private String mobile;

    public User(){
        this.userName = null;
        this.ID = null;
        this.password = null;
        this. mobile = null;
    }
    public User(String ID, String password) {
        this.password = password ;
        this.ID = ID;
    }
    public User(String ID, String n, String mno, String password) {
        this.password = password ;
        this.ID = ID;
        this.mobile = mno;
        this.userName = n;
    }

    public String getName() {
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
        boolean ret_val = false;
        try{
            ret_val = this.password.equals(pwd);
        }catch(NullPointerException e){
            System.out.println("No password was provided");
            return false;
        }
        return ret_val;
    }

    @Override
    public String toString() {
        return "UserName:"+ userName + " UserID " + ID + " Password:" + password;
    }

}
