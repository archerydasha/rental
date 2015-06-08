package com.rental.dao.h2;

import com.google.common.io.Resources;
import com.rental.dao.DaoFactory;
import com.rental.dao.PersonDao;
import com.rental.dao.connection.ConnectionProvider;
import com.rental.model.Person;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by Dasha on 08.06.15.
 */
public class H2PersonDaoTest {
    @Before
    public void before() throws FileNotFoundException, SQLException {
        RunScript.execute(ConnectionProvider.createH2Connection(), new FileReader(Resources.getResource("com/rental/dao/rental_create.sql").getPath()));
    }

    @Test
    public void testPersonDaoCreateAndRead() {
        PersonDao h2PersonDao = DaoFactory.createInMemoryDao();
        Person createdPerson = new Person(1, "Hello", "Kitty", "K", "kitty@gmail.com", new Date(2015, 05, 28), "12345");
        h2PersonDao.save(createdPerson);
        Person readPerson = h2PersonDao.findById(1);
        assertTrue(readPerson.equals(createdPerson));
    }

    @After
    public void after() throws FileNotFoundException, SQLException {
        RunScript.execute(ConnectionProvider.createH2Connection(), new FileReader(Resources.getResource("com/rental/dao/rental_drop.sql").getPath()));
    }
}
