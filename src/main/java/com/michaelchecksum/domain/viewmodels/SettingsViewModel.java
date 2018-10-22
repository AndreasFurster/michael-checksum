package com.michaelchecksum.domain.viewmodels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public class SettingsViewModel {
    private ObservableList<Path> paths;

    public ObservableList<String> getPaths(){
        ArrayList<String> pathsByString = new ArrayList<>();

        for(Path path : this.paths){
            pathsByString.add(path.toString());

            System.out.println(path);
        }

        return FXCollections.observableArrayList(pathsByString);
    }

    public void setPaths(ArrayList<Path> paths){
        this.paths = FXCollections.observableArrayList(paths);
    }

    public void onBrowserClick(){
        DirectoryChooser chooser = new DirectoryChooser();
        File file = new File(System.getProperty("user.home"));
    }

}
