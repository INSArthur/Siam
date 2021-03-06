/*
 * EcouteurComboBox.java
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
 
 import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class EcouteurComboBox implements ActionListener {
    
    private FenetreTheme fen;
    
    public EcouteurComboBox(FenetreTheme f){ fen = f; }
    
    public void actionPerformed(ActionEvent e){
        JComboBox combo = (JComboBox)e.getSource();
        String s = (String)combo.getSelectedItem();
        s = s.toLowerCase();
        char choix = s.charAt(0);
        fen.setTheme(choix);
    }
 
}

