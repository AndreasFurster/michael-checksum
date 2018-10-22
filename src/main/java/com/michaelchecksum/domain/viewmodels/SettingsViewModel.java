package com.michaelchecksum.domain.viewmodels;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SettingsViewModel {
    private List<String> paths = new ArrayList<>();
    private ListProperty<String> listProperty = new SimpleListProperty<>();

    private EventHandler<MouseEvent> onAddClick;
    private String pathToAdd;

    public String getPathToAdd() {
        return pathToAdd;
    }

    public SettingsViewModel() {
        this.listProperty.set(FXCollections.observableArrayList(paths));
    }

    public ListProperty<String> getListProperty(){
        return this.listProperty;
    }

    public List<String> getPaths(){
        return this.paths;
    }

    public void setPaths(ArrayList<String> paths){
        this.paths = paths;
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
