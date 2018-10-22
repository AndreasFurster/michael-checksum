package com.michaelchecksum.domain.viewmodels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public class SettingsViewModel {
    private ObservableList<String> paths;
    private EventHandler<MouseEvent> onAddClick;
    private String pathToAdd;

    public String getPathToAdd() {
        return pathToAdd;
    }

    public ObservableList<String> getPaths(){
        return FXCollections.observableArrayList(this.paths);
    }

    public void setPaths(ArrayList<String> paths){
        this.paths = FXCollections.observableArrayList(paths);
    }

    public void setOnSettingsAddEventHandler(EventHandler<MouseEvent> eventHandler) {
            this.onAddClick = eventHandler;
    }

    public EventHandler<MouseEvent> onPathAdd(){
        return this.onAddClick;
    }

    public void onBrowserClick(){
        DirectoryChooser chooser = new DirectoryChooser();
        File file = new File(System.getProperty("user.home"));
    }

}
