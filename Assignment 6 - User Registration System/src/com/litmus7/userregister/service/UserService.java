package com.litmus7.userregister.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.litmus7.userregister.dto.User;
import com.litmus7.userregister.exception.InvalidAgeException;
import com.litmus7.userregister.exception.InvalidEmailException;
import com.litmus7.userregister.exception.WeakPasswordException;
import com.litmus7.userregister.util.DBUtil;

public class UserService {
	
    public void validateUser(User user) throws InvalidAgeException, InvalidEmailException, WeakPasswordException {
        if (user.getAge() < 18 || user.getAge() > 60) {
            throw new InvalidAgeException("Error: Age must be between 18 and 60.");
        }

        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            throw new InvalidEmailException("Error: Invalid email format. Must contain '@' and '.'.");
        }

        if (user.getPassword().length() < 6) {
            throw new WeakPasswordException("Error: Password too weak. Must be at least 6 characters.");
        }
    }
    
    public void saveUser(User user) {
        String sql = "INSERT INTO users (username, age, email, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = 	DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setInt(2, user.getAge());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.executeUpdate();

            System.out.println("User saved successfully!");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
