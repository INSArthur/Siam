import java.awt.event.*;
import java.util.*;

public class EcouteurEntree implements ActionListener {
	
	private FenetreMenu fen ;
    //private FenetrePrincipale fp;
	public EcouteurEntree (FenetreMenu fen) {
		this.fen=fen ;
	}
	
	//actionPerformed indique les instructions a executer au clic du bouton
    public void actionPerformed(ActionEvent e){
		System.out.println("choix validé");
        System.out.println(fen.nomJoueur());
        //fp=new FenetrePrincipale (fen.getJeu());
    }
	
	
}
