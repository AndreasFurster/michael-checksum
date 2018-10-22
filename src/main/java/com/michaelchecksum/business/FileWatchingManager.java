package com.michaelchecksum.business;

import com.michaelchecksum.data.SettingStorage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileWatchingManager {
    private ArrayList<FileEventListener> fileEventListeners = new ArrayList<FileEventListener>();
    private SettingStorage settingStorage = new SettingStorage();
    public void attachResponder(FileEventListener responder) {
        this.fileEventListeners.add(responder);
    }

    //start all the watcher from the settings file
    public void startWatchers() {
        //get the paths from the settings4
        try {
            ArrayList<String> paths = this.settingStorage.getFilewatchPaths();

            //foreach through the pathlist
            for (String path : paths) {
                FileWatcher watcher = new FileWatcher(Paths.get(path), this.fileEventListeners);
                watcher.start();
            }
        }
        catch(IOException ex){
            // TODO: Throw exception
        }
    }

    //start a single watcher
    public void startSingleWatcher(Path path) {
        new FileWatcher(path, this.fileEventListeners).start();
    }
}

interface FileEventListener {
    void handleNewFileFound(File file);
}
