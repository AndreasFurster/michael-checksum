package com.michaelchecksum.business;

import com.michaelchecksum.data.SettingStorage;
import com.michaelchecksum.domain.viewmodels.SettingsViewModel;
import com.michaelchecksum.presentation.Settings;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class SettingManager {

    private ArrayList<Path> settings = new ArrayList<>();
    private SettingStorage  settingStorage = new SettingStorage();
    private Settings gui;
    private SettingsViewModel viewModel;

    public SettingManager() {
        this.gui = new Settings();
        this.viewModel = new SettingsViewModel();

        this.getSettings();
    }


    public void openSettings() {
        this.viewModel.setPaths(this.getSettings());
        this.gui.initializeComponent(this.viewModel);
        this.gui.show();
    }

    public ArrayList<Path> getSettings() {
        try {
            ArrayList<Path> listOfPaths = settingStorage.getFilewatchPaths();
            return listOfPaths;
        }
        catch(IOException exception){
            System.out.println(exception.toString());
        }
        return new ArrayList<>();

    }
}
