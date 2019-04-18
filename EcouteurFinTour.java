import java.awt.event.*;
import java.util.*;

public class EcouteurFinTour implements ActionListener {
    
    private FenetrePrincipale fen ;

    public EcouteurFinTour (FenetrePrincipale fen) {
        this.fen=fen ;
    }
    
    //actionPerformed indique les instructions a executer au clic du bouton
    public void actionPerformed(ActionEvent e){
        fen.finTour();
        fen.miseAJour();

    }
    
    
}
