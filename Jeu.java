/*
 * Jeu.java
 * 
 * Copyright 2019 Batel Arthur <abatel@pc107-043-17>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */
import java.util.*;

public class Jeu {
    
    private Joueur[] lesJoueurs;
    private Piece[][] plateau;
    
    private boolean modeMulti; 								//true = mode multijoueurs ; false = mode solo contre IA
    private int joueurCourant;
    private Piece pieceSelectionnee;
    private String[] directions = {"Nord" , "Est", "Sud", "Ouest"}; /**inutilis�**/
    
    public boolean estFini;
    private boolean isPieceDeplacee;				//Indique si une piece a été déplacé par le joueur durant le tour (utilisé pour vérifier si on peut pivoter la sélection)
    private boolean isPiecePoussee;					//Indique si une ou plusieurs pièces ont été déplacées pendant le tour (utilisé pour vérifier si on peut pivoter la sélection)
    
    public ArrayList <Piece> piecej1 ;
    public ArrayList <Piece> piecej2 ;
    
    public Jeu(Joueur j1, Joueur j2, boolean modeMulti){
        
        /* Création du plateau de jeu
         * Initialisation des joueurs
         * Créations des pièces*/
         
         
        lesJoueurs = new Joueur[2];
        lesJoueurs[0] = j1;
        lesJoueurs[1] = j2;
        joueurCourant = 0;
        plateau = new Piece[7][7]; // -> changement 7 au lieu de 6 
        pieceSelectionnee = null;   
        creerPieces();
        estFini = false;
        isPieceDeplacee = false;
        isPiecePoussee = false;
        piecej1= new ArrayList <Piece>(5);
        piecej2 = new ArrayList <Piece> (5); 
        this.modeMulti = modeMulti;
    }
    
    public void creerPieces(){
        
        /*Bout de code à reprendre
         * Création des pièces :
         * les montagnes (Type=0) sont placées au centre
         * les pièces joueurs (Type=1 -> j1 ; Type=2 -> j2) sont placées en bord de plateau où elles ne sont pas visibles*/
         
        for (int i = 0; i < 13; i++)
        {
            if (i<3)
            {
                plateau[3][2+i]=new Piece(i);
                plateau[3][2+i].tourner(0);
                plateau[3][2+i].setType(0);
            } else if (i<7)
            {
                plateau[0][i] = new Piece(i);
                plateau[0][i].setType(1);
            } else {
                plateau[6][12-i] = new Piece(i);
                plateau[6][12-i].setType(2);
            }
        }
    }

    
    public void tour(int direction){
        /*Fonctionnement d'un tour (à reprendre ?) :
         * Cliquer sur une pièce modifie pieceSelectionnée
         * On choisit une direction
         * Si la case est vide, on deplace la pièce, et on a le droit de pivoter la pièce
         * Si non, on déplace (après vérif) toutes les pièces dans la bonne direction et on ne peut pas tourner la pièce
         * On regarde si une montagne est sortie
         * Victoire (à implenter) ou joueur suivant*/
         
        Piece piece = getPieceSelectionnee();
        Coordonnees coord = new Coordonnees(getHorizontal(piece), getVertical(piece));
        
        if(!appartientAuJoueur(piece)){
        System.out.println("Vous ne pouvez pas déplacer cette pièce!");
        }
        else {
            if(caseVide(piece, direction)){
                deplacerPiece(piece, direction);
                System.out.println("techniquement, tu peux pivoter ta pièce, mais c'est pas encore implémenter");
            }
            else{
                if(!mouvementPossible(piece, direction)){
                    System.out.println("Vous ne pouvez pas faire ca !");
                }
                else {
                    deplacerToutesPieces(coord,direction);
                }
            }
        }
        
        //Vérification qu'une montagne hors dans le plateau. La façon de manipuler les pièces doit être discutée
        if (!estDansLePlateau(new Piece(1)) || !estDansLePlateau(new Piece(0)) || !estDansLePlateau(new Piece(2)))
        {
            estFini = true;
            System.out.println("Victoire du joueur "+joueurCourant+" !");
        }
        
        changerJoueurCourant();
    }
    
    
       public void deplacerToutesPieces(Coordonnees c, int d){
        int h = c.h();
        int v =c.v();
        int aDeplacer =0; //Permet de compter combien de pièces devront être déplacées
        
        //On recupère les coord de la première case vide dans la direction donnée
        if(mouvementPossible(plateau[h][v],d)){
            while (plateau[h][v] instanceof Piece && estDansLePlateau(plateau[h][v])){ // permet de monter jusqu'à la dernière case à déplacer
                switch (d)
                {
                    case 1: //nord
                        v--;
                        break;
                    case 2: //est
                        h++;
                        break;
                    case 3: //sud
                        v++;
                        break;
                    case 4: //ouest
                        h --;
                        break;
                }
                aDeplacer++;
            }
            
             isPieceDeplacee = true; 
            //redescendre d'une case
            switch (directionOpposee(d))
            {
                case 1: //nord
                    v--;
                    break;
                case 2: //est
                    h++;
                    break;
                case 3: //sud
                    v++;
                    break;
                case 4: //ouest
                    h --;
                    break;
            }
            
             if(aDeplacer!=0){

				isPiecePoussee = true;

				//redescendre d'une case
				switch (directionOpposee(d))
				{
					case 1: //nord
						v--;
						break;
					case 2: //est
						h++;
						break;
					case 3: //sud
						v++;
						break;
					case 4: //ouest
						h --;
						break;
				}

				Piece tmpPiece;
				/*On se place sur la case x,y
				 * On place la pièce dans la case vide adjacente
				 * On supprime la pièce de la case x,y 
				 * On se décale d'une case dans la bonne direction
				 * On recommence*/
				for(int i=0 ; i<aDeplacer ; i++){
					tmpPiece = plateau[h][v];
					deplacerPiece(tmpPiece,d);
					switch (directionOpposee(d))
					{
						case 1: //nord
							v--;
							break;
						case 2: //est
							h++;
							break;
						case 3: //sud
							v++;
							break;
						case 4: //ouest
							h --;
							break;
					}
				}
			}
        }
    }
    
    public int directionOpposee(int d){
        int i = (d+2)%4; 
        if (i == 0) //permet d'éviter q'une pièce se transforme en montagne
        {
            i=4;
        }
        return i;
    }
    
    public boolean appartientAuJoueur(Piece p){
        return ((p.getType()-1)==joueurCourant);
    }

    public int getJoueurCourant(){
        return joueurCourant;
    }
    public String getNomJoueurCourant() {
    	System.out.println("test");
    	return this.lesJoueurs[joueurCourant].getNom();
    }
    
    public void changerJoueurCourant(){
        joueurCourant = (1+joueurCourant)%2;
    }
    
    public Piece getPieceSelectionnee(){
        return pieceSelectionnee;
    }
    
    public boolean mouvementPossible(Piece p, int direction){
        boolean estPossible = false;
        // récupérer les coordonnées de la pièce
        int horizontal = getPosition(p)[0];
        int vertical = getPosition(p)[1];
        double pieceADeplacer = 0;
        //int a=0; Inutilis�
        //regarder la direction indiquée
        while (plateau[horizontal][vertical] instanceof Piece && estDansLePlateau(plateau[horizontal][vertical])){ 
            
            
            if (plateau[horizontal][vertical].getType() == 0) // si la pièce est une montagne
            {
                pieceADeplacer += -1;
            } else if (plateau[horizontal][vertical].getOrientation() == p.getOrientation()) //si la pièce est dans le meme sens
            {
                pieceADeplacer += 1.3;
            } else if (plateau[horizontal][vertical].getOrientation() == directionOpposee(p.getOrientation())) //si la pièce est dans le sens opposé
            {
                pieceADeplacer += -1.3;
            }else{
                pieceADeplacer += -1;
            }
            
            switch (direction)
            {
                case 1: //nord
                    vertical--;
                    break;
                case 2: //est
                    horizontal++;
                    break;
                case 3: //sud
                    vertical++;
                    break;
                case 4: //ouest
                    horizontal --;
                    break;
            }
            
        }
        if(pieceADeplacer > 0){
            estPossible = true;
        }
        return estPossible;
    }
        
    public boolean estDansLePlateau(Piece p){
        boolean a = true;
        for(int i=0;i<plateau.length; i++){
            if(p.equals(plateau[i][0])  || p.equals(plateau[i][6]) || p.equals(plateau[0][i]) || p.equals(plateau[6][i])){
                a=false;
            }
        }
            return a;
    }
    
    public void pivoter(Coordonnees c, int rotation){
		if(plateau[c.h()][c.v()]!=null && isPiecePoussee==false && isPieceDeplacee==true){
			Piece p = plateau[c.h()][c.v()];
			int orientation = p.getOrientation();

			switch (orientation)
			{
				case 4:									//Permet d'empecher une pièce d'avoir l'orientation 0 (montagne) lorsque orientation = (4 + 1) % 5
					if(rotation==1){
						orientation = 1;
					}else{
						orientation = 3;
					}
					break;
				case 1:									//Permet d'empecher une pièce d'avoir l'orientation 0 (montagne) lorsque orientation = 1 - 1
					if(rotation==-1){
						orientation = 4;
					}else{
						orientation = 2;
					}
					break;
				default:
					orientation=(orientation+rotation)%5; //Pivote de 45°
			}

			p.tourner(orientation);
		}
	}
    
    public int getHorizontal(Piece p){
        int horizontal=0;
        for(int i=0; i<plateau.length; i++){
            for(int j=0; j<plateau.length;j++){
                if(p instanceof Piece && p.equals(plateau[i][j])){
                    horizontal=i;
                }
            }
        }
        return horizontal;
    }   
    
    public int getVertical(Piece p){
        int vertical=0;
        for(int i=0; i<plateau.length; i++){
            for(int j=0; j<plateau.length;j++){
                if(p instanceof Piece && p.equals(plateau[i][j])){
                    vertical=j;
                }
            }
        }
        return vertical;
    }   
    
    public int[] getPosition(Piece p){
        int[] a = {getHorizontal(p), getVertical(p)};
        
        return a;
    }
    
    public void setPieceSelectionnee(Piece p){
        pieceSelectionnee = p;
    }
    
    public void deplacerPiece(Piece p, int direction){
        int horizontal = getPosition(p)[0];
        int vertical = getPosition(p)[1];
        plateau[horizontal][vertical] = null;
        switch (direction)
        {
            case 1: //nord
                vertical--;
                break;
            case 2: //est
                horizontal++;
                break;
            case 3: //sud
                vertical++;
                break;
            case 4: //ouest
                horizontal --;
                break;
        }
        plateau[horizontal][vertical] = p;
        
    }
    
    public boolean finJeu(){
        return estFini;
    }
    
    public void placerPiece(int id, int[] coord){
        
    }
    
    public int getDirection(Coordonnees cCible, Coordonnees cOrigine){
        /*Transforme deux jeux de coordonées (la position de p et {h2,v2}, position d'arrivée
        /*Transforme deux jeux de coordonées (la position de p et {h2,v2}
         * en une direction*/
         
        int direction=0;
        int dh=cOrigine.h()- cCible.h();
        int dv=cOrigine.v()-cCible.v();
        
        if(dh==0 && dv==1){//nord
            direction=1;}
        if(dh==0 && dv==-1){//sud
            direction=3;}
        if(dh==-1 && dv==0){//est
            direction=2;}
        if(dh==1 && dv==0){//ouest
        direction=4;}
        return direction;
    }
    
    public boolean caseVide(Piece p, int d){
        /*Vérifie si la première case dans la direction d en
         * partant de la piece p est vide ou non.
         * Si non, des calculs en plus sont nécessaires pour déterminer si le mouvement est possible*/
         
        boolean b = false;
        int h = getHorizontal(p);
        int v = getVertical(p);
         switch (d)
            {
                case 1: //nord
                    v--;
                    break;
                case 2: //est
                    h++;
                    break;
                case 3: //sud
                    v++;
                    break;
                case 4: //ouest
                    h --;
                    break;
            }
        if(!(plateau[h][v] instanceof Piece)){
            b= true;
        }
        return b;
    }
    
    public int[] switchDirection(int direction, int horizontal, int vertical){
        switch (direction)
        {
            case 1: //nord
                vertical--;
                break;
            case 2: //est
                horizontal++;
                break;
            case 3: //sud
                vertical++;
                break;
            case 4: //ouest
                horizontal --;
                break;
        }
        int[] tmp = { horizontal , vertical };
        return tmp;
    
    }
    
    public boolean pivoter(int d, Coordonnees cOrigine, Coordonnees cCible){
		if(plateau[cCible.h()][cCible.v()]==null){
			plateau[cOrigine.h()][cOrigine.v()].tourner(d);
			return true;
		}else{
			return false;
		}
	}


    
    public String toString(){
        String message ="|";
        for (int i = 1; i <6 ; i++)
        {
            for (int k = 1; k < 6; k++)
            {
                if (plateau[i][k] instanceof Piece)
                {
                    message+= plateau[i][k].getType();
                    message+= plateau[i][k].getOrientation();
                } else {
                    message += "  ";
                }
                message += "|";
            }
            message += "\n|";
        }
        message = message.substring(0, message.length()-2);
        return message;
    }
    
    public void sortirPieceReserve (int i){ //sortir pièce de l'arrayList du joueur i
        if(i==1){
            piecej1.remove(0);
        }else{
            piecej2.remove(0);
        }
    }
    
    public void entreePieceReserve (Piece p, int i){//entrer pièce dans l'arraylist du joueur i
        
        if(i==1){
            piecej1.add(p);
        }else{
            piecej2.add(p);
        }
    }
    
    public String getImagePlateau(int h, int v){
		String s ="";
		if ( plateau[h][v] != null){
			s = plateau[h][v].getImage();
			}
			else {
			s = "vide";
		}
		return s;
	}
    
    public String getImageReserve(int i, int joueur){
		String s ="";
		if(joueur ==1) {
			if ( piecej1.get(i) != null){
				s = piecej1.get(i).getImage();
				}
				else {
				s = "vide";
			}
		}else if (joueur ==2) {
			if ( piecej2.get(i) != null){
				s = piecej2.get(i).getImage();
				}
				else {
				s = "vide";
			}
		}
		return s;
	}
   
}

