package com.rental.dao.h2;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.rental.dao.DaoFactory;
import com.rental.dao.H2DaoFactory;
import com.rental.dao.IPersonDao;
import com.rental.dao.IPropertyDao;
import com.rental.dao.connection.ConnectionProvider;
import com.rental.dao.connection.H2ConnectionProvider;
import com.rental.model.Person;
import com.rental.model.Property;
import org.h2.tools.RunScript;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
 * Created by Dasha on 10.06.15.
 */
public class H2PropertyDaoTest {
    DaoFactory daoFactory = new H2DaoFactory();
    private IPropertyDao h2PropertyDao = daoFactory.createPropertyDao();
    private IPersonDao h2PersonDao = daoFactory.createPersonDao();
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
    public void testPropertyDaoCreateAndRead() throws SQLException {
        Person owner = new Person(1, "Hello", "Kitty", "K", "kitty@gmail.com", new Date(2015, 05, 28), "12345");
        Property createdProperty = new Property(11, "GreatHouse", owner, "MonteNegro", null, null, "Bar", "Somestreet,1", new Date(2015, 06, 10), 10, 250);
        h2PersonDao.save(owner);
        boolean savingProperty = h2PropertyDao.save(createdProperty);
        Property readProperty = h2PropertyDao.findById(11);
        assertTrue(savingProperty);
        assertTrue(readProperty.equals(createdProperty));
    }

    @Test
    public void testPropertyDaoCreateAndDelete() throws SQLException {
        Person owner = new Person(2, "Hello", "Kitty", "K", "kitty@gmail.com", new Date(2015, 05, 28), "12345");
        h2PersonDao.save(owner);
        Property property = new Property(12, "GreatHouse", owner, "MonteNegro", null, null, "Bar", "Somestreet,1", new Date(2015, 06, 10), 10, 250);
        h2PropertyDao.save(property);
        boolean result = h2PropertyDao.delete(12);
        Property readProperty = h2PropertyDao.findById(12);
        assertTrue(result);
        assertNull(readProperty);
    }

    @Test
    public void testPropertyUnsuccessfulDelete() throws SQLException {
        boolean result = h2PersonDao.delete(215);
        assertFalse(result);
    }

    @Test
    public void testPropertyDaoCreateUpdate() throws SQLException {
        Person owner = new Person(3, "Hello", "Kitty", "K", "kitty@gmail.com", new Date(2015, 05, 28), "12345");
        Person newOwner = new Person(2, "Some", "Kitty", "K", "otherkitty@gmail.com", new Date(2015, 05, 28), "12345");
        h2PersonDao.save(owner);
        h2PersonDao.save(newOwner);
        Property property = new Property(12, "GreatHouse", owner, "MonteNegro", null, null, "Bar", "Somestreet,1", new Date(2015, 06, 10), 10, 250);
        h2PropertyDao.save(property);
        property.setName("SomeName");
        property.setAddress("newAddress");
        property.setCity("SinCity");
        property.setCountry("newCountry");
        property.setState("newState");
        property.setRegion("TX");
        property.setCreationDate(new Date(2015, 05, 11));
        property.setDailyPrice(15);
        property.setMonthlyPrice(300);
        property.setOwner(newOwner);
        boolean updateResult = h2PropertyDao.update(property);
        Property readProperty = h2PropertyDao.findById(12);
        assertTrue(updateResult);
        assertTrue(readProperty.equals(property));
    }


    @Test
    public void testPropertyDaoUnsuccessfulUpdate() throws SQLException {
        Person owner = new Person(2, "Hello", "Kitty", "K", "kitty@gmail.com", new Date(2015, 05, 28), "12345");
        Property property = new Property(12, "GreatHouse", owner, "MonteNegro", null, null, "Bar", "Somestreet,1", new Date(2015, 06, 10), 10, 250);
        Person foundPerson = h2PersonDao.findById(12);
        boolean updateResult = h2PropertyDao.update(property);
        assertNull(foundPerson);
        assertFalse(updateResult);
    }

    @Test
    public void testReadAll() throws SQLException {
        Person kittyOwner = new Person(3, "Hello", "Kitty", "K", "kitty@gmail.com", new Date(2015, 05, 28), "12345");
        Person batmanOwner = new Person(1, "Bruce", "Wayne", "B", "batman@gmail.com", new Date(2015, 06, 07), "555-555");
        h2PersonDao.save(kittyOwner);
        h2PersonDao.save(batmanOwner);
        Property kittyProperty = new Property(12, "GreatHouse", kittyOwner, "MonteNegro", null, null, "Bar", "Somestreet,1", new Date(2015, 06, 10), 10, 250);
        Property batmanProperty = new Property(11, "smallhouse", batmanOwner, "Ukraine", null, null, "Dnipropetrovsk", "Karla Marksa,555", new Date(2015, 05, 15), 15, 300);
        List<Property> expectedResult = Lists.newArrayList(kittyProperty, batmanProperty);
        boolean saveKittyPropertyResult = h2PropertyDao.save(kittyProperty);
        boolean saveBatmanPropertyResult = h2PropertyDao.save(batmanProperty);
        List<Property> actualResult = h2PropertyDao.readAll();
        Collections.sort(expectedResult);
        Collections.sort(actualResult);
        assertTrue(saveKittyPropertyResult);
        assertTrue(saveBatmanPropertyResult);
        assertTrue(Iterables.elementsEqual(expectedResult, actualResult));
    }

    @AfterClass
    public static void afterClass() throws FileNotFoundException, SQLException {
        runSqlScript("rental_drop.sql");
    }
}
