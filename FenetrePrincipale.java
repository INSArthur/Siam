import javax.swing.*;
import java.awt.*;

public class FenetrePrincipale extends JFrame{
    
    //Déclaration Jeu
    private Jeu siam;
    
    //Déclaration bouton
    private JButton bEntrerPiece;       //Bouton pour inserer une pièce sur le plateau 
    private JButton bFinTour;           //Bouton pour indiquer la fin d'un tour
    
    //Déclaration de la barre de menu
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem item1;
    private JMenuItem item2;
    private JMenuItem item3;
    private JMenuItem item4;
    
    //Déclaration réserve grille et étiquette J1
    private JLabel[] eArrayJ1;
    private JButton[] bArrayJ1;
    
    //Déclaration réserve grille et étiquette J2
    private JLabel[] eArrayJ2;
    private JButton[] bArrayJ2;
    
    //Déclaration Etiquette joueur
    private JLabel eJoueur;             //Etiquette avec l'inscription joueur
    private JLabel eNomJoueur;          //Etiquette indiquant le nom du joueur dont c'est le tour
    
    //Déclaration du tableau d'etiquettes + tableau de boutons
    private JLabel[][] eGrille; //Grille d'étiquette représentant les cases du plateau de jeu
    private JButton[][] bGrille;    //Grille de boutons permettant d'inter-agir avec le plateau de jeu plateau de jeu
    
    //Déclaration variables d'étapes et de sélection de pièce 
    private boolean isPieceSelectionee;
    private boolean isPieceDeplacee;
    private Coordonnees pieceSelectionnee;
    
    public FenetrePrincipale(Jeu siam){
        super("Jeu du Siam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setVisible(true);
        this.setLocationRelativeTo(null);
        
        this.siam = siam;
        
        //Initialisation boutons
        bEntrerPiece = new JButton("Entrer piece");
        bFinTour = new JButton("Fin du tour");
        bFinTour.setVisible(true);
        
        //Initialisation barre de menu
        menu = new JMenu("Menu");
        menuBar = new JMenuBar();
        item1 = new JMenuItem("Nouvelle partie");
        item2 = new JMenuItem("Paramatre");
        item3 = new JMenuItem("Aide");
        item4 = new JMenuItem("Quitter");
        item1.addActionListener(new EcouteurMenu(this,1));
        item2.addActionListener(new EcouteurMenu(this,2));
        item3.addActionListener(new EcouteurMenu(this,3));
        item4.addActionListener(new EcouteurMenu(this,4));
        
        //Initialisation etiquette joueur
        eJoueur = new JLabel("joueur : ");
        eNomJoueur = new JLabel();
        eNomJoueur.setText(siam.getNomJoueurCourant());
        
        //Initialisation tableau d'étiquette + tableau de boutons
        eGrille = new JLabel[5][5];
        bGrille = new JButton[5][5];
        Icon vide = new ImageIcon("vide.png");
        
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                eGrille[i][j] = new JLabel();
                eGrille[i][j].setSize(new Dimension(50,50));
                eGrille[i][j].setIcon(vide);
            }
        }
        
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                bGrille[i][j] = new JButton();
                bGrille[i][j].addActionListener(new EcouteurPiece(this,new Coordonnees(i,j)));
                bGrille[i][j].setSize(new Dimension(50,50));
                bGrille[i][j].setVisible(false);
            }
        }
        
        //Initialisation bouton et étiquette de la réserve 1
        Icon flecheVerte = new ImageIcon("f_1_1.png");
        
        eArrayJ1 = new JLabel[5];
        bArrayJ1 = new JButton[5];
        for(int i=0; i<5; i++){
            eArrayJ1[i] = new JLabel();
            eArrayJ1[i].setSize(new Dimension(50,50));
            eArrayJ1[i].setIcon(flecheVerte);
        }
        
        for(int i=0; i<5; i++){
            bArrayJ1[i] = new JButton();
            bArrayJ1[i].setSize(new Dimension(50,50));
            bArrayJ1[i].setVisible(false);
        }
        
        //Initialisation bouton et étiquette de la réserve 2
        eArrayJ2 = new JLabel[5];
        bArrayJ2 = new JButton[5];
        for(int i=0; i<5; i++){
            eArrayJ2[i] = new JLabel();
            eArrayJ2[i].setSize(new Dimension(50,50));
            eArrayJ2[i].setIcon(vide);
        }
        
        for(int i=0; i<5; i++){
            bArrayJ2[i] = new JButton();
            bArrayJ2[i].setSize(new Dimension(50,50));
            bArrayJ2[i].setVisible(false);
        }
        
        //Initialisation variables de selection de piece et d'étapes
        isPieceSelectionee = false;
        isPieceDeplacee = false;
        pieceSelectionnee = new Coordonnees(0,0);

        
        //Declaration Panneaux et remplissage
            //Declaration et initialisation du panneau superieur (Menu et joueur) + attribution boutons
                
                
                //Déclaration et initialisation du panneau superieur centré (joueur)
                JPanel pJoueur = new JPanel();
                pJoueur.add(eJoueur);
                pJoueur.add(eNomJoueur);
                
                //Ajout de la barre de menu au panneau supérieur
                menu.add(item1);
                menu.add(item2);
                menu.add(item3);
                menu.add(item4);
                menuBar.add(menu);
                
                JPanel pNord = new JPanel(new BorderLayout());
                pNord.setBackground(new Color(100,50,50,100));
                pNord.add(menuBar, BorderLayout.WEST);
                pNord.add(pJoueur, BorderLayout.CENTER);
            
            //Declaration et initialisation du panneau central
                //Declaration et initialisation du panneau central Gauche (pièces joueur 1)
                JPanel pPieceJ1 = new JPanel();
                pPieceJ1.setBackground(new Color(100,50,100,100));
                pPieceJ1.setSize(new Dimension(50,300));
                JPanel pArrayEtiquetteJ1 = new JPanel (new GridLayout(5,1));
                pArrayEtiquetteJ1.setSize(new Dimension(50,300));
                JPanel pArrayBoutonJ1 = new JPanel(new GridLayout(5,1));
                pArrayBoutonJ1.setSize(new Dimension(50,300));
                
                for (int i = 0; i < 5; i++)
                {
                    pArrayEtiquetteJ1.add(eArrayJ1[i]);
                }
                for(int i=0; i<5; i++){
                    pArrayBoutonJ1.add(bArrayJ1[i]);
                }
                
                JLayeredPane layeredPaneJ1 = new JLayeredPane();
                layeredPaneJ1.setLayout(new PlateauLayout());
                layeredPaneJ1.setSize(new Dimension(50, 300));
                layeredPaneJ1.add(pArrayEtiquetteJ1, JLayeredPane.DEFAULT_LAYER);  //Ajoute le panneau d'etiquette à la couche principale
                layeredPaneJ1.add(pArrayBoutonJ1, 1);
                pPieceJ1.add(layeredPaneJ1);
                
                
                
                //Declaration et initialisation du panneau central droit (pièces joueur 2)
                JPanel pPieceJ2 = new JPanel();
                pPieceJ2.setBackground(new Color(100,200,100,100));
                pPieceJ2.setSize(new Dimension(50,300));
                JPanel pArrayEtiquetteJ2 = new JPanel (new GridLayout(5,1));
                pArrayEtiquetteJ2.setSize(new Dimension(50,300));
                JPanel pArrayBoutonJ2 = new JPanel(new GridLayout(5,1));
                pArrayBoutonJ2.setPreferredSize(new Dimension(50,300));
                
                for (int i = 0; i < 5; i++)
                {
                    pArrayEtiquetteJ2.add(eArrayJ2[i]);
                }
                for(int i=0; i<5; i++){
                    pArrayBoutonJ2.add(bArrayJ2[i]);
                }
                
                JLayeredPane layeredPaneJ2 = new JLayeredPane();
                layeredPaneJ2.setLayout(new PlateauLayout());
                layeredPaneJ2.setSize(new Dimension(50, 300));
                layeredPaneJ2.add(pArrayEtiquetteJ2, JLayeredPane.DEFAULT_LAYER);  //Ajoute le panneau d'etiquette à la couche principale
                layeredPaneJ2.add(pArrayBoutonJ2, 1);
                pPieceJ2.add(layeredPaneJ2);
                
                //Declaration et initialisation du panneau central avec plusieurs couches (plateau) + ajout grille d'etiquettes et de boutons
                
                JPanel pGrilleEtiquette = new JPanel(new GridLayout(5,5));      //Panneau contenant les etiquettes
                pGrilleEtiquette.setSize(new Dimension(400,400));
                JPanel pGrilleBouton = new JPanel(new GridLayout(5,5));         //Panneau contenant les boutons
                pGrilleBouton.setSize(new Dimension(400,400));
                
                for(int i=0; i<5; i++){
                        for(int j=0; j<5; j++){
                            pGrilleEtiquette.add(eGrille[i][j]);
                        }
                }
                for(int i=0; i<5; i++){
                        for(int j=0; j<5; j++){
                            pGrilleBouton.add(bGrille[i][j]);
                        }
                }
                
                JLayeredPane layeredPane = new JLayeredPane();
                layeredPane.setLayout(new PlateauLayout());
                layeredPane.setSize(new Dimension(400, 400));
                layeredPane.add(pGrilleEtiquette, JLayeredPane.DEFAULT_LAYER);  //Ajoute le panneau d'etiquette à la couche principale
                layeredPane.add(pGrilleBouton, 1);
                //Ajoute le panneau de boutons à la couche superieure
                
                //Declaration et initialisation du panneau central (Pièces + plateau) + attribution panneau inferieur
                JPanel pCentral = new JPanel(new BorderLayout());
                pCentral.setSize(new Dimension(400, 400));
                pCentral.add(layeredPane, BorderLayout.CENTER);
                pCentral.add(pPieceJ1, BorderLayout.WEST);
                pCentral.add(pPieceJ2, BorderLayout.EAST);
                
            
            //Declaration, initialisation du panneau inferieur + attribution boutons
                JPanel pSud = new JPanel();
                pSud.setBackground(new Color(25,43,57,100));
                pSud.add(bEntrerPiece);
                pSud.add(bFinTour);
                
            //Declaration, initialisation du conteneur principale et attribution des panneaux
                JPanel conteneurPrincipal = new JPanel(new BorderLayout());
                conteneurPrincipal.add(pNord, BorderLayout.NORTH);
                conteneurPrincipal.add(pCentral, BorderLayout.CENTER);
                conteneurPrincipal.add(pSud, BorderLayout.SOUTH);
                conteneurPrincipal.addMouseWheelListener(new MyMouseWheelListener(this));
                this.add(conteneurPrincipal);       
        
    }
    
    public void deplacementPiece(Coordonnees c){
        if(isPieceSelectionee){
            this.siam.deplacerToutesPieces(c, siam.getDirection(c,pieceSelectionnee));
            bGrille[c.h()][c.v()] = bGrille[pieceSelectionnee.h()][pieceSelectionnee.v()];
            this.isPieceSelectionee = false;
            this.isPieceDeplacee = true;
        }else{
            if(!isPieceDeplacee){
                this.pieceSelectionnee = c;
                this.isPieceSelectionee = true;
            }
        }
    }
    
    public void actionMenu(int i){
        switch (i)
        {
            case 1 : 
                System.out.println("Nouvelle Partie");
                break;
            case 2 : 
                System.out.println("Paramètres");
                break;
            case 3:
                System.out.println("Aide");
                break;
            case 4:
                System.out.println("Quitter");
                break;
            default:   
        }
     }
     
    public void pivoterPiece(int i){
        siam.pivoter(pieceSelectionnee, i);
    }
     
    public void finTour(){
         isPieceDeplacee = false;
         isPieceSelectionee = false;
    }
    
    public void miseAJour() {
    	
    	for(int i=0; i<5; i++ ) {
    		for(int j=0; j<5; j++ ) {
        		eGrille[i][j].setIcon(new ImageIcon(siam.getImagePlateau(i,j)));
        	}
    		eArrayJ1[i].setIcon(new ImageIcon(siam.getImageReserve(i, 1)));
    		eArrayJ2[i].setIcon(new ImageIcon(siam.getImageReserve(i, 2)));
    	}
    	
    	eNomJoueur.setText(siam.getNomJoueurCourant());
    	this.repaint();
    }
    
    
        public class PlateauLayout implements LayoutManager {

        private int minWidth; 
        private int minHeight;
        private int preferredWidth; 
        private int preferredHeight;
        private boolean sizeUnknown;
     
        public PlateauLayout() {
            minWidth = 0;
            minHeight = 0;
            preferredWidth = 0;
            preferredHeight = 0;
            sizeUnknown = true;
        }
     
        /* Required by LayoutManager. */
        public void addLayoutComponent(String name, Component comp) {
        }
     
        /* Required by LayoutManager. */
        public void removeLayoutComponent(Component comp) {
        }
     
        private void setSizes(Container parent) {                   //Modifie la taille du layout
            int nComps = parent.getComponentCount();                //nombre d'élement dans le conteneur parent 
            Dimension d = null;
     
            //Reset preferred/minimum width and height.
            preferredWidth = 0;
            preferredHeight = 0;
            minWidth = 0;
            minHeight = 0;
     
            for (int i = 0; i < nComps; i++) {                      //Pour chaque composant i du parent
                Component c = parent.getComponent(i);               //c devient le composant i
                if (c.isVisible()) {                                // S'il est visible
                    d = c.getPreferredSize();                           //la dimension d est égale à la dimension préférée de c
     
                    preferredWidth = Math.max(d.width,preferredWidth);      //la largeur préférée est le max de l'ancienne largeur préférée et de la largeur du composant
                    preferredHeight = Math.max(d.height,preferredHeight);   //la hauteur préférée est le max de l'ancienne hauteur préférée et de la largeur du composant
     
                    minWidth = Math.max(c.getMinimumSize().width,minWidth);     //la largeur minimale est égale au max de la précédente largeur minimale et de celle du composant   
                    minHeight = Math.max(c.getMinimumSize().height,minHeight);      //la hauteur minimale est égale au max de la précédente hauteur minimale et de celle du composant
                }
            }
        }
     
     
        /* Required by LayoutManager. */
        public Dimension preferredLayoutSize(Container parent) {    //Retourne les dimensions préférée 
            Dimension dim = new Dimension(0, 0);
     
            setSizes(parent);
     
            //Always add the container's insets!
            Insets insets = parent.getInsets();
            dim.width = preferredWidth + insets.left + insets.right;
            dim.height = preferredHeight + insets.top + insets.bottom;
     
            sizeUnknown = false;
     
            return dim;
        }
     
        /* Required by LayoutManager. */
        public Dimension minimumLayoutSize(Container parent) {  //Retourne les dimensions minimales 
            Dimension dim = new Dimension(0, 0);
     
            //Always add the container's insets!
            Insets insets = parent.getInsets();
            dim.width = minWidth + insets.left + insets.right;
            dim.height = minHeight + insets.top + insets.bottom;
     
            sizeUnknown = false;
     
            return dim;
        }
     
        public void layoutContainer(Container parent) {
            
            Insets insets = parent.getInsets();                                 //espace q'un conteneur doit laisser sur chacun de ses bords
            int maxWidth = parent.getWidth()- (insets.left + insets.right);     //largeur max = largeur conteneur - espaces nécessaires sur les bords du conteneur
            int maxHeight = parent.getHeight() - (insets.top + insets.bottom);  //hauteur max = largeur conteneur - espaces nécessaires sur les bords du conteneur
            int nComps = parent.getComponentCount();                            //Gets the number of components in this pane
            
            int x = 0;
            int y = 0;
            
            if (sizeUnknown) {                                              //Met à jour la taille si nécessaire
                setSizes(parent);
            }
     
            for (int i = 0 ; i < nComps ; i++) {                            //Pour chaque composant
                Component c = parent.getComponent(i);                       //c est le composant i
                if (c.isVisible()) {                                        //si c est visible 
                    
                    c.setBounds(x, y, maxWidth, maxHeight);                 // Set the component's size and position.
     
                }
            }
        }
     
        public String toString() {
            return getClass().getName();
        }
    }
}
