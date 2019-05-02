
import java.awt.event.*;
import java.util.*;

public class EcouteurAide implements ActionListener {
    
    private FenetreRegles fen;
    
    public EcouteurAide(FenetreRegles fen){
        this.fen = fen;
    }
    
    //actionPerformed indique les instructions a executer au clic du bouton
    public void actionPerformed(ActionEvent e){
		fen.changer();

    }
    
   
    
}

