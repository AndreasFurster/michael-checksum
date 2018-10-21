package com.michaelchecksum.business;

import com.michaelchecksum.legacy.HashChecker;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;

public class FileWatcher {
    private Path path;

    FileWatcher(Path path, ArrayList<FileEventListener> fileEventListeners){
        this.path = path;
        try {
            WatchService watchService = path.getFileSystem().newWatchService();
            WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            //start file watching
            while(true){
                if (watchKey != null) {
                    watchKey.pollEvents().stream().forEach(event -> {
                        //there has to be a pop up here
                        String filePath = this.path + event.context().toString();
                        File file = new File(filePath);
                        for(FileEventListener fileEventListener : fileEventListeners){
                            fileEventListener.handleNewFileFound(file);
                        }

                    });
                }
                watchKey.reset();
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
