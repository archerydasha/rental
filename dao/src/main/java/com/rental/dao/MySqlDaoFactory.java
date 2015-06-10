package com.rental.dao;

import com.rental.dao.connection.MySqlConnectionProvider;

/**
 * Created by Dasha on 09.06.15.
 */
public class MySqlDaoFactory implements DaoFactory {
    @Override
    public IPersonDao createPersonDao() {
        return new PersonDao(new MySqlConnectionProvider());
    }

    @Override
    public IPropertyDao createPropertyDao() {
        return new PropertyDao(new MySqlConnectionProvider());
    }

}
