package com.revature;

import com.revature.model.User;
import com.revature.service.UserService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        User currentUser;
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("Option 1: create");
            System.out.println("Option 2: login");
            System.out.println("Option 0: exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userService.createUser();
                    break;
                case "2": {
                    User tempUser;
                    tempUser = userService.login();
                    if (tempUser != null) {
                        currentUser = tempUser;
                        System.out.println("Whew!");
                    }
                    break;
                }
                case "0":
                    System.exit(0);
                    break;
            }
        }
    }
}
