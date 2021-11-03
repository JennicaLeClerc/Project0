package com.revature.service;

/**
 * MenuService should provide messages to the user to prompt them
 * to perform certain actions based on the selection of the option
 */
public class MenuService {
    public void mainMenuPrompt(){
        System.out.println("Welcome to RevEstate!");
        mainMenuOptions();
    }

    public void mainMenuOptions(){
        System.out.println("1) Create an Account");
        System.out.println("2) Login to your Account");
        System.out.println("0) Exit the Application");
    }

    /**
     * Print Statements with inputs
     */
    // Prints out the Account Number with input of the account number
    public void printAccountNumber( int account_no ){
        System.out.println("Account Number: " + account_no + "\n");
    }

    // Prints out the balances for both Checking and Savings accounts
    public void printBalances( double checking, double savings ){
        System.out.println("Checking Balance: $" + String.format("%.2f", checking));
        System.out.println("Savings Balance:  $" + String.format("%.2f", savings));
    }

    // Prints out the balance for Checking account
    public void printCheckingBalance( double checking ){
        System.out.println("Checking Balance: $" + String.format("%.2f", checking));
    }

    // Prints out the balance for Savings account
    public void printSavingsBalance( double savings ){
        System.out.println("Savings Balance: $" + String.format("%.2f", savings));

    }

    // Prints out the account in which you are Depositing to
    public void printDepositAcc( String account ){
        System.out.println("Depositing funds to " + account );
    }

    // Prints out the account in which you are Withdrawing from
    public void printWithdrawAcc( String account ){
        System.out.println("Withdrawing funds from " + account );
    }

    /**
     *      One Line Prompts
     */
    // Prompts the user for their Username
    public void enterUsernamePrompt(){
        System.out.println("Please enter your Username: ");
    }

    // Prompts the user for their Pincode
    public void enterPinPrompt(){
        System.out.println("Please enter your Pin: ");
    }

    // Prompts user for their First Name
    public void enterFirstNamePrompt(){
        System.out.println("Enter First Name: ");
    }

    // Prompts user for their Last Name
    public void enterLastNamePrompt(){
        System.out.println("Enter Last Name: ");
    }

    /**
     *      One Line Printed Statements
     */
    // Lets the user know that their menu option does not exist
    public void incorrectMenuSelection(){
        System.out.println("Please enter a valid option.");
    }

    // Tells not valid username or password in bold
    public void notValidUsernamePin(){
        System.out.println("\033[1mInvalid Username or Pin\033[0m");
    }

    // Lets the user know their account was created
    public void accountCreatedPrint(){
        System.out.println("You have created an account.");
    }

    // Lets the user know the length restrictions of the Username
    public void usernameLengthPrint(){
        System.out.println("\nYour Username must be at least 5 characters and no more than 10.");
    }

    // Lets the user know that the pin must be 4 DIGITS long
    public void pinLengthPrint(){
        System.out.println("\nYour Pin must be 4 digits.");
    }

    // Lets the user know the Account prompt they chose doesn't exist
    public void invalidAccountPrint(){
        System.out.println("\033[1mACCOUNT DOES NOT EXIST\033[0m");
    }

    // Lets the user know that they don't have enough money to withdraw from the account they chose
    public void invalidFunds(){
        System.out.println("          \033[1mNOT ENOUGH FUNDS\033[0m");
    }

    // Lets the user know that the Username they chose is already in user
    public void invalidUsername(){
        System.out.println("\033[1mUsername in use. Please try again.\033[0m");
    }

    // Lets the user know that they did not enter the requested 4 Digits. And to try again
    public void invalidType(){
        System.out.println("You did not enter a number. Please try again.");
    }
}
