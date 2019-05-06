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
    private Sauvegarde sauv;
    private char theme = 'f';
    private int gagnant;
    
    private boolean modeMulti;                  //true = mode multijoueurs ; false = mode solo contre IA
    private int joueurCourant;
    private Piece pieceSelectionnee;
    //private String[] directions = {"Nord" , "Est", "Sud", "Ouest"}; /*inutilis*/
    
    private boolean estFini = false;
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
         
        faireUneSauvegarde(); 
        this.modeMulti = modeMulti;
    }
    
    public void creerPieces(){//instanciation des pièces du plateau et des reserves des joueurs; utilise par le constructeur 
        
        for (int i = 0; i < 3; i++){
            plateau[3][2+i]=new Piece(0);
            plateau[3][2+i].tourner(0);
        }
        for (int i = 0; i < 5; i++){
            piecej1.add(new Piece(1));
            piecej2.add(new Piece(2));
        }    
    }
    
       public boolean deplacerPlateauVersPlateau(Coordonnees cOrigine, Coordonnees cCible){ //Deplace si possible la piece de coordonne c dans la direction d. Renvoie true si deplacee. Appele par fenetrePrincipale
        int h = cOrigine.h();
        int v =cOrigine.v();
        int d = getDirection(cOrigine,cCible);
        boolean reussite = false;
        
        int aDeplacer =0; //Permet de compter combien de pieces devront Ãªtre deplacees
        //On recupere les coord de la premiere case vide dans la direction donnee
        if(mouvementPossible(cOrigine,d, false,0)){
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
                
                if (aDeplacer > 1)
                {
                    isPiecePoussee = true;
                }
                
                Coordonnees tmpCoord;
                /*On se place sur la case x,y
                 * On place la piece dans la case vide adjacente
                 * On supprime la piece de la case x,y 
                 * On se decale d'une case dans la bonne direction
                 * On recommence*/
                for(int i=0 ; i<aDeplacer ; i++){
                    tmpCoord = new Coordonnees(h,v); //case d'rrive ???
                    deplacerPiece(tmpCoord,d);
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
                }
            }
            reussite = true;
        }
        return reussite;
    }
    
    public boolean pousserDepuisReserve(Coordonnees c,int direction){//decale pieces du plateau lorsqu'on rentre une piece venant de la reserve sur une case deja occupee. Renvoie true si effectue. Utilisee par deplaceReserveVersPLateau()
        boolean reussite = false;
        int horizontal = c.h();
        int vertical = c.v();
        int aDeplacer = 0;
        
        if(direction==0) {
            if (horizontal >1 && horizontal < 5 && vertical == 1)
            {
                direction = 2;
            }
            if (horizontal >1 && horizontal < 5 && vertical == 5)
            {
                direction = 4;
            }
            if (vertical > 1 && vertical < 5 && horizontal == 1)
            {
                direction = 3;
            }
            if (vertical > 1 && vertical < 5 && horizontal == 5)
            {
                direction = 1;
            }
        }
        
        if (mouvementPossible(c,direction,true,direction))
        {
            while (plateau[horizontal][vertical] instanceof Piece && estDansLePlateau(plateau[horizontal][vertical])){ // permet de monter jusqu'a la derniere case a deplacer
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
                aDeplacer++;
            }
            
            //redescendre d'une case
            switch (directionOpposee(direction))
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
            
            if(aDeplacer!=0){
                
                if (aDeplacer > 1)
                {
                    isPiecePoussee = true;
                }
                
                Coordonnees tmpCoord;
                /*On se place sur la case x,y
                 * On place la piece dans la case vide adjacente
                 * On supprime la piece de la case x,y 
                 * On se decale d'une case dans la bonne direction
                 * On recommence*/
                for(int i=0 ; i<aDeplacer ; i++){
                    tmpCoord = new Coordonnees(horizontal,vertical); //case d'arrivee ???
                    deplacerPiece(tmpCoord,direction);
                    switch (directionOpposee(direction))
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
                            vertical--;
                            break;
                    }
                }
            }
            reussite = true;
        }
              
        return reussite;
    }
    
    public int directionOpposee(int d){//renvoie direction opposee a celle entree en parametre
        int i = (d+2)%4; 
        if (i == 0) //permet d'eviter q'une piece se transforme en montagne
        {
            i=4;
        }
        return i;
    }
    
    public int appartientAuJoueur(Coordonnees c){//renvoie le joueur a qui appartient la piece selectionnee. Utilisee par FenetrePrincipale
        /*
         * b==0 -> case vide
         * b==1 -> joueur courant
         * b==2 -> joueur adverse
         * */
        
        int b = 0;
        if (plateau[c.h()][c.v()] instanceof Piece)
        {
            if((plateau[c.h()][c.v()].getType()-1)==joueurCourant){
                b = 1;
            } else{
                b = 2;
            }
        }
        
        return b;
    }

    public int getJoueurCourant(){//renvoie identifiant joueur courant
        return joueurCourant;
    }
    public String getNomJoueurCourant() {//renvoie nom joueur courant. Utilisee par FenetrePrincipale
        return this.lesJoueurs[joueurCourant].getNom();
    }
    
    public void changerJoueurCourant(){//change de joueur courant. Utilisee par FenetrePrincipale
        joueurCourant = (1+joueurCourant)%2;
        isPiecePoussee = false;
    }
    
    /**
    public Piece getPieceSelectionnee(){//retourne pièce selectionnée
        return pieceSelectionnee;
    }**/
    
    public boolean mouvementPossible(Coordonnees c, int direction, boolean pieceReserve, int orientation){//renvoie vrai si le mouvement estPossible
        boolean estPossible = false;

        // recuperer les coordonnees de la piece
        int horizontal = c.h();
        int vertical = c.v();
        if(orientation == 0){ 
            orientation = plateau[horizontal][vertical].getOrientation();
        }
        double pieceADeplacer = 0;
        if ((!pieceReserve && caseVide(c,direction)) || (pieceReserve && estVide(c)))
        {
            estPossible = true;
        }else if(orientation != 0 && direction == orientation){
        
            //regarder la direction indiquee
            while (plateau[horizontal][vertical] instanceof Piece && estDansLePlateau(plateau[horizontal][vertical])){ 
                if (plateau[horizontal][vertical].getType() == 0) // si la piece est une montagne
                {
                    pieceADeplacer += -1;
                } else if (plateau[horizontal][vertical].getOrientation() == orientation) //si la piece est dans le meme sens
                {
                    pieceADeplacer += 1.3;
                } else if (plateau[horizontal][vertical].getOrientation() == directionOpposee(orientation)) //si la piece est dans le sens oppose
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
            if(pieceReserve){
                pieceADeplacer += 1.3;
            }
            boolean test = pieceADeplacer > 0;
            if(pieceADeplacer > 0){
                estPossible = true;
            }
        }
        return estPossible;
    }
        
    public boolean estDansLePlateau(Piece p){//renvoie vrai si la pièce est sur le plateau
        boolean a = true;
        for(int i=0;i<plateau.length; i++){
            if(p.equals(plateau[i][0])  || p.equals(plateau[i][6]) || p.equals(plateau[0][i]) || p.equals(plateau[6][i])){
                a=false;
            }
        }
            return a;
    }
    
    public void pivoter(Coordonnees c, int rotation, boolean angle){//permet de faire pivoter la pièce sélectionnée
        if(angle && plateau[c.h()][c.v()] instanceof Piece && !isPiecePoussee){
            Piece p = plateau[c.h()][c.v()];
            int orientation = p.getOrientation();

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
                    orientation=(orientation+rotation)%5; //Pivote de 45Â°
            }

            p.tourner(orientation);
        }
    }
    
    public int getHorizontal(Piece p){//renvoie ligne où se situe la pièce
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
    
    public int getVertical(Piece p){//renvoie colonne où se situe la pièce
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
    
    public int[] getPosition(Piece p){//renvoie position(lignes et colonnes) de la pièce
        int[] a = {getHorizontal(p), getVertical(p)};
        
        return a;
    }
    
    public void setPieceSelectionnee(Piece p){//renvoie la pièce sélectionnée
        pieceSelectionnee = p;
    }
    
    public void deplacerPiece(Coordonnees c, int direction){//permet de déplacer pièce dans la direction voulue  
        /**ATTENTION**/
        /**horizontal = lign, vertical = colonne**/
        /**Attention au signe (voir indice du tableau**/
        
        int horizontal = c.h();
        int vertical = c.v();
        Piece tmp = plateau[horizontal][vertical];
        
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
        plateau[horizontal][vertical] = tmp;
        
    }
    
    public boolean pieceAPivote(Coordonnees c){
        if (c instanceof Coordonnees)
        {
            int h = c.h();
            int v = c.v();
        
            if(plateau[h][v] instanceof Piece){
                return plateau[h][v].aPivote();
            } else { return false; }
        }else{return false; }
    }
    
    public boolean finJeu(){
        /*tester si une montagne se trouve Ã  l'exterieur du plateau
         * 
         *           3
         * 00 01 02 03 04 05 06
           10                16
           20                26
        4  30                36 2 
           40                46
           50                56
           60 61 62 63 64 65 66 
                     1          */
        //
        int bord = 0;
        
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                if(plateau[i][j] instanceof Piece && plateau[i][j].getType()==0){
                    estFini = true;
                    if(i==6) bord =1;
                    if(i==0) bord =3;
                    if(j==6) bord =4;
                    if(j==0) bord =2;
                    chercherVainqueur(i,j,bord);
                    return estFini;
                }
                if (i>0 && i<6 && j==0)
                {
                    j = 5;
                }
            }
        }
        return estFini;
    }
    
    public void chercherVainqueur(int i, int j, int direction){
        /*il s'agit de chercher quelle est la premiere piece
         * en partant de la montagne, oriente dans le bon sens, a  avoir sorti
         * la montagne*/
    	
        switch (direction)
            {
                case 1: //nord
                    i--;
                    break;
                case 2: //est
                    j++;
                    break;
                case 3: //sud
                    i++;
                    break;
                case 4: //ouest
                    j--;
                    break;
            }
 
        while (plateau[i][j].getOrientation() != directionOpposee(direction))
        {
            switch (direction)
            {
                case 1: //nord
                    i--;
                    break;
                case 2: //est
                    j++;
                    break;
                case 3: //sud
                    i++;
                    break;
                case 4: //ouest
                    j--;
                    break;
            }

        }
        
        gagnant = plateau[i][j].getType();
    }   
    
    public void finTour(){
      /*vider les bordures invisibles du plateau
         * 00 01 02 03 04 05 06
           10                16
           20                26
           30                36
           40                46
           50                56
           60 61 62 63 64 65 66 */
           
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                if(plateau[i][j] instanceof Piece){
                    int tmp = plateau[i][j].getType();
                    this.entrerPieceReserve(plateau[i][j].getType());
                    plateau[i][j] = null;
                    
                }
                if (i>0 && i<6 && j==0)
                {
                    j = 5;
                }
                
            }
        }
        
        // rendre toutes les piÃ¨ces Ã  nouveau pivotables
        for (int i = 1; i < 6; i++)
        {
            for (int j = 1; j < 6; j++)
            {
                if (plateau[i][j] instanceof Piece)
                {
                    plateau[i][j].resetAPivote();
                }
            }
        }
    }  
    
    public boolean deplacerReserveVersPlateau(Coordonnees coord, boolean enBordure, int direction ){
        
        int horizontal = coord.h();
        int vertical = coord.v();
        boolean reussite = false;
        Piece p=null;
        
        
        if(enBordure) {
            p = new Piece(joueurCourant + 1);
            if(direction==0) {
                if (horizontal >1 && horizontal < 5 && vertical == 1)
                {
                    p.tourner(2);
                }
                if (horizontal >1 && horizontal < 5 && vertical == 5)
                {
                    p.tourner(4);
                }
                if (vertical > 1 && vertical < 5 && horizontal == 1)
                {
                    p.tourner(3);
                }
                if (vertical > 1 && vertical < 5 && horizontal == 5)
                {
                    p.tourner(1);
                }
            }else {
                p.tourner(direction);
            }
            
            if (plateau[horizontal][vertical]==null){
                sortirPieceReserve(joueurCourant+1); 
                plateau[horizontal][vertical] = p;
                reussite = true;
            }else{
                if (pousserDepuisReserve(coord, direction)){
                    sortirPieceReserve(joueurCourant+1); 
                    plateau[horizontal][vertical] = p;
                    isPiecePoussee = true;
                    reussite = true;
                }
            }
        }

        return reussite;
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
        
        if(dh==0 && dv==-1){//ouest
            direction=4;}
        if(dh==0 && dv==1){//est
            direction=2;}
        if(dh==-1 && dv==0){//nord
            direction=1;}
        if(dh==1 && dv==0){//sud
        direction=3;}
        return direction;
    }
    
    public boolean caseVide(Coordonnees c, int d){
        /*Verifie si la premiere case dans la direction d en
         * partant de la piece p est vide ou non.
         * Si non, des calculs en plus sont necessaires pour determiner si le mouvement est possible*/
         
        boolean b = false;
        int h = c.h();
        int v = c.v();
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
                    v--;
                    break;
            }
        if(!(plateau[h][v] instanceof Piece)){
            b= true;
        }
        return b;
    }
    public boolean estVide(Coordonnees c) {
        if (plateau[c.h()][c.v()]==null){
            return true;
        }else {
            return false;
        }
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
        }else if (i==2){
            piecej2.add(new Piece(2));
        }
    }
    
    public void supprimerPiece(Coordonnees c){
        plateau[c.h()][c.v()] = null;
    }
    
    public String getImagePlateau(int h, int v, char theme){
        String s ="";
        if (plateau[h][v] instanceof Piece ){
            s = plateau[h][v].getImage(theme);
            }
            else {
            s = "vide.png";
        }
        return s;
    }
    
    public String getImageReserve(int i, int joueur, char theme){
        String s ="";
        if(joueur ==1) {
            if (i<piecej1.size()){
                s = piecej1.get(i).getImage(theme);
                }
                else {
                s = "videR1.png";
            }
        }else if (joueur ==2) {
            if (i<piecej2.size()){
                s = piecej2.get(i).getImage(theme);
                }
                else {
                s = "videR2.png";
            }
        }
        return s;
    }
    
    public void faireUneSauvegarde(){
        sauv = new Sauvegarde(this);
    }
    
   public void chargerUneSauvegarde(){
        if (sauv.getJoueurCourant() == joueurCourant)
        {
            //rÃ©cuperer le plateau
            for (int i = 0; i < 7; i++)
            {
                for (int j = 0; j < 7; j++)
                {
                    plateau[i][j] = sauv.getPlateau()[i][j];
                }
                
            }
            //rÃ©cuperer les deux reserves
            piecej1.clear();
            for (int i = 0; i < sauv.getReserve(1).size(); i++)
            {
                piecej1.add(sauv.getReserve(1).get(i));
            }
            
            piecej2.clear();
            for (int i = 0; i < sauv.getReserve(2).size(); i++)
            {
                piecej2.add(sauv.getReserve(2).get(i));
            }
            
            // rendre toutes les piÃ¨ces Ã  nouveau pivotables
            for (int i = 1; i < 6; i++)
            {
                for (int j = 1; j < 6; j++)
                {
                    if (plateau[i][j] instanceof Piece)
                    {
                        plateau[i][j].resetAPivote();
                    }
                }
            } 
        }
    }
    
    public Piece[][] getPlateau(){
        return plateau;
    }
    
    public String[] getResultat(){
        String[] resultat = new String[2];
        resultat[0] = lesJoueurs[gagnant-1].getNom();
        resultat[1] = lesJoueurs[gagnant%2].getNom();
        return resultat; 
    }
    
    public void deplacerPiecePlateauVersReserve(Coordonnees c) { 
        if (plateau[c.h()][c.v()].getType()== joueurCourant+1){
        entrerPieceReserve(joueurCourant+1);
        supprimerPiece(c);
    }
        

   }
}

