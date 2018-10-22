package com.michaelchecksum.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.michaelchecksum.domain.FileValidationResult;

import java.io.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;


public class FileValidationStorage {
    private SettingStorage settingStorage;

    public FileValidationStorage(){
        this.settingStorage = new SettingStorage();
    }

    public void add(FileValidationResult result) {
        try {
            String username = this.settingStorage.getUsername();
            String country = this.getUserLocale();

            try(Database database = new Database()) {
                database.insertUser(username);
                database.insertCountry(country);

                int userId = database.getUserId(username);
                int countryId = database.getCountryId(country);

                database.addUserToCountry(userId, countryId);

                database.insertFileValidationResult(userId, result);
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUserLocale() throws IOException {
        InputStream resultStream = new URL("http://api.ipstack.com/check?access_key=457379d7bf9e2bb49147f06a0043aea2").openStream();
        String json = this.readInputStream(resultStream);
        return new Gson().fromJson(json, JsonObject.class).get("country_name").getAsString();
    }

    private String readInputStream(InputStream resultStream) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(resultStream);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while(result != -1) {
            buf.write((byte) result);
            result = bis.read();
        }

        return buf.toString("UTF-8");
    }
}
