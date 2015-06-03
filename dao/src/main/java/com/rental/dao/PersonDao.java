package com.rental.dao;

import com.rental.model.Person;

import java.util.List;

/**
 * Created by Dasha on 01.06.15.
 */
public interface PersonDao {
    boolean save(Person person);

    Person findById(int id);

    boolean update(Person person);

    boolean delete(int id);

    List<Person> readAll();
}
