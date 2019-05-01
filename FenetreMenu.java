import java.awt.*;
import javax.swing.*;

public class FenetreMenu extends JFrame {
    
    //Declaration des attributs de la fenetre 
    private JButton easy ;
    private JButton medium ;
    private JButton hard ;
    private JButton classique ;
    private JButton valider ;
    
    private JButton boutonSelec;
    private JButton precBoutonSelec;
    
    private JLabel eJ1 ;
    private JLabel eJ2 ;
    private JLabel eIA ;
    
    private JTextField txtFieldJ1;
    private JTextField txtFieldJ2;
    private JTextField txtFieldIA;
    
    private JPanel panelJoueur1;
    private JPanel panelJoueur2;
    private JPanel panelIA;
    private JPanel panelModeSolo;
    private JPanel panelModeMulti;
    private JPanel panelEntree;
    
    private int mode;
    private Jeu siam;
    private FenetrePrincipale fen;

    
    /**Constructeur de la classe FenetreMenu**/
    public FenetreMenu() {

        //on definit le nom du jeu
        super("Jeu du Siam");
        
        // ====== Instanciation des widgets de la fenetre ======
        easy = new JButton ("Easy");
        medium = new JButton ("Medium");
        hard = new JButton ("Hard");
        classique=new JButton("Classique");
        valider =new JButton ("Demarrer");
        
        boutonSelec = new JButton();
        precBoutonSelec = new JButton();
        
        eJ1=new JLabel ("Joueur 1 : ");
        eJ2=new JLabel ("Joueur 2 : ");
        eIA=new JLabel ("IA : ");
        
        txtFieldJ1= new JTextField(15);
        txtFieldJ2= new JTextField (15);
        txtFieldIA= new JTextField (15);
        txtFieldIA.setText("HAL");
        
        //Dimensions de la fenetre graphique et fermeture
        this.setSize(400,105);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ====== Organisation structurelle et apparence======
            //Panneau d'acquisition des noms
            JPanel panelJoueur= new JPanel();
            panelJoueur1= new JPanel();
            panelJoueur2= new JPanel();
            panelIA= new JPanel();
    
            panelJoueur1.setVisible(false);
            panelJoueur2.setVisible(false);
            panelIA.setVisible(false);
            
            panelJoueur.add(panelJoueur1);
            panelJoueur.add(panelJoueur2);
            panelJoueur.add(panelIA);
            
            panelJoueur1.add(eJ1);
            panelJoueur1.add(txtFieldJ1);
            panelJoueur2.add(eJ2);
            panelJoueur2.add(txtFieldJ2);
            panelIA.add(eIA);
            panelIA.add(txtFieldIA);
            
            //Panneau Superieur de choix du mode
            JPanel panelChoix= new JPanel();
            JPanel panelModeSolo = new JPanel();
            JPanel panelModeMulti = new JPanel();
            panelModeSolo.setBorder(BorderFactory.createTitledBorder("Solo contre IA"));
            panelModeMulti.setBorder(BorderFactory.createTitledBorder("Multi joueur"));
            
            panelChoix.add(panelModeMulti);
            panelChoix.add(panelModeSolo);
            panelModeMulti.add(classique);
            panelModeSolo.add(easy);
            panelModeSolo.add(medium);
            panelModeSolo.add(hard);
            
            //Panneau de validation
            panelEntree = new JPanel ();
            panelEntree.add(valider);
            panelEntree.setVisible(false);
            
            //Panneau Principal
            JPanel cadrePrincipal= new JPanel(new BorderLayout());
            
            cadrePrincipal.add(panelChoix,BorderLayout.NORTH);
            cadrePrincipal.add(panelJoueur,BorderLayout.CENTER);
            cadrePrincipal.add(panelEntree,BorderLayout.SOUTH);
            
            add(cadrePrincipal);

        // ===== liaison bouttons <-> ecouteurs =====
        easy.addActionListener(new EcouteurMode(this,1));
        medium.addActionListener(new EcouteurMode(this,2));
        hard.addActionListener(new EcouteurMode(this,3));
        classique.addActionListener(new EcouteurMode(this,0));
        valider.addActionListener(new EcouteurEntree(this));
        valider.setEnabled(false);
        
        classique.addKeyListener(new EcouteurToucheEnter(this));
        valider.addKeyListener(new EcouteurToucheEnter(this));
         
        //===== Rendre la fenetre visible ===== 
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    
    //Creer jeu
    public void setJeu(){
        System.out.println("test set jeu");
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
    
    //Acquisition du mode et affichage des parametres (noms des joueurs)
    public void mode(int mode){
        
        this.mode = mode;
        panelJoueur1.setVisible(true);
        panelEntree.setVisible(true);
        this.setSize(400,220);
        txtFieldJ1.requestFocusInWindow();
        precBoutonSelec = boutonSelec;

        valider.setEnabled(true);
        
        if(mode==0) {
            
            panelJoueur2.setVisible(true);
            panelIA.setVisible(false);
            boutonSelec = classique;
            
        }else {
            panelIA.setVisible(true);
            panelJoueur2.setVisible(false);
            
            switch(mode) {
            case 1:
                boutonSelec = easy;
                break;
            case 2:
                boutonSelec = medium;
                break;
            case 3:
                boutonSelec = hard;
                break;
            default:
                break;
            }
            
            }
        if(boutonSelec!=precBoutonSelec) {
            boutonSelec.setBorder(BorderFactory.createRaisedSoftBevelBorder());
            precBoutonSelec.setBorder(valider.getBorder());
        }
    }
              
}






