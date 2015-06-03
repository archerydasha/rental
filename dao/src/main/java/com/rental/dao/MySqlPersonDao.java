package com.rental.dao;

import com.rental.dao.connection.ConnectionProvider;
import com.rental.model.Person;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Dasha on 28.05.15.
 */
public class MySqlPersonDao implements PersonDao {
    String SAVE_SQL = "insert into Person (id, firstname, lastname, middlename," +
            "email, registration_date, phone) values (?,?,?,?,?,?,?)";
    String READ_SQL = "select * from Person where id = ?";
    String UPDATE_SQL = "update Person set firstname = ?, lastname = ?, middlename = ?, email = ?, registartion_date = ?, phone = ?";
    String DELETE_SQL = "delete from Person where id = ?";
    String SELECT_ALL_SQL = "select * from Person";

    MySqlPersonDao() {
    }

    @Override
    public boolean save(Person person) {
        Connection connection = ConnectionProvider.createConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SAVE_SQL);
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setString(4, person.getMiddleName());
            preparedStatement.setString(5, person.getEmail());
            preparedStatement.setDate(6, new Date(person.getRegistrationDate().getTime()));
            preparedStatement.setString(7, person.getPhone());
            int numberOfAffectedRows = preparedStatement.executeUpdate();
            return (numberOfAffectedRows == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Person findById(int id) {
        Connection connection = ConnectionProvider.createConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(READ_SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String middleName = resultSet.getString(4);
            String email = resultSet.getString(5);
            Date registrationDate = resultSet.getDate(6);
            String phone = resultSet.getString(7);
            Person person = new Person(id, firstName, lastName, middleName, email, registrationDate, phone);
            return person;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean update(Person person) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Person> readAll() {
        return null;
    }


}
