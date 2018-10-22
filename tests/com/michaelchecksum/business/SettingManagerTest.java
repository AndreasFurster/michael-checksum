package com.michaelchecksum.business;

import org.junit.Test;

import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SettingManagerTest {
    private SettingManager settingManager = new SettingManager();

    @Test
    public void getSettings() {
        ArrayList<Path> paths = settingManager.getSettings();


    }


}