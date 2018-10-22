package com.michaelchecksum.domain;

public class User {
    private Country country;
    private String username;

    String getUsername() {
        return this.username;
    }

    void setUsername(String value) {
        this.username = value;
    }

    Country getCountry() {
        return this.country;
    }

    void setCountry(Country value) {
        this.country = value;
    }

}
