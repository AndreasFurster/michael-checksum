package com.michaelchecksum.domain;

import java.io.File;

public class FileValidationResult {
    private File file;
    private HashType hashType;
    private String hash;
    private boolean success;

    public FileValidationResult(File file, String hash, HashType hashType, boolean success) {
        this.file = file;
        this.hash = hash;
        this.hashType = hashType;
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
