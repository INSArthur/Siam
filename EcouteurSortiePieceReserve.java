import java.awt.event.*;
import java.util.*;

public class EcouteurSortiePieceReserve implements ActionListener {
    
    private FenetrePrincipale fen ;
    private int idJoueur;

    public EcouteurSortiePieceReserve (FenetrePrincipale fen, int idJoueur) {
        this.fen=fen ;
        this.idJoueur = idJoueur;
    }
    
    //actionPerformed indique les instructions a executer au clic du bouton
    public void actionPerformed(ActionEvent e){
        fen.sortirPieceReserve(idJoueur);
    }
    
    
}
