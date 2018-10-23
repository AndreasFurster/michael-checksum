package com.michaelchecksum.business;

import com.michaelchecksum.data.FileValidationStorage;
import com.michaelchecksum.domain.FileValidationResult;
import com.michaelchecksum.domain.HashType;
import com.michaelchecksum.domain.viewmodels.DashboardViewModel;
import com.michaelchecksum.domain.viewmodels.FileValidationResultViewModel;
import com.michaelchecksum.presentation.DashboardUi;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

class DashboardManager {
    private DashboardUi gui;
    private DashboardViewModel viewModel;
    private SettingManager settingManager = new SettingManager();
    private FileValidationStorage fileValidationStorage = new FileValidationStorage();

    DashboardManager() {
        this.gui = new DashboardUi();
        this.viewModel = new DashboardViewModel();
        this.viewModel.setOnSettingsClickEventHandler(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                settingManager.openSettings();
            }
        });
    }

    void openDashboard() {
        this.gui.initializeComponent(this.viewModel);
        this.gui.show();

        this.loadStatistics();
    }

    private void loadStatistics() {
        Iterable<FileValidationResult> validationResults = this.fileValidationStorage.getAll();

        for (FileValidationResult validationResult : validationResults) {
            FileValidationResultViewModel validationResultViewModel = new FileValidationResultViewModel(validationResult.getUsername(), validationResult.getHashType(), validationResult.getFileName(), validationResult.getSuccess());

            this.viewModel.addValidationResult(validationResultViewModel);
        }
    }
}
