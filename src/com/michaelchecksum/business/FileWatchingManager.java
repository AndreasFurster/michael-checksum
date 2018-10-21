package com.michaelchecksum.business;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileWatchingManager {
    private ArrayList<FileWatcher> fileWatchers = new ArrayList<>();
    private ArrayList<FileEventListener> fileEventListeners =  new ArrayList<FileEventListener>();

    public void attachResponder(FileEventListener responder) {
        this.fileEventListeners.add(responder);
    }

    //start all the watcher from the settings file
    public void startWatchers() {
        //get the paths from the settings
        List<Path> paths = new ArrayList<>();
        paths.add(Paths.get(System.getProperty("user.home") + "\\Downloads\\"));

        //foreach through the pathlist
        for(Path path : paths){
            fileWatchers.add(new FileWatcher(path, this.fileEventListeners));
        }
    }

    //start a single watcher
    public void startSingleWatcher(Path path){
        // TODO: add a single watcher

    }
}

interface FileEventListener {
    void handleNewFileFound(File file);
}
