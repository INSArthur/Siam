import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FenetreRegles extends JFrame{
	
	//panel principal
	private JPanel principal;
    private JPanel bouton ;
    private JPanel texte ;
	private JTextArea textRegles;
    private Path path = Paths.get("Reglesjeu.txt");
    private Path path2 =Paths.get("Reglesjeu2.txt");
    private Charset charset = Charset.forName("ISO-8859-1");
    private String text = "";
    private String text2 ="";
    private JButton suite ;
    private int compteur=0;
    private JButton precedant ;
	
	//constructeur de la fenêtre
	public FenetreRegles() {
		
		super("Regles du Jeu : Siam");
		setSize(1300,800);
		
		// ====== Instanciation des widgets de la fenetre======
			
        try {
            List<String> lines = Files.readAllLines(path, charset);

            for (String line : lines) {
                text = text + line + "\n";
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        
        
        textRegles = new JTextArea(text);
        textRegles.setFont(new Font("Calibri",Font.BOLD + Font.ITALIC, 18));
        textRegles.setForeground(Color.black);
        textRegles.setEditable(false);
        
		
		// ====== Organisation structurelle ======
		
        suite = new JButton ("Suite");
        bouton = new JPanel (new FlowLayout());
        texte = new JPanel (new BorderLayout());
        texte.setBackground(Color.WHITE);
        bouton.add(suite);
        texte.add(textRegles);
        principal = new JPanel(new BorderLayout());
        principal.add(bouton, BorderLayout.SOUTH);
        principal.add(texte);
		
		add(principal);
        
        // ===== liaison bouttons <-> ecouteurs =====
        suite.addActionListener(new EcouteurAide(this));
        
        //===== Rendre la fenetre visible ===== 
		this.setLocationRelativeTo(null); // Centre la fenetre à l'écran
        this.setResizable(true); // Permet le redimensionnement
		this.setVisible(true);
	}
    
    public void changer(){
        if(compteur==0){
            texte.remove(textRegles);
            try {
                List<String> lines = Files.readAllLines(path2, charset);

                for (String line : lines) {
                    text2 = text2 + line + "\n";
                }
            } catch (IOException f) {
                System.out.println(f);
            }
            
            textRegles = new JTextArea(text2);
            textRegles.setFont(new Font("Calibri",Font.BOLD + Font.ITALIC,18 ));
            textRegles.setForeground(Color.black);
            textRegles.setEditable(false);
            texte.add(textRegles);
            principal.remove(bouton);
            compteur ++;
        }
        
        this.validate();
        this.repaint();
    }
}

