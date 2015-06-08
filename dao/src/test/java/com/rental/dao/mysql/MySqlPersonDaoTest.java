package com.rental.dao.mysql;

import com.google.common.io.Resources;
import com.rental.dao.DaoFactory;
import com.rental.dao.PersonDao;
import com.rental.dao.connection.ConnectionProvider;
import com.rental.model.Person;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Date;
import static org.junit.Assert.*;
/**
 * Created by Dasha on 28.05.15.
 */
public class MySqlPersonDaoTest {
    @Ignore
    @Test
    public void testSaveAndRead(){
        PersonDao mySqlPersonDao = DaoFactory.createMySqlDao();
        Person createdPerson = new Person(1, "Hello", "Kitty", "K","kitty@gmail.com", new Date(2015, 05, 28), "12345");
        mySqlPersonDao.save(createdPerson);
        Person readPerson = mySqlPersonDao.findById(1);
        assertTrue(readPerson.equals(createdPerson));
    }
}
