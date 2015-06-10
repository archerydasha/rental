package com.rental.dao;

import com.rental.model.Property;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dasha on 09.06.15.
 */
public interface IPropertyDao {

    boolean save(Property property) throws SQLException;

    Property findById(int id) throws SQLException;

    boolean update(Property property) throws SQLException;

    boolean delete(int id) throws SQLException;

    List<Property> readAll() throws SQLException;
}
