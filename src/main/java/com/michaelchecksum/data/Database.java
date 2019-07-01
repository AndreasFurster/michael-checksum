package com.michaelchecksum.data;

import com.michaelchecksum.domain.FileValidationResult;
import com.michaelchecksum.domain.HashType;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

class Database implements AutoCloseable {
    private Connection connection;

    Database() {
        this(false);
    }

    Database(boolean test) {
        String dbName = test ? "michael_checksum_dev" : "michael_checksum";

        connect(dbName);
    }

    private void connect(String dbName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/michael_checksum_dev?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

        } catch (SQLException | ClassNotFoundException ex) {
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

        if (!result.first()) {
            throw new RuntimeException("No result found");
        }

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
        PreparedStatement statement = connection.prepareStatement("INSERT INTO user_country (user_id, country_id) VALUES (?, ?)");
        statement.setInt(1, userId);
        statement.setInt(2, countryId);

        statement.execute();
    }

    void insertFileValidationResult(int userId, FileValidationResult result) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO validation_result (user_id, hash_type, hash, file_name, validated) VALUES (?, ?, ?, ?, ?)");
        statement.setInt(1, userId);
        statement.setInt(2, result.getHashType().getValue());
        statement.setString(3, result.getHash());
        statement.setString(4, result.getFileName());
        statement.setBoolean(5, result.getSuccess());

        statement.execute();
    }

    public ArrayList<FileValidationResult> selectAllValidationResults() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT validation_result.id, validation_result.file_name, validation_result.hash_type, validation_result.hash, validation_result.validated, user.username FROM validation_result LEFT JOIN user ON validation_result.user_id = user.id");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<FileValidationResult> result = new ArrayList<>();

        while (resultSet.next()) {
            try {
                String fileName = resultSet.getString("file_name");
                String hash = resultSet.getString("hash");
                HashType hashType = HashType.from(resultSet.getInt("hash_type"));
                boolean succeed = resultSet.getInt("validated") == 1;
                String username = resultSet.getString("username");

                FileValidationResult item = new FileValidationResult(fileName, hash, hashType, succeed);
                item.setUsername(username);

                result.add(item);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        resultSet.close();

        return result;
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }

}
