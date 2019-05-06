import java.io.*;
import javazoom.jl.player.*;

public class Mp3Player extends Thread {

    private String fileLocation;
    private boolean loop;
    private Player lecteur;

    public Mp3Player(String fileLocation) {
        this.fileLocation = fileLocation;
        loop = true;
    }

    public void run() {

        try {
            do {
                FileInputStream buff = new FileInputStream(fileLocation);
                lecteur = new Player(buff);
                lecteur.play();
            } while (loop);
        } catch (Exception ioe) {
            // TODO error handling
        }
    }

    public void close(){
        loop = false;
        lecteur.close();
        this.interrupt();
    }
}
