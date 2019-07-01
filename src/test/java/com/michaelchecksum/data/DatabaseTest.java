package com.michaelchecksum.data;

import com.michaelchecksum.domain.FileValidationResult;
import com.michaelchecksum.domain.HashType;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    private int user1Id;
    private int user2Id;

    private int country1Id;
    private int country2Id;

    @Test()
    void insertUser() {
        // Arrange
        final String user1Name = "testuser1_".concat(Double.toString(Math.random()));
        final String user2Name = "testuser2_".concat(Double.toString(Math.random()));

        final Database sut = new Database();

        // Act & Assert
        assertDoesNotThrow(() -> {
            // Insert test user 1
            sut.insertUser(user1Name);

            // Insert test user 2
            sut.insertUser(user2Name);

            sut.close();
        });
    }

    @Test
    void getUserId(){
        // Arrange
        final String user1Name = "testuser1_".concat(Double.toString(Math.random()));
        final String user2Name = "testuser2_".concat(Double.toString(Math.random()));

        final Database sut = new Database();

        // Act & Assert
        assertDoesNotThrow(() -> {
            // Insert test user 1
            sut.insertUser(user1Name);

            // Insert test user 2
            sut.insertUser(user2Name);

            // Get user ids
            user1Id = sut.getUserId(user1Name);
            user2Id = sut.getUserId(user2Name);

            // The id of test user 2 should be one more than test user 1
            assertEquals(user1Id + 1, user2Id);

            sut.close();
        });
    }

    @Test
    void insertCountry() {
        assertDoesNotThrow(() -> {
            final Database _database = new Database();

            final String user1Name = "testuser1_".concat(Double.toString(Math.random()));
            final String user2Name = "testuser2_".concat(Double.toString(Math.random()));

            // Insert test user 1
            _database.insertUser(user1Name);

            // Insert test user 2
            _database.insertUser(user2Name);

            // Get user ids
            user1Id = _database.getUserId(user1Name);
            user2Id = _database.getUserId(user2Name);


            final String country1Name = "The Netherlands".concat(Double.toString(Math.random()));
            final String country2Name = "Belgium".concat(Double.toString(Math.random()));

            // Insert test country 1
            _database.insertCountry(country1Name);

            // Insert test country 2
            _database.insertCountry(country2Name);

            _database.close();
        });
    }

    @Test
    void getCountryId() {
        assertDoesNotThrow(() -> {
            final Database _database = new Database();

            final String country1Name = "The Netherlands".concat(Double.toString(Math.random()));
            final String country2Name = "Belgium".concat(Double.toString(Math.random()));

            // Insert test country 1
            _database.insertCountry(country1Name);

            // Insert test country 2
            _database.insertCountry(country2Name);

            int country1Id;
            int country2Id;

            country1Id = _database.getCountryId(country1Name);
            country2Id = _database.getCountryId(country2Name);

            // The id of test country 2 should be one more than test country 1
            assertEquals(country1Id + 1, country2Id);

            _database.close();
        });
    }

    @Test
    void addUserToCountry() {
        Database _database = new Database();

        assertDoesNotThrow(() -> {
            final String user1Name = "testuser1_".concat(Double.toString(Math.random()));
            final String user2Name = "testuser2_".concat(Double.toString(Math.random()));

            // Insert test user 1
            _database.insertUser(user1Name);

            // Insert test user 2
            _database.insertUser(user2Name);

            // Get user ids
            user1Id = _database.getUserId(user1Name);
            user2Id = _database.getUserId(user2Name);


            final String country1Name = "The Netherlands".concat(Double.toString(Math.random()));
            final String country2Name = "Belgium".concat(Double.toString(Math.random()));

            // Insert test country 1
            _database.insertCountry(country1Name);

            // Insert test country 2
            _database.insertCountry(country2Name);

            country1Id = _database.getCountryId(country1Name);
            country2Id = _database.getCountryId(country2Name);

            // Users can be in multiple countries
            _database.addUserToCountry(user1Id, country1Id);
            _database.addUserToCountry(user1Id, country2Id);
        });

        // Expect a sql exception if a invalid country id is provided
        assertThrows(Exception.class, () -> {
            _database.addUserToCountry(user1Id, 100000);
        });

        // Expect a sql exception if a invalid user id is provided
        assertThrows(SQLException.class, () -> {
            _database.addUserToCountry(100000, country1Id);
        });

        assertDoesNotThrow(_database::close);
    }

    @Test
    void insertFileValidationResult() {
        final Database _database = new Database();

        // Try to insert without an exception
        assertDoesNotThrow(() -> {
            final String user1Name = "testuser1_".concat(Double.toString(Math.random()));

            // Insert test user 1
            _database.insertUser(user1Name);

            // Get user id
            user1Id = _database.getUserId(user1Name);

            final FileValidationResult fileValidationResult = new FileValidationResult("insertFileValidationResult.txt", "12345678910".concat(Double.toString(Math.random())), HashType.SHA512, true);

            _database.insertFileValidationResult(user1Id, fileValidationResult);

            _database.close();
        });
    }

    @Test
    void selectAllValidationResults() {
        final Database _database = new Database();

        assertDoesNotThrow(() -> {
            final String user1Name = "testuser1_".concat(Double.toString(Math.random()));

            // Insert test user 1
            _database.insertUser(user1Name);

            // Get user id
            user1Id = _database.getUserId(user1Name);

            final FileValidationResult fileValidationResult = new FileValidationResult("selectAllValidationResults.txt", "12345678910".concat(Double.toString(Math.random())), HashType.SHA512, true);
            _database.insertFileValidationResult(user1Id, fileValidationResult);

            // Get all results from database
            Iterable<FileValidationResult> validationResults = _database.selectAllValidationResults();

            // Loop through results
            for (FileValidationResult result : validationResults) {

                // If one of the results is the same as the inserted result we did good. End the test case.
                if(result.getHash().equals(fileValidationResult.getHash())){
                    return;
                }
            }

            // If the test case is not ended by now we should fail this test.
            fail("Inserted validation result is not found");
        });

        assertDoesNotThrow(_database::close);
    }
}