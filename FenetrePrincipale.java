import javax.swing.*;
import java.awt.*;

public class FenetrePrincipale extends JFrame{
	
	//Déclaration Jeu
	private Jeu siam;
	
	//Déclaration boutons
	private JButton bMenu; 				//Bouton de menu
	private JButton bEntrerPiece; 		//Bouton pour inserer une pièce sur le plateau 
	private JButton bPivoter; 			//Bouton pour faire pivoter une pièce sélectoinnée
	
	//Déclaration Etiquette joueur
	private JLabel eJoueur; 			//Etiquette avec l'inscription joueur
	private JLabel eNomJoueur;			//Etiquette indiquant le nom du joueur dont c'est le tour
	
	//Déclaration du tableau d'etiquettes + tableau de boutons
	private JLabel[][] eGrille;	//Grille d'étiquette représentant les cases du plateau de jeu
	private JButton[][] bGrille;	//Grille de boutons permettant d'inter-agir avec le plateau de jeu plateau de jeu
	
	//Déclaration variable de sélection de pièce
	private boolean pieceSelectionee;
	
	public FenetrePrincipale(Jeu siam){
		super("Jeu du Siam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,800);
		setVisible(true);
		
		this.siam = siam;
		
		//Initialisation boutons
		bMenu = new JButton("Menu");
		bEntrerPiece = new JButton("Entrer piece");
		bPivoter = new JButton("Pivoter");
		
		//Initialisation etiquette joueur
		eJoueur = new JLabel("joueur : ");
		eNomJoueur = new JLabel("");
		
		//Initialisation tableau d'étiquette + tableau de boutons
		eGrille = new JLabel[5][5];
		bGrille = new JButton[5][5];
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				eGrille[i][j] = new JLabel("test étiquette");
				eGrille[i][j].setBackground(Color.BLUE);
				eGrille[i][j].setPreferredSize(new Dimension(20,20));
			}
		}
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				bGrille[i][j] = new JButton("test");
				bGrille[i][j].setBackground(new Color(50,50,50,0));
				//bGrille[i][j].setVisible(true);
				bGrille[i][j].setPreferredSize(new Dimension(20,20));
			}
		}
		
		//Initialisation variable de selection de piece
		pieceSelectionee = false;
		
		//Declaration Panneaux et remplissage
			//Declaration et initialisation du panneau superieur (Menu et joueur) + attribution boutons
				//Déclaration et initialisation du panneau superieur gauche (menu) + attribution bouton
				JPanel pMenu = new JPanel();
				pMenu.add(bMenu);
				
				//Déclaration et initialisation du panneau superieur centré (joueur)
				JPanel pJoueur = new JPanel();
				pJoueur.add(eJoueur);
				pJoueur.add(eNomJoueur);
				
				JPanel pNord = new JPanel(new BorderLayout());
				pNord.setBackground(new Color(100,50,50,100));
				pNord.add(pMenu, BorderLayout.WEST);
				pNord.add(pJoueur, BorderLayout.CENTER);
			
			//Declaration et initialisation du panneau central
				//Declaration et initialisation du panneau central Gauche (pièces joueur 1)
				JPanel pPieceJ1 = new JPanel();
				pPieceJ1.setBackground(new Color(100,50,100,100));
				pPieceJ1.setPreferredSize(new Dimension(200,300));
				
				//Declaration et initialisation du panneau central droit (pièces joueur 2)
				JPanel pPieceJ2 = new JPanel();
				pPieceJ2.setBackground(new Color(100,200,100,100));
				pPieceJ2.setPreferredSize(new Dimension(200,300));
				
				//Declaration et initialisation du panneau central avec plusieurs couches (plateau) + ajout grille d'etiquettes et de boutons
				
				JPanel pGrilleEtiquette = new JPanel(new GridLayout(5,5)); 		//Panneau contenant les etiquettes
				pGrilleEtiquette.setPreferredSize(new Dimension(100,100));
				JPanel pGrilleBouton = new JPanel(new GridLayout(5,5));			//Panneau contenant les boutons
				pGrilleBouton.setPreferredSize(new Dimension(100,100));
				
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
				layeredPane.setPreferredSize(new Dimension(200, 200));
				layeredPane.add(pGrilleEtiquette, JLayeredPane.DEFAULT_LAYER); 	//Ajoute le panneau d'etiquette à la couche principale
				layeredPane.add(pGrilleBouton, 1);

							//Ajoute le panneau de boutons à la couche superieure
				
				//Declaration et initialisation du panneau central (Pièces + plateau) + attribution panneau inferieur
				JPanel pCentral = new JPanel(new BorderLayout());
				pCentral.setPreferredSize(new Dimension(200, 200));
				pCentral.add(layeredPane, BorderLayout.CENTER);
				pCentral.add(pPieceJ1, BorderLayout.WEST);
				pCentral.add(pPieceJ2, BorderLayout.EAST);
				
			
			//Declaration, initialisation du panneau inferieur + attribution boutons
				JPanel pSud = new JPanel();
				pSud.setBackground(new Color(25,43,57,100));
				pSud.add(bEntrerPiece);
				pSud.add(bPivoter);
				
			//Declaration, initialisation du conteneur principale et attribution des panneaux
				JPanel conteneurPrincipal = new JPanel(new BorderLayout());
				conteneurPrincipal.add(pNord, BorderLayout.NORTH);
				conteneurPrincipal.add(pCentral, BorderLayout.CENTER);
				conteneurPrincipal.add(pSud, BorderLayout.SOUTH);
				this.add(conteneurPrincipal);
		
	}
	
	public void deplacementPiece(/*Piece p*/){
		/**if(pieceSelectionee){
			this.siam.deplacerPiece(p);
			pieceSelectionee = false;
		}else{
			this.siam.setPieceSelectionnee(p);
			pieceSelectionee = true;
		}**/
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
	 
		private void setSizes(Container parent) {					//Modifie la taille du layout
			int nComps = parent.getComponentCount(); 				//nombre d'élement dans le conteneur parent 
			Dimension d = null;
	 
			//Reset preferred/minimum width and height.
			preferredWidth = 0;
			preferredHeight = 0;
			minWidth = 0;
			minHeight = 0;
	 
			for (int i = 0; i < nComps; i++) {						//Pour chaque composant i du parent
				Component c = parent.getComponent(i);				//c devient le composant i
				if (c.isVisible()) {								// S'il est visible
					d = c.getPreferredSize();							//la dimension d est égale à la dimension préférée de c
	 
					preferredWidth = Math.max(d.width,preferredWidth);		//la largeur préférée est le max de l'ancienne largeur préférée et de la largeur du composant
					preferredHeight = Math.max(d.height,preferredHeight);	//la hauteur préférée est le max de l'ancienne hauteur préférée et de la largeur du composant
	 
					minWidth = Math.max(c.getMinimumSize().width,minWidth);		//la largeur minimale est égale au max de la précédente largeur minimale et de celle du composant	
					minHeight = Math.max(c.getMinimumSize().height,minHeight);		//la hauteur minimale est égale au max de la précédente hauteur minimale et de celle du composant
				}
			}
		}
	 
	 
		/* Required by LayoutManager. */
		public Dimension preferredLayoutSize(Container parent) { 	//Retourne les dimensions préférée 
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
		public Dimension minimumLayoutSize(Container parent) {	//Retourne les dimensions minimales 
			Dimension dim = new Dimension(0, 0);
	 
			//Always add the container's insets!
			Insets insets = parent.getInsets();
			dim.width = minWidth + insets.left + insets.right;
			dim.height = minHeight + insets.top + insets.bottom;
	 
			sizeUnknown = false;
	 
			return dim;
		}
	 
		public void layoutContainer(Container parent) {
			
			Insets insets = parent.getInsets();									//espace q'un conteneur doit laisser sur chacun de ses bords
			int maxWidth = parent.getWidth()- (insets.left + insets.right);		//largeur max = largeur conteneur - espaces nécessaires sur les bords du conteneur
			int maxHeight = parent.getHeight() - (insets.top + insets.bottom);	//hauteur max = largeur conteneur - espaces nécessaires sur les bords du conteneur
			int nComps = parent.getComponentCount();							//Gets the number of components in this pane
			
			int x = 0;
			int y = 0;
			
			if (sizeUnknown) {												//Met à jour la taille si nécessaire
				setSizes(parent);
			}
	 
			for (int i = 0 ; i < nComps ; i++) {							//Pour chaque composant
				Component c = parent.getComponent(i);						//c est le composant i
				if (c.isVisible()) {										//si c est visible 
					
					c.setBounds(x, y, maxWidth, maxHeight);					// Set the component's size and position.
	 
				}
			}
		}
	 
		public String toString() {
			return getClass().getName();
		}
	}
}
