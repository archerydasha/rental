package com.rental.dao;

import com.rental.dao.connection.H2ConnectionProvider;

/**
 * Created by Dasha on 01.06.15.
 */
public class H2DaoFactory implements DaoFactory {
    @Override
    public PersonDao createPersonDao() {
        return new H2PersonDao(new H2ConnectionProvider());
    }
}
