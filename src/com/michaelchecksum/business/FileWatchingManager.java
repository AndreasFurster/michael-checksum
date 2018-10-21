package com.michaelchecksum.business;

public class FileWatchingManager {

    public void attatchResponer(FileEventListener responder) {
        // TODO: Add responder to responder list
    }

    public void startWatchers() {
        // TODO: Call all responders in responder list
    }
}

interface FileEventListener {
    void NewFileFound();
}
