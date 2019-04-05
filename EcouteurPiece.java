import javax.swing.*;
import java.awt.event.*;

public class EcouteurPiece implements ActionListener{
	
	private FenetrePrincipale fen;
	private Coordonnees c;
	
	public EcouteurPiece(FenetrePrincipale fen, Coordonnees c){
		this.fen = fen;
		this.c = c;
	}
	
	public void actionPerformed(ActionEvent a){ //Réinitialise la grille lors du clic sur le bouton
		fen.deplacementPiece(this.c);
	}

}
