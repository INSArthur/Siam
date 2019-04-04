import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FenetreMenu extends JFrame {
	
	//Declaration des attributs de la fenetre 
	private JButton easy ;
	private JButton medium ;
    private JButton hard ;
    private JButton multi ;
    private JButton valider ;
    
	private JLabel j1 ;
	private JLabel j2 ;
	
	private JTextField joueur1;
	private JTextField joueur2;
    
	private Jeu siam;
	
	/**
     * Constructeur de la classe FenetreMenu
     */
    public FenetreMenu() {

        //on definit le nom du jeu
        super("Jeu du Siam");
        
        this.siam=siam ;
        
        // ====== Instanciation des widgets de la fenetre ======
        easy = new JButton ("Easy");
        medium = new JButton ("Medium");
        hard = new JButton ("Hard");
        multi=new JButton("Multi");
        valider =new JButton ("Valider");
        
        j1=new JLabel ("Joueur 1 : ");
        j2=new JLabel ("Joueur 2 : ");
        
        joueur1= new JTextField (15);
        joueur2= new JTextField (15);
        
        //Dimensions de la fenetre graphique et fermeture
        this.setSize(700,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ====== Organisation structurelle ======
        JPanel monPanelJoueur= new JPanel();
        monPanelJoueur.add(j1);
        monPanelJoueur.add(joueur1);
        monPanelJoueur.add(j2);
        monPanelJoueur.add(joueur2);
        
        JPanel monPanelChoixGauche= new JPanel();
        monPanelChoixGauche.add(multi);
        
        JPanel monPanelChoixDroit= new JPanel();
        monPanelChoixDroit.add(easy);
        monPanelChoixDroit.add(medium);
        monPanelChoixDroit.add(hard);
        
        JPanel monPanelEntree = new JPanel ();
        monPanelEntree.add(valider);
        
        JPanel cadrePrincipal= new JPanel(new BorderLayout());
        cadrePrincipal.add(monPanelJoueur,BorderLayout.NORTH);
        cadrePrincipal.add(monPanelChoixGauche,BorderLayout.WEST);
        cadrePrincipal.add(monPanelChoixDroit,BorderLayout.EAST);
        cadrePrincipal.add(monPanelEntree,BorderLayout.SOUTH);
        
        add(cadrePrincipal);

		// ===== liaison bouttons <-> écouteurs =====
		easy.addActionListener(new EcouteurJoueur(this,1));
		medium.addActionListener(new EcouteurJoueur(this,2));
        hard.addActionListener(new EcouteurJoueur(this,3));
        multi.addActionListener(new EcouteurJoueur(this,0));
        valider.addActionListener(new EcouteurEntree(this));
		 
        //===== Rendre la fenêtre visible ===== 
        this.setVisible(true);
    }
    
    
    //Recuperer jeu
    public Jeu getJeu(){
        return this.siam ;
    }
        
    //Bouton valider récupère nom des joueurs
    public String nomJoueur (){
        String a= joueur1.getText();
        String b=joueur2.getText() ;
        return "Joueur 1 "+ a + "joueur 2 " +b ;
    }
        
}






