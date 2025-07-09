package com.litmus7.userregister.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.litmus7.userregister.dao.UserRegisterDao;
import com.litmus7.userregister.dto.User;
import com.litmus7.userregister.exception.InvalidAgeException;
import com.litmus7.userregister.exception.InvalidEmailException;
import com.litmus7.userregister.exception.InvalidUsernameException;
import com.litmus7.userregister.exception.WeakPasswordException;
import com.litmus7.userregister.util.DBUtil;

public class UserService {
	
	private UserRegisterDao userDAO = new UserRegisterDao();
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
	        return matcher.matches();
	}

    public void validateUser(User user) throws InvalidAgeException, InvalidEmailException, WeakPasswordException, InvalidUsernameException {
        if (user.getAge() < 18 || user.getAge() > 60) {
            throw new InvalidAgeException("Error: Age must be between 18 and 60.");
        }

        if (!validate(user.getEmail())) {
            throw new InvalidEmailException("Error: Invalid email format. Must contain '@' and '.'.");
        }

        if (user.getPassword().length() < 6) {
            throw new WeakPasswordException("Error: Password too weak. Must be at least 6 characters.");
        }
        
        if (userDAO.isUsernameExists(user.getUsername())) {
        	throw new InvalidUsernameException("Error: Username already taken.");
        }
    }
    
    public void registerUser(User user) {
    	userDAO.saveUser(user);
    	System.out.println("User registered successfully!");
    }
}
