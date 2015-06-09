package com.rental.dao.connection;

import com.google.common.base.Throwables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dasha on 09.06.15.
 */
public class H2ConnectionProvider implements ConnectionProvider {

    public static final String H2_DRIVER_NAME = "org.h2.Driver";
    public static final String H2_DB_URL = "jdbc:h2:~/test";
    static boolean h2DriverInitialized = false;

    @Override
    public Connection createConnection() {
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

