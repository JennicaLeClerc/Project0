package com.revature.service;

import com.revature.model.User;

import java.util.*;

// Bold: "\033[1mTEXT\033[0m"

public class UserService {
    List<String> usernameList = new ArrayList<>();
    List<User> userpinList = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    private static int current_account_num = 1110; // The number has to have these modifiers for the incrementation to work

    public void createUser(){ // purpose create user?
        int account_number = Account_Number();
        String[] names = Name();
        String[] credentials = usernameAndPasswordCreater();

        userpinList.add( new User(credentials[0], credentials[1])) ;
        User newuser = new User(account_number, credentials[0], credentials[1], names[0], names[1]);
        newuser.setChecking_balance(0);
        newuser.setSavings_balance(0);
        System.out.println("You have created an account.");
    }

    // Logs in user
    public User login(){
        // Has you input your username and pincode
        String[] credentials = usernameAndPasswordInput();

        // For each user in the userlist, checks if the username exists and if it does,
        // checks to see if the pincode is correct for that user.
        for(User user: userpinList){
            String tempUsername = user.getUsername(); // explicit assignment
            if(tempUsername.equals(credentials[0])){
                if(user.getPincode().equals(credentials[1])){ // method chaining to just return the value that .equals()
                    return user;
                }
            }
        }
        // If the username or pincode is incorrect it returns this:
        System.out.println("\033[1mInvalid Username or Pincode\033[0m");
        return null;
    }

    // Asking for a non-empty First and Last Name
    public String[] Name(){
        String fname;
        String lname;
        Scanner scanner = new Scanner(System.in); // Creating Scanner Object
        do{
            System.out.println("Enter First Name: ");
            fname = scanner.nextLine();
        }
        while( fname == null || fname.length() <= 1 );

        do{
            System.out.println("Enter Last Name: ");
            lname = scanner.nextLine(); // nextLine is for Strings
        }
        while( lname == null || lname.length() <= 1 );

        return new String[]{fname, lname};
    }

    // Setting the account Number
    public int Account_Number(){
        // Gives the next account number
        current_account_num++;
        System.out.println("Account Number: " + current_account_num + "\n");
        return current_account_num;
    }

    // Creates a username and pincode string
    public String[] usernameAndPasswordCreater(){
        String username = UserName();
        String pincode = PinCode();
        scanner.nextLine();
        return new String[]{username, pincode};
    }

    // Asks for username and pincode for loggin purposes
    public String[] usernameAndPasswordInput(){
        //scanner.nextLine(); // Fixes: Doesn't let you input the username the first time. No longer a problem with the order of inputs
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();

        System.out.println("\nPlease enter your 4 digit pincode: ");
        //String pincode = scanner.nextLine();
        String pincode = String.valueOf(scanner.nextInt());
        return new String[]{username, pincode};
    }

    // Has you create a username
    // - length: more than 5 characters and less than 10 characters
    public String UserName(){
        boolean isnewuser = true;
        System.out.println("\nUser Name must be at least 5 characters and no more than 10.");
        String username;

        do{
            do{
                System.out.println("Enter User Name: ");
                username = scanner.nextLine();
                isnewuser = GoodUserName( username );
            }
            while( username == null || username.length() > 10 || username.length() < 5);
        }
        while( !isnewuser );

        usernameList.add(username);

        //System.out.println("");
        return username;
    }

    // Checks if username
    public boolean GoodUserName( String username ){
        boolean isgood = true;
        for( String i : usernameList){
            if( i.equals( username ) ){ // Use .equals() for strings
                System.out.println("\033[1mUsername in use. Please try again.\033[0m");
                isgood = false;
            }
        }
        return isgood;
    }

    //Create a pincode
    // - length: 4 digit pin
    public String PinCode(){
        String pincode;
        int pin = 0;
        int numberOfDigits = 0;
        do{
            System.out.println("Select 4 digit pin: ");
            try{
                pin = scanner.nextInt(); // nextInt is for integers
            }
            catch ( InputMismatchException e ){
                System.out.println("You did not enter a number. Please try again.");
                pin = 0;
                scanner.nextLine(); // Fixed: without this the do loop was infinite without letting me input a new pin.
            }
            numberOfDigits = String.valueOf(pin).length();
        }
        while( numberOfDigits != 4 );

        pincode = String.valueOf(pin);

        System.out.println("");
        return pincode;
    }

    // Withdraw and Deposite
    /*public void Deposit( String account, double amount ){
        System.out.println("Depositing funds to " + account );
        switch ( account ){
            case "Checking":
                this.checking_balance += amount;
                break;
            case "Savings":
                this.savings_balance += amount;
                break;
            default:
                System.out.println("\033[1mACCOUNT DOES NOT EXIST\033[0m");
                break;
        }
        this.Print_Balance( account );
    }

    public void Withdraw( String account, double amount ){
        System.out.println("Withdrawing funds from " + account);
        switch ( account ){
            case "Checking":
                if( this.checking_balance < amount ){
                    System.out.println("          \033[1mNOT ENOUGH FUNDS\033[0m");
                    this.Print_Balance("All");
                    break;
                }
                else{
                    this.checking_balance -= amount;
                    this.Print_Balance(account);
                }
                break;
            case "Savings":
                if( this.savings_balance < amount ){
                    System.out.println("          \033[1mNOT ENOUGH FUNDS\033[0m");
                    this.Print_Balance("All");
                    break;
                }
                else{
                    this.savings_balance -= amount;
                    this.Print_Balance(account);
                }
                break;
            default:
                System.out.println("\033[1mACCOUNT DOES NOT EXIST\033[0m");
                this.Print_Balance("All");
                break;
        }
    }

    public void Print_Balance(){
        System.out.println("   Checking account balance: $" + String.format("%.2f", this.checking_balance));
        System.out.println("   Savings account balance:  $" + String.format("%.2f", this.savings_balance) + "\n");
    }

    public void Print_Balance( String account ){
        switch (account){
            case "Checking":
                System.out.println("   Checking account balance: $" + String.format("%.2f", this.checking_balance) + "\n");
                break;
            case "Savings":
                System.out.println("   Savings account balance: $" + String.format("%.2f", this.savings_balance) + "\n");
                break;
            case "All":
                this.Print_Balance();
                break;
            default:
                System.out.println("\033[1mACCOUNT DOES NOT EXIST\033[0m \n");
                break;
        }
    }*/
}
