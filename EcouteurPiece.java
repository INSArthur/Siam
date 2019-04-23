import javax.swing.*;
import java.awt.event.*;

public class EcouteurPiece implements ActionListener{
    
    private FenetrePrincipale fen;
    private Coordonnees c;
    private boolean bordure= false;
    
    public EcouteurPiece(FenetrePrincipale fen, Coordonnees c){
        this.fen = fen;
        this.c = c;
        if (c.h() == 1 || c.h() == 5 || c.v() == 1 || c.v() == 5)
        {
            bordure = true;
        }
    }
    
    public void actionPerformed(ActionEvent a){ //Réinitialise la grille lors du clic sur le bouton
        fen.deplacementPiece(this.c);
        System.out.println("hor "+c.h()+"  ver "+c.v());
        if (bordure)
        {
            fen.afficherBoutonSortiePlateau(c);
        }
        fen.miseAJour();

    }

}
