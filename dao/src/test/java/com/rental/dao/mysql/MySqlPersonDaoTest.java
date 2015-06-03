package com.rental.dao.mysql;

import com.rental.dao.DaoFactory;
import com.rental.dao.PersonDao;
import com.rental.model.Person;
import org.junit.Test;

import java.util.Date;
import static org.junit.Assert.*;
/**
 * Created by Dasha on 28.05.15.
 */
public class MySqlPersonDaoTest {
    @Test
    public void testSaveAndRead(){
        PersonDao mySqlPersonDao = DaoFactory.createMySqlDao();
        Person createdPerson = new Person(1, "Hello", "Kitty", "K","kitty@gmail.com", new Date(2015, 05, 28), "12345");
        mySqlPersonDao.save(createdPerson);
        Person readPerson = mySqlPersonDao.findById(1);
        assertTrue(readPerson.equals(createdPerson));
    }
}
