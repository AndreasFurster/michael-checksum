package com.michaelchecksum.business;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class ApplicationManager extends Application  {
    private FileWatchingManager fileWatchingManager;
    private FileValidationManager fileValidationManager;
    private DashboardManager dashboardManager;

    public void initiate(String[] args){
        // this.fileWatchingManager = new FileWatchingManager();
        // this.initiateFileWatchers();

        launch(args);

    }

    @Override
    public void start(Stage primaryStage)  {
        this.fileValidationManager = new FileValidationManager();
        this.dashboardManager = new DashboardManager();

        this.dashboardManager.openDashboard();
        this.fileValidationManager.handleNewFileFound(new File("C:\\Users\\andre\\Downloads\\vlc-3.0.4-win64.exe"));
    }

    private void initiateFileWatchers(){
        this.fileWatchingManager.attachResponder(this.fileValidationManager);
        this.fileWatchingManager.startWatchers();
    }
}
