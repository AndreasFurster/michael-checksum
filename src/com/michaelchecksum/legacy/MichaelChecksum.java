package com.michaelchecksum.legacy;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public class MichaelChecksum {
    public void Run() {
        StartFileWatcher();
    }

    private void StartFileWatcher() {

        Responder r = new Responder();

        FileWatcher fileWatcher = new FileWatcher(r);
        fileWatcher.StartWatching();
    }

    void OnFileCreated (){
        // TODO ROY: Implement user input

        CheckHash("url", "hash");
    }

    private void CheckHash(String uriString, String hash) {
        HashChecker hc = new HashChecker();

        URI uri;
        try {
            // uri = new URI("file:///C:/Users/andre/Downloads/npp.7.5.8.Installer.x64.exe");

            uri = new URI(uriString);
        } catch (URISyntaxException e) {
            System.out.println("Uri invalid!");
            return;
        }

        try {
            // boolean sha1 = hc.CheckHash(uri, "19a9a168e523637b56cb15afad6377f09f3cb6aa");
            // boolean md5 = hc.CheckHash(uri, "538431fd315f4da79eefc9072e4146a8");

            // System.out.println("SHA1: " + sha1);
            // System.out.println("MD5: " + md5);

            System.out.println("Result: " + hc.CheckHash(uri, hash));
        } catch (NoSuchAlgorithmException e){
            System.out.println("Algorithm unknown");
        } catch (IOException e) {
            System.out.println("Lost connection to file");
        }
    }
}
