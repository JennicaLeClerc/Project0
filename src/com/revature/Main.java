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
        Main driver = new Main();

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
                        menuService.loginText();
                        driver.LogInMenu(tempUser);
                    }
                    // LinkedList<String> ll = new LinkedList<String>();

                    // If you're back here after all the menus you
                    // must have logged out.
                    tempUser = null;
                    break;
                }
                case "0":
                    menuService.textClosing();
                    System.exit(0);
                    break;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.mainMenuOptions();
            }
        }
    }

    public void LogInMenu( User user ){
        Main driver = new Main();

        while (true){
            menuService.logInMenuPrompt();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    driver.CheckBalanceMenu(user);
                    break;
                case "2":
                    driver.WithdrawMenu(user);
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "0":
                    return;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.mainMenuOptions();
            }
            return;
        }
    }

    public void CheckBalanceMenu( User user ){
        while(true){
            menuService.checkBalanceMenuPrompt();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userService.ViewBalance(user);
                    break;
                case "2":
                    userService.ViewBalance(user, "Checking");
                    break;
                case "3":
                    userService.ViewBalance(user, "Savings");
                    break;
                case "0":
                    return;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.mainMenuOptions();
            }
            return;
        }
    }

    public void WithdrawMenu( User user ){
        while(true){
            menuService.withdrawMenuPrompt();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userService.Withdraw(user, "Checking");
                    break;
                case "2":
                    userService.Withdraw(user, "Savings");
                    break;
                case "0":
                    return;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.mainMenuOptions();
            }
            return;
        }
    }

}
