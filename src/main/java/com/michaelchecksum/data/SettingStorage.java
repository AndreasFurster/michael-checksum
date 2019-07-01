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
    private Gson gson = new Gson();
    private Settings settings = new Settings("",new ArrayList<>());
    private ArrayList<Path> paths = new ArrayList<>();

    public SettingStorage() {
        this(Paths.get(System.getProperty("user.dir") + "\\settings.json"));
    }

    public SettingStorage(Path location) {
        this.location = location;
        File file = new File(location.toString());
        try {
            if(!file.exists()) {
                file.createNewFile();
                List<String> lines = new ArrayList<>();
                lines.add("{\"folders\":[], \"username\" : \"\"}");
                Files.write(this.location, lines);
            }
        }
        catch(IOException ex){
            // TODO: handle the exception
        }

    }

    public boolean insertFilewatchPath(String path){
        // TODO: INSERTFILEWATCHPATH
        if(!this.findFolders(path)){
            this.settings.folders.add(path);
            this.writeNewFile();
        }
        return true;
    }

    public boolean deleteFilewatchPath(String path) {
        // TODO: DELETEFILEWATCHPATH
        if(this.findFolders(path)){
            if (path != null) {
                this.settings.folders.remove(path);
            }

            this.writeNewFile();
        }
        return true;
    }

    public String getUsername() throws IOException{
        String content = new String(Files.readAllBytes(location));

        Settings settings = gson.fromJson(content, Settings.class);

        this.settings.username = settings.username;
        return this.settings.username;
    }

    public ArrayList<String> getFilewatchPaths() throws IOException{

        String content = new String(Files.readAllBytes(this.location));

        Settings settings = gson.fromJson(content, Settings.class);
        if(settings.folders != null) {
            this.settings.folders = settings.folders;
            return this.settings.folders;
        }
        return new ArrayList<>();
    }

    private boolean writeNewFile(){
        File file = new File(location.toString());
        try {
            String json = gson.toJson(this.settings);

            file.createNewFile();
            List<String> lines = new ArrayList<>();
            lines.add(json);
            Files.write(this.location, lines);
            return true;
        }
        catch(IOException ex){
            // TODO: exception handling
            //return false;
            System.out.println("ALLES KAPOT");
            return false;
        }
    }

    private boolean writeNewFile(Settings settings){
        File file = new File(location.toString());
        try {
            String json = gson.toJson(settings);

            file.createNewFile();
            List<String> lines = new ArrayList<>();
            lines.add(json);
            Files.write(this.location, lines);
            return true;
        }
        catch(IOException ex){
            // TODO: exception handling
            //return false;
            System.out.println("ALLES KAPOT");
            return false;
        }
    }

    private boolean findFolders(String givenFolder){
        for(String folder : this.settings.folders){
            if(folder.equals(givenFolder)){
                return true;
            }
        }
        return false;
    }

    public void setUsername(String username) {
        try{
            String content = new String(Files.readAllBytes(this.location));

            Settings settings = gson.fromJson(content, Settings.class);
            settings.username = username;
            this.writeNewFile(settings);
        }
        catch(IOException ex){
            // TODO: catch exception
        }
    }
}
