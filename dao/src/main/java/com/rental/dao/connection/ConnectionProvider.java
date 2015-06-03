package com.rental.dao.connection;

import com.google.common.base.Throwables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dasha on 28.05.15.
 */
public class ConnectionProvider {
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/renting";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";
    static boolean driverInitialized = false;

    public static Connection createConnection() {
        if (!driverInitialized) {
            try {
                Class.forName(DRIVER_NAME);
                driverInitialized = true;
            } catch (ClassNotFoundException e) {
                Throwables.propagate(e);
            }
        }
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            return connection;
        } catch (SQLException e) {
            Throwables.propagate(e);
        }
        return null;
    }
}
