package com.michaelchecksum.domain;

import java.io.File;

public class FileValidationResult {
    private File file;
    private User user;
    private HashType hashType;
    private String hash;
    private boolean success;

    public FileValidationResult(File file, String hash, boolean success) {
        this.file = file;
        this.hash = hash;
        this.success = success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public HashType getHashType() {
        return hashType;
    }

    public String getHash() {
        return hash;
    }

    public File getFile() {
        return file;
    }
}
