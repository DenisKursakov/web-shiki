package by.epam.lab.service;

import by.epam.lab.exceptions.InitRuntimeException;
import by.epam.lab.utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private final static Connection CONNECTION = getInstance();

    private DBManager() {

    }

    private static Connection getInstance() {
        try {
            return DriverManager.getConnection(Constants.URL,
                    Constants.USER, Constants.PASSWORD);
        } catch (SQLException e) {
            throw new InitRuntimeException(Constants.ERROR_INIT_CONNECTION);
        }
    }

    public static Connection getConnection() {
        return CONNECTION;
    }

    public static void closeConnection() {
        if (CONNECTION != null) {
            try {
                CONNECTION.close();
            } catch (SQLException e) {
                System.err.println(Constants.ERROR_CLOSE_CONNECTION);
            }
        }
    }
}

