package com.rental.dao.connection;

import com.google.common.base.Throwables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dasha on 28.05.15.
 */
public class ConnectionProvider {
    public static final String MYSQL_DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/renting";
    public static final String MYSQL_DB_USERNAME = "root";
    public static final String MYSQL_DB_PASSWORD = "";
    static boolean mysqlDriverInitialized = false;

    public static final String H2_DRIVER_NAME = "org.h2.Driver";
    public static final String H2_DB_URL = "jdbc:h2:~/test";
    static boolean h2DriverInitialized = false;

    public static Connection createMysqlConnection() {
        if (!mysqlDriverInitialized) {
            try {
                Class.forName(MYSQL_DRIVER_NAME);
                mysqlDriverInitialized = true;
            } catch (ClassNotFoundException e) {
                Throwables.propagate(e);
            }
        }
        try {
            Connection connection = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_DB_USERNAME, MYSQL_DB_PASSWORD);
            return connection;
        } catch (SQLException e) {
            Throwables.propagate(e);
        }
        return null;
    }

    public static Connection createH2Connection() {
        if (!h2DriverInitialized) {
            try {
                Class.forName(H2_DRIVER_NAME);
                h2DriverInitialized = true;
            } catch (ClassNotFoundException e) {
                Throwables.propagate(e);
            }
        }
        try {
            Connection connection = DriverManager.getConnection(H2_DB_URL);
            return connection;
        } catch (SQLException e) {
            Throwables.propagate(e);
        }
        return null;
    }
}
