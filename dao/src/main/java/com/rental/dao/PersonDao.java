package com.rental.dao;

import com.rental.model.Person;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dasha on 01.06.15.
 */
public interface PersonDao {
    boolean save(Person person) throws SQLException;

    Person findById(int id) throws SQLException;

    boolean update(Person person) throws SQLException;

    boolean delete(int id) throws SQLException;

    List<Person> readAll();
}
