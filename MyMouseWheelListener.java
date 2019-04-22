import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MyMouseWheelListener implements MouseWheelListener {
    
    private FenetrePrincipale fen ;
    
    public MyMouseWheelListener (FenetrePrincipale fen) {
        this.fen= fen ;
    }
    
    public void mouseWheelMoved(MouseWheelEvent e) {
        fen.pivoterPiece(e.getWheelRotation());
        fen.miseAJour();
    }   
}

