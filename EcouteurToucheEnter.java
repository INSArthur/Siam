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
        System.out.println("n = "+n);
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER && n==0)
        {
            System.out.println("Boutotn enter");
            System.out.println("b2 = "+b);
            fen.setJeu();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER && n==1)
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
