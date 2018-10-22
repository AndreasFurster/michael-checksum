package com.michaelchecksum.domain;

import java.util.ArrayList;

public class Settings {
    public String username;
    public ArrayList<String> folders;

    public Settings(String username, ArrayList<String> folders) {
        this.username = username;
        this.folders = folders;
    }
}
