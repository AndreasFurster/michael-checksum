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

    public ArrayList<Path> GetFilewatchPaths() throws IOException{
        //return all
        String content = new String(Files.readAllBytes(Paths.get("settings.json")));

        System.out.println(content);
        return new ArrayList<Path>();
    }
}
