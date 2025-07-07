package com.litmus7.userregister.controller;

import java.util.Scanner;

import com.litmus7.userregister.dto.User;
import com.litmus7.userregister.exception.InvalidAgeException;
import com.litmus7.userregister.exception.InvalidEmailException;
import com.litmus7.userregister.exception.WeakPasswordException;
import com.litmus7.userregister.service.UserService;

public class UserController {

    private UserService userService = new UserService();
    private Scanner scanner = new Scanner(System.in);

    public void registerUser(String username, int age, String email, String password) {
        
        User user = new User(username, age, email, password);

        try {
            userService.validateUser(user);
            userService.saveUser(user);
            user.displayDetails();
        } catch (InvalidAgeException | InvalidEmailException | WeakPasswordException e) {
            System.err.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}