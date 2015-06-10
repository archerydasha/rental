package com.rental.dao;

import com.rental.dao.connection.ConnectionProvider;
import com.rental.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dasha on 08.06.15.
 */
public class PersonDao implements IPersonDao {
    public static String SAVE_SQL = "insert into Person (id, firstname, lastname, middlename," +
            "email, registration_date, phone) values (?,?,?,?,?,?,?)";
    public static String READ_SQL = "select * from Person where id = ?";
    public static String UPDATE_SQL = "update Person set firstname = ?, lastname = ?, middlename = ?, email = ?, " +
            "registration_date = ?, phone = ? where id = ?";
    public static String DELETE_SQL = "delete from Person where id = ?";
    public static String SELECT_ALL_SQL = "select * from Person";

    protected ConnectionProvider connectionProvider;

    protected PersonDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public boolean save(Person person) throws SQLException {
        Connection connection = connectionProvider.createConnection();
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
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Person findById(int id) throws SQLException {
        Connection connection = connectionProvider.createConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(READ_SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            boolean existsInDb = resultSet.next();
            if (existsInDb) {
                return getPersonFromResultSet(resultSet);
            } else {
                return null;
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private Person getPersonFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        String middleName = resultSet.getString(4);
        String email = resultSet.getString(5);
        Date registrationDate = resultSet.getDate(6);
        String phone = resultSet.getString(7);
        Person person = new Person(id, firstName, lastName, middleName, email, registrationDate, phone);
        return person;
    }

    @Override
    public boolean update(Person person) throws SQLException {
        Connection connection = connectionProvider.createConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getMiddleName());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setDate(5, new Date(person.getRegistrationDate().getTime()));
            preparedStatement.setString(6, person.getPhone());
            preparedStatement.setInt(7, person.getId());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated == 1;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        Connection connection = connectionProvider.createConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, id);
            return statement.executeUpdate() == 1;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Person> readAll() throws SQLException {
        Connection connection = connectionProvider.createConnection();
        Statement statement = null;
        List<Person> result = new ArrayList<Person>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL);
            while (resultSet.next()) {
                result.add(getPersonFromResultSet(resultSet));
            }
        }finally {
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return result;
    }
}
