package com.rental.dao;

import com.rental.dao.connection.ConnectionProvider;

import java.sql.Connection;

/**
 * Created by Dasha on 08.06.15.
 */
public class H2PersonDao extends AbstractPersonDao {
    @Override
    protected Connection getConnection() {
        return ConnectionProvider.createH2Connection();
    }

    H2PersonDao(){

    }
}
