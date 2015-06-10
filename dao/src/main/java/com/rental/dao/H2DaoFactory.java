package com.rental.dao;

import com.rental.dao.connection.H2ConnectionProvider;

/**
 * Created by Dasha on 01.06.15.
 */
public class H2DaoFactory implements DaoFactory {
    @Override
    public IPersonDao createPersonDao() {
        return new PersonDao(new H2ConnectionProvider());
    }

    @Override
    public IPropertyDao createPropertyDao() {
        return new PropertyDao(new H2ConnectionProvider());
    }
}
