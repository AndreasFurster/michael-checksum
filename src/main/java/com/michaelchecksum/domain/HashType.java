package com.michaelchecksum.domain;

import com.sun.javaws.exceptions.InvalidArgumentException;

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

    public static HashType from(int hashTypeInt) {
        for (HashType hashType : HashType.values()) {
            if (hashType.value == hashTypeInt) {
               return hashType;
            }
        }

        throw new IllegalArgumentException("Hashtype of value " + hashTypeInt + " does not exist.");
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return name;
    }
}
