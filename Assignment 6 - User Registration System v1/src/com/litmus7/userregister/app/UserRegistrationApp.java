package com.litmus7.userregister.app;

import java.util.Scanner;

import com.litmus7.userregister.controller.UserController;

public class UserRegistrationApp {
    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        
        UserController controller = new UserController();
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        
        controller.registerUser(username, age, email, password);
        
        scanner.close();
    }
}