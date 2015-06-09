package com.rental.dao.connection;

import com.google.common.base.Throwables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dasha on 28.05.15.
 */
public interface ConnectionProvider {
    Connection createConnection();
}
