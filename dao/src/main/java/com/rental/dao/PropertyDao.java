package com.rental.dao;

import com.rental.dao.connection.ConnectionProvider;
import com.rental.model.Person;
import com.rental.model.Property;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dasha on 09.06.15.
 */
public class PropertyDao implements IPropertyDao {
    protected ConnectionProvider connectionProvider;
    public static String SAVE_SQL = "insert into Property (id, name, owner_id, country," +
            "state, region, city, address, creation_date, daily_price, monthly_price) values " +
            "(?,?,?,?,?,?,?,?,?,?,?)";
    public static String READ_SQL = "select * from Property where id = ?";
    public static String UPDATE_SQL = "update Property set name = ?, owner_id = ?, country = ?," +
            " state = ?, region = ?, city = ?, address = ?, " +
            "creation_date = ?, daily_price = ?, monthly_price = ? where id=?";
    public static String DELETE_SQL = "delete from Property where id = ?";
    public static String SELECT_ALL_SQL = "select * from Property";

    protected PropertyDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public boolean save(Property property) throws SQLException {
        Connection connection = connectionProvider.createConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SAVE_SQL);
            preparedStatement.setInt(1, property.getId());
            fillInPreparedStatement(preparedStatement, property, 2);
            int numberOfAffectedRows = preparedStatement.executeUpdate();
            return numberOfAffectedRows == 1;
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
    public Property findById(int id) throws SQLException {
        Connection connection = connectionProvider.createConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(READ_SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            boolean existsInDb = resultSet.next();
            if (existsInDb) {
                return getPropertyFromResultSet(resultSet);
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

    @Override
    public boolean update(Property property) throws SQLException {
        Connection connection = connectionProvider.createConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_SQL);
            fillInPreparedStatement(preparedStatement, property, 1);
            preparedStatement.setInt(11, property.getId());
            int numberOfUpdatedRows = preparedStatement.executeUpdate();
            return numberOfUpdatedRows == 1;
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
    public List<Property> readAll() throws SQLException {
        Connection connection = connectionProvider.createConnection();
        Statement statement = null;
        List<Property> result = new ArrayList<Property>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL);
            while (resultSet.next()) {
                result.add(getPropertyFromResultSet(resultSet));
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }


    private void fillInPreparedStatement(PreparedStatement preparedStatement, Property property, int startIndex) throws SQLException {
        preparedStatement.setString(startIndex, property.getName());
        preparedStatement.setInt(startIndex + 1, property.getOwner().getId());
        preparedStatement.setString(startIndex + 2, property.getCountry());
        preparedStatement.setString(startIndex + 3, property.getState());
        preparedStatement.setString(startIndex + 4, property.getRegion());
        preparedStatement.setString(startIndex + 5, property.getCity());
        preparedStatement.setString(startIndex + 6, property.getAddress());
        preparedStatement.setDate(startIndex + 7, new Date(property.getCreationDate().getTime()));
        preparedStatement.setInt(startIndex + 8, property.getDailyPrice());
        preparedStatement.setInt(startIndex + 9, property.getMonthlyPrice());
    }

    private Property getPropertyFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        Person owner = new PersonDao(connectionProvider).findById(resultSet.getInt(3));
        String country = resultSet.getString(4);
        String state = resultSet.getString(5);
        String region = resultSet.getString(6);
        String city = resultSet.getString(7);
        String address = resultSet.getString(8);
        Date creationDate = resultSet.getDate(9);
        int dailyPrice = resultSet.getInt(10);
        int monthlyPrice = resultSet.getInt(11);
        return new Property(id, name, owner, country, state, region, city, address, creationDate, dailyPrice, monthlyPrice);
    }
}
