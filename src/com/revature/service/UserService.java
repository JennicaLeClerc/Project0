package com.revature.service;

import com.revature.model.User;

import java.util.*;

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
    }

    public User login(){ // login
        String[] credentials = usernameAndPasswordInput();

        for(User user: userpinList){ // for each User in userList
            String tempUsername = user.getUsername(); // explicit assignment
            if(tempUsername.equals(credentials[0])){
                if(user.getPincode().equals(credentials[1])){ // method chaining to just return the value that .equals()
                    return user;
                }
            }
        }
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

    public int Account_Number(){
        // Gives the next account number
        current_account_num++;
        System.out.println("Account Number: " + current_account_num + "\n");
        return current_account_num;
    }

    public String[] usernameAndPasswordCreater(){
        String username = UserName();
        String pincode = PinCode();
        scanner.nextLine();
        return new String[]{username, pincode};
    }

    public String[] usernameAndPasswordInput(){
        //scanner.nextLine(); // Fixes: Doesn't let you input the username the first time
            // No longer a problem with the order of inputs
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();

        System.out.println("\nPlease enter your 4 digit pincode: ");
        //String pincode = scanner.nextLine();
        String pincode = String.valueOf(scanner.nextInt());
        return new String[]{username, pincode};
    }

    // Want a User Name more than 5 characters and less than 10 characters in length
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

    // Want a PinCode that is a 4 digit pin
    public String PinCode(){
        String pincode;
        int pin = 0;
        int numberOfDigits = 0;
        do{// length give number of digits of string or int.
            System.out.println("Select 4 digit pin: ");
            try{
                pin = scanner.nextInt(); // nextInt is for integers
            }
            catch ( InputMismatchException e ){
                System.out.println("You did not enter a number. Please try again.");
                pin = 0;
                scanner.nextLine(); // Fixed: without this the do loop was infite withou letting me input a new pin.
            }
            numberOfDigits = String.valueOf(pin).length();
        }
        while( numberOfDigits != 4 );

        pincode = String.valueOf(pin);

        System.out.println("");
        //pin.close();
        return pincode;
    }
}
