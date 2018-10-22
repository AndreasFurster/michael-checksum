package com.michaelchecksum.data;

import com.google.gson.Gson;
import com.michaelchecksum.domain.Settings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SettingStorage {
    private Path location;
    private File file;
    private Gson gson = new Gson();
    private ArrayList<Path> paths = new ArrayList<>();

    public SettingStorage(Path location) {
        this.location = location;
        this.file = new File(location.toString());
        try {
            if(!this.file.exists()) {
                this.file.createNewFile();
                List<String> lines = new ArrayList<>();
                lines.add("{\"folders\":[], \"username\" : \"\"}");
                Files.write(this.location, lines);
            }
        }
        catch(IOException ex){
            // TODO: handle the exception
        }

    }

    public boolean InsertFilewatchPath(Path path){
        // TODO: INSERTFILEWATCHPATH

        return true;
    }

    public boolean DeleteFilewatchPath(Path path) {
        // TODO: DELETEFILEWATCHPATH
        return true;
    }

    public String getUsername() throws IOException{
        String content = new String(Files.readAllBytes(Paths.get("settings.json")));

        Settings settings = gson.fromJson(content, Settings.class);

        return settings.username;
    }

    public ArrayList<String> getFilewatchPaths() throws IOException{

        String content = new String(Files.readAllBytes(Paths.get("settings.json")));

        Settings settings = gson.fromJson(content, Settings.class);

        return settings.folders;
    }
}
