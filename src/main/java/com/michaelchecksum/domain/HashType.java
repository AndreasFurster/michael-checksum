package com.michaelchecksum.domain;

public enum HashType {
    MD5(1), SHA1(2), SHA256(3), SHA512(4);

    private final int value;

    HashType(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
