import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class fenetreMolette extends JFrame {
	
	//Declaration des attributs de l'interface 
    private JButton b1 ;
    private JLabel texte;
    
    private boolean res;
    
    /**
     * Constructeur de la classe testMolette
     */
    public fenetreMolette() {
        
        //on definit le nom de l'interface
        super("l'algo c'est cool !");
        
        //Dimensions de la fenetre graphique et fermeture
        JPanel conteneurPrincipal = new JPanel(new BorderLayout());
        this.setContentPane(conteneurPrincipal);
        this.setSize(800,450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        res=false;
        
        b1 = new JButton ("0");
        conteneurPrincipal.add(b1);
        
        //lier ecouteurs et boutons
        b1.addMouseWheelListener(new MyMouseWheelListener(this));
        
        //Rendre la fenêtre visible
        this.setVisible(true);
    }
    
    public void tour (int t){
        if(t==0){
            b1.setText("haut");
        }else{
            b1.setText("bas");
        }
    }
}

