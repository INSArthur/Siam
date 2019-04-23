import javax.swing.*;
import java.awt.*;

public class FenetrePrincipale extends JFrame{
    
    //Declaration Jeu
    private Jeu siam;
    
    //Dclaration bouton
    private JButton bFinTour;           //Bouton pour indiquer la fin d'un tour
    private JButton bEntrerReserve;
    private EcouteurEntreePieceReserve entrerPiece;
    
    //Declaration de la barre de menu
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem item1;
    private JMenuItem item2;
    private JMenuItem item3;
    private JMenuItem item4;
    
    //Declaration reserve grille et etiquette J1
    private JButton[] bArrayJ1;
    
    //Declaration reserve grille et etiquette J2
    private JButton[] bArrayJ2;
    
    //Declaration Etiquette joueur
    private JLabel eJoueur;             //Etiquette avec l'inscription joueur
    private JLabel eNomJoueur;          //Etiquette indiquant le nom du joueur dont c'est le tour
    
    //Declaration du tableau d'etiquettes + tableau de boutons
    private JButton[][] bGrille;    //Grille de boutons permettant d'inter-agir avec le plateau de jeu plateau de jeu
    
    //Declaration variables d'etapes et de selection de piece 
    private boolean isPieceSelectionee;
    //private boolean isPieceDeplacee;
    private boolean isPieceSelectioneeReserve;
    private Coordonnees pieceSelectionnee;
    private Piece pieceSelectionneeReserve;
    
    public FenetrePrincipale(Jeu siam){
        super("Jeu du Siam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setVisible(true);
        this.setLocationRelativeTo(null);
        
        this.siam = siam;
        
        //Initialisation boutons
        bFinTour = new JButton("Fin du tour");
        bFinTour.addActionListener(new EcouteurFinTour(this));
        bEntrerReserve = new JButton("Placer la pièce dans la réserve");
        bEntrerReserve.setEnabled(false);
        entrerPiece = new EcouteurEntreePieceReserve(this);
        bEntrerReserve.addActionListener(entrerPiece);
        
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
        
        //Initialisation tableau d'etiquette + tableau de boutons
        bGrille = new JButton[5][5];
        Icon vide = new ImageIcon("vide.png");
        Icon montagne = new ImageIcon("f_0_0.png");
        
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                bGrille[i][j] = new JButton();
                bGrille[i][j].addActionListener(new EcouteurPiece(this,new Coordonnees(i+1,j+1)));
                bGrille[i][j].setSize(new Dimension(50,50));
                bGrille[i][j].setOpaque(false);
                bGrille[i][j].setContentAreaFilled(false); // On met a false pour empecher le composant de peindre l'interieur du JButton.
                bGrille[i][j].setBorderPainted(false); // De meme, on ne veut pas afficher les bordures.
                bGrille[i][j].setFocusPainted(false); // On n'affiche pas l'effet de focus.
                bGrille[i][j].setIcon(vide);
                
            }
        }
        
        
        //Initialisation bouton et etiquette de la reserve 1
        Icon flecheVerte = new ImageIcon("f_1_1.png");
        
        bArrayJ1 = new JButton[5];
        
        for(int i=0; i<5; i++){
            bArrayJ1[i] = new JButton();
            bArrayJ1[i].setSize(new Dimension(50,50));
            bArrayJ1[i].setOpaque(false);
            bArrayJ1[i].setContentAreaFilled(false);
            bArrayJ1[i].setBorderPainted(false);
        }
        
        //Initialisation bouton et etiquette de la reserve 2
        Icon flecheJaune = new ImageIcon("f_2_1.png");
        
        bArrayJ2 = new JButton[5];
        
        for(int i=0; i<5; i++){
            bArrayJ2[i] = new JButton();
            bArrayJ2[i].setSize(new Dimension(50,50));
            bArrayJ2[i].setOpaque(false);
            bArrayJ2[i].setContentAreaFilled(false);
            bArrayJ2[i].setBorderPainted(false);
        }
        
        //Initialisation variables de selection de piece et d'etapes
        isPieceSelectionee = false;
        isPieceSelectioneeReserve = false;
        pieceSelectionneeReserve = null;
        pieceSelectionnee = new Coordonnees(0,0);

        
        //Declaration Panneaux et remplissage
            //Declaration et initialisation du panneau superieur (Menu et joueur) + attribution boutons
                
                
                //Declaration et initialisation du panneau superieur centre (joueur)
                JPanel pJoueur = new JPanel();
                pJoueur.add(eJoueur);
                pJoueur.add(eNomJoueur);
                
                //Ajout de la barre de menu au panneau superieur
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
                //Declaration et initialisation du panneau central Gauche (pieces joueur 1)
                JPanel pPieceJ1 = new JPanel(new GridLayout(5,1));
                pPieceJ1.setBackground(new Color(100,50,100,100));
                pPieceJ1.setSize(new Dimension(20,100));

                
                for(int i=0; i<5; i++){
                    pPieceJ1.add(bArrayJ1[i]);
                    bArrayJ1[i].addActionListener(new EcouteurSortiePieceReserve(this, 1));
                }
                
                
                
                //Declaration et initialisation du panneau central droit (pieces joueur 2)
                JPanel pPieceJ2 = new JPanel(new GridLayout(5,1));
                pPieceJ2.setBackground(new Color(100,200,100,100));
                pPieceJ2.setSize(new Dimension(20,100));
                
                for(int i=0; i<5; i++){
                    pPieceJ2.add(bArrayJ2[i]);
                    bArrayJ2[i].addActionListener(new EcouteurSortiePieceReserve(this, 2));
                }
               
                
                //Declaration et initialisation du panneau central avec plusieurs couches (plateau) + ajout grille d'etiquettes et de boutons
                
                JPanel pGrilleBouton = new JPanel(new GridLayout(5,5));         //Panneau contenant les boutons
                pGrilleBouton.setSize(new Dimension(400,400));
                
                for(int i=0; i<5; i++){
                    for(int j=0; j<5; j++){
                        pGrilleBouton.add(bGrille[i][j]);
                    }
                }
                
                JPanel panneauCentral = new JPanel();
                panneauCentral.setLayout(new PlateauLayout());
                panneauCentral.setSize(new Dimension(400, 400));
                panneauCentral.add(pGrilleBouton);
                //Ajoute le panneau de boutons a la couche superieure
                
                //Declaration et initialisation du panneau central (Pieces + plateau) + attribution panneau inferieur
                JPanel pCentral = new JPanel(new BorderLayout());
                pCentral.setSize(new Dimension(400, 400));
                pCentral.add(panneauCentral, BorderLayout.CENTER);
                pCentral.add(pPieceJ1, BorderLayout.WEST);
                pCentral.add(pPieceJ2, BorderLayout.EAST);
                
            
            //Declaration, initialisation du panneau inferieur + attribution boutons
                JPanel pSud = new JPanel();
                pSud.setBackground(new Color(25,43,57,100));
                pSud.add(bFinTour);
                pSud.add(bEntrerReserve);
                
            //Declaration, initialisation du conteneur principale et attribution des panneaux
                JPanel conteneurPrincipal = new JPanel(new BorderLayout());
                conteneurPrincipal.add(pNord, BorderLayout.NORTH);
                conteneurPrincipal.add(pCentral, BorderLayout.CENTER);
                conteneurPrincipal.add(pSud, BorderLayout.SOUTH);
                conteneurPrincipal.addMouseWheelListener(new MyMouseWheelListener(this));
                this.add(conteneurPrincipal);       
        
        this.miseAJour();
        //Prepare le premier tour :
        for (int i = 0; i < 5; i++)
        {
            bArrayJ2[i].setEnabled(false);
        }
        
    }
    
    
    public void deplacementPiece(Coordonnees c){
        if (isPieceSelectioneeReserve)
        {
            siam.placerPiece(pieceSelectionneeReserve.getType(),c);
            siam.isPieceDeplacee = true;
            this.pieceSelectionnee = c;
            this.isPieceSelectioneeReserve = false;
        }else if(isPieceSelectionee){
            this.siam.deplacerToutesPieces(c, siam.getDirection(c,pieceSelectionnee));
            siam.isPieceDeplacee = true;
            this.isPieceSelectionee = false;
        }else if(!siam.isPieceDeplacee){
            this.pieceSelectionnee = c;
            this.isPieceSelectionee = true;
        }
    }
    
    public void actionMenu(int i){
        switch (i)
        {
            case 1 : 
                System.out.println("Nouvelle Partie");
                break;
            case 2 : 
                System.out.println("Parametres");
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
    
    public void afficherBoutonSortiePlateau(Coordonnees c){
        if (isPieceSelectionee)
        {
            entrerPiece.setEcouteur(siam.getJoueurCourant()+1,c);
            bEntrerReserve.setEnabled(true);
        }
    }
     
    public void finTour(){
        siam.isPieceDeplacee = false;
        isPieceSelectionee = false;
        isPieceSelectioneeReserve = false;
        siam.changerJoueurCourant();
        this.changerLesBoutons();
    }
    
    public void changerLesBoutons(){
        int joueur = siam.getJoueurCourant();
        for (int i = 0; i < 5; i++)
        {
            if (joueur == 0)
            {
                bArrayJ1[i].setEnabled(true);
                bArrayJ1[i].setOpaque(false);
                bArrayJ1[i].setContentAreaFilled(false);
                bArrayJ1[i].setBorderPainted(false);
                bArrayJ2[i].setEnabled(false);
            }else{
                bArrayJ2[i].setEnabled(true);
                bArrayJ2[i].setOpaque(false);
                bArrayJ2[i].setContentAreaFilled(false);
                bArrayJ2[i].setBorderPainted(false);
                bArrayJ1[i].setEnabled(false);
            }
        }
    }
        
    
    public void sortirPieceReserve(int i){
        if (!isPieceSelectioneeReserve)
        {
            Piece p = siam.sortirPieceReserve(i);
            pieceSelectionneeReserve= new Piece(i);
            isPieceSelectioneeReserve = true;
        }
    }
    
    public void entrerPieceReserve(int i, Coordonnees c){
        siam.entrerPieceReserve(i);
        siam.supprimerPiece(c);
        siam.isPieceDeplacee = true;
        bEntrerReserve.setEnabled(false);
    }
    
    public void miseAJour() {
        for(int i=0; i<5; i++ ) {
            for(int j=0; j<5; j++ ) {
                bGrille[i][j].setIcon(new ImageIcon(siam.getImagePlateau(i+1,j+1)));
                bArrayJ1[i].setIcon(new ImageIcon(siam.getImageReserve(i, 1)));
                bArrayJ2[i].setIcon(new ImageIcon(siam.getImageReserve(i, 2)));
            }
        }
        
       
        eNomJoueur.setText(siam.getNomJoueurCourant());
        System.out.println(siam.toString()+"\n");
        this.repaint();
        this.validate();
        
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
            int nComps = parent.getComponentCount();                //nombre d'element dans le conteneur parent 
            Dimension d = null;
     
            //Reset preferred/minimum width and height.
            preferredWidth = 0;
            preferredHeight = 0;
            minWidth = 0;
            minHeight = 0;
     
            for (int i = 0; i < nComps; i++) {                      //Pour chaque composant i du parent
                Component c = parent.getComponent(i);               //c devient le composant i
                if (c.isVisible()) {                                // S'il est visible
                    d = c.getPreferredSize();                           //la dimension d est egale a la dimension preferee de c
     
                    preferredWidth = Math.max(d.width,preferredWidth);      //la largeur preferee est le max de l'ancienne largeur preferee et de la largeur du composant
                    preferredHeight = Math.max(d.height,preferredHeight);   //la hauteur preferee est le max de l'ancienne hauteur preferee et de la largeur du composant
     
                    minWidth = Math.max(c.getMinimumSize().width,minWidth);     //la largeur minimale est egale au max de la precedente largeur minimale et de celle du composant   
                    minHeight = Math.max(c.getMinimumSize().height,minHeight);      //la hauteur minimale est egale au max de la precedente hauteur minimale et de celle du composant
                }
            }
        }
     
     
        /* Required by LayoutManager. */
        public Dimension preferredLayoutSize(Container parent) {    //Retourne les dimensions preferee 
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
            int maxWidth = parent.getWidth()- (insets.left + insets.right);     //largeur max = largeur conteneur - espaces necessaires sur les bords du conteneur
            int maxHeight = parent.getHeight() - (insets.top + insets.bottom);  //hauteur max = largeur conteneur - espaces necessaires sur les bords du conteneur
            int nComps = parent.getComponentCount();                            //Gets the number of components in this pane
            
            int x = 0;
            int y = 0;
            
            if (sizeUnknown) {                                              //Met a jour la taille si necessaire
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
