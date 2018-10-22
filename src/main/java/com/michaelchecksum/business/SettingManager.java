package com.michaelchecksum.business;

import com.michaelchecksum.data.SettingStorage;
import com.michaelchecksum.domain.viewmodels.SettingsViewModel;
import com.michaelchecksum.presentation.SettingsUi;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SettingManager {
    private SettingStorage  settingStorage = new SettingStorage(Paths.get(System.getProperty("user.dir") + "\\settings.json"));
    private SettingsUi gui;
    private SettingsViewModel viewModel;

    public SettingManager() {
        this.gui = new SettingsUi();
        this.viewModel = new SettingsViewModel();

        this.getSettings();
    }

    public void addPathToSettings(String path){
        this.settingStorage.insertFilewatchPath(path);
    }

    public void openSettings() {
        this.viewModel.setOnSettingsAddEventHandler(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t ) {
                // TODO: HANDLE IT PROPERLY;
                Button btn = (Button) t.getSource();
                String path = btn.getId();
                addPathToSettings(path);
            }
        });
        this.gui.initializeComponent(this.viewModel);
        this.viewModel.setPaths(this.getSettings());
        this.gui.show();
    }


    public ArrayList<String> getSettings() {
        try {
            this.viewModel.setPaths(settingStorage.getFilewatchPaths());
            return settingStorage.getFilewatchPaths();
        }
        catch(IOException exception){
            System.out.println(exception.toString());
        }
        return null;
    }
}
