package com.michaelchecksum.business;

import javafx.application.Platform;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;

public class FileWatcher extends Thread {
    private Path path;
    private WatchKey watchKey;
    private ArrayList<FileEventListener> fileEventListeners;

    FileWatcher(Path path, ArrayList<FileEventListener> fileEventListeners){
        this.path = path;
        this.fileEventListeners = fileEventListeners;

        try {
            WatchService watchService = path.getFileSystem().newWatchService();
            this.watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        //start file watching
        while(true){
            if (watchKey != null) {
                watchKey.pollEvents().stream().forEach(event -> {
                    //there has to be a pop up here
                    String filePath = this.path + "\\" + event.context().toString();
                    File file = new File(filePath);
                    System.out.println(fileEventListeners);
                    for (FileEventListener fileEventListener : fileEventListeners) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                fileEventListener.handleNewFileFound(file);
                            }
                        });
                    }

                });
            }
            watchKey.reset();
        }
    }
}
