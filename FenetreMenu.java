import java.awt.*;
import javax.swing.*;

public class FenetreMenu extends JFrame {
	
	//Declaration des attributs de la fenetre 
	private JButton easy ;
	private JButton medium ;
    private JButton hard ;
    private JButton multi ;
    private JButton valider ;
    
	private JLabel eJ1 ;
	private JLabel eJ2 ;
	private JLabel eIA ;
	
	private JTextField txtFieldJ1;
	private JTextField txtFieldJ2;
	private JTextField txtFieldIA;
	
	private JPanel monPanelJoueur1;
	private JPanel monPanelJoueur2;
	private JPanel monPanelIA;
    
	private int mode;
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
        valider =new JButton ("Demarrer");
        
        eJ1=new JLabel ("Joueur 1 : ");
        eJ2=new JLabel ("Joueur 2 : ");
        eIA=new JLabel ("IA : ");
        
        txtFieldJ1= new JTextField (15);
        txtFieldJ2= new JTextField (15);
        txtFieldIA= new JTextField (15);
        txtFieldIA.setText("HAL");
        
        //Dimensions de la fenetre graphique et fermeture
        this.setSize(400,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ====== Organisation structurelle ======
        JPanel monPanelJoueur= new JPanel();
        monPanelJoueur1= new JPanel();
        monPanelJoueur2= new JPanel();
        monPanelIA= new JPanel();

        monPanelJoueur1.setVisible(false);
        monPanelJoueur2.setVisible(false);
        monPanelIA.setVisible(false);
        
        monPanelJoueur.add(monPanelJoueur1);
        monPanelJoueur.add(monPanelJoueur2);
        monPanelJoueur.add(monPanelIA);
        
        monPanelJoueur1.add(eJ1);
        monPanelJoueur1.add(txtFieldJ1);
        monPanelJoueur2.add(eJ2);
        monPanelJoueur2.add(txtFieldJ2);
        monPanelIA.add(eIA);
        monPanelIA.add(txtFieldIA);
       
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
        this.setLocationRelativeTo(null);
    }
    
    
    //Creer jeu
    public void setJeu(){
		if(mode==0 && !txtFieldJ1.getText().isEmpty() && !txtFieldJ2.getText().isEmpty()){
			Joueur J1 = new Joueur(txtFieldJ1.getText(),0);
			Joueur J2 = new Joueur(txtFieldJ2.getText(),1);
			this.siam = new Jeu(J1,J2,true);
		}else if (mode!=0 && !txtFieldJ1.getText().isEmpty() && !txtFieldIA.getText().isEmpty()){
			
			Joueur J1 = new Joueur(txtFieldJ1.getText(),0);
			IA J2 = new IA(txtFieldIA.getText(),1);
			this.siam = new Jeu(J1,J2,false);
		}
		
		this.fen = new FenetrePrincipale(siam);
		this.dispose();
    }
    
    public void mode(int mode){
    	
    	this.mode = mode;
    	monPanelJoueur1.setVisible(true);
    	monPanelJoueur1.setFocusable(true);
    	
    	if(mode==0) {
    		
			monPanelJoueur2.setVisible(true);
			monPanelIA.setVisible(false);
    	}else {
    		monPanelIA.setVisible(true);
    		monPanelJoueur2.setVisible(false);
    	}
		
	}
		      
}






