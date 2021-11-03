package com.revature;

import com.revature.model.User;
import com.revature.service.MenuService;
import com.revature.service.UserService;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    User currentUser;
    UserService userService;
    MenuService menuService;
    Scanner scanner;

    public Main(){
        this.menuService = new MenuService();
        this.scanner = new Scanner(System.in);
        this.userService = new UserService(this.scanner, this.menuService);
    }

    public static void main(String[] args) {
        Main driver = new Main();
        driver.mainMenu();
    }

    public void mainMenu(){
        while (true) {
            menuService.mainMenuPrompt();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userService.createUser();
                    break;
                case "2": {
                    User tempUser;
                    tempUser = userService.Login();
                    if (tempUser != null) {
                        currentUser = tempUser;
                        System.out.println("You are now Logged In.");
                    }
                    /*
                     - Check balances
                     - Withdraw amount
                     - Deposit amount
                     - Transfer between accounts
                     - Sign out
                     */
                    // LinkedList<String> ll = new LinkedList<String>();
                    break;
                }
                case "0":
                    System.out.println("Closing Program");
                    System.exit(0);
                    break;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.mainMenuOptions();
            }
        }
    }

}
