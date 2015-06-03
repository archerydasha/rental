package com.rental.dao;

/**
 * Created by Dasha on 01.06.15.
 */
public class DaoFactory {
    public static PersonDao createMySqlDao(){
        return new MySqlPersonDao();
    }

    public static PersonDao createInMemoryDao(){
        return null;
        //for unit-tests
    }
}
