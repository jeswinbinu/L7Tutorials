package com.litmus7.retailcatalog.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBUtil {
    private static final String url;
    private static final String username;
    private static final String password;
    private static final String driverClassName;

    static {
        try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find db.properties");
            }

            Properties props = new Properties();
            props.load(input);

            url = props.getProperty("jdbc.url");
            username = props.getProperty("jdbc.username");
            password = props.getProperty("jdbc.password");
            driverClassName = props.getProperty("jdbc.driverClassName");

            Class.forName(driverClassName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load DB configuration", e);
        }
    }

    /**
     * Get a database connection using the loaded configuration.
     *
     * @return Connection object
     * @throws SQLException If there is an issue establishing the connection
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Close the given database connection.
     *
     * @param conn The Connection object to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}