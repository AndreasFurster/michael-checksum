package com.michaelchecksum.business;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SettingManagerTest {
    @Test
    void addPathToSettings() {
        SettingManager _settingsManager = new SettingManager();
        final String _path = "C:/fake/path/addPathToSettings";

        _settingsManager.addPathToSettings(_path);
        ArrayList<String> paths = _settingsManager.getSettings();

        assertTrue(paths.contains(_path));
    }

    @Test
    void deletePathFromSettings() {
        SettingManager _settingsManager = new SettingManager();
        final String _path = "C:/fake/path/deletePathFromSettings";

        _settingsManager.addPathToSettings(_path);
        _settingsManager.deletePathFromSettings(_path);
        ArrayList<String> paths = _settingsManager.getSettings();

        assertFalse(paths.contains(_path));
    }

    @Test
    void getSettings() {
        SettingManager _settingsManager = new SettingManager();
        final String _path1 = "C:/fake/path/getSettings1";
        final String _path2 = "C:/fake/path/getSettings2";
        final String _path3 = "C:/fake/path/getSettings3";
        final String _path4 = "C:/fake/path/getSettings4";

        _settingsManager.addPathToSettings(_path1);
        _settingsManager.addPathToSettings(_path2);
        _settingsManager.addPathToSettings(_path3);
        _settingsManager.addPathToSettings(_path4);

        ArrayList<String> paths = _settingsManager.getSettings();

        assertEquals(4, paths.size());
    }
}