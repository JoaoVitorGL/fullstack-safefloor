package br.ifpe.shopfloorissuemanager.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/shopfloorissue";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection conn;

    protected static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conn;
    }

    protected static Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
