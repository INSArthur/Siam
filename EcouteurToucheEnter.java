import java.awt.event.*;
import java.util.*;

public class EcouteurToucheEnter implements KeyListener{
    
    private FenetreMenu fen ;
    private static int n;
    private static boolean b = true;

    public EcouteurToucheEnter (FenetreMenu fen, int n) {
        this.fen=fen ;
        this.n = n;
    }
    
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    
    public void keyPressed(KeyEvent e){
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER && n==0)
        {
            fen.setJeu();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER && n==1)
        {
            fen.mode(0);
            b = !b;
        }
    }
}
