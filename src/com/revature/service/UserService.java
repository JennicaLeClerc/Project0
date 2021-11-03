package com.revature.service;

import com.revature.model.User;

import java.util.*;

// Bold: "\033[1mTEXT\033[0m"

public class UserService {
    Scanner scanner;
    MenuService menuService;

    public UserService(Scanner scanner, MenuService menuService){
        this.scanner = scanner;
        this.menuService = menuService;
    }

    List<String> usernameList = new ArrayList<>();
    List<User> userpinList = new ArrayList<>();

    private static int current_account_num = 1110; // The number has to have these modifiers for the incrementation to work

    /**
     * Creates a user with First Name, Last Name, Username, Pincode, Account No.
     * Checking Balance, Savings Balance.
     */
    public void createUser(){
        int account_number = Account_Number();
        String[] names = Name();
        String[] credentials = UsernameAndPinCreater();
        double[] balances = InitialBalances();

        userpinList.add( new User(credentials[0], credentials[1])) ;
        User newuser = new User(account_number, credentials[0],
                credentials[1], names[0], names[1], balances[0], balances[1]);
        menuService.accountCreatedPrint();
    }

    /**
     * Log in user has you input your username and pincode.
     * For each user in the userlist, checks if the username exists and
     * if it does, checks to see if the pincode is correct for that user.
     * If the username and password is correct it returns the user.
     * If not it returns that it is invalid.
    */
     public User Login(){
        String[] credentials = UsernameAndPinInput();

        for(User user: userpinList){
            String tempUsername = user.getUsername(); // explicit assignment
            if(tempUsername.equals(credentials[0])){
                if(user.getPincode().equals(credentials[1])){ // method chaining to just return the value that .equals()
                    return user;
                }
            }
        }
        menuService.notValidUsernamePin();
        return null;
    }

    /**
     * Asking for a non-empty First and Last Name from user then returns them.
     */
    public String[] Name(){
        String fname;
        String lname;
        Scanner scanner = new Scanner(System.in); // Creating Scanner Object
        do{
            menuService.enterFirstNamePrompt();
            fname = scanner.nextLine();
        }
        while( fname == null || fname.length() <= 1 );

        do{
            menuService.enterLastNamePrompt();
            lname = scanner.nextLine(); // nextLine is for Strings
        }
        while( lname == null || lname.length() <= 1 );

        return new String[]{fname, lname};
    }

    /**
     * Gives the new user the next Account Number then returns the Account Number.
     */
    public int Account_Number(){
        current_account_num++;
        menuService.printAccountNumber(current_account_num);
        return current_account_num;
    }

    /**
     * Creates a Username and Pincode string
     */
    public String[] UsernameAndPinCreater(){
        String username = UserName();
        String pincode = PinCode();
        scanner.nextLine();
        return new String[]{username, pincode};
    }

    /**
     * Asks for Username and Pincode for login purposes.
     * Returns string of Username and Pincode
     */
    public String[] UsernameAndPinInput(){
        menuService.enterUsernamePrompt();
        String username = scanner.nextLine();

        menuService.enterPinPrompt();
        String pincode = String.valueOf(scanner.nextInt());
        return new String[]{username, pincode};
    }

    /**
     * Has the user create a Username where the length has to be between 5
     * and 10 characters. Returns the Username.
     */
    public String UserName(){
        boolean isnewuser;
        String username;
        menuService.usernameLengthPrint();
        do{
            do{
                menuService.enterUsernamePrompt();
                username = scanner.nextLine();
                isnewuser = GoodUserName( username );
            }
            while( username == null || username.length() > 10 || username.length() < 5);
        }
        while( !isnewuser );
        usernameList.add(username);
        return username;
    }

    /**
     * Checks if the chosen Username is already in use. Returns true if
     * unique Username. Returns false if Username is already in use.
     */
    public boolean GoodUserName( String username ){
        boolean isgood = true;
        for( String i : usernameList){
            if( i.equals( username ) ){
                menuService.invalidUsername();
                isgood = false;
            }
        }
        return isgood;
    }

    /**
     * Has the user create a 4 Digit Pin. Catches if they do not enter 4 Digits.
     * Returns a String of the Pin.
     */
    public String PinCode(){
        int pin;
        int numberOfDigits;
        menuService.pinLengthPrint();
        do{
            menuService.enterPinPrompt();
            try{
                pin = scanner.nextInt();
            }
            catch ( InputMismatchException e ){
                menuService.invalidType();
                pin = 0;
                scanner.nextLine();
            }
            numberOfDigits = String.valueOf(pin).length();
        }
        while( numberOfDigits != 4 );
        return String.valueOf(pin);
    }

    /**
     * Makes sure that the initial balance for Checking and Savings is 0.
     * Prints out Balances. Returns the balances.
     */
    public double[] InitialBalances(){
        double checking = 0;
        double savings = 0;
        menuService.printBalances( checking, savings );
        return new double[]{checking, savings};
    }

    /**
     * Checks if you have enough money in the Account Type (Checking or Savings)
     * you selected for the Amount you want. If you don't have enough funds in
     * that account, you are shown your balances. If you do have enough fund,
     * that Amount is Withdrawn from the selected Account Type.
     */
    public void Withdraw( User user, String account, double amount ) {
        menuService.printWithdrawAcc(account);
        switch (account) {
            case "Checking":
                if (user.getChecking_balance() < amount) {
                    menuService.invalidFunds();
                    ViewBalance(user);
                    break;
                } else {
                    user.setChecking_balance(user.getChecking_balance() - amount);
                    ViewBalance(user, account);
                }
                break;
            case "Savings":
                if (user.getSavings_balance() < amount) {
                    menuService.invalidFunds();
                    ViewBalance(user);
                    break;
                } else {
                    user.setSavings_balance(user.getSavings_balance() - amount)
                    ViewBalance(user, account);
                }
                break;
            default:
                menuService.invalidAccountPrint();
                ViewBalance(user);
                break;
        }
    }

    /**
     * Adds the amount you want to Deposit to your desired Account Type
     * (Checking or Savings). Then prints out your current balance
     */
    public void Deposit( User user, String account, double amount){
        menuService.printDepositAcc( account );
        switch ( account ){
            case "Checking":
                user.setChecking_balance(user.getChecking_balance() + amount);
                break;
            case "Savings":
                user.setSavings_balance(user.getSavings_balance() + amount);
                break;
            default:
                menuService.invalidAccountPrint();
                break;
        }
        ViewBalance( user,account );
    }

    /**
     * Takes in a User and an Account Type (Checking or Savings or All) and prints out
     * the blanance(s) for those chosen. Tells you the Account Doesn't Exist if invalid
     * account given.
     */
    public void ViewBalance( User user, String account ) {
        switch( account ) {
            case "Checking":
                menuService.printCheckingBalance(user.getChecking_balance());
                break;
            case "Savings":
                menuService.printSavingsBalance(user.getSavings_balance());
                break;
            case "All":
                ViewBalance( user );
                break;
            default:
                menuService.invalidAccountPrint();
                break;
        }
    }

    /**
     * Takes in a User and prints out the balances for both Checking and Savings
     */
    public void ViewBalance( User user ){
        menuService.printBalances( user.getChecking_balance(), user.getSavings_balance() );
    }
}
