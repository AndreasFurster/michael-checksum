package com.michaelchecksum.data;
import com.michaelchecksum.domain.FileValidationResult;

import java.sql.*;

class Database implements AutoCloseable {
    private Connection connection;

    Database(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mariadb://185.182.57.80:3306/cornefs247_mchecksum", "cornefs247_mchecksum", "cck7C0Hi");

        } catch(SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    void insertUser(String username) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT IGNORE INTO user SET username = ?");
        statement.setString(1, username);

        statement.execute();
    }

    int getUserId(String username) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM user WHERE username = ?");
        statement.setString(1, username);

        ResultSet result = statement.executeQuery();

        result.first();
        return result.getInt("id");
    }

    void insertCountry(String country) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT IGNORE INTO country SET country_name= ?");
        statement.setString(1, country);

        statement.execute();
    }

    int getCountryId(String country) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM country WHERE country_name = ?");
        statement.setString(1, country);

        ResultSet result = statement.executeQuery();

        result.first();
        return result.getInt("id");
    }

    void addUserToCountry(int userId, int countryId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT IGNORE INTO user_country (user_id, country_id) VALUES (?, ?)");
        statement.setInt(1, userId);
        statement.setInt(2, countryId);

        statement.execute();
    }

    void insertFileValidationResult(int userId, FileValidationResult result) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO validation_result (user_id, hash_type, hash, file_name, validated) VALUES (?, ?, ?, ?, ?)");
        statement.setInt(1, userId);
        statement.setInt(2, result.getHashType().getValue());
        statement.setString(3, result.getHash());
        statement.setString(4, result.getFile().getName());
        statement.setBoolean(5, result.getSuccess());

        statement.execute();
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }
}
