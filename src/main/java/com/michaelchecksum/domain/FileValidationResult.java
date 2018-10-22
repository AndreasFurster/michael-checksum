package com.michaelchecksum.domain;

public class FileValidationResult {
    private String fileName;
    private HashType hashType;
    private String hash;
    private boolean success;
    private String username;

    public FileValidationResult(String fileName, String hash, HashType hashType, boolean success) {
        this.fileName = fileName;
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

    public String getFileName() {
        return fileName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
