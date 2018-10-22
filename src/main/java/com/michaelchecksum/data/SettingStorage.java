package com.michaelchecksum.data;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SettingStorage {
    private Path location;
    private Gson gson = new Gson();
    private ArrayList<Path> paths = new ArrayList<>();

    public boolean InsertFilewatchPath(Path path){
        //get paths first

        return true;
    }

    public boolean DeleteFilewatchPath(Path path) {
        //delete
        return true;
    }

    public ArrayList<Path> getFilewatchPaths() throws IOException{

        //String content = new String(Files.readAllBytes(Paths.get("settings.json")));

        ArrayList<Path> paths = new ArrayList<>();
        paths.add(Paths.get(System.getProperty("user.home") + "\\Downloads\\"));
        return paths;
    }
}
