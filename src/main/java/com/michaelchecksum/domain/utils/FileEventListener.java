package com.michaelchecksum.domain.utils;

import java.io.File;

public interface FileEventListener {
    void handleNewFileFound(File file);
}
