package com.michaelchecksum.business;

import com.michaelchecksum.domain.viewmodels.DashboardViewModel;
import com.michaelchecksum.presentation.Dashboard;

class DashboardManager {
    private Dashboard gui;
    private DashboardViewModel viewModel;

    DashboardManager() {
        this.gui = new Dashboard();
        this.viewModel = new DashboardViewModel();
    }

    void openDashboard() {
        this.gui.initializeComponent(viewModel);
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
