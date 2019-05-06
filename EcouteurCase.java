
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurCase implements ActionListener{
    
    private FenetrePrincipale fen;
    private boolean casePlateau;    //true = case du plateau; false = case de la reserve
    private Coordonnees c;          //Coordonnees si case du plateau
    private int i;                  //indice si case de la reserve
    private boolean bordure;
    private int angle; 				//0 : la case n'est pas dans un angle; puis angle de 1 a 4 dans le sens des aiguilles d'une montre en partant du nord ouest
    
    public EcouteurCase(FenetrePrincipale fen, Coordonnees c){
        this.fen = fen;
        casePlateau = true;
        this.c = c;
        this.i = 0;
        if (c.h() == 1 || c.h() == 5 || c.v() == 1 || c.v() == 5)
        {
            bordure = true;
        }
        this.angle = setAngle();
    }
    
    public EcouteurCase(FenetrePrincipale fen, int i){
        this.fen = fen;
        casePlateau = false;
        this.c = new Coordonnees(0,0);
        this.i = i;
        this.angle = 0;
        
    }
    
    public void actionPerformed(ActionEvent a){ //Reinitialise la grille lors du clic sur le bouton
        
        
        if(casePlateau) {
                fen.actionCasePlateau(c, bordure, angle);
        }else {
                fen.actionCaseReserve(i);
        }
        
    }
    private int setAngle() {
    	if(c.h()==1) {
			if(c.v()==1) {
				return 1;
			}else if(c.v()==5){
				return 2;
			}
		}else if(c.h()==5){
			if(c.v()==5) {
				return 3;
			}else if(c.v()==1){
				return 4;
			}
		}
    	return 0;
    }

}
