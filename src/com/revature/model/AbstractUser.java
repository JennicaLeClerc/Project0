package com.revature.model;

import java.io.Serializable;

public abstract class AbstractUser implements Serializable {
    protected String firstName;
    protected String lastName;

    protected String username;
    protected String pincode;

    protected int account_number;
    protected double checking_balance;
    protected double savings_balance;

    public AbstractUser() {

    }

    public AbstractUser(String username, String pincode) {
        this.username = username;
        this.pincode = pincode;
    }

    public AbstractUser(int account_number, String username, String pincode) {
        this.account_number = account_number;
        this.username = username;
        this.pincode = pincode;
    }

    public AbstractUser(int account_number, double checking_balance, double savings_balance) {
        this.account_number = account_number;
        this.checking_balance = checking_balance;
        this.savings_balance = savings_balance;
    }

    public AbstractUser(int account_number, String firstName, String lastName, String username, String pincode) {
        this.account_number = account_number;

        this.firstName = firstName;
        this.lastName = lastName;

        this.username = username;
        this.pincode = pincode;
    }

    public AbstractUser(int account_number, String username, String firstName, String lastName, String pincode, double checking_balance, double savings_balance) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.username = username;
        this.pincode = pincode;

        this.account_number = account_number;
        this.checking_balance = checking_balance;
        this.savings_balance = savings_balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public double getChecking_balance() {
        return checking_balance;
    }

    public void setChecking_balance(double checking_balance) {
        this.checking_balance = checking_balance;
    }

    public double getSavings_balance() {
        return savings_balance;
    }

    public void setSavings_balance(double savings_balance) {
        this.savings_balance = savings_balance;
    }
}
