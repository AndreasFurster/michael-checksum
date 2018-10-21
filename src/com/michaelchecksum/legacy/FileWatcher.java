package com.michaelchecksum.legacy;

import com.sun.org.apache.xml.internal.security.Init;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Scanner;

public class FileWatcher extends MichaelChecksum {
    Path path = Paths.get(System.getProperty("user.home") + "\\Downloads\\");
    Scanner reader = new Scanner(System.in);
    Initiator i = new Initiator();

    FileWatcher(Responder r){

        i.addListener(r);
    }

    void StartWatching() {
        try {
            WatchService watchService = path.getFileSystem().newWatchService();


            WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            while (true) {
                if (watchKey != null) {
                    watchKey.pollEvents().stream().forEach(event -> {

                        i.fire();
                        System.out.println("What is your hash of: " + event.context());
                        String hash = reader.nextLine();
                        if (hash == "") {
                            return;
                        }
                        System.out.println(hash);
                        System.out.println(event);


                        HashChecker hc = new HashChecker();

                        URI uri;
                        try {
                            //   uri = new URI("file:///C:/Users/andre/Downloads/npp.7.5.8.Installer.x64.exe");
                            String uriStr = "file:///" + System.getProperty("user.home").replace('\\', '/') + "/Downloads/" + event.context();
                            System.out.println(uriStr);
                            uri = new URI(uriStr);
                        } catch (URISyntaxException e) {
                            System.out.println("Uri invalid!");
                            return;
                        }

                        try {

                            System.out.println(hc.CheckHash(uri, hash));
                        } catch (Exception e) {

                        }

                    });
                }
                watchKey.reset();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(path);
    }


}
