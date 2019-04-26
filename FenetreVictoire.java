/*
 * EcouteurVictoire.java
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
import javax.swing.*;
import java.awt.*;

public class FenetreVictoire extends JFrame {
    
    private JLabel texte1;
    private JLabel texte2;
    private JLabel texte3;
    private JButton quitter;
    private JButton revanche;
    
    private String gagnant;
    private String perdant;
    private FenetrePrincipale fen;
    
    public FenetreVictoire(String gagnant, String perdant, FenetrePrincipale fen){
        super("Victoire !");
        
        this.gagnant = gagnant;
        this.perdant = perdant;
        this.fen = fen;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,150);
        setVisible(true);
        this.setLocationRelativeTo(null);
        
        texte1 = new JLabel(gagnant+" a gagne !");
        texte2 = new JLabel("Felicitations !");
        texte3 = new JLabel("Une revanche ?");
        texte1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        texte2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        texte3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        quitter = new JButton("Quitter");
        quitter.addActionListener(new EcouteurVictoire(this,0));
        revanche = new JButton("Revanche");
        revanche.addActionListener(new EcouteurVictoire(this,1));
        
        JPanel pSud = new JPanel(new FlowLayout());
        pSud.add(quitter);
        pSud.add(revanche);
        
        JPanel pCentral = new JPanel(new GridLayout(3,1));
        pCentral.add(texte1);
        pCentral.add(texte2);
        pCentral.add(texte3);
        
        JPanel pPrincipal = new JPanel(new BorderLayout());
        pPrincipal.add(pCentral, BorderLayout.CENTER);
        pPrincipal.add(pSud, BorderLayout.SOUTH);
        
        this.add(pPrincipal);
    }
    
    public void actionRevanche(){
        new FenetrePrincipale(new Jeu(new Joueur(perdant,1), new Joueur(gagnant,2), true));
        fen.dispose();
        this.dispose();
    }
    
    public void actionQuitter(){
        fen.dispose();
        this.dispose();
    }
}



