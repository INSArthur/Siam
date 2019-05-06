import java.awt.event.*;
import java.util.*;

public class EcouteurAngle implements ActionListener {
    
    private FenetrePrincipale fen;
    private int direction;  //de 1 a 4 en partant du nord et dans le sens des aiguilles d'une montre
    
    
    public EcouteurAngle(FenetrePrincipale fen){
        this.fen = fen;
        this.direction = 0;
    }
    
    public void actionPerformed(ActionEvent e){
            fen.selectionAngle(direction);
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
}
