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
        this.fileWatchingManager = new FileWatchingManager();
        this.fileValidationManager = new FileValidationManager();
        this.dashboardManager = new DashboardManager();


        this.initiateFileWatchers();

        this.dashboardManager.openDashboard();
    }

    private void initiateFileWatchers(){
        this.fileWatchingManager.attachResponder(this.fileValidationManager);
        this.fileWatchingManager.startWatchers();
    }
}
