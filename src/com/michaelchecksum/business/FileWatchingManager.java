package com.michaelchecksum.business;

import java.io.File;

public class FileWatchingManager {

    public void addFilewatcher(){

    }

    public void attachResponder(FileEventListener responder) {
        // TODO: Add responder to responder list
    }

    public void startWatchers() {
        // TODO: Call all responders in responder list
    }
}

interface FileEventListener {
    void handleNewFileFound(File file);
}
