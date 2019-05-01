import java.awt.event.*;
import java.util.*;

public class EcouteurToucheEnter implements KeyListener{
    
    private FenetreMenu fen ;
    private static int n=0;
    private static boolean b = true;

    public EcouteurToucheEnter (FenetreMenu fen) {
        this.fen=fen ;
    }
    
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    
    public void keyPressed(KeyEvent e){
        System.out.println("b1 = "+b);
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !b)
        {
            System.out.println("Boutotn enter");
            System.out.println("b2 = "+b);
            fen.setJeu();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER && b)
        {
            System.out.println("Boutotn classique");
            fen.mode(0);
            b = !b;
            System.out.println("b3 = "+b);
        }
        //~ b = !b;
        System.out.println("b4 = "+b);
    }
}
