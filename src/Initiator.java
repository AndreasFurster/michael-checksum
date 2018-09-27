import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Initiator {
    private List<FileEventListener> eventListeners = new ArrayList<FileEventListener>();

    public void addListener(FileEventListener toAdd){
        eventListeners.add(toAdd);
    }

    public void fire(){
        System.out.println("FIREEE");
        for(FileEventListener el : eventListeners){
            el.fileDiscovered();
        }
    }
}
