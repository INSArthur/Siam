import javax.swing.*;
import java.awt.event.*;

public class EcouteurPiece implements ActionListener{
	
	private FenetrePrincipale fen;
	private Piece p;
	
	public EcouteurPiece(FenetrePrincipale fen, Piece p){
		this.fen = fen;
		this.p = p;
	}
	
	public void actionPerformed(ActionEvent a){ //Réinitialise la grille lors du clic sur le bouton
		fen.deplacementPiece(p);
	}

}
