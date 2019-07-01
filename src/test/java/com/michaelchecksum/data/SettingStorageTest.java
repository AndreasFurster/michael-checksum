package com.michaelchecksum.data;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SettingStorageTest {

    @Test
    void canInitiateDefaultSettingStorage() {
        // Arrange
        SettingStorage sut;

        // Act
        sut = new SettingStorage();

        // Assert
        // Instantiation should not have thrown an exception
    }

    @Test
    void canInitiateSettingStorageWithCustomPath() {
        // Arrange
        Path path = this.getTestSettingsPath();

        // Act
        SettingStorage sut = new SettingStorage(path);

        // Assert
        // Instantiation should not have thrown an exception
    }

    @Test
    void canInsertAndGetPath() throws Exception {
        // Arrange
        Path path = this.getTestSettingsPath();
        SettingStorage sut = new SettingStorage(path);
        String testPath = "c:/users/charlotte/downloads";

        // Act
        sut.insertFilewatchPath(testPath);
        ArrayList<String> paths = sut.getFilewatchPaths();

        // Assert
        assertTrue(paths.contains(testPath));
    }

    @Test
    void canDeletePath() throws Exception {
        // Arrange
        Path path = this.getTestSettingsPath();
        SettingStorage sut = new SettingStorage(path);
        String testPath = "c:/users/charlotte/downloads";
        sut.insertFilewatchPath(testPath);

        // Act
        sut.deleteFilewatchPath(testPath);

        // Assert
        ArrayList<String> paths = sut.getFilewatchPaths();
        assertFalse(paths.contains(testPath));
    }

    @Test
    void canSetAndGetUsername() throws Exception{
        // Arrange
        Path path = this.getTestSettingsPath();
        SettingStorage sut = new SettingStorage(path);
        String testUser = "Charlotte";

        // Act
        sut.setUsername(testUser);
        String resultUser = sut.getUsername();

        // Assert
        assertEquals(testUser, resultUser);
    }

    private Path getTestSettingsPath() {
        return Paths.get(System.getProperty("user.dir") + "\\settings_test.json");
    }
}