import java.nio.file.*;
import java.util.Scanner;

public class FileWatcher {
    Path path = Paths.get(System.getProperty("user.home") + "\\Downloads\\");
    Scanner reader = new Scanner(System.in);
    void StartWatching(){
        try {
            WatchService watchService = path.getFileSystem().newWatchService();



            WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            while (true) {
                if(watchKey != null) {
                    watchKey.pollEvents().stream().forEach(event -> {
                        System.out.println("What is your hash?");
                        String hash = reader.nextLine();
                        System.out.println(hash);
                        System.out.println(event.context());
                    });
                }
                watchKey.reset();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

        System.out.println(path );
    }


}
