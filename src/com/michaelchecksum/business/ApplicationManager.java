package com.michaelchecksum.business;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationManager extends Application  {
    private FileWatchingManager fileWatchingManager;
    private FileValidationManager fileValidationManager;
    private DashboardManager dashboardManager;

    public void initiate(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        fileWatchingManager = new FileWatchingManager();
        fileValidationManager = new FileValidationManager();
        dashboardManager = new DashboardManager();

        this.initiateFileWatchers();

        this.openDashboard();
    }

    private void initiateFileWatchers(){
        this.fileWatchingManager.attatchResponer(this.fileValidationManager);
        this.fileWatchingManager.startWatchers();
    }

    private void openDashboard(){
        dashboardManager.openDashboard();
    }


}
