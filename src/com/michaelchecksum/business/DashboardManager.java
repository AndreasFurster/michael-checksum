package com.michaelchecksum.business;

import com.michaelchecksum.presentation.Dashboard;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardManager {
    private Dashboard gui;

    public DashboardManager() {
        this.gui = new Dashboard();
    }

    public void openDashboard() {
        this.gui.initializeComponent();
        this.gui.show();
    }
}
