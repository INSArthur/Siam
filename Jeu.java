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
    
    private boolean modeMulti;                  //true = mode multijoueurs ; false = mode solo contre IA
    private int joueurCourant;
    private Piece pieceSelectionnee;
    //private String[] directions = {"Nord" , "Est", "Sud", "Ouest"}; /*inutilis*/
    
    private boolean estFini;
    private boolean isPiecePoussee;                 //Indique si une ou plusieurs pieces ont ete deplacees pendant le tour (utilise pour verifier si on peut pivoter la selection)
    
    public LinkedList <Piece> piecej1 ;
    public LinkedList <Piece> piecej2 ;
    
    public Jeu(Joueur j1, Joueur j2, boolean modeMulti){
        
        /* Creation du plateau de jeu
         * Initialisation des joueurs
         * Creations des pieces*/
         
         
        lesJoueurs = new Joueur[2];
        lesJoueurs[0] = j1;
        lesJoueurs[1] = j2;
        joueurCourant = 0;
        plateau = new Piece[7][7]; // -> changement 7 au lieu de 6 
        pieceSelectionnee = null;   
        estFini = false;
        isPiecePoussee = false;
        piecej1= new LinkedList <Piece>();
        piecej2 = new LinkedList <Piece>();
        creerPieces();
         
        
        this.modeMulti = modeMulti;
    }
    
    public void creerPieces(){
        
        for (int i = 0; i < 3; i++){
            plateau[3][2+i]=new Piece(0);
            plateau[3][2+i].tourner(0);
        }
        for (int i = 0; i < 5; i++){
            piecej1.add(new Piece(1));
            piecej2.add(new Piece(2));
        }    
    }

    
    /**public void tour(int direction){
        /*Fonctionnement d'un tour (a reprendre ?) :
         * Cliquer sur une piece modifie pieceSelectionnee
         * On choisit une direction
         * Si la case est vide, on deplace la piece, et on a le droit de pivoter la piece
         * Si non, on deplace (apres verif) toutes les pieces dans la bonne direction et on ne peut pas tourner la piece
         * On regarde si une montagne est sortie
         * Victoire (a implenter) ou joueur suivant*/
    /**
        Piece piece = getPieceSelectionnee();
        Coordonnees coord = new Coordonnees(getHorizontal(piece), getVertical(piece));
        
        if(!appartientAuJoueur(piece)){
        System.out.println("Vous ne pouvez pas deplacer cette piece!");
        }
        else {
            if(caseVide(piece, direction)){
                deplacerPiece(piece, direction);
                System.out.println("techniquement, tu peux pivoter ta piece, mais c'est pas encore implementer");
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
        
        //Verification qu'une montagne hors dans le plateau. La facon de manipuler les pieces doit etre discutee
        if (!estDansLePlateau(new Piece(1)) || !estDansLePlateau(new Piece(0)) || !estDansLePlateau(new Piece(2)))
        {
            estFini = true;
            System.out.println("Victoire du joueur "+joueurCourant+" !");
        }
        
        changerJoueurCourant();
    }**/
    
    
       public boolean deplacerPlateauVersPlateau(Coordonnees cOrigine, Coordonnees cCible){ //Deplace si possible la piece de coordonne c dans la direction d. Renvoie true si deplacee
        int h = cOrigine.h();
        int v =cOrigine.v();
        int d = getDirection(cOrigine,cCible);
        System.out.println(d);
        int aDeplacer =0; //Permet de compter combien de pieces devront être deplacees
        System.out.println(mouvementPossible(plateau[h][v],d));
        //On recupere les coord de la premiere case vide dans la direction donnee
        if(mouvementPossible(plateau[h][v],d)){
            while (plateau[h][v] instanceof Piece && estDansLePlateau(plateau[h][v])){ // permet de monter jusqu'a la derniere case a deplacer
                switch (d)
                {
                    case 1: //nord
                        h--;
                        break;
                    case 2: //est
                        v++;
                        break;
                    case 3: //sud
                        h++;
                        break;
                    case 4: //ouest
                        v --;
                        break;
                }
                aDeplacer++;
            }
            
            //redescendre d'une case
            switch (directionOpposee(d))
            {
                case 1: //nord
                    h--;
                    break;
                case 2: //est
                    v++;
                    break;
                case 3: //sud
                    h++;
                    break;
                case 4: //ouest
                    v --;
                    break;
            }
            
             if(aDeplacer!=0){

                isPiecePoussee = true;

                //redescendre d'une case
                switch (directionOpposee(d))
                {
                    case 1: //nord
                        h--;
                        break;
                    case 2: //est
                        v++;
                        break;
                    case 3: //sud
                        h++;
                        break;
                    case 4: //ouest
                        v--;
                        break;
                }

                Piece tmpPiece;
                /*On se place sur la case x,y
                 * On place la piece dans la case vide adjacente
                 * On supprime la piece de la case x,y 
                 * On se decale d'une case dans la bonne direction
                 * On recommence*/
                for(int i=0 ; i<aDeplacer ; i++){
                    tmpPiece = plateau[h][v];
                    deplacerPiece(tmpPiece,d);
                    switch (directionOpposee(d))
                    {
                        case 1: //nord
                            h--;
                            break;
                        case 2: //est
                            v++;
                            break;
                        case 3: //sud
                            h++;
                            break;
                        case 4: //ouest
                            v--;
                            break;
                    }
                }
            }
             return true;
        }
        return false;
    }
    
    public int directionOpposee(int d){
        int i = (d+2)%4; 
        if (i == 0) //permet d'eviter q'une piece se transforme en montagne
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
        
        // recuperer les coordonnees de la piece
        int horizontal = getPosition(p)[0];
        int vertical = getPosition(p)[1];
        double pieceADeplacer = 0;

        //regarder la direction indiquee
        while (plateau[horizontal][vertical] instanceof Piece && estDansLePlateau(plateau[horizontal][vertical])){ 
            
            
            if (plateau[horizontal][vertical].getType() == 0) // si la piece est une montagne
            {
                pieceADeplacer += -1;
            } else if (plateau[horizontal][vertical].getOrientation() == p.getOrientation()) //si la piece est dans le meme sens
            {
                pieceADeplacer += 1.3;
            } else if (plateau[horizontal][vertical].getOrientation() == directionOpposee(p.getOrientation())) //si la piece est dans le sens oppose
            {
                pieceADeplacer += -1.3;
            }else{
                pieceADeplacer += -1;
            }
            
            switch (direction)
            {
                case 1: //nord
                    horizontal--;
                    break;
                case 2: //est
                    vertical++;
                    break;
                case 3: //sud
                    horizontal++;
                    break;
                case 4: //ouest
                    vertical --;
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
        System.out.println("test pivoter isPiecePousse="+isPiecePoussee);
        boolean test = plateau[c.h()][c.v()] instanceof Piece;
        System.out.println("test pivoter isntance of ="+test);
        if(plateau[c.h()][c.v()] instanceof Piece && !isPiecePoussee ){
            Piece p = plateau[c.h()][c.v()];
            int orientation = p.getOrientation();
            System.out.println("test orintation oreientation = "+orientation);

            switch (orientation)
            {
                case 4:                                 //Permet d'empecher une piece d'avoir l'orientation 0 (montagne) lorsque orientation = (4 + 1) % 5
                    if(rotation==1){
                        orientation = 1;
                    }else{
                        orientation = 3;
                    }
                    break;
                case 1:                                 //Permet d'empecher une piece d'avoir l'orientation 0 (montagne) lorsque orientation = 1 - 1
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
    	/**ATTENTION**/
    	/**horizontal = lign, vertical = colonne**/
    	/**Attention au signe (voir indice du tableau**/
    	
        int horizontal = getPosition(p)[0];
        int vertical = getPosition(p)[1];
        
        plateau[horizontal][vertical] = null;
        switch (direction)
        {
            case 1: //nord
                horizontal--;
                break;
            case 2: //est
                vertical++;
                break;
            case 3: //sud
                horizontal++;
                break;
            case 4: //ouest
                vertical --;
                break;
        }
        plateau[horizontal][vertical] = p;
        
    }
    
    public boolean finJeu(){
        return estFini;
    }
    
    public boolean deplacerReserveVersPlateau(Coordonnees coord, boolean enBordure){
    	
    	int horizontal = coord.h();
	    int vertical = coord.v();
	     
    	if(enBordure && plateau[horizontal][vertical]==null) {
    		sortirPieceReserve(joueurCourant+1); 
	        plateau[horizontal][vertical] = new Piece(joueurCourant+1);
	        return true;
        }
    	return false;
    }
    
    public int getDirection(Coordonnees cCible, Coordonnees cOrigine){
        /*Transforme deux jeux de coordonees (la position de p et {h2,v2}, position d'arrivee
        /*Transforme deux jeux de coordonees (la position de p et {h2,v2}
         * en une direction*/
        /**ATTENTION**/
    	/**h = ligne et v=colonne car pour chercher une piece on ecrit plateau[h][v] **/
    	/**De plus dh est fonde sur l indice du tableau donc attention au signe**/
    	
        int direction=0;
        int dh=cOrigine.h()- cCible.h();
        int dv=cOrigine.v()-cCible.v();
        
        if(dh==0 && dv==1){//ouest
            direction=4;}
        if(dh==0 && dv==-1){//est
            direction=2;}
        if(dh==-1 && dv==0){//nord
            direction=1;}
        if(dh==1 && dv==0){//sud
        direction=3;}
        return direction;
    }
    
    public boolean caseVide(Piece p, int d){
        /*Verifie si la premiere case dans la direction d en
         * partant de la piece p est vide ou non.
         * Si non, des calculs en plus sont necessaires pour determiner si le mouvement est possible*/
         
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
    
    public Piece sortirPieceReserve (int i){ //sortir piece de l'arrayList du joueur i
        Piece p;
        if(i==1){ 
            p = piecej1.removeLast();
        }else{
            p = piecej2.removeLast();
        }
        return p;
    }
    
    public void entrerPieceReserve (int i){ //entrer piece dans l'arraylist du joueur i
        if(i==1){
            piecej1.add(new Piece(1));
        }else{
            piecej2.add(new Piece(2));
        }
    }
    
    public void supprimerPiece(Coordonnees c){
        plateau[c.h()][c.v()] = null;
    }
    
    public String getImagePlateau(int h, int v){
        String s ="";
        if (plateau[h][v] instanceof Piece ){
            s = plateau[h][v].getImage();
            }
            else {
            s = "vide.png";
        }
        return s;
    }
    
    public String getImageReserve(int i, int joueur){
        String s ="";
        if(joueur ==1) {
            if (i<piecej1.size()){
                s = piecej1.get(i).getImage();
                }
                else {
                s = "vide.png";
            }
        }else if (joueur ==2) {
            if (i<piecej2.size()){
                s = piecej2.get(i).getImage();
                }
                else {
                s = "vide.png";
            }
        }
        return s;
    }
    
    public void deplacerPiecePlateauVersReserve(Coordonnees c) {
    	entrerPieceReserve(joueurCourant+1);
        supprimerPiece(c);
    }
   
}

