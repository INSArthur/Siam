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
    private JMenuItem item5;
    
    //Declaration reserve grille et etiquette J1
    private JButton[] bArrayJ1;
    
    //Declaration reserve grille et etiquette J2
    private JButton[] bArrayJ2;
    
    //Declaration Etiquette joueur
    private JLabel eJoueur;             //Etiquette avec l'inscription joueur
    private JLabel eNomJoueur;          //Etiquette indiquant le nom du joueur dont c'est le tour
    private JLabel eMessage;
    
    //Declaration du tableau de boutons et du paneau les contenant
    private JButton[][] bGrille;    //Grille de boutons permettant d'inter-agir avec le plateau de jeu plateau de jeu
    private JPanel pGrilleBouton; 	//Panneau contenant la grille de boutons
    
    //Declaration variables d'etapes et de selection de piece et de bouton d'angle
    private boolean isCaseSelectionneePlateau;
    private boolean isPieceDeplacee;
    private boolean isCaseSelectionneeReserve;
    private boolean isBoutonAngle;
    private JButton boutonAnglePrecedent;
    private Coordonnees caseSelectionneePlateau;
    private int caseSelectionneeReserve;
    private char theme = 'f';
    
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
        bEntrerReserve = new JButton("Placer la piece dans la reserve");
        bEntrerReserve.setEnabled(false);
        entrerPiece = new EcouteurEntreePieceReserve(this);
        bEntrerReserve.addActionListener(entrerPiece);
        
        //Initialisation barre de menu
        menu = new JMenu("Menu");
        menuBar = new JMenuBar();
        item1 = new JMenuItem("Nouvelle partie");
        item2 = new JMenuItem("Theme");
        item3 = new JMenuItem("Aide");
        item4 = new JMenuItem("Quitter");
	item5 = new JMenuItem("Retour au début du tour");
        item1.addActionListener(new EcouteurMenu(this,1));
        item2.addActionListener(new EcouteurMenu(this,2));
        item3.addActionListener(new EcouteurMenu(this,3));
        item4.addActionListener(new EcouteurMenu(this,4));
	item5.addActionListener(new EcouteurMenu(this,5));
        
        //Initialisation etiquette joueur
        eJoueur = new JLabel("joueur : ");
        eNomJoueur = new JLabel();
        eNomJoueur.setText(siam.getNomJoueurCourant());
        eMessage = new JLabel();
        
        //Initialisation tableau d'etiquette + tableau de boutons
        bGrille = new JButton[5][5];
        Icon vide = new ImageIcon("vide.png");
        
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                bGrille[i][j] = new JButton();
                bGrille[i][j].addActionListener(new EcouteurCase(this,new Coordonnees(i+1,j+1)));
                bGrille[i][j].addKeyListener(new EcouteurClavier(this));
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
        
        //Initialisation variables de selection de piece, de bouton d'angle et d'etapes
        isCaseSelectionneePlateau = false;
        isCaseSelectionneeReserve = false;
        isPieceDeplacee = false;
        isBoutonAngle = false;
        boutonAnglePrecedent = null;
        caseSelectionneeReserve = 0;
        caseSelectionneePlateau = null;

        
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
	        menu.add(item5);
                menuBar.add(menu);
                
                JPanel pNord = new JPanel(new BorderLayout());
                pNord.setBackground(new Color(100,50,50,100));
                pNord.add(menuBar, BorderLayout.WEST);
                pNord.add(pJoueur, BorderLayout.CENTER);
            
            //Declaration et initialisation du panneau central
                //Declaration et initialisation du panneau central Gauche (pieces joueur 1)
                JPanel pPieceJ1 = new JPanel(new GridLayout(5,1));
                pPieceJ1.setBackground(new Color(100,50,100,255));
                pPieceJ1.setSize(new Dimension(20,100));

                
                for(int i=0; i<5; i++){
                    pPieceJ1.add(bArrayJ1[i]);
                    bArrayJ1[i].addActionListener(new EcouteurCase(this, i));
                }
                
                
                
                //Declaration et initialisation du panneau central droit (pieces joueur 2)
                JPanel pPieceJ2 = new JPanel(new GridLayout(5,1));
                pPieceJ2.setBackground(new Color(100,200,100,255));
                pPieceJ2.setSize(new Dimension(20,100));
                
                for(int i=0; i<5; i++){
                    pPieceJ2.add(bArrayJ2[i]);
                    bArrayJ2[i].addActionListener(new EcouteurCase(this, i));
                }
               
                
                //Declaration et initialisation du panneau central avec plusieurs couches (plateau) + ajout grille d'etiquettes et de boutons
                
                pGrilleBouton = new JPanel(new GridLayout(5,5));         //Panneau contenant les boutons
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
                
                
                //Declaration et initialisation du panneau central (Pieces + plateau) + attribution panneau inferieur
                JPanel pCentral = new JPanel(new BorderLayout());
                pCentral.setSize(new Dimension(400, 400));
                pCentral.add(panneauCentral, BorderLayout.CENTER);
                pCentral.add(pPieceJ1, BorderLayout.WEST);
                pCentral.add(pPieceJ2, BorderLayout.EAST);
                
            
            //Declaration, initialisation du panneau inferieur + attribution boutons
                JPanel pSud = new JPanel();
                pSud.setBackground(new Color(25,43,57));
                pSud.add(bFinTour);
                pSud.add(bEntrerReserve);
                pSud.add(eMessage);
                
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
    
    public void actionCasePlateau(Coordonnees c, boolean bordure, int angle){ //Action a realisees si la case de coordonnee c est dans le plateau
        boolean aPivote = false;
        eMessage.setText(""); validate(); repaint();
        
        if(!siam.pieceAPivote(caseSelectionneePlateau)){
	        if(!isPieceDeplacee) {
	            //~ System.out.println("!isPieceDeplacee");
	            if(isCaseSelectionneeReserve) { //ok                     //Si une case de la reserve est deja selectionnee
	                System.out.println("isCaseSelectionneeReserve");
	               if(bordure) { //ok
	            	   System.out.println(siam.estVide(c));
	            	   if(siam.estVide(c)) {
	            		   
		            	   if(siam.deplacerReserveVersPlateau(c, bordure,0)) {       //Deplacement reserve vers plateau et MAJ si deplacee
		  	                    //~ System.out.println("deplacerReserveVersPlateau effectue");
		  	                    caseSelectionneePlateau = c;                        //L'ancienne case selectionnee (s'il y en a une) est remplacee par la nouvelle pour pouvoir la pivoter
		  	                    isPieceDeplacee = true;                             //Permet d'empecher toute autre action que le pivotement
		  	                    isCaseSelectionneePlateau = true;
		  	                    isCaseSelectionneeReserve = true;
					    bEntrerReserve.setEnabled(false);
		  	                    miseAJour();
		  	               }
	            	   }else{ //ok
						
	                       	System.out.println(angle);
	                       	
	                       	if(angle!=0) {			//Si la case est dans un coin du plateau
	                       		affichageEntreePieceAngle(c,angle);
	                       		System.out.println("angle");
	                       		caseSelectionneePlateau = c;                        //L'ancienne case selectionnee (s'il y en a une) est remplacee par la nouvelle pour pouvoir la pivoter
	                       		caseSelectionneeReserve = 0;
	       	                    isPieceDeplacee = true;
				    bEntrerReserve.setEnabled(false);
	       	                    isCaseSelectionneePlateau = true;
	       	                    isCaseSelectionneeReserve = false;
	                       	}else {
	                       		if(siam.deplacerReserveVersPlateau(c, bordure,0)) {       //Deplacement reserve vers plateau et MAJ si deplacee
	                       	
	       	                    //~ System.out.println("deplacerReserveVersPlateau effectue");
	       	                    caseSelectionneePlateau = c;                        //L'ancienne case selectionnee (s'il y en a une) est remplacee par la nouvelle pour pouvoir la pivoter
	       	                    caseSelectionneeReserve = 0;
	       	                    isPieceDeplacee = true;                             //Permet d'empecher toute autre action que le pivotement
	       	                    isCaseSelectionneePlateau = true;
	       	                    isCaseSelectionneeReserve = false;
	       	                    miseAJour();
	                       		}
	       	                } 
	            		 
						}
	                }else{
	                   eMessage.setText("Une piece de la reserve se place en bordure de plateau.");
	                   isCaseSelectionneeReserve = false; 
	                }
	                
	            }else{ 
	                if(isCaseSelectionneePlateau) {             //Si une case de la reserve est deja selectionnee
	                    //~ System.out.println("isCaseSelectionneePlateau");
	                    //~ System.out.println(caseSelectionneePlateau.h()+" "+caseSelectionneePlateau.v());
	                    //~ System.out.println(c.h()+" "+c.v());
	                    //~ System.out.println("isCaseSelectionneePlateau");
	                    if(siam.deplacerPlateauVersPlateau(caseSelectionneePlateau, c)) {       //Deplacement plateau vers reserve et MAJ si deplacee
	                        //~ System.out.println("deplacerPlateauVersPlateau effectue");
	                        isPieceDeplacee = true;                     //Permet d'empecher toute autre action que le pivotement
	                    }else{
	                        eMessage.setText("Mouvement impossible.");
	                        caseSelectionneePlateau = null;
	                        isCaseSelectionneePlateau = false;                                        //Sinon l'ancienne case selectionnee est remplacee par la nouvelle
	                        //~ System.out.println("case selectionnee");
	                        //~ bEntrerReserve.setEnabled(true);
	                    }
	                }else{
			    if(bordure && siam.appartientAuJoueur(c)==1){
	                        bEntrerReserve.setEnabled(true);
			    }
	                }
	                if(siam.appartientAuJoueur(c)==1){
	                    caseSelectionneePlateau = c;                //L'ancienne case selectionnee est remplacee par la nouvelle pour pouvoir la pivoter 
	                    caseSelectionneeReserve = 0;
	                    isCaseSelectionneePlateau = true;
	                    isCaseSelectionneeReserve = false;
	                    miseAJour();
	                }else if(siam.appartientAuJoueur(c)==2){
	                    eMessage.setText("Ce n'est pas l'une de vos pieces");
	                }
	            }
	        }else{
	            eMessage.setText("Vous ne pouvez deplacer qu'une piece par tour.");
	        }
	        }else{ 
	        	eMessage.setText("Vous ne pouvez pas deplacer une piece qui a pivote"); 
	        }
        
        
        miseAJour();
    }
    
    
    public void actionCaseReserve(int i) {
        if(!isPieceDeplacee) {
            //~ System.out.println("!isPieceDeplacee");
            //~ System.out.println("!isCaseSelectionneeReserve");
            caseSelectionneePlateau = null;
            caseSelectionneeReserve = i;
            isCaseSelectionneeReserve = true;
            isCaseSelectionneePlateau = false ;
            miseAJour();
        }
        miseAJour();
    }

    public void actionMenu(int i){                          //Realise les action associees a chaque bouton du menu
        switch (i)
        {
            case 1 : 
                System.out.println("Nouvelle Partie");
                new FenetreMenu ();
                this.dispose();
                break;
            case 2 : 
                System.out.println("Parametres");
                break;
            case 3:
                System.out.println("Aide");
                break;
            case 4:
                System.out.println("Quitter");
                this.dispose();
                break;
	    case 5:
                this.recupererSauvegarde();
		break;
            default:   
        }
     }

    public void pivoterPiece(int i){                            //Pivote la piece selectionnee dans le plateau si possible
        //~ System.out.println("pivoter"+i);
        //~ System.out.println(isCaseSelectionneePlateau);
        if(isCaseSelectionneePlateau) {
            siam.pivoter(caseSelectionneePlateau, i);
            //~ System.out.println(" a pivote"+i);
            miseAJour();
        }
    }
    
	public void affichageEntreePieceAngle(Coordonnees c,int angle) { //Fait apparaitre ou disparaitre les boutons necessaire pour savoir dans quel direction rentre la piece dans un angle du plateau, angles numerote de 1 a 4 dans le sens des aiguilles d une montre en partant du nord ouest
		if(isBoutonAngle) {
			System.out.println(isBoutonAngle);
			changementGrilleBouton(c,boutonAnglePrecedent);	
			isBoutonAngle = false;
			
		}else{
			System.out.println("!isBoutonAngle");
			JPanel panneauAngle = new JPanel(new BorderLayout());
			JButton boutonAngleColonne = new JButton();				//Bouton qui est dans la direction de la colonne
			JButton boutonAngleLigne = new JButton();				//Bouton qui est dans la direction de la ligne
			
			EcouteurAngle eColonne = new EcouteurAngle(this);
			EcouteurAngle eLigne = new EcouteurAngle(this);
			boutonAngleColonne.addActionListener(eColonne);
			boutonAngleLigne.addActionListener(eLigne);
			
			boutonAnglePrecedent = bGrille[c.h()-1][c.v()-1];
			
			switch (angle) {
			case 1 :
				
				eLigne.setDirection(2);
				eColonne.setDirection(3);
				panneauAngle.add(boutonAngleColonne, BorderLayout.NORTH);
				panneauAngle.add(boutonAngleLigne, BorderLayout.WEST);
				panneauAngle.add(boutonAnglePrecedent, BorderLayout.CENTER);
				changementGrilleBouton(new Coordonnees(0,0),panneauAngle);
				break;
			case 2:
				eLigne.setDirection(4);
				eColonne.setDirection(3);
				panneauAngle.add(boutonAngleColonne, BorderLayout.NORTH);
				panneauAngle.add(boutonAngleLigne, BorderLayout.EAST);
				panneauAngle.add(boutonAnglePrecedent, BorderLayout.CENTER);
				changementGrilleBouton(new Coordonnees(0,4),panneauAngle);
				break;
			case 3:
				eLigne.setDirection(4);
				eColonne.setDirection(1);
				panneauAngle.add(boutonAngleColonne, BorderLayout.SOUTH);
				panneauAngle.add(boutonAngleLigne, BorderLayout.EAST);
				panneauAngle.add(boutonAnglePrecedent, BorderLayout.CENTER);
				changementGrilleBouton(new Coordonnees(4,4),panneauAngle);
				break;
			case 4:
				eLigne.setDirection(2);
				eColonne.setDirection(1);
				panneauAngle.add(boutonAngleColonne, BorderLayout.SOUTH);
				panneauAngle.add(boutonAngleLigne, BorderLayout.WEST);
				panneauAngle.add(boutonAnglePrecedent, BorderLayout.CENTER);
				changementGrilleBouton(new Coordonnees(4,0),panneauAngle);
				break;
			default :
				break;
			}
			isBoutonAngle = true;
		}
	}
	
	public void changementGrilleBouton(Coordonnees c, JComponent composant) { //Change le JComponent de coordonnees i,j da,s le plateau par composant
		System.out.println(composant);
		System.out.println(c.h());
		System.out.println(c.v());
		pGrilleBouton.removeAll();
		for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
            	if(i==(c.h()) && j==(c.v())) {
                	pGrilleBouton.add(composant);
                	
            	}else {
            		pGrilleBouton.add(bGrille[i][j]);
            	}
            }
        }
		System.out.println(pGrilleBouton);
		this.repaint();
        this.validate();
        miseAJour();
	}
	
	public void selectionAngle(int direction) {		//Utilise par ECouteurAngle, fait rentrer une piece dan un angle suivant la direction selectionnee
		System.out.println("selectionAngle");
		boolean bordure = false; 
		Coordonnees c = caseSelectionneePlateau;
		if (c.h() == 1 || c.h() == 5 || c.v() == 1 || c.v() == 5){
	            bordure = true;
	    }
		changementGrilleBouton(c,boutonAnglePrecedent);
		System.out.println(siam.deplacerReserveVersPlateau(c,bordure,direction));
		miseAJour();
		System.out.println(bordure);
		
	}

    public void finTour(){      //Reinitialise les variable d'etapes, change le joueur courant et donne l'accessibilite a sa reserve exclusivement
        //~ System.out.println("finTour()");
        if(siam.finJeu()){
            String gagnant = siam.getNomJoueurCourant();
            siam.changerJoueurCourant();
            String perdant = siam.getNomJoueurCourant();
            siam.changerJoueurCourant(); // utile ?
            new FenetreVictoire(gagnant, perdant, this);
        }else{
            isCaseSelectionneePlateau = false;
            isCaseSelectionneeReserve = false;
            isPieceDeplacee = false;
            caseSelectionneePlateau = null;
            caseSelectionneeReserve = 0;
            siam.finTour();
            siam.changerJoueurCourant();
            bEntrerReserve.setEnabled(false);
            this.changerLesBoutons();
	    siam.faireUneSauvegarde();
        }
        eMessage.setText("");
        miseAJour();
    }
    
    public void changerLesBoutons(){                    //Rend uniquement accessible la reserve du joueur courant
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
    
    public void entrerPieceReserve(int idJoueur, Coordonnees c){    //Supprime la piece de la case de coordonnee c, rajoute une piece dans la reserve du joueur i, fait disparaitre le bouton de rentree de piece
        siam.deplacerPiecePlateauVersReserve(caseSelectionneePlateau);
        finTour();
    }
    
    public void miseAJour() {                               //Met a jour l'affichage du plateau,des reserves et du joueur courant
        for(int i=0; i<5; i++ ) {
            for(int j=0; j<5; j++ ) {
                bGrille[i][j].setIcon(new ImageIcon(siam.getImagePlateau(i+1,j+1),theme));
                bArrayJ1[i].setIcon(new ImageIcon(siam.getImageReserve(i, 1),theme));
                bArrayJ2[i].setIcon(new ImageIcon(siam.getImageReserve(i, 2),theme));
                bGrille[i][j].setBorder(bFinTour.getBorder());
                bArrayJ1[i].setBorder(bFinTour.getBorder());
                bArrayJ2[i].setBorder(bFinTour.getBorder());
            }
        }
        
        if(isCaseSelectionneePlateau) {
            String iconName = "images/"+theme+"/selec_"+bGrille[caseSelectionneePlateau.h()-1][caseSelectionneePlateau.v()-1].getIcon();
            if (iconName.equals("images/"+theme+"/selec_f_0_0.png"))
            {
                iconName = "images/"+theme+"/selec_f_0_0.png";
            }
            bGrille[caseSelectionneePlateau.h()-1][caseSelectionneePlateau.v()-1].setIcon(new ImageIcon(iconName));
            System.out.println(iconName);
        }else if(isCaseSelectionneeReserve) {
            if(siam.getJoueurCourant()==0) {
                String iconName = "images/"+theme+"/selec_"+bArrayJ1[caseSelectionneeReserve].getIcon();
                bArrayJ1[caseSelectionneeReserve].setIcon(new ImageIcon(iconName));
            }else {
                String iconName = "images/"+theme+"/selec_"+bArrayJ2[caseSelectionneeReserve].getIcon();
                bArrayJ2[caseSelectionneeReserve].setIcon(new ImageIcon(iconName));
            }
        }
       
        eNomJoueur.setText(siam.getNomJoueurCourant());
        System.out.println(siam.toString()+"\n");
        this.repaint();
        this.validate();
        System.out.println("mise a jour");
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
     
        public void layoutContainer(Container parent) {							//Redimensionne tous les composants de la fenètre
            
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
