import java.awt.event.*;
import java.util.*;

public class EcouteurEntree implements ActionListener {
	
	private FenetreMenu fen ;

	public EcouteurEntree (FenetreMenu fen) {
		this.fen=fen ;
	}
	
	//actionPerformed indique les instructions a executer au clic du bouton
    public void actionPerformed(ActionEvent e){
		fen.setJeu();

    }
	
	
}
