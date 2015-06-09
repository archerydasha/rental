package com.rental.dao.connection;

import com.google.common.base.Throwables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dasha on 09.06.15.
 */
public class MySqlConnectionProvider implements ConnectionProvider{
    public static final String MYSQL_DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/renting";
    public static final String MYSQL_DB_USERNAME = "root";
    public static final String MYSQL_DB_PASSWORD = "";
    static boolean mysqlDriverInitialized = false;

    @Override
    public Connection createConnection() {
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
}
