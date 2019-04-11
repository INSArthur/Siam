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
	
	private JPanel monPanelJoueur;
    
	private Jeu siam;
	private FenetrePrincipale fen;
	
	/**
     * Constructeur de la classe FenetreMenu
     */
    public FenetreMenu() {

        //on definit le nom du jeu
        super("Jeu du Siam");
        
        // ====== Instanciation des widgets de la fenetre ======
        easy = new JButton ("Easy");
        medium = new JButton ("Medium");
        hard = new JButton ("Hard");
        multi=new JButton("Multi");
        valider =new JButton ("Valider");
        
        j1=new JLabel ("Joueur 1 : ");
        //j1.setVisible(false);
        j2=new JLabel ("Joueur 2 : ");
        //j2.setVisible(false);
        
        joueur1= new JTextField (15);
        //joueur1.setVisible(false);
        joueur2= new JTextField (15);
        //joueur2.setVisible(false);
        
        //Dimensions de la fenetre graphique et fermeture
        this.setSize(700,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ====== Organisation structurelle ======
        monPanelJoueur= new JPanel();
        monPanelJoueur.add(j1);
        monPanelJoueur.add(joueur1);
        monPanelJoueur.add(j2);
        monPanelJoueur.add(joueur2);
        monPanelJoueur.setVisible(false);
        
       
        
        JPanel monPanelChoix= new JPanel();
        monPanelChoix.add(multi);
        monPanelChoix.add(easy);
        monPanelChoix.add(medium);
        monPanelChoix.add(hard);
        
        JPanel monPanelEntree = new JPanel ();
        monPanelEntree.add(valider);
        
        JPanel cadrePrincipal= new JPanel(new BorderLayout());
        
        cadrePrincipal.add(monPanelChoix,BorderLayout.NORTH);
        cadrePrincipal.add(monPanelJoueur,BorderLayout.CENTER);
        cadrePrincipal.add(monPanelEntree,BorderLayout.SOUTH);
        
        add(cadrePrincipal);

		// ===== liaison bouttons <-> écouteurs =====
		easy.addActionListener(new EcouteurMode(this,1));
		medium.addActionListener(new EcouteurMode(this,2));
        hard.addActionListener(new EcouteurMode(this,3));
        multi.addActionListener(new EcouteurMode(this,0));
        valider.addActionListener(new EcouteurEntree(this));
		 
        //===== Rendre la fenêtre visible ===== 
        this.setVisible(true);
    }
    
    
    //Créer jeu
    public void setJeu(){
		if(!joueur1.getText().isEmpty() && !joueur2.getText().isEmpty()){
			Joueur J1 = new Joueur(joueur1.getText(),1);
			Joueur J2 = new Joueur(joueur2.getText(),2);
			this.siam = new Jeu(J1,J2);
			this.fen = new FenetrePrincipale(siam);
		}
    }
    
    public void mode(int mode){
		switch (mode)
		{
			case 0:
			/*j1.setVisible(true);
			j2.setVisible(true);
			joueur1.setVisible(true);
			joueur2.setVisible(true);*/
				monPanelJoueur.setVisible(true);
				break;
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			default:
				break;
		}
		
	}      
}






