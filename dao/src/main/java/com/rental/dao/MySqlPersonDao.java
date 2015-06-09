package com.rental.dao;

import com.rental.dao.connection.ConnectionProvider;
import com.rental.model.Person;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Dasha on 28.05.15.
 */
public class MySqlPersonDao extends AbstractPersonDao {

    protected MySqlPersonDao(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }
}
