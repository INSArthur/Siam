import java.awt.event.*;
import java.util.*;


public class EcouteurMode implements ActionListener{
	
	//attributs de la classe
    private FenetreMenu fen ;
    private int mode;
	
	//Définition du constructeur à paramétrer
	public EcouteurMode(FenetreMenu fen, int mode){
        this.fen=fen;
        this.mode=mode;
	}
	
	//actionPerformed indique les instructions a executer au clic du bouton
	public void actionPerformed(ActionEvent e){
        fen.mode(mode);
	}
	

}
