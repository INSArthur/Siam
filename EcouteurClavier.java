import java.awt.event.*;
import java.util.*;

public class EcouteurClavier implements KeyListener{
    
    private FenetrePrincipale fen ;

    public EcouteurClavier (FenetrePrincipale fen) {
        this.fen=fen ;
    }
    
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        int direction;
        switch (key)
        {
            case KeyEvent.VK_UP:
                direction = 1;
                break;
                
            case KeyEvent.VK_RIGHT:
                direction = 1;
                break;
            case KeyEvent.VK_DOWN:
                direction = -1;
                break;
            case KeyEvent.VK_LEFT:
                direction = -1;
                break;
            default:
                direction = 0;
        }
        fen.pivoterPiece(direction);
        
    }
}

