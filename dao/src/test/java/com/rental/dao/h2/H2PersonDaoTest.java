package com.rental.dao.h2;

import com.google.common.io.Resources;
import com.rental.dao.DaoFactory;
import com.rental.dao.PersonDao;
import com.rental.dao.connection.ConnectionProvider;
import com.rental.model.Person;
import org.h2.tools.RunScript;
import org.junit.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dasha on 08.06.15.
 */
public class H2PersonDaoTest {
    private PersonDao h2PersonDao = DaoFactory.createInMemoryDao();;

    @BeforeClass
    public static void beforeClass() throws FileNotFoundException, SQLException {
       RunScript.execute(ConnectionProvider.createH2Connection(), new FileReader(Resources.getResource("com/rental/dao/rental_create.sql").getPath()));
    }

    @Test
    public void testPersonDaoCreateAndRead() throws SQLException {
        Person createdPerson = new Person(1, "Hello", "Kitty", "K", "kitty@gmail.com", new Date(2015, 05, 28), "12345");
        boolean savingResult = h2PersonDao.save(createdPerson);
        Person readPerson = h2PersonDao.findById(1);
        assertTrue(savingResult);
        assertTrue(readPerson.equals(createdPerson));
        h2PersonDao.delete(1);
    }

    @Test
    public void testPersonDaoCreateAndDelete() throws SQLException {
        Person createdPerson = new Person(2, "Hello", "Kitty", "K", "kitty@gmail.com", new Date(2015, 05, 28), "12345");
        h2PersonDao.save(createdPerson);
        boolean result = h2PersonDao.delete(2);
        Person readPerson = h2PersonDao.findById(2);
        assertTrue(result);
        assertNull(readPerson);
    }

    @Test
    public void testPersonDaoCreateUpdate() throws SQLException{
        Person createdPerson = new Person(3, "Hello", "Kitty", "K", "kitty@gmail.com", new Date(2015, 05, 28), "12345");
        h2PersonDao.save(createdPerson);
        createdPerson.setFirstName("SoftKitty");
        createdPerson.setLastName("Sheldon's cat");
        createdPerson.setMiddleName("M");
        createdPerson.setEmail("softkittywarmkitty@gmail.com");
        createdPerson.setRegistrationDate(new Date(2015, 06, 8));
        createdPerson.setPhone("00000");
        boolean updateResult = h2PersonDao.update(createdPerson);
        Person readPerson = h2PersonDao.findById(3);
        assertTrue(updateResult);
        assertTrue(createdPerson.equals(readPerson));
    }

    @AfterClass
    public static void afterClass() throws FileNotFoundException, SQLException {
        RunScript.execute(ConnectionProvider.createH2Connection(), new FileReader(Resources.getResource("com/rental/dao/rental_drop.sql").getPath()));
    }
}
