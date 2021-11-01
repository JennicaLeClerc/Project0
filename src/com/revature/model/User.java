package com.revature.model;

import java.io.Serializable;

// java bean design pattern - for models
// 1. private properties/variables (public getter/setter)
// 2. public no-argument constructor - X
// 3. implements - X
public class User extends AbstractUser implements Serializable {

    public User(){
    }

    public User(String username, String pincode){
        super(username, pincode);
    }

    public User(int account_number, String username, String pincode){
        super(account_number, username, pincode);
    }

    public User(int account_number, double checking_balance, double savings_balance){
        super(account_number, checking_balance, savings_balance);
    }

    public User(int account_number, String firstName, String lastName, String username, String pincode){
        super(account_number, firstName, lastName, username, pincode);
    }

    public User(int account_number, String firstName, String lastName, String username, String pincode, double checkiing_balance, double savings_balance){
        super( account_number, firstName, lastName, username, pincode, checkiing_balance, savings_balance);
    }
}
