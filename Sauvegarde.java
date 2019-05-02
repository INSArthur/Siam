/*
 * Sauvegarde.java
 * 
 * Copyright 2019 Fanny Buonomo <phanie@elendil>
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

public class Sauvegarde {
    
    private Piece[][] plateau;
    private LinkedList <Piece> reserve1;
    private LinkedList <Piece> reserve2;
    private int joueurCourant;
    
    public Sauvegarde(Jeu jeu){
        plateau = new Piece[7][7];
        reserve1 = new LinkedList <Piece>();
        reserve2 = new LinkedList <Piece>();
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 7; j++)
            {

                this.plateau[i][j] = jeu.getPlateau()[i][j];
                
            }
        }
        for (int i = 0; i < jeu.piecej1.size(); i++)
        {
            reserve1.add(jeu.piecej1.get(i));
        }
        for (int i = 0; i < jeu.piecej2.size(); i++)
        {
            reserve2.add(jeu.piecej2.get(i));
        }
        this.joueurCourant = jeu.getJoueurCourant();
        System.out.println("PLateau sauvegarder :");
        System.out.println(jeu.toString()+"\n\n\n");
    }
    
    public int getJoueurCourant(){
        return joueurCourant;
    }
    
    public Piece[][] getPlateau(){
        return plateau;
    }
    
    public LinkedList <Piece> getReserve(int i){
        if (i ==1)
        {
            return reserve1;
        }else
        {
            return reserve2;
        }
    }
        
}

