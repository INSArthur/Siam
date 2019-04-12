import javax.swing.*;
import java.awt.*;

public class TestFenetrePrincipale{
	public static void main(String[] args){
	
	Joueur J1 = new Joueur("1",0);
	Joueur J2 = new Joueur("2",1);
	Jeu siam = new Jeu(J1,J2,true);
	FenetrePrincipale fen = new FenetrePrincipale(siam);
	
	}
}
