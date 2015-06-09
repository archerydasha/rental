package com.rental.dao.h2;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.rental.dao.DaoFactory;
import com.rental.dao.H2DaoFactory;
import com.rental.dao.PersonDao;
import com.rental.dao.connection.ConnectionProvider;
import com.rental.dao.connection.H2ConnectionProvider;
import com.rental.model.Person;
import org.h2.tools.RunScript;
import org.junit.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dasha on 08.06.15.
 */
public class H2PersonDaoTest {
    DaoFactory daoFactory = new H2DaoFactory();
    private PersonDao h2PersonDao = daoFactory.createPersonDao();
    private static ConnectionProvider connectionProvider = new H2ConnectionProvider();

    private static void runSqlScript(String filename) throws SQLException, FileNotFoundException {
        RunScript.execute(connectionProvider.createConnection(), new FileReader(Resources.getResource("com/rental/dao/" + filename).getPath()));
    }

    @BeforeClass
    public static void beforeClass() throws FileNotFoundException, SQLException {
        runSqlScript("rental_create.sql");
    }

    @Before
    public void before() throws FileNotFoundException, SQLException {
       runSqlScript("cleanup.sql");
    }

    @Test
    public void testPersonDaoCreateAndRead() throws SQLException {
        Person createdPerson = new Person(1, "Hello", "Kitty", "K", "kitty@gmail.com", new Date(2015, 05, 28), "12345");
        boolean savingResult = h2PersonDao.save(createdPerson);
        Person readPerson = h2PersonDao.findById(1);
        assertTrue(savingResult);
        assertTrue(readPerson.equals(createdPerson));
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
    public void testPersonUnsuccessfulDelete() throws SQLException {
        boolean result = h2PersonDao.delete(2);
        assertFalse(result);
    }

    @Test
    public void testPersonDaoCreateUpdate() throws SQLException {
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

    @Test
    public void testPersonDaoUnsuccessfulUpdate() throws SQLException {
        Person createdPerson = new Person(3, "Hello", "Kitty", "K", "kitty@gmail.com", new Date(2015, 05, 28), "12345");
        Person foundPerson = h2PersonDao.findById(3);
        boolean updateResult = h2PersonDao.update(createdPerson);
        assertNull(foundPerson);
        assertFalse(updateResult);
    }

    @Test
    public void testReadAll() throws SQLException{
        Person kittyPerson = new Person(3, "Hello", "Kitty", "K", "kitty@gmail.com", new Date(2015, 05, 28), "12345");
        Person batmanPerson = new Person(1, "Bruce", "Wayne", "B", "batman@gmail.com", new Date(2015,06,07), "555-555");
        List<Person> expectedResult = Lists.newArrayList(kittyPerson, batmanPerson);
        boolean saveKittyResult = h2PersonDao.save(kittyPerson);
        boolean saveBatmanResult = h2PersonDao.save(batmanPerson);
        List<Person> actualResult = h2PersonDao.readAll();
        Collections.sort(expectedResult);
        Collections.sort(actualResult);
        assertTrue(saveKittyResult);
        assertTrue(saveBatmanResult);
        assertTrue(Iterables.elementsEqual(expectedResult, actualResult));
    }

    @AfterClass
    public static void afterClass() throws FileNotFoundException, SQLException {
        runSqlScript("rental_drop.sql");
    }
}
