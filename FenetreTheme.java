
import javax.swing.*;
import java.awt.*;

public class FenetreTheme extends JFrame {
    
    private JLabel texte;
    private JButton valider;
    private char theme;
    
    private FenetrePrincipale fen;
    
    public FenetreTheme(FenetrePrincipale fen){
        super("Choix du thème");
        this.fen = fen;
        theme = 'f';
        texte = new JLabel("Veuillez choisir un thème :");
        

        JPanel central = new JPanel(new FlowLayout());
        JPanel inf = new JPanel(new FlowLayout());
        JPanel principal = new JPanel(new BorderLayout());
        
        //Création d'un menu déroulant        
        JComboBox<String> menu = new JComboBox<String>();
        menu.addActionListener(new EcouteurComboBox(this));
        menu.addItem("Flèches");
        menu.addItem("Traditionnel");
        central.add(texte);
        central.add(menu);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,120);
        setVisible(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        valider = new JButton("Valider");
        valider.addActionListener(new EcouteurTheme(this));
        inf.add(valider);
        
        principal.add(central, BorderLayout.CENTER);
        principal.add(inf, BorderLayout.SOUTH);
        this.add(principal);
        
    }
    
    public void setTheme(char c){
        theme = c;
    }
    
    public void changerTheme(){
        fen.changerTheme(theme);
        this.dispose();
    }
    
}



