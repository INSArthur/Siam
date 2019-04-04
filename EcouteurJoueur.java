import java.awt.event.*;
import java.util.*;


public class EcouteurJoueur implements ActionListener{
	
	//attributs de la classe
    private FenetreMenu fen ;
    private int num;
	
	//Définition du constructeur à paramétrer
	public EcouteurJoueur(FenetreMenu fen, int num){
        this.fen=fen;
        this.num=num;
	}
	
	//actionPerformed indique les instructions a executer au clic du bouton
	public void actionPerformed(ActionEvent e){
        if(num==1){
            System.out.println("simple");
        }else if(num==2) {
            System.out.println("moyen");
        }else if (num==3){
            System.out.println("dur");
        }else{
            System.out.println("multijoueur");
        }
	}
	

}
