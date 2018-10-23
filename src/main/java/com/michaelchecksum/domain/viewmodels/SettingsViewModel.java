package com.michaelchecksum.domain.viewmodels;

import com.michaelchecksum.data.SettingStorage;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    private ObservableList<String> paths = FXCollections.observableArrayList();
    private ListProperty<String> listProperty = new SimpleListProperty<>(paths);
    private StringProperty username = new SimpleStringProperty();

    private EventHandler<MouseEvent> onAddClick;
    private EventHandler<MouseEvent> onRemoveClick;
    private EventHandler<MouseEvent> onSetUsername;

    private SettingStorage settingStorage = new SettingStorage();

    public StringProperty getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username.set(username);
    }
    public ListProperty<String> getListProperty(){
        return this.listProperty;
    }

    public List<String> getPaths(){
        return this.paths;
    }

    public void setPaths(ArrayList<String> paths){
        this.paths.setAll(paths);
    }

    public void setOnSettingsAddEventHandler(EventHandler<MouseEvent> eventHandler) {
        this.onAddClick = eventHandler;
    }

    public void setOnSettingsDeleteEventHandler(EventHandler<MouseEvent> eventHandler){
        this.onRemoveClick = eventHandler;
    }

    public void setOnSetUsername(EventHandler<MouseEvent> eventHandler){
        this.onSetUsername = eventHandler;
    }

    public EventHandler<MouseEvent> onPathAdd(){
        return this.onAddClick;
    }

    public EventHandler<MouseEvent> onPathRemove(){
        return this.onRemoveClick;
    }

    public EventHandler<MouseEvent> onSetUsername(){
        return this.onSetUsername;
    }
}
