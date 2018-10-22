package com.michaelchecksum.domain;

public enum HashType {
    MD5(1, "MD5"),
    SHA1(2, "SHA-1"),
    SHA256(3, "SHA-256"),
    SHA512(4, "SHA-512");

    private final int value;
    private final String name;

    HashType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return name;
    }
}
