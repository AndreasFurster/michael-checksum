package com.michaelchecksum.business;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ApplicationManager extends Application  {
    private FileWatcherManager fileWatcherManager;
    private HashManager hashManager;
    private DashboardManager dashboardManager;

    public void initiate(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        fileWatcherManager = new FileWatcherManager();
        hashManager = new HashManager();
        dashboardManager = new DashboardManager();

        this.initiateFileWatcher();
        this.openDashboard();
    }

    private void initiateFileWatcher(){
        // TODO: Attatch HashManager to FileWatcherManager
        // TODO: FileWatcherManager.StartWatchers()
    }

    private void openDashboard(){
        dashboardManager.openDashboard();
    }


}
