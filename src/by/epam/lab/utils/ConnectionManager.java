package by.epam.lab.utils;

import by.epam.lab.exceptions.ConnectionException;
import by.epam.lab.exceptions.InitException;
import by.epam.lab.exceptions.InitRuntimeException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionManager {
    private static final ResourceBundle rb = ResourceBundle.getBundle(ConstantsJSP.FILE_VALUE_PARAM);
    private static String DB_NAME;
    private static String DB_USER;
    private static String DB_PASS;
    private Connection con;
    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new InitRuntimeException(e.getMessage());
        }
    }

    public ConnectionManager(ResourceBundle rb) {
        DB_NAME = rb.getString(ConstantsJSP.DB_NAME_PARAM);
        DB_USER = rb.getString(ConstantsJSP.DB_USER_PARAM);
        DB_PASS = rb.getString(ConstantsJSP.DB_PASS_PARAM);
    }

    public Connection getConnection() throws SQLException {
        return con = DriverManager.getConnection(ConstantsJSP.URL + DB_NAME, DB_USER, DB_PASS);
    }

    public void startTransactions() throws ConnectionException {
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    public void closeConnection() throws ConnectionException {
        try {
            con.close();
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage());
        }

    }
    public void rollbackTransaction() throws ConnectionException {
        try {
            con.rollback();
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    public void endTransaction() throws ConnectionException {
        try {
            con.setAutoCommit(true);
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    public void commitConnection() throws ConnectionException {
        try {
            con.commit();
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage());
        }
    }
}
