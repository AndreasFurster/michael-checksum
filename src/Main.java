import javax.swing.*;
public class Main {

    public static void main(String[] args) {
        // Dashboard d = new Dashboard();
        // d.setVisible(true);

        FileWatcher fileWatcher = new FileWatcher();
        fileWatcher.StartWatching();

        MichaelChecksum mc = new MichaelChecksum();
        mc.Run();
    }
}
