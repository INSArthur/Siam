
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurCase implements ActionListener{
    
    private FenetrePrincipale fen;
    private boolean casePlateau; 	//true = case du plateau; false = case de la reserve
    private Coordonnees c; 			//Coordonnees si case du plateau
    private int i;					//indice si case de la reserve
    private boolean bordure;
    
    public EcouteurCase(FenetrePrincipale fen, Coordonnees c){
        this.fen = fen;
        casePlateau = true;
        this.c = c;
        this.i = 0;
        if (c.h() == 1 || c.h() == 5 || c.v() == 1 || c.v() == 5)
        {
            bordure = true;
        }
    }
    
    public EcouteurCase(FenetrePrincipale fen, int i){
        this.fen = fen;
        casePlateau = false;
        this.c = new Coordonnees(0,0);
        this.i = i;
        if (c.h() == 1 || c.h() == 5 || c.v() == 1 || c.v() == 5)
        {
            bordure = true;
        }
    }
    
    public void actionPerformed(ActionEvent a){ //Reinitialise la grille lors du clic sur le bouton
        
    	
    	if(casePlateau) {
    			fen.actionCasePlateau(c, bordure);
    			System.out.println("EcouteurCase case plateau");
    	}else {
    			fen.actionCaseReserve(i);
    			System.out.println("EcouteurCase case reserve");
    	}
    	
    }

}