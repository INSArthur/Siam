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


public class Piece{
    private int id;
    private boolean estDedans;
    private int orientation;
    private int type;
  
    
    /* Orientation prend 5 valeurs : 
     * 0 -> Montagne
     * 1 -> Nord
     * 2 -> Est
     * 3 -> Sud
     * 4 -> Ouest
     * 
     * type prend 3 valeurs (par défaut, orientation = 1) :
     * 0 -> Montagne
     * 1 -> Joueur 1
     * 2 -> Joueur 2
     */
     
     
    public Piece(int id){
        this.id = id;
        estDedans = false;
        orientation = 1;
    }
    
    public int getType(){
        return type;
    }
    
    public void setType(int type){
        this.type = type;
    }
    
    public int getId(){
        return id;
    }
    
    public boolean equals(Piece p){
        return p instanceof Piece && p.getId ()== this.id;
    }
    
    public String getImage(){
        String s= "f_"+getType()+"_"+getOrientation()+".png";
        return s;
    }
           
    
    public void tourner(int orientation){
        this.orientation = orientation;
    }
    
    public int getOrientation(){
        return orientation;
    }
}

