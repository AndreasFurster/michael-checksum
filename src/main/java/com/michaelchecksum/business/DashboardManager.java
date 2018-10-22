package com.michaelchecksum.business;

import com.michaelchecksum.domain.viewmodels.DashboardViewModel;
import com.michaelchecksum.presentation.DashboardUi;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

class DashboardManager {
    private DashboardUi gui;
    private DashboardViewModel viewModel;
    private SettingManager settingManager = new SettingManager();

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
        // TODO: Load data from database
        this.viewModel.setSuccess(20);
        this.viewModel.setFailedAmount(10);
        this.viewModel.setTotalAmount(30);
    }
}
